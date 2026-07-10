package com.redwood.scheduler.custom.snowflake;

import com.redwood.scheduler.api.exception.CredentialEncodingException;
import com.redwood.scheduler.api.exception.CredentialNotExternallyAvailableException;
import com.redwood.scheduler.api.model.Credential;
import com.redwood.scheduler.api.model.SchedulerSession;
import com.redwood.scheduler.custom.snowflake.security.SnowflakeJwtGenerator;
import com.redwood.scheduler.infrastructure.logging.api.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Snowflake {
    private final SchedulerSession session;
    private final Logger log;
    private final SnowflakeJwtGenerator jwtGenerator;

    public Snowflake(SchedulerSession session, Logger log) {
        this.session = session;
        this.log = log;
        jwtGenerator = new SnowflakeJwtGenerator();
    }


    public SnowflakeResponse execute(SnowflakeConfig config, String statement) throws Exception {

        return execute(config, statement, Collections.emptyMap());

    }

    public SnowflakeResponse execute(SnowflakeConfig config, String sql, Map<Integer, List<String>> bindings) throws Exception {

        Credential snowflakeCredential = findCredentialByUser(config.getSnowflakeCredential());

        config.setEndpoint(snowflakeCredential.getEndpoint());
        String user = snowflakeCredential.getRealUser();
        String password = session.unprotectPassword(snowflakeCredential.getProtectedPassword());
        String privateKey = loadPrivateKey(config.getPrivateKeyCredential());

        String jwt = jwtGenerator.generateToken(config.getEndpoint(), user, privateKey, password, log);

        return new SnowflakeClient(config, jwt, log).execute(sql,bindings);

    }


    private String loadPrivateKey(String credentialName) throws CredentialEncodingException, CredentialNotExternallyAvailableException {

        Credential credential = findPrivateKeyCredential(credentialName);
        return session.unprotectPassword(credential.getProtectedPassword());
    }

    Credential findCredentialByUser(String user) {
        Iterator<Credential> it = session.executeObjectQuery(Credential.TYPE, "select * from Credential where RealUser = ?", user);
        return it.hasNext() ? it.next() : null;
    }

    Credential findPrivateKeyCredential(String user) {
        Iterator<Credential> it = session.executeObjectQuery(Credential.TYPE, "select * from Credential where Endpoint = ?", user);
        return it.hasNext() ? it.next() : null;
    }


}

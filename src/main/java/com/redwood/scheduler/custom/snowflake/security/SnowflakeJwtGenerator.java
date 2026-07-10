package com.redwood.scheduler.custom.snowflake.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.redwood.scheduler.infrastructure.logging.api.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;

import java.io.StringReader;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.Security;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class SnowflakeJwtGenerator {

    private static final long TOKEN_LIFETIME_MINUTES = 59;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public String generateToken(
            String endpoint,
            String user,
            String pemContent,
            String password,
            Logger log)
            throws Exception {


        String account = extractAccount(endpoint);

        account = prepareAccount(account);

        String qualifiedUser =
                account.toUpperCase()
                        + "."
                        + user.toUpperCase();

        KeyPair keyPair = loadKeyPair(pemContent, password,log);

        String fingerprint =
                calculateFingerprint(
                        keyPair.getPublic());

        Instant now = Instant.now();

        JWTClaimsSet claims =
                new JWTClaimsSet.Builder()
                        .issuer(qualifiedUser + "." + fingerprint)
                        .subject(qualifiedUser)
                        .issueTime(Date.from(now))
                        .expirationTime(Date.from(now.plusSeconds(TOKEN_LIFETIME_MINUTES * 60)))
                        .build();

        SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claims);

        jwt.sign(new RSASSASigner(keyPair.getPrivate()));

        return jwt.serialize();
    }

    private String prepareAccount(
            String account) {

        if (!account.contains(".global")) {

            int idx = account.indexOf('.');

            if (idx > 0) {
                account = account.substring(
                        0,
                        idx);
            }

        } else {

            int idx = account.indexOf('-');

            if (idx > 0) {
                account = account.substring(
                        0,
                        idx);
            }
        }

        return account.toUpperCase();
    }

    private KeyPair loadKeyPair(
            String pemContent,
            String password, Logger log)
            throws Exception {

        try (PEMParser parser =
                     new PEMParser(
                             new StringReader(
                                     pemContent))) {

            Object object = parser.readObject();


            log.info(object == null ? "NULL" : object.getClass().getName());


            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

            if (object instanceof PEMEncryptedKeyPair encrypted) {

                PEMKeyPair decrypted = encrypted.decryptKeyPair(new JcePEMDecryptorProviderBuilder().build(password.toCharArray()));

                return converter.getKeyPair(decrypted);
            }

            if (object instanceof PEMKeyPair keyPair) {
                return converter.getKeyPair(
                        keyPair);
            }

            throw new IllegalArgumentException(
                    "Unsupported PEM key format");
        }
    }

    private String calculateFingerprint(
            PublicKey publicKey)
            throws Exception {

        byte[] encoded =
                publicKey.getEncoded();

        java.security.MessageDigest sha256 =
                java.security.MessageDigest
                        .getInstance(
                                "SHA-256");

        byte[] hash =
                sha256.digest(
                        encoded);

        return "SHA256:"
                + Base64.getEncoder()
                .encodeToString(hash);
    }


    private String extractAccount(
            String endpoint) {

        String host =
                java.net.URI
                        .create(endpoint)
                        .getHost();

        int idx =
                host.indexOf(
                        ".snowflakecomputing.com");

        return idx > 0
                ? host.substring(0, idx)
                : host;
    }

}
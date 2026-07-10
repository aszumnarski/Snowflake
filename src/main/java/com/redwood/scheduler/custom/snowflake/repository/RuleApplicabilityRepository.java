package com.redwood.scheduler.custom.snowflake.repository;

import com.redwood.scheduler.custom.snowflake.Snowflake;
import com.redwood.scheduler.custom.snowflake.SnowflakeConfig;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.RuleApplicability;
import com.redwood.scheduler.custom.snowflake.util.SnowflakeBindings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RuleApplicabilityRepository extends BaseRepository<RuleApplicability>{

    private static final String INSERT_SQL =
            """
            INSERT INTO LND_REDWOOD_RULE_APPLICABILITY
            (
                SOURCE,
                SYSTEM,
                CLIENT,
                BUKRS,
                ACCOUNT_ITEM,
                RESPONSIBILITY_GROUP,
                KONTO,
                FILENAME
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?
            )
            """;

    private final Snowflake snowflake;
    private final SnowflakeConfig config;

    public RuleApplicabilityRepository(
            Snowflake snowflake,
            SnowflakeConfig config) {

        this.snowflake = snowflake;
        this.config = config;
    }

    public SnowflakeResponse save(
            List<RuleApplicability> records)
            throws Exception {

        validate(records);

        return snowflake.execute(config, INSERT_SQL, createBindings(records));
    }

    private Map<Integer, List<String>> createBindings(
            List<RuleApplicability> records) {

        return SnowflakeBindings.create(
                records,
                List.of(
                        RuleApplicability::getSource,
                        RuleApplicability::getSystem,
                        RuleApplicability::getClient,
                        RuleApplicability::getBukrs,
                        RuleApplicability::getAccountItem,
                        RuleApplicability::getResponsibilityGroup,
                        RuleApplicability::getKonto,
                        RuleApplicability::getFilename
                ));
    }

}

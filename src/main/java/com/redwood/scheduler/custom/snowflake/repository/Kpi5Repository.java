package com.redwood.scheduler.custom.snowflake.repository;

import com.redwood.scheduler.custom.snowflake.Snowflake;
import com.redwood.scheduler.custom.snowflake.SnowflakeConfig;
import com.redwood.scheduler.custom.snowflake.model.Kpi5;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.util.SnowflakeBindings;

import java.util.List;
import java.util.Map;

public class Kpi5Repository extends BaseRepository<Kpi5>{
    private static final String INSERT_SQL =
            """
            INSERT INTO LND_REDWOOD_TASK
            (
                OWNER,
                ASSIGNEE,
                DESCRIPTION,
                DEADLINE,
                ACCOUNT_NUMBER,
                REVIEW_NOTIFICATION_ID,
                COMPANY_CODE,
                "START",
                END,
                STATUS,
                JOB_ID,
                CATALOG_PATH_CURRENT_PERIOD,
                DELEGATION_HISTORY,
                FILENAME,
                IMPORT_DATE
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP()
            )
            """;


    private final Snowflake snowflake;
    private final SnowflakeConfig config;

    public Kpi5Repository(
            Snowflake snowflake,
            SnowflakeConfig config) {

        this.snowflake = snowflake;
        this.config = config;
    }


    public SnowflakeResponse save(
            List<Kpi5> tasks)
            throws Exception {

        validate(tasks);

        return snowflake.execute(config, INSERT_SQL, createBindings(tasks));
    }

    private Map<Integer, List<String>> createBindings(
            List<Kpi5> tasks) {

        return SnowflakeBindings.create(
                tasks,
                List.of(
                        Kpi5::getOwner,
                        Kpi5::getAssignee,
                        Kpi5::getDescription,
                        Kpi5::getDeadline,
                        Kpi5::getAccountNumber,
                        Kpi5::getReviewNotificationId,
                        Kpi5::getCompanyCode,
                        Kpi5::getStart,
                        Kpi5::getEnd,
                        Kpi5::getStatus,
                        Kpi5::getJobId,
                        Kpi5::getCatalogPathCurrentPeriod,
                        Kpi5::getDelegationHistory,
                        Kpi5::getFilename
                ));
    }
}
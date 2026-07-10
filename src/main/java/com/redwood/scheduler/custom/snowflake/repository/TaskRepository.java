package com.redwood.scheduler.custom.snowflake.repository;

import com.redwood.scheduler.custom.snowflake.Snowflake;
import com.redwood.scheduler.custom.snowflake.SnowflakeConfig;
import com.redwood.scheduler.custom.snowflake.model.RedwoodTask;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.util.SnowflakeBindings;

import java.util.List;
import java.util.Map;

public class TaskRepository extends BaseRepository<RedwoodTask>{
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
                FILENAME
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?,?,?,?,?,?,?
            )
            """;


    private final Snowflake snowflake;
    private final SnowflakeConfig config;

    public TaskRepository(
            Snowflake snowflake,
            SnowflakeConfig config) {

        this.snowflake = snowflake;
        this.config = config;
    }


    public SnowflakeResponse save(
            List<RedwoodTask> tasks)
            throws Exception {

        validate(tasks);

        return snowflake.execute(config, INSERT_SQL, createBindings(tasks));
    }

    private Map<Integer, List<String>> createBindings(
            List<RedwoodTask> tasks) {

        return SnowflakeBindings.create(
                tasks,
                List.of(
                        RedwoodTask::getOwner,
                        RedwoodTask::getAssignee,
                        RedwoodTask::getDescription,
                        RedwoodTask::getDeadline,
                        RedwoodTask::getAccountNumber,
                        RedwoodTask::getReviewNotificationId,
                        RedwoodTask::getCompanyCode,
                        RedwoodTask::getStart,
                        RedwoodTask::getEnd,
                        RedwoodTask::getStatus,
                        RedwoodTask::getJobId,
                        RedwoodTask::getCatalogPathCurrentPeriod,
                        RedwoodTask::getDelegationHistory,
                        RedwoodTask::getFilename
                ));
    }
}
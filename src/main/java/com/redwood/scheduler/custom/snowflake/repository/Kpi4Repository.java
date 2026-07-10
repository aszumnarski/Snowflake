package com.redwood.scheduler.custom.snowflake.repository;

import com.redwood.scheduler.custom.snowflake.Snowflake;
import com.redwood.scheduler.custom.snowflake.SnowflakeClient;
import com.redwood.scheduler.custom.snowflake.SnowflakeConfig;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.Kpi4;
import com.redwood.scheduler.custom.snowflake.util.SnowflakeBindings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Kpi4Repository extends BaseRepository<Kpi4> {

    private static final String INSERT_SQL =
            """
            INSERT INTO LND_REDWOOD_KPI4
            (
                JOB_ID,
                JOB_STATUS,
                START_TIME,
                END_TIME,
                COMPANY_CODE,
                ACCOUNT,
                ACCOUNT_GROUP,
                LIST_OF_PREPARER,
                PREPARER_RESPONDER,
                LIST_OF_APPROVER,
                APPROVER_RESPONDER,
                LIST_OF_REVIEWER,
                REVIEWER_RESPONDER,
                CERTIFICATION_STATUS,
                PREPARER_TIMESTAMP,
                APPROVER_TIMESTAMP,
                REVIEWER_TIMESTAMP,
                SAP_BALANCE,
                CURRENCY,
                PERIOD,
                FISCAL_YEAR,
                PREPARER_COMMENT,
                APPROVER_COMMENT,
                REVIEWER_COMMENT,
                CERTIFICATIONID,
                RECONCILIATION_TYPE,
                AUTO_CERTIFICATION_RULE,
                AUTO_CERTIFIED,
                DUE_DATE,
                ACCOUNT_CATEGORY,
                ANALYZED_QUANTITY,
                ANALYZED_BALANCE,
                UNANALYZED_QUANTITY,
                UNANALYZED_BALANCE,
                REQUESTID,
                FILENAME
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?,?,?,
                ?,?,?,?,?,?,?,?,?,?,
                ?,?,?,?,?,?,?,?,?,?,
                ?,?,?,?,?,?
            )
            """;


    private final Snowflake snowflake;

    private final SnowflakeConfig config;

    public Kpi4Repository(
            Snowflake snowflake,
            SnowflakeConfig config) {

        this.snowflake = snowflake;
        this.config = config;
    }


    public SnowflakeResponse save(
            List<Kpi4> records)
            throws Exception {

        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException(
                    "Record list cannot be empty");
        }

        return snowflake.execute(
                config,
                INSERT_SQL,
                createBindings(records));
    }

    private Map<Integer, List<String>> createBindings(
            List<Kpi4> records) {

        return SnowflakeBindings.create(
                records,
                List.of(
                        Kpi4::getJobId,
                        Kpi4::getJobStatus,
                        Kpi4::getStartTime,
                        Kpi4::getEndTime,
                        Kpi4::getCompanyCode,
                        Kpi4::getAccount,
                        Kpi4::getAccountGroup,
                        Kpi4::getListOfPreparer,
                        Kpi4::getPreparerResponder,
                        Kpi4::getListOfApprover,

                        Kpi4::getApproverResponder,
                        Kpi4::getListOfReviewer,
                        Kpi4::getReviewerResponder,
                        Kpi4::getCertificationStatus,
                        Kpi4::getPreparerTimestamp,
                        Kpi4::getApproverTimestamp,
                        Kpi4::getReviewerTimestamp,
                        Kpi4::getSapBalance,
                        Kpi4::getCurrency,
                        Kpi4::getPeriod,

                        Kpi4::getFiscalYear,
                        Kpi4::getPreparerComment,
                        Kpi4::getApproverComment,
                        Kpi4::getReviewerComment,
                        Kpi4::getCertificationId,
                        Kpi4::getReconciliationType,
                        Kpi4::getAutoCertificationRule,
                        Kpi4::getAutoCertified,
                        Kpi4::getDueDate,
                        Kpi4::getAccountCategory,

                        Kpi4::getAnalyzedQuantity,
                        Kpi4::getAnalyzedBalance,
                        Kpi4::getUnanalyzedQuantity,
                        Kpi4::getUnanalyzedBalance,
                        Kpi4::getRequestId,
                        Kpi4::getFilename
                ));
    }
}
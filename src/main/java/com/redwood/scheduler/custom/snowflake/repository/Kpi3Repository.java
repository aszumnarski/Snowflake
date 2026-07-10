package com.redwood.scheduler.custom.snowflake.repository;

import com.redwood.scheduler.custom.snowflake.Snowflake;
import com.redwood.scheduler.custom.snowflake.SnowflakeClient;
import com.redwood.scheduler.custom.snowflake.SnowflakeConfig;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.Kpi3;
import com.redwood.scheduler.custom.snowflake.util.SnowflakeBindings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Kpi3Repository extends BaseRepository<Kpi3> {

    private static final String INSERT_SQL =
            """
            INSERT INTO LND_REDWOOD_KPI3
            (
                PERIOD,
                COMPANYCODE,
                TYPE,
                TOTALOPENITEMS,
                PROPOSED,
                CLEARED,
                ERRORS,
                ACCOUNTITEMS,
                FILENAME
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?,?
            )
            """;


    private final Snowflake snowflake;

    private final SnowflakeConfig config;

    public Kpi3Repository(
            Snowflake snowflake,
            SnowflakeConfig config) {

        this.snowflake = snowflake;
        this.config = config;
    }


    public SnowflakeResponse save(
            List<Kpi3> records)
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
            List<Kpi3> records) {

        return SnowflakeBindings.create(
                records,
                List.of(
                        Kpi3::getPeriod,
                        Kpi3::getCompanyCode,
                        Kpi3::getType,
                        Kpi3::getTotalOpenItems,
                        Kpi3::getProposed,
                        Kpi3::getCleared,
                        Kpi3::getErrors,
                        Kpi3::getAccountItems,
                        Kpi3::getFilename
                ));
    }

}

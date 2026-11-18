package com.redwood.scheduler.custom.snowflake.importer;

import com.redwood.scheduler.api.model.JobFile;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.Kpi3;
import com.redwood.scheduler.custom.snowflake.repository.Kpi3Repository;
import com.redwood.scheduler.custom.snowflake.util.CsvUtils;

import java.util.ArrayList;
import java.util.List;

public class Kpi3CsvImporter
        extends BaseCsvImporter {

    private final Kpi3Repository repository;

    public Kpi3CsvImporter(
            Kpi3Repository repository) {

        this.repository = repository;
    }

    public SnowflakeResponse importFile(
            JobFile file)
            throws Exception {

        List<Kpi3> records = new ArrayList<>();

        for (String[] row : CsvUtils.read(file)) {

            Kpi3 record = new Kpi3();

            record.setPeriod(get(row, 0));
            record.setCompanyCode(get(row, 1));
            record.setType(get(row, 2));
            record.setTotalOpenItems(get(row, 3));
            record.setProposed(get(row, 4));
            record.setCleared(get(row, 5));
            record.setErrors(get(row, 6));
            record.setAccountItems(get(row, 7));
            record.setFilename(file.getName());

            records.add(record);
        }

        return repository.save(records);
    }
}

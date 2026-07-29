package com.redwood.scheduler.custom.snowflake.importer;

import com.redwood.scheduler.api.model.JobFile;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.RuleApplicability;
import com.redwood.scheduler.custom.snowflake.repository.RuleApplicabilityRepository;
import com.redwood.scheduler.custom.snowflake.util.CsvUtils;

import java.util.ArrayList;
import java.util.List;

public class RuleApplicabilityCsvImporter
        extends BaseCsvImporter {

    private final RuleApplicabilityRepository repository;

    public RuleApplicabilityCsvImporter(
            RuleApplicabilityRepository repository) {

        this.repository = repository;
    }

    public SnowflakeResponse importFile(
            JobFile file)
            throws Exception {

        List<RuleApplicability> records =
                new ArrayList<>();

        for (String[] row : CsvUtils.read(file)) {

            RuleApplicability record =
                    new RuleApplicability();

            record.setSource(get(row, 0));
            record.setSystem(get(row, 1));
            record.setClient(get(row, 2));
            record.setBukrs(get(row, 3));
            record.setAccountItem(get(row, 4));
            record.setResponsibilityGroup(get(row, 5));
            record.setKonto(get(row, 6));
            record.setFilename(file.getName());

            records.add(record);
        }

        return repository.save(records);
    }
}
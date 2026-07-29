package com.redwood.scheduler.custom.snowflake.importer;

import com.redwood.scheduler.api.exception.JobFileException;
import com.redwood.scheduler.api.exception.JobFileTransientException;
import com.redwood.scheduler.api.model.JobFile;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.Kpi5;
import com.redwood.scheduler.custom.snowflake.repository.Kpi5Repository;
import com.redwood.scheduler.custom.snowflake.schema.Kpi5Schema;
import com.redwood.scheduler.custom.snowflake.util.CsvUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.redwood.scheduler.custom.snowflake.schema.Kpi5Schema.*;
import static com.redwood.scheduler.custom.snowflake.util.SnowflakeValueSanitizer.safe;

public class Kpi5CsvImporter extends BaseCsvImporter {

    private final Kpi5Repository repository;

    public Kpi5CsvImporter(
            Kpi5Repository repository) {

        this.repository = repository;
    }

    public SnowflakeResponse importFile(
            JobFile file)
            throws Exception {

        return repository.save(readTasks(file));
    }

    private List<Kpi5> readTasks(
            JobFile file)
            throws JobFileTransientException,
            JobFileException,
            IOException {

        List<Kpi5> tasks = new ArrayList<>();

        for (String[] row : CsvUtils.read(file)) {

            Kpi5 task = new Kpi5();

            task.setOwner(safe(get(row, 0), OWNER));
            task.setAssignee(safe(get(row, 1),ASSIGNEE));
            task.setDescription(safe(get(row, 2),DESCRIPTION));
            task.setDeadline(safe(get(row, 3),DEADLINE));
            task.setAccountNumber(safe(get(row, 4),ACCOUNT_NUMBER));
            task.setReviewNotificationId(safe(get(row, 5),REVIEW_NOTIFICATION_ID));
            task.setCompanyCode(safe(get(row, 6),COMPANY_CODE));
            task.setStart(safe(get(row, 7),START));
            task.setEnd(safe(get(row, 8),END));
            task.setStatus(safe(get(row, 9),STATUS));
            task.setJobId(safe(get(row, 10),JOB_ID));
            task.setCatalogPathCurrentPeriod(safe(get(row, 11),CATALOG_PATH_CURRENT_PERIOD));
            task.setDelegationHistory(safe(get(row, 12),DELEGATION_HISTORY));
            task.setFilename(safe(file.getName(),FILENAME));

            tasks.add(task);
        }

        return tasks;
    }
}
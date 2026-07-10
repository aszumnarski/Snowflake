package com.redwood.scheduler.custom.snowflake.importer;

import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.RedwoodTask;
import com.redwood.scheduler.custom.snowflake.repository.TaskRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskCsvImporter {

    private final TaskRepository repository;

    public TaskCsvImporter(
            TaskRepository repository) {

        this.repository = repository;
    }

    public SnowflakeResponse importFile(
            Path csvFile)
            throws Exception {

        List<RedwoodTask> tasks =
                read(csvFile);

        return repository.save(tasks);
    }

    private List<RedwoodTask> read(
            Path csvFile)
            throws Exception {

        List<RedwoodTask> tasks =
                new ArrayList<>();

        List<String> lines =
                Files.readAllLines(csvFile);

        for (String line : lines) {

            String[] cols =
                    line.split(";", -1);

            RedwoodTask task =
                    new RedwoodTask();

            task.setOwner(cols[0]);
            task.setAssignee(cols[1]);
            task.setDescription(cols[2]);
            task.setDeadline(cols[3]);
            task.setAccountNumber(cols[4]);
            task.setReviewNotificationId(cols[5]);
            task.setCompanyCode(cols[6]);
            task.setStart(cols[7]);
            task.setEnd(cols[8]);
            task.setStatus(cols[9]);
            task.setJobId(cols[10]);
            task.setCatalogPathCurrentPeriod(cols[11]);
            task.setDelegationHistory(cols[12]);
            task.setFilename(cols[13]);

            tasks.add(task);
        }

        return tasks;
    }
}
package com.redwood.scheduler.custom.snowflake.util;

import com.redwood.scheduler.api.exception.JobFileException;
import com.redwood.scheduler.api.exception.JobFileTransientException;
import com.redwood.scheduler.api.model.JobFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class CsvUtils {

    private CsvUtils() {
    }

    public static List<String[]> read(JobFile file)
            throws JobFileTransientException, JobFileException, IOException {

        return read(file, ';', true);
    }

    public static List<String[]> read(
            JobFile file,
            char delimiter,
            boolean skipHeader)
            throws JobFileTransientException, JobFileException, IOException {

        List<String[]> rows = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDelimiter(delimiter)
                .setQuote('"')
                .setSkipHeaderRecord(skipHeader)
                .build();

        try (Reader reader =
                     new InputStreamReader(
                             file.getInputStream(),
                             StandardCharsets.UTF_8);
             CSVParser parser = format.parse(reader)) {

            boolean first = true;

            for (CSVRecord record : parser) {

                if (skipHeader && first) {
                    first = false;
                    continue;
                }

                String[] row = new String[record.size()];

                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }

                rows.add(row);
            }
        }

        return rows;
    }
}
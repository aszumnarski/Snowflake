package com.redwood.scheduler.custom.snowflake.util;

import com.redwood.scheduler.api.exception.JobFileException;
import com.redwood.scheduler.api.exception.JobFileTransientException;
import com.redwood.scheduler.api.model.JobFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class CsvUtils {


    private CsvUtils() {
    }

    public static List<String[]> read(JobFile file) throws JobFileTransientException, JobFileException, IOException {

        return read(file,";",true);
    }

    public static List<String[]> read(JobFile file,String separator,boolean skipHeader) throws JobFileTransientException, JobFileException, IOException {

        List<String[]> rows = new ArrayList<>();
        String line;
        boolean firstLine = true;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {

                if (skipHeader && firstLine) {
                    firstLine = false;
                    continue;
                }

                rows.add(line.split(separator, -1));
            }
        }
        return rows;
    }
}

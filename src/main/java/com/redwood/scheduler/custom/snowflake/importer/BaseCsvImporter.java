package com.redwood.scheduler.custom.snowflake.importer;

public abstract class BaseCsvImporter {

    protected String get(String[] row, int index) {

        return index < row.length ? row[index] : null;
    }
}


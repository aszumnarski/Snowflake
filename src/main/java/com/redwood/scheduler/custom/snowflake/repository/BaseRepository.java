package com.redwood.scheduler.custom.snowflake.repository;

import java.util.List;

public abstract class BaseRepository<T> {

    protected void validate(
            List<T> records) {

        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException(
                    "Record list cannot be empty");
        }
    }
}
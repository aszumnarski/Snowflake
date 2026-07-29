package com.redwood.scheduler.custom.snowflake.util;

public final class SnowflakeValueSanitizer {

    private SnowflakeValueSanitizer() {
    }

    public static String safe(String value, int maxLength) {
        if (value == null) {
            return null;
        }

        return value.length() <= maxLength
                ? value
                : value.substring(0, maxLength);
    }
}
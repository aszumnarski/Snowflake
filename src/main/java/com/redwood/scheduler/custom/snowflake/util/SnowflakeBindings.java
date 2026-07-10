package com.redwood.scheduler.custom.snowflake.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class SnowflakeBindings {

    private SnowflakeBindings() {
    }

    public static <T> Map<Integer, List<String>> create(
            List<T> records,
            List<Function<T, String>> extractors) {

        Map<Integer, List<String>> bindings =
                new LinkedHashMap<>();

        for (int i = 0; i < extractors.size(); i++) {
            bindings.put(
                    i + 1,
                    new ArrayList<>());
        }

        for (T record : records) {

            for (int i = 0; i < extractors.size(); i++) {

                String value =
                        extractors.get(i)
                                .apply(record);

                bindings.get(i + 1)
                        .add(value);
            }
        }

        return bindings;
    }
}
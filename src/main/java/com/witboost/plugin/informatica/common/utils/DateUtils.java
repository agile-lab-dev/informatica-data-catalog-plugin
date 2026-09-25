package com.witboost.plugin.informatica.common.utils;

/** Date helpers for descriptor values sent to Informatica. */
public final class DateUtils {

    private DateUtils() {}

    /**
     * Returns the {@code yyyy-MM-dd} part of a descriptor date/timestamp, or {@code null} when
     * blank.
     *
     * <p>Descriptors may carry full ISO timestamps (e.g. {@code 2026-06-22T13:55:41.344Z}) or empty
     * strings; Informatica date fields expect {@code yyyy-MM-dd} and reject the rest.
     */
    public static String toIsoDate(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim().split("[T ]", 2)[0];
    }
}

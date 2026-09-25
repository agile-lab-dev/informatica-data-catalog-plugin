package com.witboost.plugin.informatica.common.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void keepsPlainDateUnchanged() {
        assertEquals("2026-07-03", DateUtils.toIsoDate("2026-07-03"));
    }

    @Test
    void stripsTimeFromIsoTimestamp() {
        assertEquals("2026-06-22", DateUtils.toIsoDate("2026-06-22T13:55:41.344Z"));
    }

    @Test
    void stripsTimeFromSpaceSeparatedTimestamp() {
        assertEquals("2026-06-22", DateUtils.toIsoDate("2026-06-22 14:05:35.230000+00:00"));
    }

    @Test
    void returnsNullForBlankOrNull() {
        assertNull(DateUtils.toIsoDate(null));
        assertNull(DateUtils.toIsoDate(""));
        assertNull(DateUtils.toIsoDate("   "));
    }
}

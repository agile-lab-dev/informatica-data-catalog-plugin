package com.witboost.plugin.informatica.common.mapper.dataproduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class VocabularyNormalizerTest {

    @Test
    void matchesCaseInsensitivelyAndTrims() {
        assertEquals("PULL", VocabularyNormalizer.toCanonical("pull", "PUSH", "PULL"));
        assertEquals(
                "Active",
                VocabularyNormalizer.toCanonical("  active ", "Draft", "Active", "Retired"));
        assertEquals(
                "In Development",
                VocabularyNormalizer.toCanonical("IN DEVELOPMENT", "Draft", "In Development"));
        assertEquals(
                "Private",
                VocabularyNormalizer.toCanonical("private", "Public", "Private", "Reserved"));
    }

    @Test
    void keepsCanonicalValueUnchanged() {
        assertEquals(
                "Full",
                VocabularyNormalizer.toCanonical("Full", "Merge", "Upsert", "Incremental", "Full"));
    }

    @Test
    void leavesUnknownAndNullUntouched() {
        // unknown value is returned as-is so the strict @Pattern still rejects it
        assertEquals("WrongMode", VocabularyNormalizer.toCanonical("WrongMode", "Merge", "Full"));
        assertNull(VocabularyNormalizer.toCanonical(null, "Merge", "Full"));
    }
}

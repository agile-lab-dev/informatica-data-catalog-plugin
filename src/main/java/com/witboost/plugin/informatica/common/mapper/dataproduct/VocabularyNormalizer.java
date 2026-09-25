package com.witboost.plugin.informatica.common.mapper.dataproduct;

/**
 * Normalizes a descriptor value to the canonical entry of a closed vocabulary, matching
 * case-insensitively (after trimming).
 *
 * <p>This lets descriptors use off-case values (e.g. {@code "pull"}, {@code "active"}) while the
 * strict {@code @Pattern} constraints on the mapped models keep the stored/emitted value canonical
 * ("output strict"). Unrecognized values are returned unchanged so the {@code @Pattern} validation
 * still rejects genuine typos.
 */
final class VocabularyNormalizer {

    private VocabularyNormalizer() {}

    /**
     * @param value the raw descriptor value (may be {@code null})
     * @param allowed the canonical vocabulary entries, in their exact (strict) casing
     * @return the canonical entry matching {@code value} case-insensitively, or {@code value}
     *     unchanged when it is {@code null} or matches no entry
     */
    static String toCanonical(String value, String... allowed) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        for (String canonical : allowed) {
            if (canonical.equalsIgnoreCase(trimmed)) {
                return canonical;
            }
        }
        return value;
    }
}

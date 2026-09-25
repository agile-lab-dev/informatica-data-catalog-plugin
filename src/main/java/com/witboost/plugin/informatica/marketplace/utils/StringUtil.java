package com.witboost.plugin.informatica.marketplace.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern DIACRITICAL_MARKS = Pattern.compile("\\p{M}+");

    /**
     * Normalizes a string to plain ASCII by decomposing accented characters (e.g. "à" → "a") and
     * stripping the resulting diacritical marks.
     *
     * @param input The input string to convert
     * @return The ASCII-normalized string
     */
    public static String toAscii(String input) {
        if (input == null) {
            return "";
        }

        var decomposed = Normalizer.normalize(input, Normalizer.Form.NFD);
        return DIACRITICAL_MARKS.matcher(decomposed).replaceAll("");
    }

    /**
     * Converts a string to SNAKE_CASE uppercase format.
     *
     * <p>Examples:
     *
     * <ul>
     *   <li>"My Data Product" → "MY_DATA_PRODUCT"
     *   <li>"salesDataProduct" → "SALES_DATA_PRODUCT"
     *   <li>"Sales-Data-Product" → "SALES_DATA_PRODUCT"
     *   <li>"Città" → "CITTA"
     * </ul>
     *
     * @param input The input string to convert
     * @return The string in SNAKE_CASE uppercase format
     */
    public static String toSnakeUpperCase(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        return toAscii(input)
                // Replace common separators (space, hyphen, dot) with underscore
                .replaceAll("[\\s\\-.]", "_")
                // Add underscore before uppercase letters (for camelCase)
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                // Replace multiple consecutive underscores with single underscore
                .replaceAll("_+", "_")
                // Convert to uppercase
                .toUpperCase()
                // Remove leading/trailing underscores
                .replaceAll("^_+|_+$", "");
    }

    /**
     * Converts a string to snake_case lowercase format.
     *
     * <p>Examples:
     *
     * <ul>
     *   <li>"My Data Product" → "my_data_product"
     *   <li>"salesDataProduct" → "sales_data_product"
     *   <li>"Sales-Data-Product" → "sales_data_product"
     *   <li>"Città" → "citta"
     * </ul>
     *
     * @param input The input string to convert
     * @return The string in snake_case lowercase format
     */
    public static String toSnakeLowerCase(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        return toAscii(input)
                // Replace common separators (space, hyphen, dot) with underscore
                .replaceAll("[\\s\\-.]", "_")
                // Add underscore before uppercase letters (for camelCase)
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                // Replace multiple consecutive underscores with single underscore
                .replaceAll("_+", "_")
                // Convert to lowercase
                .toLowerCase()
                // Remove leading/trailing underscores
                .replaceAll("^_+|_+$", "");
    }
}

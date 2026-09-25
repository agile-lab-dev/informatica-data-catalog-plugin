package com.witboost.plugin.informatica.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class ErrorBuilderTest {

    @Test
    void systemErrorSurfacesRootCauseFromWrappedException() {
        var root =
                new IllegalArgumentException(
                        "Category not found for company 'bad'. Accepted values: [example-company]");
        var wrapped = new RuntimeException("Failed to insert Data Product 'X'", root);

        var error = ErrorBuilder.buildSystemError(Optional.empty(), wrapped);

        // The descriptive root message must survive, not just the generic wrapper.
        assertTrue(
                error.getError().contains("Failed to insert Data Product 'X'"), error.getError());
        assertTrue(
                error.getError().contains("Category not found for company 'bad'"),
                error.getError());
    }

    @Test
    void describeCauseChainSkipsBlankAndDuplicateMessages() {
        var root = new IllegalStateException("root cause");
        var middle = new RuntimeException("root cause", root); // duplicate of root
        var blank = new RuntimeException("   ", middle); // blank, skipped
        var top = new RuntimeException("top", blank);

        assertEquals("top: root cause", ErrorBuilder.describeCauseChain(top));
    }

    @Test
    void describeCauseChainFallsBackToToStringWhenNoMessages() {
        var ex = new RuntimeException();

        assertEquals(ex.toString(), ErrorBuilder.describeCauseChain(ex));
    }
}

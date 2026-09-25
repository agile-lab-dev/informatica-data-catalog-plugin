package com.witboost.plugin.informatica.common.exceptions;

/**
 * Exception thrown when there are issues with workbook operations.
 *
 * <p>This is a checked exception (extends IOException) that should be used for workbook-related
 * errors such as template loading, parsing, or initialization failures.
 *
 * <p>Unlike {@link ApiCallException} which is a RuntimeException used for API communication errors,
 * this exception is meant for I/O and template-related issues.
 */
public class WorkbookException extends RuntimeException {

    /**
     * Constructs a new WorkbookException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public WorkbookException(String message) {
        super(message);
    }

    /**
     * Constructs a new WorkbookException with the specified detail message and cause.
     *
     * @param message the detail message explaining the cause of the exception
     * @param cause the cause of this exception
     */
    public WorkbookException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new WorkbookException with the specified cause.
     *
     * @param cause the cause of this exception
     */
    public WorkbookException(Throwable cause) {
        super(cause);
    }
}

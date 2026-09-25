package com.witboost.plugin.informatica.datacatalog.service.client;

/**
 * Enumeration of possible job status values in Informatica Data Catalog.
 *
 * <p>These statuses represent the lifecycle states of asynchronous jobs such as asset imports or
 * data processing operations.
 *
 * @see JobApiClient#getJobStatus(String)
 * @see JobApiClient#waitForJobCompletion(String)
 */
public enum JobStatus {

    /** Job has been submitted. */
    SUBMITTED,

    /** Job is currently starting/initializing. */
    STARTING,

    /** Job is actively running/processing. */
    RUNNING,

    /** Job completed successfully. */
    COMPLETED,

    /** Job completed partially (some tasks succeeded, others failed). */
    PARTIAL_COMPLETED,

    /** Job failed with errors. */
    FAILED;

    /**
     * Checks if this status represents a terminal state (job has finished).
     *
     * @return true if the job is in a final state (COMPLETED, PARTIAL_COMPLETED, or FAILED)
     */
    public boolean isTerminal() {
        return this == COMPLETED || this == PARTIAL_COMPLETED || this == FAILED;
    }

    /**
     * Checks if this status represents an in-progress state (job is still running).
     *
     * @return true if the job is still in progress (STARTING or RUNNING)
     */
    public boolean isInProgress() {
        return this == SUBMITTED || this == STARTING || this == RUNNING;
    }

    /**
     * Checks if this status represents a successful completion.
     *
     * @return true if the job completed successfully (COMPLETED or PARTIAL_COMPLETED)
     */
    public boolean isSuccess() {
        return this == COMPLETED;
    }

    /**
     * Parses a string status value to the corresponding enum constant.
     *
     * @param statusString The status string from the API response
     * @return The corresponding JobStatus enum value
     * @throws IllegalArgumentException if the status string doesn't match any known status
     */
    public static JobStatus fromString(String statusString) {
        if (statusString == null || statusString.isBlank()) {
            throw new IllegalArgumentException("Status string cannot be null or blank");
        }

        try {
            return JobStatus.valueOf(statusString.toUpperCase().replace('-', '_'));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "Unknown job status: '%s'. Valid values are: %s",
                            statusString,
                            String.join(
                                    ", ",
                                    java.util.Arrays.stream(JobStatus.values())
                                            .map(Enum::name)
                                            .toArray(String[]::new))));
        }
    }
}

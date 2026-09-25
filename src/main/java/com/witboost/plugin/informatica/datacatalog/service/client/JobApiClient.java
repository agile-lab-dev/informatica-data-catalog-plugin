package com.witboost.plugin.informatica.datacatalog.service.client;

import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.exceptions.FailedImportJobException;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogApiConfig;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * API client for interacting with Informatica job status endpoints.
 *
 * <p>This client provides functionality to check the status of asynchronous jobs in Informatica
 * Data Catalog, such as import operations.
 */
@Service
@Slf4j
public class JobApiClient extends BaseClient {

    private static final int HTTP_OK = 200;
    private static final String EXPAND_CHILDREN_PARAM = "expandChildren";
    private static final String TASK_HIERARCHY = "TASK-HIERARCHY";
    private static final String OUTPUT_PROPERTIES = "OUTPUT-PROPERTIES";

    private final InformaticaApiClient informaticaApiClient;
    private final DataCatalogApiConfig config;

    public JobApiClient(InformaticaApiClient informaticaApiClient, DataCatalogApiConfig config) {
        this.informaticaApiClient = informaticaApiClient;
        this.config = config;
    }

    /**
     * Retrieves the current status of a job from Informatica Data Catalog.
     *
     * <p>This method queries the Informatica job API to get the status of an asynchronous job (such
     * as an asset import job).
     *
     * @param jobId The unique identifier of the job to check (must not be null or blank)
     * @return The current status of the job as a JobStatus enum
     * @throws ApiCallException if the API call fails, returns a non-200 status, or response parsing
     *     fails
     * @see #getJobStatusUrl(String)
     * @see JobStatus
     */
    public JobStatus getJobStatus(String jobId) {
        try {
            log.debug("Fetching job status for job ID: {}", jobId);

            // Step 1: Build job status URL with query parameters
            String jobStatusUrl = getJobStatusUrl(jobId);
            log.debug("Job status URL: {}", jobStatusUrl);

            // Step 2: Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(new URI(jobStatusUrl))
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch job status");

            // Step 3: Send request
            HttpResponse<String> httpResponse =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Step 4: Validate response
            HttpResponseValidator.validateResponse(
                    httpResponse, HTTP_OK, "Job status retrieval", ApiCallException::new);
            log.debug("Received successful response for job status request");

            // Step 5: Parse JSON response
            String responseBody = httpResponse.body();
            log.trace("Response body: {}", responseBody);

            JSONObject jsonResponse = new JSONObject(responseBody);

            String statusString = jsonResponse.getString("status");
            JobStatus status = JobStatus.fromString(statusString);
            log.debug("Job status for job ID '{}': {}", jobId, status);

            if (status.isTerminal() && !status.isSuccess()) {
                log.error(
                        "Job '{}' completed with unsuccessful state '{}'. Response body: {}",
                        jobId,
                        status,
                        responseBody);
            }

            return status;

        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Unexpected error while fetching job status for job ID '%s': %s",
                            jobId, e.getMessage());
            log.error(errorMessage, e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Builds the complete job status URL with query parameters.
     *
     * <p>Constructs the URL by combining the base jobs endpoint, job ID, and query parameters for
     * expanding children (output properties and task hierarchy).
     *
     * <p>URL format:
     *
     * <pre>
     * {baseJobsUrl}/{jobId}?expandChildren=OUTPUT-PROPERTIES&expandChildren=TASK-HIERARCHY
     * </pre>
     *
     * @param jobId The job ID to include in the URL
     * @return The complete job status URL with query parameters
     */
    private String getJobStatusUrl(String jobId) {
        return String.format(
                "%s/%s?%s=%s&%s=%s",
                config.getJobsUrl(),
                jobId,
                EXPAND_CHILDREN_PARAM,
                OUTPUT_PROPERTIES,
                EXPAND_CHILDREN_PARAM,
                TASK_HIERARCHY);
    }

    /**
     * Waits for a job to complete by polling its status until it reaches a terminal state.
     *
     * <p>This method continuously polls the job status at regular intervals until the job is no
     * longer in RUNNING or STARTING state. The polling interval and timeout are automatically
     * configured from application settings.
     *
     * <p>Polling configuration (from application.yml):
     *
     * <ul>
     *   <li><strong>polling-interval-millis</strong> - Time between status checks (default: 30000ms
     *       = 30 seconds)
     *   <li><strong>polling-timeout-millis</strong> - Maximum wait time before timeout (default:
     *       300000ms = 5 minutes)
     * </ul>
     *
     * <p>The method will keep polling as long as the job status is:
     *
     * <ul>
     *   <li>STARTING - Job is initializing
     *   <li>RUNNING - Job is actively processing
     * </ul>
     *
     * <p>The polling stops when the job reaches a terminal state:
     *
     * <ul>
     *   <li>COMPLETED - Job finished successfully
     *   <li>PARTIAL_COMPLETED - Job finished with some failures
     *   <li>FAILED - Job failed completely
     * </ul>
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * // Automatic polling with configured intervals
     * String jobId = "job-12345";
     * JobStatus finalStatus = jobApiClient.waitForJobCompletion(jobId);
     *
     * if (finalStatus.isSuccess()) {
     *     log.info("Job completed successfully");
     * } else {
     *     log.error("Job failed with status: {}", finalStatus);
     * }
     * }</pre>
     *
     * <p>Configuration example in application.yml:
     *
     * <pre>{@code
     * informatica:
     *   data-catalog:
     *     api:
     *       settings:
     *         polling-interval-millis: 30000  # Poll every 30 seconds
     *         polling-timeout-millis: 300000  # Timeout after 5 minutes
     * }</pre>
     *
     * @param jobId The unique identifier of the job to monitor (must not be null or blank)
     * @return The final terminal status of the job (COMPLETED, PARTIAL_COMPLETED, or FAILED)
     * @throws IllegalArgumentException if jobId is null/blank or if configuration values are
     *     invalid
     * @throws ApiCallException if polling times out or an error occurs during status checks
     * @throws FailedImportJobException if job returns an unsuccessful status
     * @see #getJobStatus(String)
     * @see JobStatus
     * @see DataCatalogApiConfig.Settings
     */
    public JobStatus waitForJobCompletion(String jobId) {
        return waitForJobCompletion(jobId, "job");
    }

    /**
     * Waits for a job to complete by polling its status until it reaches a terminal state, using a
     * human-readable description to make the polling logs identifiable.
     *
     * <p>Behaves exactly like {@link #waitForJobCompletion(String)} but includes {@code
     * jobDescription} in the polling, terminal-state and "starting polling" log lines so it is
     * clear which job (e.g. catalog source sync vs. import step 1/2 vs. 2/2) the logs refer to.
     *
     * @param jobId The unique identifier of the job to monitor (must not be null or blank)
     * @param jobDescription A human-readable label describing the job, used in log messages
     * @return The final terminal status of the job (COMPLETED, PARTIAL_COMPLETED, or FAILED)
     * @throws IllegalArgumentException if jobId is null/blank or if configuration values are
     *     invalid
     * @throws ApiCallException if polling times out or an error occurs during status checks
     * @throws FailedImportJobException if job returns an unsuccessful status
     * @see #getJobStatus(String)
     * @see JobStatus
     * @see DataCatalogApiConfig.Settings
     */
    public JobStatus waitForJobCompletion(String jobId, String jobDescription) {

        Long pollingIntervalMs = config.settings().pollingIntervalMillis();
        Long timeoutMs = config.settings().pollingTimeoutMillis();

        log.info(
                "Starting job status polling for {} (job ID '{}', interval: {}ms, timeout: {}ms)",
                jobDescription,
                jobId,
                pollingIntervalMs,
                timeoutMs);

        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeoutMs;
        int pollCount = 0;

        try {
            while (System.currentTimeMillis() < endTime) {
                pollCount++;

                // Get current job status
                JobStatus currentStatus = getJobStatus(jobId);

                log.info(
                        "Poll #{}: {} (job ID '{}') status is {}",
                        pollCount,
                        jobDescription,
                        jobId,
                        currentStatus);

                // Check if job has reached a terminal state
                if (currentStatus.isTerminal()) {
                    if (currentStatus.isSuccess()) {
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        log.info(
                                "{} (job ID '{}') reached terminal state '{}' after {} polls ({} ms)",
                                jobDescription,
                                jobId,
                                currentStatus,
                                pollCount,
                                elapsedTime);
                        return currentStatus;
                    } else {
                        throw new FailedImportJobException(
                                String.format(
                                        "Job '%s' completed with unsuccessful state '%s'",
                                        jobId, currentStatus));
                    }
                }

                // Check if status is still in progress
                if (currentStatus.isInProgress()) {
                    log.trace(
                            "Job '{}' is still in progress ({}), waiting {}ms before next poll",
                            jobId,
                            currentStatus,
                            pollingIntervalMs);

                    // Sleep before next poll
                    Thread.sleep(pollingIntervalMs);
                } else {
                    // Unexpected status - log warning but continue polling
                    log.warn(
                            "Job '{}' has unexpected status '{}', continuing to poll",
                            jobId,
                            currentStatus);
                    Thread.sleep(pollingIntervalMs);
                }
            }

            // Timeout reached
            long elapsedTime = System.currentTimeMillis() - startTime;
            String errorMessage =
                    String.format(
                            "Timeout waiting for job '%s' to complete after %d polls (%d ms). "
                                    + "Job is still in progress. Consider increasing the timeout or checking the job manually.",
                            jobId, pollCount, elapsedTime);
            log.error(errorMessage);
            throw new ApiCallException(errorMessage);

        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Unexpected error while polling job status for job '%s' after %d polls: %s",
                            jobId, pollCount, e.getMessage());
            log.error(errorMessage, e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }
}

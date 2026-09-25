package com.witboost.plugin.informatica.common.utils;

import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for validating HTTP responses and handling error responses. Provides methods to
 * check response status codes and throw appropriate exceptions with detailed error messages.
 */
@Slf4j
public final class HttpResponseValidator {

    private HttpResponseValidator() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Validates that the HTTP response has the expected status code. If the status code doesn't
     * match, logs the error and throws an exception.
     *
     * @param response The HTTP response to validate
     * @param expectedStatus The expected HTTP status code (e.g., 200)
     * @param operationName Descriptive name of the operation (e.g., "Login", "Token generation")
     * @param <T> The type of the response body
     * @throws RuntimeException if the status code doesn't match the expected value
     */
    public static <T> void validateResponse(
            HttpResponse<T> response, int expectedStatus, String operationName) {
        if (response.statusCode() != expectedStatus) {
            String errorBody =
                    response.body() != null ? response.body().toString() : "No response body";
            extracted(operationName, response.statusCode(), errorBody);
            throw new RuntimeException(
                    String.format(
                            "%s failed with status %d. Response: %s",
                            operationName, response.statusCode(), errorBody));
        }
    }

    /**
     * Validates that the HTTP response has the expected status code. If the status code doesn't
     * match, logs the error and throws a custom exception.
     *
     * @param response The HTTP response to validate
     * @param expectedStatus The expected HTTP status code (e.g., 200)
     * @param operationName Descriptive name of the operation (e.g., "Login", "Token generation")
     * @param exceptionFactory Factory function to create the exception with error message
     * @param <T> The type of the response body
     * @param <E> The type of exception to throw
     * @throws E if the status code doesn't match the expected value
     */
    public static <T, E extends RuntimeException> void validateResponse(
            HttpResponse<T> response,
            int expectedStatus,
            String operationName,
            java.util.function.Function<String, E> exceptionFactory) {
        if (response.statusCode() != expectedStatus) {
            String errorBody =
                    response.body() != null ? response.body().toString() : "No response body";
            extracted(operationName, response.statusCode(), errorBody);
            String errorMessage =
                    String.format(
                            "%s failed with status %d. Response: %s",
                            operationName, response.statusCode(), errorBody);
            throw exceptionFactory.apply(errorMessage);
        }
    }

    private static <T> void extracted(String operationName, int response, String errorBody) {
        log.error("{} failed with status {}: {}", operationName, response, errorBody);
    }

    /**
     * Checks if the HTTP response status code matches the expected status.
     *
     * @param response The HTTP response to check
     * @param expectedStatus The expected HTTP status code
     * @param <T> The type of the response body
     * @return true if status code matches, false otherwise
     */
    public static <T> boolean isStatusCodeValid(HttpResponse<T> response, int expectedStatus) {
        return response.statusCode() == expectedStatus;
    }

    /**
     * Extracts error information from an HTTP response for logging/error handling.
     *
     * @param response The HTTP response
     * @param operationName Descriptive name of the operation
     * @param <T> The type of the response body
     * @return Formatted error message with status code and response body
     */
    public static <T> String extractErrorMessage(HttpResponse<T> response, String operationName) {
        String errorBody =
                response.body() != null ? response.body().toString() : "No response body";
        return String.format(
                "%s failed with status %d. Response: %s",
                operationName, response.statusCode(), errorBody);
    }
}

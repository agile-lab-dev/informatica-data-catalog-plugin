package com.witboost.plugin.informatica.common.exceptions;

import com.witboost.plugin.informatica.datacatalog.openapi.model.ErrorMoreInfo;
import com.witboost.plugin.informatica.datacatalog.openapi.model.RequestValidationError;
import com.witboost.plugin.informatica.datacatalog.openapi.model.SystemError;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ErrorBuilder {

    public static RequestValidationError buildRequestValidationError(
            Optional<String> message, FailedOperation failedOperation) {
        var error =
                new RequestValidationError()
                        .userMessage(
                                message.orElse(
                                        "Validation on the received descriptor failed, check the error details for more information"));

        List<String> problems =
                failedOperation.problems().stream()
                        .map(Problem::getMessage)
                        .collect(Collectors.toList());

        ArrayList<String> solutions =
                new ArrayList<>(
                        failedOperation.problems().stream()
                                .flatMap(p -> p.solutions().stream())
                                .toList());
        solutions.add("If the problem persists, contact the platform team");

        return error.errors(problems).moreInfo(new ErrorMoreInfo(problems, solutions));
    }

    public static RequestValidationError buildRequestValidationError(
            ConstraintViolationException validationException) {
        var error =
                new RequestValidationError()
                        .userMessage(
                                "Validation on the received descriptor failed, check the error details for more information");

        var problems =
                validationException.getConstraintViolations().stream()
                        .map(Problem::fromConstraintViolation)
                        .map(Problem::description)
                        .toList();

        if (validationException.getConstraintViolations().size() == 1) {
            error =
                    error.inputErrorField(
                            validationException.getConstraintViolations().stream()
                                    .map(cv -> cv.getPropertyPath().toString())
                                    .findFirst()
                                    .get());
        }

        return error.errors(problems)
                .moreInfo(
                        new ErrorMoreInfo(
                                problems,
                                List.of(
                                        "Check the input descriptor is compliant with the schema expected by this Data Catalog Plugin and try again",
                                        "If the problem persists, contact the platform team")));
    }

    public static SystemError buildSystemError(Optional<String> message, Throwable throwable) {
        var error =
                new SystemError()
                        .userMessage(
                                message.orElse(
                                        "An unexpected error occurred while processing the request. Check the error details for more information"));

        // Walk the cause chain so a wrapped root message is not hidden by a generic outer one.
        String detail = describeCauseChain(throwable);

        List<String> problems = List.of(detail);

        List<String> solutions =
                List.of("Please try again and if the problem persists contact the platform team.");

        return error.error(detail).moreInfo(new ErrorMoreInfo(problems, solutions));
    }

    /** Joins the messages along the cause chain, skipping blanks and consecutive duplicates. */
    static String describeCauseChain(Throwable throwable) {
        List<String> messages = new ArrayList<>();
        for (Throwable current = throwable; current != null; current = current.getCause()) {
            String message = current.getMessage();
            if (message == null || message.isBlank()) {
                continue;
            }
            if (messages.isEmpty() || !messages.get(messages.size() - 1).equals(message)) {
                messages.add(message);
            }
        }
        return messages.isEmpty() ? throwable.toString() : String.join(": ", messages);
    }
}

package com.witboost.plugin.informatica.marketplace.controller;

import com.witboost.plugin.informatica.common.exceptions.ErrorBuilder;
import com.witboost.plugin.informatica.datacatalog.openapi.model.RequestValidationError;
import com.witboost.plugin.informatica.datacatalog.openapi.model.SystemError;
import com.witboost.plugin.informatica.marketplace.exceptions.MarketplacePluginProvisioningException;
import com.witboost.plugin.informatica.marketplace.exceptions.MarketplacePluginValidationException;
import jakarta.validation.ConstraintViolationException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler for the API layer.
 *
 * <p>The following methods wrap generic exceptions into 400 and 500 errors. Implement your own
 * exception handlers based on the business exception that the provisioner throws. No further
 * modifications need to be done outside this file to make it work, as Spring identifies at startup
 * the handlers with the @ExceptionHandler annotation
 */
// Scoped to its own controllers: both plugins live in the same process, so an unscoped advice here
// would also catch the Data Catalog exceptions (and vice versa).
@RestControllerAdvice(basePackages = "com.witboost.plugin.informatica.marketplace")
public class MarketplacePluginExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(MarketplacePluginExceptionHandler.class);

    @ExceptionHandler({MarketplacePluginValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestValidationError handleConflict(MarketplacePluginValidationException ex) {
        logger.error("MarketplacePluginValidation Error", ex);
        return ErrorBuilder.buildRequestValidationError(
                Optional.ofNullable(ex.getMessage()), ex.getFailedOperation());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestValidationError handleConflict(ConstraintViolationException ex) {
        logger.error("Constraint violation Error", ex);
        return ErrorBuilder.buildRequestValidationError(ex);
    }

    @ExceptionHandler({MarketplacePluginProvisioningException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestValidationError handleConflict(MarketplacePluginProvisioningException ex) {
        logger.error("MarketplacePluginProvisioning Error", ex);
        return ErrorBuilder.buildRequestValidationError(
                Optional.ofNullable(ex.getMessage()), ex.getFailedOperation());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected SystemError handleConflict(RuntimeException ex) {
        logger.error("Error", ex);
        return ErrorBuilder.buildSystemError(Optional.empty(), ex);
    }
}

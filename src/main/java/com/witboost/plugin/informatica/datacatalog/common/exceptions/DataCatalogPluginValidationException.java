package com.witboost.plugin.informatica.datacatalog.common.exceptions;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;

public class DataCatalogPluginValidationException extends RuntimeException {
    private final FailedOperation failedOperation;

    public DataCatalogPluginValidationException(String message, FailedOperation failedOperation) {
        super(message);
        this.failedOperation = failedOperation;
    }

    public DataCatalogPluginValidationException(
            String message, FailedOperation failedOperation, Throwable cause) {
        super(message, cause);
        this.failedOperation = failedOperation;
    }

    public FailedOperation getFailedOperation() {
        return failedOperation;
    }
}

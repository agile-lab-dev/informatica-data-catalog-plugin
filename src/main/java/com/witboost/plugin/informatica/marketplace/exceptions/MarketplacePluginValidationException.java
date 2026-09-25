package com.witboost.plugin.informatica.marketplace.exceptions;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;

public class MarketplacePluginValidationException extends RuntimeException {
    private final FailedOperation failedOperation;

    public MarketplacePluginValidationException(String message, FailedOperation failedOperation) {
        super(message);
        this.failedOperation = failedOperation;
    }

    public MarketplacePluginValidationException(
            String message, FailedOperation failedOperation, Throwable cause) {
        super(message, cause);
        this.failedOperation = failedOperation;
    }

    public FailedOperation getFailedOperation() {
        return failedOperation;
    }
}

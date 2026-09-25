package com.witboost.plugin.informatica.common.exceptions;

public class SessionException extends RuntimeException {
    private final String message;

    public SessionException(String message) {
        super();
        this.message = message;
    }
}

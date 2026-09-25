package com.witboost.plugin.informatica.common.mapper.naming;

public enum OutputPortMode {
    STANDARD("standard_mode"),
    PRIVATE("private_mode");

    private final String value;

    OutputPortMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

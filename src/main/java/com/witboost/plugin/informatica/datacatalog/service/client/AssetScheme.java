package com.witboost.plugin.informatica.datacatalog.service.client;

public enum AssetScheme {
    INTERNAL("INTERNAL"),
    EXTERNAL("EXTERNAL");

    private final String value;

    AssetScheme(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.witboost.plugin.informatica.marketplace.mapper;

/** Converts a descriptor value before it is sent as a Marketplace custom attribute. */
public interface DescriptorValueTransformer {

    /** The configured transformer name. */
    String name();

    /** Transform a descriptor value. */
    Object transform(Object value);
}

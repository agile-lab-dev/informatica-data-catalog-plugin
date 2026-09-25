package com.witboost.plugin.informatica.marketplace.mapper;

import org.springframework.stereotype.Component;

/** Default transformer for values already represented in the descriptor. */
@Component
public class IdentityDescriptorValueTransformer implements DescriptorValueTransformer {

    @Override
    public String name() {
        return "identity";
    }

    @Override
    public Object transform(Object value) {
        return value;
    }
}

package com.witboost.plugin.informatica.common.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.witboost.plugin.informatica.common.config.DescriptorStructureProperties;
import com.witboost.plugin.informatica.common.parser.DescriptorTreeNode;
import com.witboost.plugin.informatica.common.parser.DescriptorTreeParser;
import org.springframework.stereotype.Service;

/** Spring boundary for descriptor-tree discovery and validation. */
@Service
public class DescriptorTreeService {

    private final DescriptorTreeParser parser;

    public DescriptorTreeService(DescriptorStructureProperties properties) {
        this.parser = new DescriptorTreeParser(properties);
    }

    public DescriptorTreeNode parse(String yamlDescriptor) {
        try {
            return parser.parse(yamlDescriptor);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Unable to parse the descriptor tree", exception);
        }
    }

    public DescriptorTreeNode parse(JsonNode descriptor) {
        return parser.parse(descriptor);
    }
}

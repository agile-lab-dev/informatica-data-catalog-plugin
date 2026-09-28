package com.witboost.plugin.informatica.common.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/** Configures the descriptor paths used to build the neutral descriptor tree. */
@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "informatica.descriptor")
public class DescriptorStructureProperties {

    @Valid private NodeProperties dataProduct = new NodeProperties();
    @Valid private NodeProperties outputPort = new NodeProperties();
    @Valid private NodeProperties subcomponent = new NodeProperties();
    @Valid private SchemaProperties schema = new SchemaProperties();

    /** Paths and flags shared by data-product, output-port and subcomponent nodes. */
    @Getter
    @Setter
    public static class NodeProperties {
        @NotBlank private String kindPath = "kind";
        private String kindValue;
        @NotBlank private String idPath = "id";
        @NotBlank private String publishPath = "specific.publishToInformatica";
        @NotBlank private String shoppablePath = "shoppable";
    }

    @Getter
    @Setter
    public static class SchemaProperties {
        @NotBlank private String path = "dataContract.schema";
        @NotBlank private String namePath = "name";
    }
}

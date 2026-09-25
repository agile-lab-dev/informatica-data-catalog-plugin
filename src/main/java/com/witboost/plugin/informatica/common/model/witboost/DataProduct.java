package com.witboost.plugin.informatica.common.model.witboost;

import static com.witboost.plugin.informatica.datacatalog.common.Constants.DATA_ASSET_KIND;
import static com.witboost.plugin.informatica.datacatalog.common.Constants.OUTPUTPORT_KIND;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.witboost.plugin.informatica.common.parser.JsonPathUtils;
import com.witboost.plugin.informatica.common.parser.Parser;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataProduct {

    private String id;
    private String name;
    private Optional<String> fullyQualifiedName;
    private String description;
    private String kind;
    private String domain;
    private String version;
    private String environment;
    private String dataProductOwner;
    private String dataProductOwnerDisplayName;
    private Optional<String> email;
    private String ownerGroup;
    private String devGroup;
    private Optional<String> informationSLA;
    private Optional<String> status;
    private Optional<String> maturity;
    private Optional<JsonNode> billing;
    private List<Tag> tags = List.of();
    private JsonNode specific;
    private List<JsonNode> components;

    @JsonIgnore private JsonNode rawDataProduct;

    public Option<JsonNode> getComponentToProvision(String componentId) {
        return Option.ofOptional(
                Optional.ofNullable(componentId)
                        .flatMap(
                                comp ->
                                        components.stream()
                                                .filter(c -> comp.equals(c.get("id").textValue()))
                                                .findFirst()));
    }

    public Option<String> getComponentKindToProvision(String componentId) {
        return getComponentToProvision(componentId)
                .flatMap(c -> Option.of(c.get("kind")))
                .map(JsonNode::textValue);
    }

    /**
     * Returns the output ports to publish on Informatica: the components of kind output port / data
     * asset, minus those opting out via {@code specific.publishToInformatica: false}. This is the
     * single entry point used by both validation and provisioning, so an excluded output port is
     * invisible to both.
     */
    public List<OutputPort<Specific>> extractOutputPorts() {
        return this.getComponents().stream()
                .filter(
                        component ->
                                component.get("kind").asText("none").equals(OUTPUTPORT_KIND)
                                        || component
                                                .get("kind")
                                                .asText("none")
                                                .equals(DATA_ASSET_KIND))
                .filter(Parser::isComponentPublishEnabled)
                .map(outputport -> Parser.parseComponent(outputport, Specific.class))
                .filter(Either::isRight)
                .map(Either::get)
                .map(x -> (OutputPort<Specific>) x)
                .toList();
    }

    public String getStringFromJsonPath(String jsonPath) {
        return JsonPathUtils.getStringFromJsonPath(this.rawDataProduct, jsonPath);
    }
}

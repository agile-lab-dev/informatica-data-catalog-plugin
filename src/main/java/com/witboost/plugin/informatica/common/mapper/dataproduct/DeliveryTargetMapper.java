package com.witboost.plugin.informatica.common.mapper.dataproduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.OutputPort;
import com.witboost.plugin.informatica.common.model.witboost.Specific;
import com.witboost.plugin.informatica.common.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper interface for converting between witboost `OutputPort` and informatica `OutputPort`
 * objects. This mapper uses MapStruct to generate the implementation at compile time.
 *
 * <p>This mapper supports two conversion modes:
 *
 * <ul>
 *   <li><b>Single conversion</b>: {@link #toOutputPort(OutputPort)} converts a single Witboost
 *       OutputPort to an Informatica OutputPort with one DataAsset
 *   <li><b>Aggregated conversion</b>: {@link #toOutputPortsGroupedByTechnology(List)} groups
 *       Witboost OutputPorts by technology and creates one Informatica OutputPort per technology,
 *       with multiple DataAssets
 * </ul>
 *
 * <p>Configuration:
 *
 * <ul>
 *   <li>Component model: Spring (enables Spring dependency injection).
 * </ul>
 */
@Mapper(componentModel = "spring", uses = DataAssetMapper.class)
public interface DeliveryTargetMapper {

    @Mapping(target = "baseCharacteristics.portName", source = "name")
    @Mapping(target = "baseCharacteristics.description", source = "description")
    @Mapping(target = "baseCharacteristics.version", source = "version")
    @Mapping(
            target = "baseCharacteristics.portTechnology",
            source = "witboostOutputPort",
            qualifiedByName = "extractTechnology")
    @Mapping(
            target = "baseCharacteristics.creationDate",
            source = "witboostOutputPort",
            qualifiedByName = "extractCreationDate")
    @Mapping(target = "dataAssets", ignore = true)
    DeliveryTarget toOutputPort(OutputPort<?> witboostOutputPort);

    /** Maps each descriptor output port independently, preserving its identity and shape. */
    default List<DeliveryTarget> toOutputPortsIndividually(
            List<? extends OutputPort<?>> witboostOutputPorts, DataAssetMapper dataAssetMapper) {
        if (witboostOutputPorts == null || witboostOutputPorts.isEmpty()) {
            return List.of();
        }
        return witboostOutputPorts.stream()
                .map(
                        outputPort -> {
                            DeliveryTarget target = toOutputPort(outputPort);
                            boolean hasChildren =
                                    outputPort.getRawComponent() != null
                                            && outputPort
                                                    .getRawComponent()
                                                    .path("components")
                                                    .isArray()
                                            && outputPort
                                                            .getRawComponent()
                                                            .path("components")
                                                            .size()
                                                    > 0;
                            List<DataAsset> dataAssets =
                                    hasChildren
                                            ? childDataAssets(outputPort, dataAssetMapper)
                                            : List.of(dataAssetMapper.toDataAsset(outputPort));
                            target.setDataAssets(dataAssets);
                            JsonNode rawComponent = outputPort.getRawComponent();
                            target.setShoppable(
                                    rawComponent == null
                                            || rawComponent.path("shoppable").asBoolean(true));
                            target.setConsumable(
                                    rawComponent != null
                                            && rawComponent.path("consumable").asBoolean(false));
                            target.setCatalogAssetType(
                                    hasChildren
                                            ? DeliveryTarget.CatalogAssetType.SYSTEM
                                            : DeliveryTarget.CatalogAssetType.DATASET);
                            return target;
                        })
                .toList();
    }

    /** Builds Marketplace offers independently for nested parents and child datasets. */
    default List<DeliveryTarget> toMarketplaceTargets(
            List<? extends OutputPort<?>> witboostOutputPorts, DataAssetMapper dataAssetMapper) {
        if (witboostOutputPorts == null || witboostOutputPorts.isEmpty()) return List.of();
        List<DeliveryTarget> result = new ArrayList<>();
        for (OutputPort<?> outputPort : witboostOutputPorts) {
            List<? extends OutputPort<?>> children = childOutputPorts(outputPort);
            if (children.isEmpty()) {
                result.add(toMarketplaceTarget(outputPort, dataAssetMapper));
            } else {
                DeliveryTarget parent = toOutputPort(outputPort);
                parent.setCatalogAssetType(DeliveryTarget.CatalogAssetType.SYSTEM);
                setMarketplaceFlags(parent, outputPort.getRawComponent());
                result.add(parent);
                children.forEach(child -> result.add(toMarketplaceTarget(child, dataAssetMapper)));
            }
        }
        return result;
    }

    private DeliveryTarget toMarketplaceTarget(
            OutputPort<?> outputPort, DataAssetMapper dataAssetMapper) {
        DeliveryTarget target = toOutputPort(outputPort);
        target.setDataAssets(List.of(dataAssetMapper.toDataAsset(outputPort)));
        target.setCatalogAssetType(DeliveryTarget.CatalogAssetType.DATASET);
        setMarketplaceFlags(target, outputPort.getRawComponent());
        return target;
    }

    private void setMarketplaceFlags(DeliveryTarget target, JsonNode rawComponent) {
        target.setShoppable(rawComponent == null || rawComponent.path("shoppable").asBoolean(true));
        target.setConsumable(
                rawComponent != null && rawComponent.path("consumable").asBoolean(false));
    }

    private List<DataAsset> childDataAssets(
            OutputPort<?> outputPort, DataAssetMapper dataAssetMapper) {
        return childOutputPorts(outputPort).stream().map(dataAssetMapper::toDataAsset).toList();
    }

    private List<? extends OutputPort<?>> childOutputPorts(OutputPort<?> outputPort) {
        if (outputPort.getRawComponent() == null
                || !outputPort.getRawComponent().path("components").isArray()) {
            return List.of();
        }
        return java.util.stream.StreamSupport.stream(
                        outputPort.getRawComponent().path("components").spliterator(), false)
                .filter(child -> "outputport".equals(child.path("kind").asText()))
                .map(child -> Parser.parseComponent(child, Specific.class))
                .filter(io.vavr.control.Either::isRight)
                .map(io.vavr.control.Either::get)
                .map(component -> (OutputPort<?>) component)
                .toList();
    }

    /**
     * Converts a list of Witboost OutputPorts to a list of Informatica OutputPorts, grouped by
     * technology. Each Witboost OutputPort becomes a DataAsset within the corresponding Informatica
     * Outpèerò
     *
     * @param witboostOutputPorts list of Witboost OutputPorts (each representing a table/view)
     * @return list of Informatica OutputPorts, one per technology, each containing multiple
     *     DataAssets
     */
    default List<DeliveryTarget> toOutputPortsGroupedByTechnology(
            List<? extends OutputPort<?>> witboostOutputPorts, DataAssetMapper dataAssetMapper) {

        if (witboostOutputPorts == null || witboostOutputPorts.isEmpty()) {
            return List.of();
        }

        // Group OutputPorts by technology
        Map<String, List<OutputPort<?>>> groupedByTechnology =
                witboostOutputPorts.stream()
                        .collect(
                                Collectors.groupingBy(
                                        op ->
                                                op.getTechnology() != null
                                                        ? op.getTechnology().orElse("unknown")
                                                        : "unknown"));

        List<DeliveryTarget> result = new ArrayList<>();

        for (Map.Entry<String, List<OutputPort<?>>> entry : groupedByTechnology.entrySet()) {
            String technology = entry.getKey();
            List<OutputPort<?>> outputPortsForTechnology = entry.getValue();

            // Use the first OutputPort as the base for the Informatica OutputPort
            OutputPort<?> firstOutputPort = outputPortsForTechnology.get(0);
            DeliveryTarget informaticaDeliveryTarget = toOutputPort(firstOutputPort);

            // Build aggregated name and description if multiple OutputPorts
            if (outputPortsForTechnology.size() > 1) {
                String aggregatedName = technology + " Output Port";
                String aggregatedDescription =
                        "Output port for "
                                + technology
                                + " containing "
                                + outputPortsForTechnology.size()
                                + " data assets";
                informaticaDeliveryTarget.getBaseCharacteristics().setPortName(aggregatedName);
                informaticaDeliveryTarget
                        .getBaseCharacteristics()
                        .setDescription(aggregatedDescription);
            }

            // Convert each Witboost OutputPort to a DataAsset
            List<DataAsset> dataAssets =
                    outputPortsForTechnology.stream().map(dataAssetMapper::toDataAsset).toList();

            informaticaDeliveryTarget.setDataAssets(dataAssets);
            result.add(informaticaDeliveryTarget);
        }

        return result;
    }

    @Named("extractTechnology")
    default String extractTechnology(OutputPort<?> outputPort) {
        Optional<String> technology = outputPort.getTechnology();
        return technology != null ? technology.orElse(null) : null;
    }

    @Named("extractCreationDate")
    default String extractCreationDate(OutputPort<?> outputPort) {
        Optional<String> creationDate = outputPort.getCreationDate();
        return creationDate != null ? creationDate.orElse(null) : null;
    }

    @AfterMapping
    default void mapJsonFields(@MappingTarget DeliveryTarget target, OutputPort<?> source) {
        JsonNode specific = source.getSpecific();
        mapBaseCharacteristicsFromSpecific(specific, target.getBaseCharacteristics());
    }

    private void mapBaseCharacteristicsFromSpecific(
            JsonNode specific, DeliveryTarget.BaseCharacteristics characteristics) {
        if (specific == null) {
            return;
        }

        setBooleanIfPresent(specific, "isPii", characteristics::setIsPii);
        setTextIfPresent(specific, "modificationDate", characteristics::setModificationDate);
        setTextIfPresent(specific, "suspensionDate", characteristics::setSuspensionDate);
        setTextIfPresent(specific, "deprecationDate", characteristics::setDeprecationDate);
        setTextIfPresent(specific, "qualityExpectations", characteristics::setQualityExpectations);
        setTextIfPresent(
                specific, "securityConsiderations", characteristics::setSecurityConsiderations);
        setTextIfPresent(
                specific, "technicalSpecifications", characteristics::setTechnicalSpecifications);
    }

    private void setTextIfPresent(JsonNode jsonData, String fieldName, Consumer<String> setter) {
        setTextIfPresent(jsonData, fieldName, setter, null);
    }

    private void setTextIfPresent(
            JsonNode jsonData, String fieldName, Consumer<String> setter, String defaultValue) {
        if (jsonData != null && jsonData.has(fieldName)) {
            JsonNode node = jsonData.get(fieldName);
            if (!node.isNull()) {
                setter.accept(node.asText());
                return;
            }
        }
        if (defaultValue != null) {
            setter.accept(defaultValue);
        }
    }

    private void setBooleanIfPresent(
            JsonNode jsonData, String fieldName, Consumer<Boolean> setter) {
        if (jsonData != null && jsonData.has(fieldName)) {
            JsonNode node = jsonData.get(fieldName);
            if (!node.isNull()) {
                setter.accept(node.asBoolean());
            }
        }
    }
}

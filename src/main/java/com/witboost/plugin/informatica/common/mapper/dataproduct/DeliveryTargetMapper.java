package com.witboost.plugin.informatica.common.mapper.dataproduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.OutputPort;
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

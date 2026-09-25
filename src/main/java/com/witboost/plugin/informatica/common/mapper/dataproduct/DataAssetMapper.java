package com.witboost.plugin.informatica.common.mapper.dataproduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.witboost.Column;
import com.witboost.plugin.informatica.common.model.witboost.OutputPort;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import org.mapstruct.*;

/**
 * Mapper interface for converting Witboost OutputPort to Informatica DataAsset.
 *
 * <p>Each Witboost OutputPort (representing a table/view) is converted to a single DataAsset
 * containing system info, entity info, and attribute info derived from the dataContract schema.
 */
@Mapper(componentModel = "spring")
public interface DataAssetMapper {

    @Mapping(target = "componentId", source = "id")
    @Mapping(
            target = "systemInfo",
            source = "witboostOutputPort",
            qualifiedByName = "mapSystemInfo")
    @Mapping(
            target = "entityInfo",
            source = "witboostOutputPort",
            qualifiedByName = "mapEntityInfo")
    @Mapping(
            target = "attributes",
            source = "witboostOutputPort",
            qualifiedByName = "mapAttributes")
    DataAsset toDataAsset(OutputPort<?> witboostOutputPort);

    @Named("mapSystemInfo")
    default DataAsset.SystemInfo mapSystemInfo(OutputPort<?> outputPort) {
        JsonNode specific = outputPort.getSpecific();
        DataAsset.SystemInfo.SystemInfoBuilder builder = DataAsset.SystemInfo.builder();

        if (specific != null) {
            setIfPresent(specific, "systemName", builder::systemName);
            setIfPresent(specific, "serverName", builder::serverName);
            setIfPresent(specific, "databaseName", builder::databaseName);
            setIfPresent(specific, "schemaName", builder::schemaName);
        }

        return builder.build();
    }

    @Named("mapEntityInfo")
    default DataAsset.EntityInfo mapEntityInfo(OutputPort<?> outputPort) {
        DataAsset.EntityInfo.EntityInfoBuilder builder = DataAsset.EntityInfo.builder();

        // Map from OutputPort base fields
        builder.entityDescription(outputPort.getDescription());

        // Map from specific fields
        JsonNode specific = outputPort.getSpecific();
        if (specific != null) {
            setIfPresent(specific, "entityName", builder::entityName);
            setIfPresent(specific, "entityType", builder::entityType);
            setIfPresent(
                    specific,
                    "feedingFrequency",
                    v ->
                            builder.feedingFrequency(
                                    VocabularyNormalizer.toCanonical(
                                            v,
                                            "Giornaliero",
                                            "Settimanale",
                                            "Decadale",
                                            "Mensile",
                                            "Trimestrale",
                                            "Bimestrale",
                                            "Semestrale",
                                            "Annuale",
                                            "Quadrimestrale",
                                            "Live",
                                            "On-demand")));
            setIfPresent(
                    specific,
                    "feedingType",
                    v -> builder.feedingType(VocabularyNormalizer.toCanonical(v, "PUSH", "PULL")));
            setIfPresent(
                    specific,
                    "loadingMode",
                    v ->
                            builder.loadingMode(
                                    VocabularyNormalizer.toCanonical(
                                            v, "Merge", "Upsert", "Incremental", "Full")));
            setBooleanIfPresent(specific, "manualProcess", builder::manualProcess);
            setBooleanIfPresent(specific, "historicized", builder::historicized);
            setIfPresent(specific, "retentionInfo", builder::retentionInfo);
            setBooleanIfPresent(specific, "sensitiveData", builder::sensitiveData);
            setSlaIfPresent(specific, builder);
            setIfPresent(specific, "semanticLinks", builder::semanticLinks);
        }

        // Set defaults for mandatory boolean fields if not set
        DataAsset.EntityInfo entityInfo = builder.build();
        if (entityInfo.getManualProcess() == null) {
            entityInfo.setManualProcess(false);
        }
        if (entityInfo.getHistoricized() == null) {
            entityInfo.setHistoricized(false);
        }
        if (entityInfo.getSensitiveData() == null) {
            entityInfo.setSensitiveData(false);
        }

        return entityInfo;
    }

    @Named("mapAttributes")
    default List<DataAsset.AttributeInfo> mapAttributes(OutputPort<?> outputPort) {
        if (outputPort.getDataContract() == null
                || outputPort.getDataContract().getSchema() == null) {
            return List.of();
        }

        AtomicInteger position = new AtomicInteger(1);
        return outputPort.getDataContract().getSchema().stream()
                .map(column -> mapColumn(column, position.getAndIncrement()))
                .toList();
    }

    default DataAsset.AttributeInfo mapColumn(Column column, int position) {
        DataAsset.AttributeInfo.AttributeInfoBuilder builder = DataAsset.AttributeInfo.builder();

        builder.attributeName(column.getName());
        builder.attributeDescription(column.getDescription());
        builder.position(position);

        // Map dataType to attributeDomain
        builder.attributeDomain(column.getDataType());

        // Map dataLength to length
        if (column.getDataLength() != null) {
            try {
                builder.length(Integer.parseInt(column.getDataLength()));
            } catch (NumberFormatException e) {
                builder.length(0);
            }
        } else {
            builder.length(0);
        }

        // Map additional fields from rawColumn if available
        JsonNode rawColumn = column.getRawColumn();
        if (rawColumn != null) {
            setBooleanIfPresent(rawColumn, "mandatory", builder::mandatory);
            setBooleanIfPresent(rawColumn, "primaryKey", builder::primaryKey);
            setIfPresent(rawColumn, "semanticLinks", builder::semanticLinks);
            setIfPresent(rawColumn, "qualityControlLinks", builder::qualityControlLinks);
            setBooleanIfPresent(rawColumn, "sensitive", builder::sensitiveData);
        }

        // Set defaults for mandatory fields if not set
        DataAsset.AttributeInfo attributeInfo = builder.build();
        if (attributeInfo.getMandatory() == null) {
            attributeInfo.setMandatory(false);
        }
        if (attributeInfo.getPrimaryKey() == null) {
            attributeInfo.setPrimaryKey(false);
        }
        if (attributeInfo.getSensitiveData() == null) {
            attributeInfo.setSensitiveData(false);
        }

        return attributeInfo;
    }

    private void setIfPresent(JsonNode jsonData, String fieldName, Consumer<String> setter) {
        if (jsonData != null && jsonData.has(fieldName)) {
            JsonNode node = jsonData.get(fieldName);
            if (!node.isNull()) {
                setter.accept(node.asText());
            }
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

    private void setSlaIfPresent(
            JsonNode jsonData, DataAsset.EntityInfo.EntityInfoBuilder builder) {
        if (jsonData != null && jsonData.has("sla")) {
            JsonNode slaNode = jsonData.get("sla");
            if (!slaNode.isNull() && slaNode.isObject()) {
                DataAsset.SLA.SLABuilder slaBuilder = DataAsset.SLA.builder();
                if (slaNode.has("refreshRate") && !slaNode.get("refreshRate").isNull()) {
                    slaBuilder.refreshRate(
                            VocabularyNormalizer.toCanonical(
                                    slaNode.get("refreshRate").asText(),
                                    "Oneshot",
                                    "Giornaliero",
                                    "Settimanale",
                                    "Mensile",
                                    "Trimestrale",
                                    "Semestrale",
                                    "Annuale"));
                }
                if (slaNode.has("retentionRate") && !slaNode.get("retentionRate").isNull()) {
                    slaBuilder.retentionRate(slaNode.get("retentionRate").asText());
                }
                builder.sla(slaBuilder.build());
            }
        }
    }
}

package com.witboost.plugin.informatica.common.mapper.dataproduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.model.witboost.OutputPort;
import com.witboost.plugin.informatica.common.model.witboost.Specific;
import com.witboost.plugin.informatica.common.parser.DescriptorPathResolver;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceMappingProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper interface for converting between `DataProduct` and `DataContract` objects. This mapper
 * uses MapStruct to generate the implementation at compile time.
 *
 * <p>The DataContract hierarchy is:
 *
 * <pre>
 * DataContract
 * ├── BaseCharacteristics
 * ├── ReferenceContext
 * ├── AdditionalInformation
 * └── N OutputPort
 *     ├── BaseCharacteristics
 *     └── M DataAsset
 * </pre>
 *
 * <p>Configuration:
 *
 * <ul>
 *   <li>Component model: Spring (enables Spring dependency injection).
 *   <li>Uses: OutputPortMapper and DataAssetMapper for nested object mapping.
 * </ul>
 */
@Mapper(
        componentModel = "spring",
        uses = {DeliveryTargetMapper.class, DataAssetMapper.class})
public abstract class DataContractMapper {

    private MarketplaceMappingProperties mappingProperties = defaultMappingProperties();

    @Autowired(required = false)
    public void setMappingProperties(MarketplaceMappingProperties mappingProperties) {
        this.mappingProperties = mappingProperties;
    }

    @Mapping(target = "baseCharacteristics.identifier", source = "id")
    @Mapping(target = "baseCharacteristics.name", source = "name")
    @Mapping(
            target = "baseCharacteristics.fullyQualifiedName",
            source = "dataProduct",
            qualifiedByName = "extractFullyQualifiedName")
    @Mapping(target = "baseCharacteristics.description", source = "description")
    @Mapping(
            target = "baseCharacteristics.productOwnerName",
            source = "dataProductOwnerDisplayName")
    @Mapping(
            target = "baseCharacteristics.status",
            source = "dataProduct",
            qualifiedByName = "extractStatus")
    @Mapping(target = "additionalInformation.version", source = "version")
    @Mapping(
            target = "additionalInformation.sla",
            source = "dataProduct",
            qualifiedByName = "extractInformationSLA")
    @Mapping(target = "deliveryTargets", ignore = true) // Mapped in toDataContractWithOutputPorts
    public abstract DataContract toDataContract(DataProduct dataProduct);

    /**
     * Converts a DataProduct to a DataContract with full OutputPort mapping. This method provides
     * explicit control over the OutputPort and DataAsset mapping process.
     *
     * @param dataProduct the source DataProduct
     * @param deliveryTargetMapper the mapper for OutputPort conversion
     * @param dataAssetMapper the mapper for DataAsset conversion
     * @return the mapped DataContract with all OutputPorts and DataAssets
     */
    public DataContract toDataContractWithOutputPorts(
            DataProduct dataProduct,
            DeliveryTargetMapper deliveryTargetMapper,
            DataAssetMapper dataAssetMapper) {

        DataContract dataContract = toDataContract(dataProduct);

        // Extract and map OutputPorts from the DataProduct
        List<OutputPort<Specific>> witboostOutputPorts = dataProduct.extractOutputPorts();

        if (witboostOutputPorts != null && !witboostOutputPorts.isEmpty()) {
            List<OutputPort<?>> castedList = new java.util.ArrayList<>(witboostOutputPorts);
            List<DeliveryTarget> informaticaDeliveryTargets =
                    deliveryTargetMapper.toOutputPortsIndividually(castedList, dataAssetMapper);
            dataContract.setDeliveryTargets(informaticaDeliveryTargets);
            dataContract.setMarketplaceDeliveryTargets(
                    deliveryTargetMapper.toMarketplaceTargets(castedList, dataAssetMapper));
        }

        return dataContract;
    }

    @Named("extractInformationSLA")
    public String extractInformationSLA(DataProduct dataProduct) {
        Optional<String> informationSLA = dataProduct.getInformationSLA();
        String value = informationSLA != null ? informationSLA.orElse(null) : null;
        return value;
    }

    @Named("extractFullyQualifiedName")
    public String extractFullyQualifiedName(DataProduct dataProduct) {
        Optional<String> fqn = dataProduct.getFullyQualifiedName();
        return fqn != null ? fqn.orElse(null) : null;
    }

    @Named("extractStatus")
    public String extractStatus(DataProduct dataProduct) {
        Optional<String> status = dataProduct.getStatus();
        String value = status != null ? status.orElse(null) : null;
        return "Published".equalsIgnoreCase(value) ? "Published" : "Unpublished";
    }

    @AfterMapping
    public void mapJsonFields(@MappingTarget DataContract target, DataProduct source) {
        JsonNode jsonData = source.getSpecific();
        mapMarketplaceFields(target, source);
        if (target.getBaseCharacteristics() != null) {
            mapBaseCharacteristics(jsonData, target.getBaseCharacteristics());
        }
        if (target.getAdditionalInformation() != null) {
            mapAdditionalInformation(jsonData, target.getAdditionalInformation());
        }
    }

    private void mapMarketplaceFields(DataContract target, DataProduct source) {
        JsonNode root = descriptorRoot(source);
        List<String> categoryPath = new ArrayList<>();
        for (MarketplaceMappingProperties.CategoryLevel level : mappingProperties.getCategories()) {
            JsonNode value = DescriptorPathResolver.read(root, level.getDescriptorPath());
            categoryPath.add(value == null || value.isNull() ? null : value.asText());
        }
        target.setMarketplaceCategoryPath(categoryPath);
        mapLegacyReferenceContext(target.getReferenceContext(), categoryPath);

        target.getCustomAttributes().clear();
        mappingProperties
                .getCustomAttributes()
                .forEach(
                        (attributeId, mapping) -> {
                            JsonNode value =
                                    DescriptorPathResolver.read(root, mapping.getDescriptorPath());
                            if (value != null && !value.isNull()) {
                                target.getCustomAttributes()
                                        .put(attributeId, descriptorValue(value));
                            }
                        });
    }

    private JsonNode descriptorRoot(DataProduct source) {
        if (source.getRawDataProduct() != null) return source.getRawDataProduct();
        ObjectNode root = JsonNodeFactory.instance.objectNode();
        if (source.getSpecific() != null) root.set("specific", source.getSpecific());
        return root;
    }

    private Object descriptorValue(JsonNode value) {
        if (value.isTextual()) return value.textValue();
        if (value.isNumber()) return value.numberValue();
        if (value.isBoolean()) return value.booleanValue();
        return value;
    }

    private void mapLegacyReferenceContext(
            DataContract.ReferenceContext context, List<String> categoryPath) {
        if (context == null) return;
        if (!categoryPath.isEmpty()) context.setCompany(categoryPath.get(0));
        if (categoryPath.size() > 1) context.setDomain(categoryPath.get(1));
        if (categoryPath.size() > 2) context.setSubdomain(categoryPath.get(2));
    }

    private static MarketplaceMappingProperties defaultMappingProperties() {
        MarketplaceMappingProperties properties = new MarketplaceMappingProperties();
        properties.setCategories(
                List.of(
                        categoryLevel("specific.company"),
                        categoryLevel("specific.businessDomain"),
                        categoryLevel("specific.businessSubdomain")));
        return properties;
    }

    private static MarketplaceMappingProperties.CategoryLevel categoryLevel(String path) {
        MarketplaceMappingProperties.CategoryLevel level =
                new MarketplaceMappingProperties.CategoryLevel();
        level.setDescriptorPath(path);
        return level;
    }

    private void mapBaseCharacteristics(
            JsonNode jsonData, DataContract.BaseCharacteristics characteristics) {
        if (jsonData != null) {
            setTextIfPresent(jsonData, "technicalOwners", characteristics::setTechnicalOwnersNames);
            setTextIfPresent(
                    jsonData,
                    "certifiedUse",
                    v ->
                            characteristics.setCertifiedUse(
                                    VocabularyNormalizer.toCanonical(
                                            v, "Generic", "Analysis", "AI Model", "Regoletory")),
                    "Generic");
        } else {
            // Apply defaults even when jsonData is null
            characteristics.setCertifiedUse("Generic");
        }
    }

    private void mapAdditionalInformation(
            JsonNode jsonData, DataContract.AdditionalInformation info) {
        if (jsonData != null) {
            if (jsonData.has("dataProductType") && !jsonData.get("dataProductType").isNull()) {
                info.setDataProductType(
                        normalizeDataProductType(jsonData.get("dataProductType").asText()));
            }
            setTextIfPresent(
                    jsonData,
                    "memorizationType",
                    v ->
                            info.setMemorizationType(
                                    VocabularyNormalizer.toCanonical(
                                            v, "Si", "No", "In identification")),
                    "Si");
            setTextIfPresent(
                    jsonData,
                    "lifeCycleStatus",
                    v ->
                            info.setLifeCycleStatus(
                                    VocabularyNormalizer.toCanonical(
                                            v,
                                            "Draft",
                                            "Proposed",
                                            "In Development",
                                            "Active",
                                            "Retired",
                                            "Deprecated")));
            setTextIfPresent(
                    jsonData,
                    "sensitiveInfo",
                    v -> info.setSensitiveInfo(normalizeSensitiveInfo(v)));
            setTextIfPresent(jsonData, "creationDate", info::setCreationDate);
            setTextIfPresent(jsonData, "endDate", info::setEndDate);
            setTextIfPresent(jsonData, "releaseDate", info::setReleaseDate);
            setTextIfPresent(jsonData, "suspensionDate", info::setSuspensionDate);
            setTextIfPresent(jsonData, "deprecationDate", info::setDeprecationDate);
            setTextIfPresent(jsonData, "norms", info::setNorms);
            setTextIfPresent(
                    jsonData,
                    "confidentiality",
                    v ->
                            info.setConfidentiality(
                                    VocabularyNormalizer.toCanonical(
                                            v, "Public", "Private", "Reserved")),
                    "Private");
            setTextIfPresent(jsonData, "linkDocumentation", info::setLinkDocumentation);
            setTextIfPresent(jsonData, "linkObservabilityPort", info::setLinkObservabilityPort);
            setTextIfPresent(jsonData, "linkDataQualityPort", info::setLinkDataQualityPort);
            setTextIfPresent(jsonData, "metadataDetails", info::setMetadataDetails);
            setTextIfPresent(jsonData, "contacts", info::setContacts);
            setTextIfPresent(jsonData, "termsOfUse", info::setTermsOfUse);
            setTextIfPresent(jsonData, "outsourcer", info::setOutsourcer);
            setTextIfPresent(jsonData, "quality", info::setQuality);
        } else {
            info.setConfidentiality("Private");
            info.setMemorizationType("Si");
        }
    }

    /**
     * Normalizes the descriptor's {@code sensitiveInfo} (PII flag) to the canonical Informatica
     * value "Si"/"No" (no accent). Accepts common variants for backward compatibility — the
     * accented "Sì", boolean {@code true}/{@code false}, "yes"/"no" — so legitimate descriptors are
     * not rejected by the {@code ^(Si|Sì|No)$} constraint. Unrecognized values are returned
     * unchanged so validation can report them.
     */
    private String normalizeSensitiveInfo(String value) {
        if (value == null) return null;
        return switch (value.trim().toLowerCase(java.util.Locale.ROOT)) {
            case "sì", "si", "true", "yes", "y", "1" -> "Si";
            case "no", "false", "n", "0" -> "No";
            default -> value;
        };
    }

    private String normalizeDataProductType(String value) {
        if (value == null) return null;
        String normalized = value.toLowerCase().replace("-", "").replace(" ", "").replace("_", "");
        return switch (normalized) {
            case "sourcealigned" -> "Source-aligned";
            case "consumeraligned", "customeraligned" -> "Consumer-aligned";
            case "aggregate", "aggregated" -> "Aggregated";
            default -> value;
        };
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
}

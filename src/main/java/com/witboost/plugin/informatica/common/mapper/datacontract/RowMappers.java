package com.witboost.plugin.informatica.common.mapper.datacontract;

import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.common.utils.DateUtils;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for mapping domain objects to Excel row data.
 *
 * <p>This class provides static methods to convert DataContract, DeliveryTarget, and DataAsset
 * objects into String arrays suitable for populating Excel rows. Each method takes a header list to
 * determine the order and presence of columns.
 *
 * <p>The class uses a generic approach to eliminate code duplication, with a single core method
 * handling the iteration and delegation to specific column extractors.
 */
public class RowMappers {

    /**
     * Maps a DataContract object to a String array based on the provided header columns.
     *
     * @param header List of column names that define the order and structure of the output
     * @param dataContract The DataContract object to extract values from
     * @param operation The operation type (CREATE or UPDATE)
     * @return String array where each element corresponds to a header column value
     */
    public static String[] map(
            List<String> header,
            DataContract dataContract,
            Operation operation,
            Map<String, Object> extras) {
        return mapGeneric(
                header, dataContract, operation, extras, RowMappers::getDataContractValueForColumn);
    }

    /**
     * Maps a DeliveryTarget object to a String array based on the provided header columns.
     *
     * @param header List of column names that define the order and structure of the output
     * @param deliveryTarget The DeliveryTarget object to extract values from
     * @param operation The operation type (CREATE or UPDATE)
     * @return String array where each element corresponds to a header column value
     */
    public static String[] map(
            List<String> header,
            DeliveryTarget deliveryTarget,
            Operation operation,
            Map<String, Object> extras) {
        return mapGeneric(
                header,
                deliveryTarget,
                operation,
                extras,
                RowMappers::getDeliveryTargetValueForColumn);
    }

    /**
     * Maps a DataAsset object to a String array based on the provided header columns.
     *
     * @param header List of column names that define the order and structure of the output
     * @param dataAsset The DataAsset object to extract values from
     * @param operation The operation type (CREATE or UPDATE)
     * @return String array where each element corresponds to a header column value
     */
    public static String[] map(
            List<String> header,
            DataAsset dataAsset,
            Operation operation,
            Map<String, Object> extras) {
        return mapGeneric(
                header, dataAsset, operation, extras, RowMappers::getDataAssetValueForColumn);
    }

    /** Functional interface for column value extraction with operation context. */
    @FunctionalInterface
    private interface ColumnExtractor<T> {
        String extract(
                String columnName, T entity, Operation operation, Map<String, Object> extras);
    }

    /**
     * Maps a Technical data element to a String array based on the provided header columns.
     *
     * @param header List of column names that define the order and structure of the output
     * @param technicalDataElement The technical data element object to extract values from
     * @param extras Additional context parameters
     * @return String array where each element corresponds to a header column value
     */
    public static String[] map(
            List<String> header,
            TechnicalDataElement technicalDataElement,
            Map<String, Object> extras) {
        return mapGeneric(
                header,
                technicalDataElement,
                Operation.UPDATE,
                extras,
                RowMappers::getTechnicalElementValueForColumn);
    }

    /**
     * Generic mapping method that eliminates code duplication across different entity types.
     *
     * <p>This method handles the common logic of:
     *
     * <ul>
     *   <li>Validating inputs (null/empty checks)
     *   <li>Creating the output array
     *   <li>Iterating through headers
     *   <li>Delegating value extraction to specific extractors
     * </ul>
     *
     * @param header List of column names
     * @param entity The entity to extract values from
     * @param operation The operation type (CREATE or UPDATE)
     * @param extras Additional context parameters (parent names, reference IDs, etc.)
     * @param extractor Function that extracts values for specific columns from the entity
     * @param <T> The type of entity being mapped
     * @return String array with mapped values
     */
    private static <T> String[] mapGeneric(
            List<String> header,
            T entity,
            Operation operation,
            Map<String, Object> extras,
            ColumnExtractor<T> extractor) {

        if (header == null || header.isEmpty()) {
            return new String[0];
        }

        if (entity == null) {
            return new String[header.size()];
        }

        String[] row = new String[header.size()];

        for (int i = 0; i < header.size(); i++) {
            row[i] = extractor.extract(header.get(i), entity, operation, extras);
        }

        return row;
    }

    /**
     * Extracts the value for a specific column from a DataContract object.
     *
     * @param columnName The name of the column
     * @param dataContract The DataContract object
     * @param operation The operation type (CREATE or UPDATE)
     * @return The string value for the column, or null if not available
     */
    private static String getDataContractValueForColumn(
            String columnName,
            DataContract dataContract,
            Operation operation,
            Map<String, Object> extras) {
        return switch (columnName) {
            case "Reference ID" ->
                    getKeyForOperation(operation, extras, MappingContext.DATA_PRODUCT_REFERENCE_ID);

            case "Name" -> safeGetFromMap(extras, MappingContext.DATA_PRODUCT_NAME);

            case "Description" ->
                    safeGet(
                            dataContract.getBaseCharacteristics(),
                            DataContract.BaseCharacteristics::getDescription);

            case "Data deprecazione" ->
                    DateUtils.toIsoDate(
                            safeGet(
                                    dataContract.getAdditionalInformation(),
                                    DataContract.AdditionalInformation::getDeprecationDate));

            case "Data sospensione" ->
                    DateUtils.toIsoDate(
                            safeGet(
                                    dataContract.getAdditionalInformation(),
                                    DataContract.AdditionalInformation::getSuspensionDate));

            case "Lifecycle" -> null;

            case "Long Name" -> {
                // Prefer the fully qualified name; fall back to the plain name when absent
                DataContract.BaseCharacteristics bc = dataContract.getBaseCharacteristics();
                if (bc == null) {
                    yield null;
                }
                yield bc.getFullyQualifiedName() != null
                        ? bc.getFullyQualifiedName()
                        : bc.getName();
            }

            case "PII" ->
                    sensitiveInfoToPii(
                            safeGet(
                                    dataContract.getAdditionalInformation(),
                                    DataContract.AdditionalInformation::getSensitiveInfo));

            case "Versione" ->
                    safeGet(
                            dataContract.getAdditionalInformation(),
                            DataContract.AdditionalInformation::getVersion);

            case "Operation" -> operation.getValue();

            case "Parent: System" -> null; // Data product is root

            default -> null; // Ignore other columns
        };
    }

    /**
     * Safe getter that handles null parent objects.
     *
     * @param parent The parent object (may be null)
     * @param getter Function to extract value from parent
     * @param <T> Type of the parent object
     * @return The extracted value or null if parent is null
     */
    private static <T> String safeGet(T parent, java.util.function.Function<T, String> getter) {
        return parent != null ? getter.apply(parent) : null;
    }

    /**
     * Maps {@code sensitiveInfo} to the Data Catalog "PII" column, whose accepted values are
     * "Si"/"No" (no accent). Returns {@code null} when not set.
     */
    private static String sensitiveInfoToPii(String sensitiveInfo) {
        if (sensitiveInfo == null) {
            return null;
        }
        return "No".equalsIgnoreCase(sensitiveInfo.trim()) ? "No" : "Si";
    }

    /**
     * Safely extracts a String value from the extras Map.
     *
     * @param extras The extras Map (may be null)
     * @param key The key to retrieve
     * @return The String value associated with the key, or null if not found or extras is null
     */
    private static String safeGetFromMap(Map<String, Object> extras, String key) {
        if (extras == null) return null;
        Object value = extras.get(key);
        return value != null ? value.toString() : null;
    }

    /**
     * Retrieves the Reference ID based on the operation type.
     *
     * <p>For CREATE operations, returns empty string (Informatica will generate the ID). For UPDATE
     * operations, retrieves the Reference ID from the extras Map.
     *
     * @param operation The operation type (CREATE or UPDATE)
     * @param extras The extras Map containing the Reference ID key
     * @param key The key to use for retrieving from extras
     * @return Empty string for CREATE, the Reference ID for UPDATE, or empty string if not found
     */
    private static String getKeyForOperation(
            Operation operation, Map<String, Object> extras, String key) {
        if (operation == Operation.CREATE) {
            return "";
        }
        return safeGetFromMap(extras, key);
    }

    /**
     * Safe nested getter that handles null objects at any level of the chain.
     *
     * @param parent The parent object (may be null)
     * @param intermediateGetter Function to extract intermediate object from parent
     * @param finalGetter Function to extract final value from intermediate object
     * @param <T> Type of the parent object
     * @param <U> Type of the intermediate object
     * @return The extracted value or null if any object in the chain is null
     */
    private static <T, U> String safeGetNested(
            T parent,
            java.util.function.Function<T, U> intermediateGetter,
            java.util.function.Function<U, String> finalGetter) {
        if (parent == null) return null;
        U intermediate = intermediateGetter.apply(parent);
        if (intermediate == null) return null;
        return finalGetter.apply(intermediate);
    }

    /**
     * Extracts the value for a specific column from a DeliveryTarget object.
     *
     * @param columnName The name of the column
     * @param deliveryTarget The DeliveryTarget object
     * @param operation The operation type (CREATE or UPDATE)
     * @param extras Additional context parameters
     * @return The string value for the column, or null if not available
     */
    private static String getDeliveryTargetValueForColumn(
            String columnName,
            DeliveryTarget deliveryTarget,
            Operation operation,
            Map<String, Object> extras) {
        return switch (columnName) {
            case "Reference ID" ->
                    getKeyForOperation(
                            operation, extras, MappingContext.DELIVERY_TARGET_REFERENCE_ID);

            case "Name" -> safeGetFromMap(extras, MappingContext.DELIVERY_TARGET_NAME);

            case "Description" -> deliveryTarget.getBaseCharacteristics().getDescription();

            case "Data deprecazione" ->
                    DateUtils.toIsoDate(
                            deliveryTarget.getBaseCharacteristics().getDeprecationDate());

            case "Data sospensione" ->
                    DateUtils.toIsoDate(
                            deliveryTarget.getBaseCharacteristics().getSuspensionDate());

            case "Lifecycle" -> null;

            case "Long Name" -> null;

            case "PII" -> {
                Boolean isPii = deliveryTarget.getBaseCharacteristics().getIsPii();
                yield isPii == null ? null : (isPii ? "Si" : "No");
            }

            case "Quality aspettative" ->
                    deliveryTarget.getBaseCharacteristics().getQualityExpectations();

            case "Security consideration" ->
                    deliveryTarget.getBaseCharacteristics().getSecurityConsiderations();

            case "Technical specifications" ->
                    deliveryTarget.getBaseCharacteristics().getTechnicalSpecifications();

            case "Tecnologia porta" -> deliveryTarget.getBaseCharacteristics().getPortTechnology();

            case "Versione" -> deliveryTarget.getBaseCharacteristics().getVersion();

            case "Operation" -> operation.getValue();

            case "Parent: System" -> safeGetFromMap(extras, MappingContext.DATA_PRODUCT_NAME);

            default -> null; // Ignore other columns
        };
    }

    /**
     * Extracts the value for a specific column from a DataAsset object.
     *
     * @param columnName The name of the column
     * @param dataAsset The DataAsset object
     * @param operation The operation type (CREATE or UPDATE)
     * @param extras Additional context parameters
     * @return The string value for the column, or null if not available
     */
    private static String getDataAssetValueForColumn(
            String columnName,
            DataAsset dataAsset,
            Operation operation,
            Map<String, Object> extras) {
        return switch (columnName) {
            case "Reference ID" ->
                    getKeyForOperation(operation, extras, MappingContext.DATA_ASSET_REFERENCE_ID);

            case "Name" -> safeGetFromMap(extras, MappingContext.DATA_ASSET_NAME);

            case "Description", "Asset - Descrizione" ->
                    safeGet(dataAsset.getEntityInfo(), DataAsset.EntityInfo::getEntityDescription);

            case "Lifecycle" -> null;

            case "Operation" -> operation.getValue();

            case "Parent: AI System" -> null; // AI System parent - not applicable for DataAsset

            case "Parent: System" -> safeGetFromMap(extras, MappingContext.DELIVERY_TARGET_NAME);

            case "Stakeholder: Governance Owner" ->
                    null; // Stakeholder field - to be populated by Informatica

            case "Stakeholder: Governance Administrator" ->
                    null; // Stakeholder field - to be populated by Informatica

            case "Stakeholder: Governance Data Owner" ->
                    null; // Stakeholder field - to be populated by Informatica

            case "Stakeholder: Governance User [Custom]" ->
                    null; // Stakeholder field - to be populated by Informatica

            case "Stakeholder: CDGC - Super Admin [Custom]" ->
                    null; // Stakeholder field - to be populated by Informatica

            default -> null; // Unknown column - leave empty
        };
    }

    /**
     * Extracts the value for a specific column from a Technical Data Element object.
     *
     * @param columnName The name of the column
     * @param technicalDataElement The Technical Data Element object
     * @return The string value for the column, or null if not available
     */
    private static String getTechnicalElementValueForColumn(
            String columnName,
            TechnicalDataElement technicalDataElement,
            Operation operation,
            Map<String, Object> extras) {
        return switch (columnName) {
            case "Reference ID" -> technicalDataElement.getExternalIdentity();
            case "Name" -> technicalDataElement.getCoreName();
            case "Asset Type" -> technicalDataElement.getClassType();
            case "Reference" -> "false";
            case "Business Dataset" ->
                    (String)
                            Optional.ofNullable(extras.get(MappingContext.DATA_ASSET_NAME))
                                    .orElseThrow(
                                            () ->
                                                    new IllegalArgumentException(
                                                            String.format(
                                                                    "Key '%s' not found",
                                                                    MappingContext
                                                                            .DATA_ASSET_NAME)));
            case "Lifecycle" -> "Published";
            case "Operation" -> operation.getValue();
            case "Identity" -> technicalDataElement.getCoreIdentity();
            case "core_Origin" -> technicalDataElement.getExternalIdentity().split(":")[0];
            case "core_Location" -> technicalDataElement.getCoreLocation();
            case "Catalog Source Name" -> technicalDataElement.getCatalogSourceName();
            case "Catalog Source Type" -> technicalDataElement.getCatalogSourceType();
            case "HierarchicalPath" -> {
                // Example:
                // core.location:
                // Example catalog source path.
                // -->
                // HierarchicalPath:
                // Example catalog source path.
                if (technicalDataElement.getCoreLocation() == null
                        || technicalDataElement.getCatalogSourceName() == null) {
                    yield null;
                }
                String tmp = technicalDataElement.getCoreLocation().split("://")[1];
                yield technicalDataElement.getCatalogSourceName() + tmp.substring(tmp.indexOf("/"));
            }
            default -> null; // Unknown column - leave empty
        };
    }
}

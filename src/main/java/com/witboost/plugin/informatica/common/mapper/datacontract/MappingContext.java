package com.witboost.plugin.informatica.common.mapper.datacontract;

/**
 * Constants for mapping context keys used in the extras Map.
 *
 * <p>These keys are used to pass contextual information when mapping domain objects to Excel rows.
 * The extras Map allows passing dynamic values that depend on the parent context (e.g., parent Data
 * Product name, reference IDs from Informatica, etc.)
 *
 * <h2>Usage Example:</h2>
 *
 * <pre>{@code
 * Map<String, Object> extras = Map.of(
 *     MappingContext.DATA_PRODUCT_NAME, "My Data Product",
 *     MappingContext.REFERENCE_ID, "REF-12345"
 * );
 *
 * String[] row = RowMappers.map(headers, dataContract, Operation.CREATE, extras);
 * }</pre>
 *
 * @see RowMappers
 * @see Operation
 */
public final class MappingContext {

    /**
     * Key for the Data Product name. Used to populate "Parent: System" field in child entities
     * (DeliveryTarget, DataAsset).
     */
    public static final String DATA_PRODUCT_NAME = "DATA_PRODUCT_NAME";

    /**
     * Key for the Reference ID from Informatica. Used to populate "Reference ID" field when
     * updating existing assets.
     */
    public static final String DATA_PRODUCT_REFERENCE_ID = "DATA_PRODUCT_REFERENCE_ID";

    /**
     * Key for the Delivery Target (Output Port) name. Used to populate "Parent: System" field in
     * DataAsset entities.
     */
    public static final String DELIVERY_TARGET_NAME = "DELIVERY_TARGET_NAME";

    /**
     * Key for the parent system name. Used to link child entities to their parent system in
     * Informatica.
     */
    public static final String DATA_PRODUCT_DOMAIN = "DATA_PRODUCT_DOMAIN";

    public static final String DATA_PRODUCT_SUBDOMAIN = "DATA_PRODUCT_SUBDOMAIN";

    /**
     * Key for the Delivery Target Reference ID from Informatica. Used to populate "Reference ID"
     * field when updating existing Delivery Targets.
     */
    public static final String DELIVERY_TARGET_REFERENCE_ID = "DELIVERY_TARGET_REFERENCE_ID";

    /**
     * Key for the Data Asset Reference ID from Informatica. Used to populate "Reference ID" field
     * when updating existing Data Assets.
     */
    public static final String DATA_ASSET_REFERENCE_ID = "DATA_ASSET_REFERENCE_ID";

    /** Key for the Data Asset Name from Informatica. Used to populate "Name" field. */
    public static final String DATA_ASSET_NAME = "DATA_ASSET_NAME";

    // Private constructor to prevent instantiation
    private MappingContext() {
        throw new AssertionError("Utility class - do not instantiate");
    }
}

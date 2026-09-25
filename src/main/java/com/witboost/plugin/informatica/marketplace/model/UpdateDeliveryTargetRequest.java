package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for updating a delivery target in Informatica Marketplace.
 *
 * <p>This class represents the PATCH request to modify delivery target properties. The operation
 * and segment parameters determine what type of modification is performed.
 *
 * <p><strong>Example JSON for summary segment:</strong>
 *
 * <pre>
 * {
 *   "operation": "replace",
 *   "segment": "summary",
 *   "value": {
 *     "name": "Snowflake Data Exchange - US Sales",
 *     "description": "Snowflake Data Exchange Portal for NA Analytics references and enrichment data sources."
 *   }
 * }
 * </pre>
 *
 * <p><strong>Operations:</strong>
 *
 * <ul>
 *   <li>add - Add a value to a parameter
 *   <li>replace - Replace an existing value of a parameter
 *   <li>remove - Remove a value from a parameter
 * </ul>
 *
 * <p><strong>Segments:</strong>
 *
 * <ul>
 *   <li>summary - Modify name and description
 *   <li>deliveryTemplate - Modify deliveryTemplateId
 *   <li>deliveryFormat - Modify deliveryFormatId
 *   <li>deliveryMethod - Modify deliveryMethodId
 *   <li>selfAttributes - Modify externalId, status, isDefault, targetSystemReference,
 *       physicalLocation
 * </ul>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDeliveryTargetRequest {

    /** Type of change to make to the delivery target. Values: "add", "replace", "remove" */
    @JsonProperty("operation")
    private String operation;

    /**
     * Type of details that the API request will modify. Values: "summary", "deliveryTemplate",
     * "deliveryFormat", "deliveryMethod", "selfAttributes"
     */
    @JsonProperty("segment")
    private String segment;

    /**
     * The value object containing the actual data to update.
     *
     * <p>The structure depends on the segment being updated:
     *
     * <ul>
     *   <li>summary → SummaryValue object
     *   <li>deliveryTemplate → DeliveryTemplateValue object
     *   <li>deliveryFormat → DeliveryFormatValue object
     *   <li>deliveryMethod → DeliveryMethodValue object
     *   <li>selfAttributes → SelfAttributesValue object
     * </ul>
     */
    @JsonProperty("value")
    private Object value;

    /**
     * Value object for summary segment updates. Maps to the "value" field when segment="summary".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SummaryValue {
        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;
    }

    /**
     * Value object for deliveryTemplate segment updates. Maps to the "value" field when
     * segment="deliveryTemplate".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryTemplateValue {
        @JsonProperty("deliveryTemplateId")
        private String deliveryTemplateId;
    }

    /**
     * Value object for deliveryFormat segment updates. Maps to the "value" field when
     * segment="deliveryFormat".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryFormatValue {
        @JsonProperty("deliveryFormatId")
        private String deliveryFormatId;
    }

    /**
     * Value object for deliveryMethod segment updates. Maps to the "value" field when
     * segment="deliveryMethod".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryMethodValue {
        @JsonProperty("deliveryMethodId")
        private String deliveryMethodId;
    }

    /**
     * Value object for selfAttributes segment updates. Maps to the "value" field when
     * segment="selfAttributes".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SelfAttributesValue {
        @JsonProperty("externalId")
        private String externalId;

        @JsonProperty("status")
        private String status;

        @JsonProperty("isDefault")
        private Boolean isDefault;

        @JsonProperty("targetSystemReference")
        private String targetSystemReference;

        @JsonProperty("physicalLocation")
        private String physicalLocation;
    }

    // Utility methods for common operations

    /**
     * Creates a request to update summary information (name, description).
     *
     * <p>Generates JSON:
     *
     * <pre>
     * {
     *   "operation": "replace",
     *   "segment": "summary",
     *   "value": {
     *     "name": "...",
     *     "description": "..."
     *   }
     * }
     * </pre>
     */
    public static UpdateDeliveryTargetRequest updateSummary(String name, String description) {
        SummaryValue summaryValue =
                SummaryValue.builder().name(name).description(description).build();

        return UpdateDeliveryTargetRequest.builder()
                .operation("replace")
                .segment("summary")
                .value(summaryValue)
                .build();
    }

    /** Creates a request to change delivery template. */
    public static UpdateDeliveryTargetRequest changeDeliveryTemplate(String deliveryTemplateId) {
        DeliveryTemplateValue deliveryTemplateValue =
                DeliveryTemplateValue.builder().deliveryTemplateId(deliveryTemplateId).build();

        return UpdateDeliveryTargetRequest.builder()
                .operation("replace")
                .segment("deliveryTemplate")
                .value(deliveryTemplateValue)
                .build();
    }

    /** Creates a request to change delivery format. */
    public static UpdateDeliveryTargetRequest changeDeliveryFormat(String deliveryFormatId) {
        DeliveryFormatValue deliveryFormatValue =
                DeliveryFormatValue.builder().deliveryFormatId(deliveryFormatId).build();

        return UpdateDeliveryTargetRequest.builder()
                .operation("replace")
                .segment("deliveryFormat")
                .value(deliveryFormatValue)
                .build();
    }

    /** Creates a request to change delivery method. */
    public static UpdateDeliveryTargetRequest changeDeliveryMethod(String deliveryMethodId) {
        DeliveryMethodValue deliveryMethodValue =
                DeliveryMethodValue.builder().deliveryMethodId(deliveryMethodId).build();

        return UpdateDeliveryTargetRequest.builder()
                .operation("replace")
                .segment("deliveryMethod")
                .value(deliveryMethodValue)
                .build();
    }

    /**
     * Creates a request to update self attributes (status, isDefault, externalId,
     * targetSystemReference, physicalLocation).
     */
    public static UpdateDeliveryTargetRequest updateSelfAttributes(
            String externalId,
            String status,
            Boolean isDefault,
            String targetSystemReference,
            String physicalLocation) {
        SelfAttributesValue selfAttributesValue =
                SelfAttributesValue.builder()
                        .externalId(externalId)
                        .status(status)
                        .isDefault(isDefault)
                        .targetSystemReference(targetSystemReference)
                        .physicalLocation(physicalLocation)
                        .build();

        return UpdateDeliveryTargetRequest.builder()
                .operation("replace")
                .segment("selfAttributes")
                .value(selfAttributesValue)
                .build();
    }

    /** Creates a request to update status only. */
    public static UpdateDeliveryTargetRequest updateStatus(String status) {
        SelfAttributesValue selfAttributesValue =
                SelfAttributesValue.builder().status(status).build();

        return UpdateDeliveryTargetRequest.builder()
                .operation("replace")
                .segment("selfAttributes")
                .value(selfAttributesValue)
                .build();
    }
}

package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for updating a data collection in Informatica Marketplace.
 *
 * <p>This class represents the PATCH request to modify data collection properties. The operation
 * and segment parameters determine what type of modification is performed.
 *
 * <p><strong>Example JSON for summary segment:</strong>
 *
 * <pre>
 * {
 *   "operation": "replace",
 *   "segment": "summary",
 *   "value": {
 *     "name": "Aggregated Sales Data",
 *     "description": "Sales data description",
 *     "externalId": "DCL-1",
 *     "status": "PUBLISHED"
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
 *   <li>category - Modify categoryId
 *   <li>customAttributes - Modify custom attributes
 *   <li>summary - Modify name, description, externalId, status
 *   <li>stakeholdership - Modify stakeholderId and roleId
 *   <li>termsOfUse - Modify terms of use
 *   <li>usageContexts - Modify usage contexts
 * </ul>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDataCollectionRequest {

    /** Type of change to make to the data collection. Values: "add", "replace", "remove" */
    @JsonProperty("operation")
    private String operation;

    /**
     * Type of details that the API request will modify. Values: "category", "customAttributes",
     * "summary", "stakeholdership", "termsOfUse", "usageContexts"
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
     *   <li>category → CategoryValue object
     *   <li>customAttributes → CustomAttributesValue object (contains list of CustomAttribute)
     *   <li>stakeholdership → List&lt;Stakeholder&gt;
     *   <li>termsOfUse → String
     *   <li>usageContexts → List&lt;String&gt;
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

        @JsonProperty("externalId")
        private String externalId;

        @JsonProperty("status")
        private String status;
    }

    /**
     * Value object for category segment updates. Maps to the "value" field when segment="category".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CategoryValue {
        @JsonProperty("categoryId")
        private String categoryId;
    }

    /**
     * Wrapper object for custom attributes segment updates. Maps to the "value" field when
     * segment="customAttributes".
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomAttributesValue {
        @JsonProperty("customAttributes")
        private List<CustomAttribute> customAttributes;
    }

    /** Custom attribute for data collection. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomAttribute {
        /** System-generated unique identifier of the custom attribute. */
        @JsonProperty("id")
        private String id;

        /**
         * Value for the custom attribute. Can be String, Number, List, or other types depending on
         * the custom attribute datatype.
         */
        @JsonProperty("value")
        private Object value;
    }

    /** Stakeholder information for data collection. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Stakeholder {
        /** System-generated unique identifier of the user account or user group. */
        @JsonProperty("stakeholderId")
        private String stakeholderId;

        /** System-generated unique identifier of the user role assigned to the stakeholder. */
        @JsonProperty("roleId")
        private String roleId;
    }

    // Utility methods for common operations

    /**
     * Creates a request to update summary information (name, description, status).
     *
     * <p>Generates JSON:
     *
     * <pre>
     * {
     *   "operation": "replace",
     *   "segment": "summary",
     *   "value": {
     *     "name": "...",
     *     "description": "...",
     *     "status": "..."
     *   }
     * }
     * </pre>
     */
    public static UpdateDataCollectionRequest updateSummary(
            String name, String description, String status) {

        SummaryValue summaryValue =
                SummaryValue.builder().name(name).description(description).status(status).build();

        return UpdateDataCollectionRequest.builder()
                .operation("replace")
                .segment("summary")
                .value(summaryValue)
                .build();
    }

    /** Creates a request to change category. */
    public static UpdateDataCollectionRequest changeCategory(String categoryId) {
        CategoryValue categoryValue = CategoryValue.builder().categoryId(categoryId).build();

        return UpdateDataCollectionRequest.builder()
                .operation("replace")
                .segment("category")
                .value(categoryValue)
                .build();
    }

    /** Creates a request to add a stakeholder. */
    public static UpdateDataCollectionRequest addStakeholder(String stakeholderId, String roleId) {
        Stakeholder stakeholder =
                Stakeholder.builder().stakeholderId(stakeholderId).roleId(roleId).build();

        return UpdateDataCollectionRequest.builder()
                .operation("add")
                .segment("stakeholdership")
                .value(List.of(stakeholder))
                .build();
    }

    /** Creates a request to update status only. */
    public static UpdateDataCollectionRequest updateStatus(String status) {
        SummaryValue summaryValue = SummaryValue.builder().status(status).build();

        return UpdateDataCollectionRequest.builder()
                .operation("replace")
                .segment("summary")
                .value(summaryValue)
                .build();
    }

    /**
     * Creates a request to update custom attributes.
     *
     * <p>Generates JSON:
     *
     * <pre>
     * {
     *   "operation": "replace",
     *   "segment": "customAttributes",
     *   "value": {
     *     "customAttributes": [
     *       {
     *         "id": "com.infa.odin.models.custom.ca_2101612002328922269",
     *         "value": "Public"
     *       },
     *       ...
     *     ]
     *   }
     * }
     * </pre>
     */
    public static UpdateDataCollectionRequest updateCustomAttributes(
            List<CustomAttribute> customAttributes) {
        CustomAttributesValue customAttributesValue =
                CustomAttributesValue.builder().customAttributes(customAttributes).build();

        return UpdateDataCollectionRequest.builder()
                .operation("replace")
                .segment("customAttributes")
                .value(customAttributesValue)
                .build();
    }

    /** Creates a request to update terms of use. */
    public static UpdateDataCollectionRequest updateTermsOfUse(String termsOfUse) {
        return UpdateDataCollectionRequest.builder()
                .operation("replace")
                .segment("termsOfUse")
                .value(termsOfUse)
                .build();
    }

    /** Creates a request to update usage contexts. */
    public static UpdateDataCollectionRequest updateUsageContexts(List<String> usageContexts) {
        return UpdateDataCollectionRequest.builder()
                .operation("replace")
                .segment("usageContexts")
                .value(usageContexts)
                .build();
    }
}

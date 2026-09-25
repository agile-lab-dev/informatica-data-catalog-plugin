package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for creating a data collection in Informatica Marketplace.
 *
 * <p>This class represents the POST request to create a new data collection.
 *
 * <p><strong>Required fields:</strong>
 *
 * <ul>
 *   <li>name - Unique name for the data collection (case-insensitive)
 *   <li>description - Description of the data collection
 *   <li>categoryId - System-generated ID of the category
 *   <li>status - PUBLISHED or UNPUBLISHED
 * </ul>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDataCollectionRequest {

    /**
     * Reference identifier for the data collection. If not specified, Data Marketplace
     * automatically assigns a unique value. Must be unique and not use the prefix configured in
     * Metadata Command Center.
     */
    @JsonProperty("externalId")
    private String externalId;

    /** Name for the data collection. Must be unique (case-insensitive). Required. */
    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String name;

    /** Description for the data collection. Required. */
    @NotBlank(message = "description is required")
    @JsonProperty("description")
    private String description;

    /** System-generated unique identifier of the category. Required. */
    @NotBlank(message = "categoryId is required")
    @JsonProperty("categoryId")
    private String categoryId;

    /**
     * Status for the data collection. Values: "PUBLISHED" (discoverable), "UNPUBLISHED" (not
     * discoverable). Required.
     */
    @NotBlank(message = "status is required")
    @JsonProperty("status")
    private String status;

    /** System-generated unique identifier of the usage type. Optional. */
    @JsonProperty("usageContextId")
    private String usageContextId;

    /** Custom attribute values for the data collection. Optional. */
    @JsonProperty("customAttributes")
    private List<CustomAttribute> customAttributes;

    /** Stakeholders for the data collection. Optional. */
    @JsonProperty("stakeholdership")
    private List<Stakeholder> stakeholdership;

    /** Terms of use identifier. Optional. */
    @JsonProperty("termsOfUse")
    private String termsOfUse;

    /** Custom attribute for data collection. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomAttribute {
        /** System-generated unique identifier of the custom attribute. */
        @NotBlank(message = "customAttribute.id is required")
        @JsonProperty("id")
        private String id;

        /** Value for the custom attribute. */
        @NotBlank(message = "customAttribute.value is required")
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

        /**
         * System-generated unique identifier of the user role assigned to the stakeholder. Required
         * if stakeholderId is specified.
         */
        @JsonProperty("roleId")
        private String roleId;
    }
}

package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for creating a delivery target in Informatica Marketplace.
 *
 * <p>This class represents the POST request to create a new delivery target.
 *
 * <p><strong>Required fields:</strong>
 *
 * <ul>
 *   <li>name - Unique name for the delivery target (case-insensitive)
 *   <li>description - Description of the delivery target
 *   <li>status - ACTIVE or INACTIVE
 *   <li>deliveryTemplateId - System-generated ID of the delivery template
 *   <li>dataCollectionId - System-generated ID of the data collection
 * </ul>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDeliveryTargetRequest {

    /**
     * Reference identifier for the delivery target. If not specified, Data Marketplace
     * automatically assigns a unique value. Must be unique and not use the prefix configured in
     * Metadata Command Center. Optional.
     */
    @JsonProperty("externalId")
    private String externalId;

    /** Name for the delivery target. Must be unique (case-insensitive). Required. */
    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String name;

    /** Description for the delivery target. Required. */
    @NotBlank(message = "description is required")
    @JsonProperty("description")
    private String description;

    /**
     * Status for the delivery target. Values: "ACTIVE" (available), "INACTIVE" (unavailable).
     * Required.
     */
    @NotBlank(message = "status is required")
    @JsonProperty("status")
    private String status;

    /**
     * Configure the delivery target as the default delivery option for a data collection. Values:
     * true or false. Optional. Default is false.
     */
    @JsonProperty("isDefault")
    private Boolean isDefault;

    /** Target system or resource reference where the data is obtained. Optional. */
    @JsonProperty("targetSystemReference")
    private String targetSystemReference;

    /** Location where the data is delivered to a Data User. Optional. */
    @JsonProperty("physicalLocation")
    private String physicalLocation;

    /** System-generated unique identifier of the delivery template. Required. */
    @NotBlank(message = "deliveryTemplateId is required")
    @JsonProperty("deliveryTemplateId")
    private String deliveryTemplateId;

    /**
     * System-generated unique identifier of the delivery method used in the delivery template.
     * Optional. By default, the API uses the first delivery method configured for the template.
     */
    @JsonProperty("deliveryMethodId")
    private String deliveryMethodId;

    /**
     * System-generated unique identifier of the delivery format used in the delivery template.
     * Optional. By default, the API uses the first delivery format configured for the template.
     */
    @JsonProperty("deliveryFormatId")
    private String deliveryFormatId;

    /**
     * System-generated unique identifier of the data collection for which to create the delivery
     * target. Required.
     */
    @NotBlank(message = "dataCollectionId is required")
    @JsonProperty("dataCollectionId")
    private String dataCollectionId;
}

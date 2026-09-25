package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response body for creating a delivery target in Informatica Marketplace.
 *
 * <p>This response is returned when a delivery target is successfully created (201 Created).
 *
 * <p>The response contains the system-generated unique identifier and details of the created
 * delivery target.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDeliveryTargetResponse {

    /** System-generated unique identifier of the created delivery target. */
    @JsonProperty("id")
    private String id;

    /** External reference identifier of the created delivery target. */
    @JsonProperty("externalId")
    private String externalId;

    /** Name of the created delivery target. */
    @JsonProperty("name")
    private String name;

    /** Description of the created delivery target. */
    @JsonProperty("description")
    private String description;

    /**
     * Status of the delivery target. Indicates whether the delivery target is available for use.
     * Values: "ACTIVE", "INACTIVE".
     */
    @JsonProperty("status")
    private String status;

    /** Indicates whether the delivery target is the default option. */
    @JsonProperty("isDefault")
    private Boolean isDefault;
}

package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response body for updating a data collection in Informatica Marketplace.
 *
 * <p>This response is returned when the update operation returns <strong>202 Accepted</strong>,
 * indicating that a background job has been created to apply the changes (typically for category or
 * stakeholder modifications).
 *
 * <p>For successful immediate updates (204 No Content), there is no response body.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDataCollectionResponse {

    /**
     * System-generated identifier of the job that enables you to track the progress of the job in
     * Metadata Command Center.
     *
     * <p>For more information about jobs in Metadata Command Center, see the Administration help in
     * Metadata Command Center.
     */
    @JsonProperty("trackerJobId")
    private String trackerJobId;

    /**
     * System-generated identifier of the internal job that applies your changes to Data
     * Marketplace.
     */
    @JsonProperty("propagationJobId")
    private String propagationJobId;
}

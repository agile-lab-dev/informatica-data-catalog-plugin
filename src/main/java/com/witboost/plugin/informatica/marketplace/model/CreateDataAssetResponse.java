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
 * Response body for creating data assets in Informatica Marketplace.
 *
 * <p>This response is returned when data assets are successfully created (200 OK).
 *
 * <p>The response contains the list of created data asset details including ID, name, description,
 * status, and asset groups.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDataAssetResponse {

    /** Processing time of the API call in milliseconds. */
    @JsonProperty("processingTime")
    private Long processingTime;

    /** List of created data asset objects. */
    @JsonProperty("objects")
    private List<DataAssetObject> objects;

    /** Errors encountered during processing (null if no errors). */
    @JsonProperty("errors")
    private String errors;

    /** Represents a single data asset object within the response. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataAssetObject {
        /** Index of the object in the response array. */
        @JsonProperty("index")
        private Integer index;

        /** System-generated unique identifier of the data asset. */
        @JsonProperty("id")
        private String id;

        /** Reference ID of the data asset. */
        @JsonProperty("refId")
        private String refId;

        /** Name of the data asset. */
        @JsonProperty("name")
        private String name;
    }
}

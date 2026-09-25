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
 * Request body for creating a data asset in Informatica Marketplace.
 *
 * <p>This class represents the POST request to update a data asset.
 *
 * <p><strong>Required fields:</strong>
 *
 * <ul>
 *   <li>name - ID for the data asset
 *   <li>name - Name for the data asset
 *   <li>description - Description for the data asset
 *   <li>source - Source system from which the data is supplied to Data Marketplace
 *   <li>type - Type for the data asset
 * </ul>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDataAssetRequest {

    /** ID for the data asset. Required. */
    @NotBlank(message = "id is required")
    @JsonProperty("id")
    private String id;

    /** Name for the data asset. Required. */
    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String name;

    /**
     * Reference identifier for the data asset. If not specified, Data Marketplace automatically
     * assigns a unique value. Must be unique and not use the prefix configured in Metadata Command
     * Center. Optional.
     */
    @JsonProperty("refId")
    private String refId;

    /** Description for the data asset. Required. */
    @NotBlank(message = "description is required")
    @JsonProperty("description")
    private String description;

    /** Source system from which the data is supplied to Data Marketplace. Required. */
    @NotBlank(message = "source is required")
    @JsonProperty("source")
    private String source;

    /** Source application from which the description of the data asset is taken. Optional. */
    @JsonProperty("descriptiveSource")
    private String descriptiveSource;

    /** Type for the data asset. Required. */
    @NotBlank(message = "type is required")
    @JsonProperty("type")
    private String type;

    /**
     * Uniform resource identifier of the location in the data source where the data asset is
     * stored. Optional.
     */
    @JsonProperty("refLink")
    private String refLink;

    /** Location of the data asset in the data source. Optional. */
    @JsonProperty("assetLocation")
    private String assetLocation;

    /** Description for the location in the data source where the data asset is stored. Optional. */
    @JsonProperty("assetLocationDescription")
    private String assetLocationDescription;

    /** Name of the data asset as it appears in the data source. Optional. */
    @JsonProperty("technicalAssetName")
    private String technicalAssetName;

    /**
     * Status for the data asset. Indicates whether the data asset is available to be added to data
     * collections. Values: "ENABLED" (available), "DISABLED" (unavailable). Optional. Default value
     * is ENABLED.
     */
    @JsonProperty("status")
    private String status;
}

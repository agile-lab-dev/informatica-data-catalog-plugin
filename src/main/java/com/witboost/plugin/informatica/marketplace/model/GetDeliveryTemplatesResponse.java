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
 * Response body for retrieving delivery templates from Informatica Marketplace.
 *
 * <p>This response is returned when delivery templates are successfully retrieved (200 OK).
 *
 * <p>The response contains a list of delivery templates and pagination information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDeliveryTemplatesResponse {

    /** Processing time of the request in milliseconds. */
    @JsonProperty("processingTime")
    private Integer processingTime;

    /** Number of items skipped before the current page. */
    @JsonProperty("offset")
    private Integer offset;

    /** Maximum number of items per page. */
    @JsonProperty("limit")
    private Integer limit;

    /** Total number of delivery templates available. */
    @JsonProperty("totalCount")
    private Integer totalCount;

    /** List of delivery templates. */
    @JsonProperty("objects")
    private List<DeliveryTemplate> objects;

    /** Delivery template details. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryTemplate {
        /** System-generated unique identifier of the delivery template. */
        @JsonProperty("id")
        private String id;

        /** Reference identifier of the delivery template. */
        @JsonProperty("refId")
        private String refId;

        /** Name of the delivery template. */
        @JsonProperty("name")
        private String name;

        /** Description of the delivery template. */
        @JsonProperty("description")
        private String description;

        /** Color code for the delivery template. Example: "#10Be21". */
        @JsonProperty("color")
        private String color;

        /** Indicates whether this delivery template is the default. */
        @JsonProperty("isDefault")
        private Boolean isDefault;

        /** Status of the delivery template. Values: "ACTIVE", "INACTIVE". */
        @JsonProperty("status")
        private String status;

        /** Managed access setting for the delivery template. Values: "ENABLED", "DISABLED". */
        @JsonProperty("managedAccess")
        private String managedAccess;

        /** Delivery type of the template. Values: "MANUAL", "AUTOMATIC". */
        @JsonProperty("deliveryType")
        private String deliveryType;

        /** List of template owners. */
        @JsonProperty("templateOwners")
        private List<String> templateOwners;

        /** Target system or resource reference where the data is obtained. */
        @JsonProperty("targetSystemReference")
        private String targetSystemReference;

        /** Default physical location where the data is delivered. */
        @JsonProperty("defaultPhysicalLocation")
        private String defaultPhysicalLocation;

        /** List of delivery methods available in this template. */
        @JsonProperty("deliveryMethods")
        private List<DeliveryMethod> deliveryMethods;

        /** List of delivery formats available in this template. */
        @JsonProperty("deliveryFormats")
        private List<DeliveryFormat> deliveryFormats;

        /** User or system that created the delivery template. */
        @JsonProperty("createdBy")
        private String createdBy;

        /** Creation timestamp in ISO 8601 format. */
        @JsonProperty("createdOn")
        private String createdOn;

        /** User or system that last modified the delivery template. */
        @JsonProperty("modifiedBy")
        private String modifiedBy;

        /** Last modification timestamp in ISO 8601 format. */
        @JsonProperty("modifiedOn")
        private String modifiedOn;
    }

    /** Delivery method details. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryMethod {
        /** System-generated unique identifier of the delivery method. */
        @JsonProperty("id")
        private String id;

        /** Reference identifier of the delivery method. */
        @JsonProperty("refId")
        private String refId;

        /**
         * Name of the delivery method. Examples: "Hypercore Protocol", "DOWNLOAD", "API", "EMAIL",
         * "FTP", "S3".
         */
        @JsonProperty("name")
        private String name;

        /** Status of the delivery method. Values: "ACTIVE", "INACTIVE". */
        @JsonProperty("status")
        private String status;

        /** User or system that created the delivery method. */
        @JsonProperty("createdBy")
        private String createdBy;

        /** Creation timestamp in ISO 8601 format. */
        @JsonProperty("createdOn")
        private String createdOn;

        /** User or system that last modified the delivery method. */
        @JsonProperty("modifiedBy")
        private String modifiedBy;

        /** Last modification timestamp in ISO 8601 format. */
        @JsonProperty("modifiedOn")
        private String modifiedOn;
    }

    /** Delivery format details. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryFormat {
        /** System-generated unique identifier of the delivery format. */
        @JsonProperty("id")
        private String id;

        /** Reference identifier of the delivery format. */
        @JsonProperty("refId")
        private String refId;

        /**
         * Name of the delivery format. Examples: "XLSX Workbook", "CSV", "JSON", "PARQUET", "AVRO",
         * "XML".
         */
        @JsonProperty("name")
        private String name;

        /** Status of the delivery format. Values: "ACTIVE", "INACTIVE". */
        @JsonProperty("status")
        private String status;

        /** User or system that created the delivery format. */
        @JsonProperty("createdBy")
        private String createdBy;

        /** Creation timestamp in ISO 8601 format. */
        @JsonProperty("createdOn")
        private String createdOn;

        /** User or system that last modified the delivery format. */
        @JsonProperty("modifiedBy")
        private String modifiedBy;

        /** Last modification timestamp in ISO 8601 format. */
        @JsonProperty("modifiedOn")
        private String modifiedOn;
    }
}

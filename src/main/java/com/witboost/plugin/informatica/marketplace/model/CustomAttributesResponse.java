package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response body for retrieving custom attributes from Informatica Marketplace.
 *
 * <p>This response contains details about custom attributes configured for specific item types
 * (data collection, consumer access, or order) in Metadata Command Center.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomAttributesResponse {

    /** Processing time in milliseconds. */
    @JsonProperty("processingTime")
    private Long processingTime;

    /** Starting index for the paginated results. */
    @JsonProperty("offset")
    private Integer offset;

    /** Maximum number of results. */
    @JsonProperty("limit")
    private Integer limit;

    /** Number of custom attributes retrieved. */
    @JsonProperty("totalCount")
    private Integer totalCount;

    /**
     * Type of item for which the custom attribute details were retrieved. Values: -
     * com.infa.cdmp.marketplace.ConsumerAccess - com.infa.cdmp.marketplace.DataCollection -
     * com.infa.cdmp.marketplace.Order
     */
    @JsonProperty("classType")
    private String classType;

    /** List of custom attributes. */
    @JsonProperty("items")
    private List<CustomAttributeItem> items;

    /** Custom attribute item details. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomAttributeItem {
        /**
         * System generated unique identifier of the custom attribute in Metadata Command Center.
         */
        @JsonProperty("customAttributeId")
        private String customAttributeId;

        /** Name of the custom attribute as configured in Metadata Command Center. */
        @JsonProperty("name")
        private String name;

        /**
         * Status of the custom attribute as configured in Metadata Command Center. Only returns
         * custom attributes in PUBLISHED state (displayed in Data Marketplace).
         */
        @JsonProperty("status")
        private String status;

        /**
         * Indicates whether the custom attribute is mandatory. - true: A user must enter a value in
         * this attribute - false: A user doesn't require to enter a value in this attribute
         */
        @JsonProperty("mandatory")
        private Boolean mandatory;

        /**
         * Indicates whether you can use the custom attribute to search for a data collection. -
         * true: You can use the custom attribute to search - false: You can't use the custom
         * attribute to search
         */
        @JsonProperty("searchable")
        private Boolean searchable;

        /** Default value of the custom attribute as configured in Metadata Command Center. */
        @JsonProperty("defaultValues")
        private List<String> defaultValues;

        /** Data type information for the custom attribute. */
        @JsonProperty("datatype")
        private DataType datatype;
    }

    /** Data type information for custom attribute. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataType {
        /** Type of the custom attribute as configured in Metadata Command Center. */
        @JsonProperty("type")
        private String type;

        /**
         * Subtype of the custom attribute as configured in Metadata Command Center. The value
         * depends on the value configured for the type parameter.
         */
        @JsonProperty("properties")
        private Object properties;

        /**
         * The values that are acceptable inputs to the custom attribute. Note: This parameter is
         * displayed only if the custom attribute is of type DROPDOWN_SINGLE_SELECT or
         * DROPDOWN_MULTI_SELECT.
         */
        @JsonProperty("dropDownOptions")
        private List<DropDownOption> dropDownOptions;
    }

    /**
     * Dropdown option for custom attributes of type DROPDOWN_SINGLE_SELECT or
     * DROPDOWN_MULTI_SELECT.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DropDownOption {
        /** The value to be sent in API requests. */
        @JsonProperty("value")
        private String value;

        /** The label to be displayed in UI. */
        @JsonProperty("label")
        private String label;
    }

    /** Class type constants for requesting custom attributes. */
    public static class ClassType {
        public static final String CONSUMER_ACCESS = "com.infa.cdmp.marketplace.ConsumerAccess";
        public static final String DATA_COLLECTION = "com.infa.cdmp.marketplace.DataCollection";
        public static final String ORDER = "com.infa.cdmp.marketplace.Order";
    }
}

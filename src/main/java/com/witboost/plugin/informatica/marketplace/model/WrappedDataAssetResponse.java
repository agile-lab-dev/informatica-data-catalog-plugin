package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper response for API responses where the actual JSON response is wrapped inside an "output"
 * field as a string.
 *
 * <p>The wrapped endpoints of the Informatica API returns responses in the format:
 *
 * <pre>
 * {
 *   "output": "{\"id\":\"...\",\"name\":\"...\"}"
 * }
 * </pre>
 *
 * <p>This wrapper class captures that outer structure so the nested JSON string can be extracted
 * and parsed.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class WrappedDataAssetResponse {

    /** The output field containing the actual JSON response as a string. */
    @JsonProperty("output")
    private String output;
}

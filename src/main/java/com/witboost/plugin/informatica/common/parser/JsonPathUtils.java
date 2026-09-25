package com.witboost.plugin.informatica.common.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.jsonpath.JsonPath;
import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.datacatalog.common.exceptions.DataCatalogPluginValidationException;
import java.util.Collections;

public class JsonPathUtils {

    /**
     * Retrieves a String value from a {@link JsonNode} based on a JSONPath. In the case multiple
     * fields apply to the JSONPath, only the first match is returned
     *
     * @param node Node to extract the string value form
     * @param jsonPath JSONPath to access the field on the node
     * @return String value stored on the field pointed by the input JSONPath
     * @throws DataCatalogPluginValidationException if the JSONPath does not point to a valid field.
     */
    public static String getStringFromJsonPath(JsonNode node, String jsonPath) {
        // JsonPath is configured to always return an array
        ArrayNode result = JsonPath.read(node, jsonPath);

        if (result.isEmpty() || result.get(0).isNull()) {
            var errorMessage =
                    String.format(
                            "Attribute could not be extracted from the input. No results for path: '%s'",
                            jsonPath);
            throw new DataCatalogPluginValidationException(
                    errorMessage,
                    new FailedOperation(Collections.singletonList(new Problem(errorMessage))));
        } else return result.get(0).asText();
    }
}

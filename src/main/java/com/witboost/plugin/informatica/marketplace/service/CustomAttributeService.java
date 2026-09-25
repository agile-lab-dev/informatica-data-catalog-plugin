package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.marketplace.model.CustomAttributesResponse;
import com.witboost.plugin.informatica.marketplace.service.client.CustomAttributeApiClient;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for managing custom attributes in Informatica Marketplace.
 *
 * @see CustomAttributeApiClient for API communication
 * @see CustomAttributesResponse for response model
 */
@Service
@Slf4j
public class CustomAttributeService {

    private final CustomAttributeApiClient customAttributeApiClient;

    /**
     * Constructs a CustomAttributeService instance.
     *
     * @param customAttributeApiClient the API client for custom attributes communication
     */
    public CustomAttributeService(CustomAttributeApiClient customAttributeApiClient) {
        this.customAttributeApiClient = customAttributeApiClient;
    }

    /**
     * Retrieves all custom attributes indexed by name.
     *
     * <p>Fetches the list of available custom attributes from the Informatica Marketplace API and
     * returns them as a map where the key is the attribute name and the value is the attribute ID.
     *
     * @return a map of custom attributes indexed by name to ID, where each entry contains:
     *     <ul>
     *       <li>key: the name of the custom attribute (String)
     *       <li>value: the custom attribute ID (String)
     *     </ul>
     *
     * @throws ApiCallException if the API call to fetch custom attributes fails
     * @see CustomAttributesResponse.CustomAttributeItem
     */
    public Map<String, String> getDataCollectionCustomAttributesNameId() {
        var customAttributes =
                customAttributeApiClient.getAllCustomAttributes(
                        CustomAttributesResponse.ClassType.DATA_COLLECTION);
        return customAttributes.getItems().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                CustomAttributesResponse.CustomAttributeItem::getName,
                                CustomAttributesResponse.CustomAttributeItem
                                        ::getCustomAttributeId));
    }
}

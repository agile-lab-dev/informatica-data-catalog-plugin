package com.witboost.plugin.informatica.datacatalog.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogApiConfig;
import com.witboost.plugin.informatica.datacatalog.model.SyncCatalogSourceResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CatalogSourceApiClient extends BaseClient {

    private final DataCatalogApiConfig config;
    private final InformaticaApiClient informaticaApiClient;
    private final ObjectMapper objectMapper;

    public CatalogSourceApiClient(
            DataCatalogApiConfig config, InformaticaApiClient informaticaApiClient) {
        this.config = config;
        this.informaticaApiClient = informaticaApiClient;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Syncs a catalog source by triggering metadata extraction.
     *
     * @param uuid The UUID of the catalog source to sync
     * @return SyncCatalogSourceResponse containing job details and status
     * @throws ApiCallException if the API call fails or returns a non-200 status
     */
    public SyncCatalogSourceResponse syncCatalogSource(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            throw new ApiCallException("uuid cannot be null or empty");
        }

        try {
            URI uri = new URI(config.getExecCatalogSourceJobUrl() + "/" + uuid);
            String body = buildSyncRequestBody();

            log.debug("Syncing catalog source with UUID: {}", uuid);

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("IDS-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

            HttpResponse<String> httpResponse =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    httpResponse, HTTP_CREATED, "Catalog source sync", ApiCallException::new);

            String responseBody = httpResponse.body();
            log.debug("Received sync response with {} characters", responseBody.length());

            return objectMapper.readValue(responseBody, SyncCatalogSourceResponse.class);

        } catch (URISyntaxException e) {
            log.error("Invalid URI constructed for catalog source sync: {}", e.getMessage());
            throw new ApiCallException("Invalid URI: " + ExceptionFormatter.format(e));
        } catch (IOException e) {
            log.error("IO error during catalog source sync: {}", e.getMessage());
            throw new ApiCallException("IO error: " + ExceptionFormatter.format(e));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Catalog source sync interrupted: {}", e.getMessage());
            throw new ApiCallException("Request interrupted: " + ExceptionFormatter.format(e));
        } catch (Exception e) {
            log.error("Unexpected error during catalog source sync: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Builds the JSON request body for catalog source sync request.
     *
     * @return JSON string containing the sync request payload
     */
    private String buildSyncRequestBody() {
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("capabilityNames", Collections.singletonList("Metadata Extraction"));
            return objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            log.warn("Failed to serialize sync request body, using fallback: {}", e.getMessage());
            return "{\"capabilityNames\":[\"Metadata Extraction\"]}";
        }
    }
}

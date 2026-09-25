package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.datacatalog.service.client.DataCatalogAssetApiClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TechnicalElementService {

    private final DataCatalogAssetApiClient assetApiClient;

    public TechnicalElementService(DataCatalogAssetApiClient assetApiClient) {
        this.assetApiClient = assetApiClient;
    }

    /**
     * Searches for technical data elements within a specific catalog source, database, schema, and
     * entity.
     *
     * @param catalogSourceName the name of the catalog source to search within
     * @param dbName the name of the database containing the schema
     * @param schemaName the name of the schema containing the entity
     * @param entityName the name of the entity (table or view) to retrieve columns from
     * @return a list of {@link TechnicalDataElement} objects representing the columns of the entity
     * @throws ApiCallException if the List Asset API returns an empty result
     */
    public List<TechnicalDataElement> searchDataElements(
            String catalogSourceName, String dbName, String schemaName, String entityName) {
        log.debug(
                "Retrieving columns tech elements for catalog src {}, db {}, schema {}, entity: {}",
                catalogSourceName,
                dbName,
                schemaName,
                entityName);
        var res =
                assetApiClient.pollForResults(
                        buildDataElementsQuery(catalogSourceName, dbName, schemaName, entityName));
        if (res.getHits() == null || res.getHits().isEmpty()) {
            throw new ApiCallException(
                    "List Asset API returned empty result for entity: " + entityName);
        }
        return res.getHits().stream()
                .map(TechnicalElementMapper::mapHitToTechnicalDataElement)
                .toList();
    }

    /**
     * Lightweight existence check: returns true if the entity has at least one technical data
     * element in the catalog source. Fetches a single result (no pagination, no mapping) so it is
     * much faster than {@link #searchDataElements} and suitable for validation/pre-flight checks.
     */
    public boolean hasDataElements(
            String catalogSourceName, String dbName, String schemaName, String entityName) {
        log.debug(
                "Checking existence of tech elements for catalog src {}, db {}, schema {}, entity: {}",
                catalogSourceName,
                dbName,
                schemaName,
                entityName);
        String body = "{\"from\":0, \"size\":1}";
        var res =
                assetApiClient.queryAssets(
                        body,
                        buildDataElementsQuery(catalogSourceName, dbName, schemaName, entityName));
        return res != null && res.getHits() != null && !res.getHits().isEmpty();
    }

    private String buildDataElementsQuery(
            String catalogSourceName, String dbName, String schemaName, String entityName) {
        return String.format(
                "data elements related to technical dataset '%s' and in (source '%s') and in (database '%s') and in (schema '%s')",
                entityName, catalogSourceName, dbName, schemaName);
    }
}

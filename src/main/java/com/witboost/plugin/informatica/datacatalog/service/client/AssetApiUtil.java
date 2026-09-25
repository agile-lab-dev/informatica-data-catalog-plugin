package com.witboost.plugin.informatica.datacatalog.service.client;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Utility service for querying Informatica assets by name. Supports generic asset types (e.g.,
 * system, dataset, etc.).
 */
@Service
@Slf4j
public class AssetApiUtil {

    private final DataCatalogAssetApiClient assetApiClient;

    public AssetApiUtil(DataCatalogAssetApiClient assetApiClient) {
        this.assetApiClient = assetApiClient;
    }

    /**
     * Gets an asset by name from Informatica Data Catalog.
     *
     * <p>This method queries the Informatica catalog using the asset's exact name. It constructs a
     * query in the format "{assetType} '{name}'" and retrieves only the first matching result
     * (size=1). The response must contain exactly one hit.
     *
     * @param assetType The type of asset to search for (e.g., "system", "dataset")
     * @param name The exact name of the asset to search for (must not be null or blank)
     * @return AssetGetResponse containing the asset details if found, or empty response if not
     *     found
     * @throws ApiCallException if the query to Informatica fails or network issues occur
     * @throws IllegalArgumentException if assetType or name is null or blank
     * @throws IllegalStateException if response contains hits but not exactly one
     * @see AssetGetResponse
     * @see DataCatalogAssetApiClient#queryAssets(String, String)
     */
    public AssetGetResponse getAssetByName(String assetType, String name) {
        // Input validation
        if (assetType == null || assetType.isBlank()) {
            log.error("Asset type cannot be null or blank");
            throw new IllegalArgumentException("Asset type cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            log.error("Asset name cannot be null or blank");
            throw new IllegalArgumentException("Asset name cannot be null or blank");
        }

        try {
            log.debug("Querying {} by name: '{}'", assetType, name);

            // Build pagination body (fetch only first result)
            String body = "{\"from\":0, \"size\":1}";

            // Build query for exact asset name match
            String query = String.format("%s '%s'", assetType, name);

            log.trace("Executing query: {} with body: {}", query, body);
            AssetGetResponse response = assetApiClient.queryAssets(body, query);

            if (response.hasValidHits()) {
                validateSingleHit(response, assetType, name);
                log.debug(
                        "{} '{}' found with {} hit(s)", assetType, name, response.getHits().size());
            } else {
                log.debug("{} '{}' not found in catalog", assetType, name);
            }

            return response;

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while getting {} by name '{}': {}",
                    assetType,
                    name,
                    e.getMessage(),
                    e);
            throw e;
        } catch (IllegalStateException e) {
            log.error(
                    "Invalid response while getting {} by name '{}': {}",
                    assetType,
                    name,
                    e.getMessage(),
                    e);
            throw e;
        } catch (Exception e) {
            log.error(
                    "Unexpected error while getting {} by name '{}': {}",
                    assetType,
                    name,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Checks if an asset with the given name exists in Informatica.
     *
     * <p>This method queries the Informatica catalog for an asset with the exact name match. If
     * hits are present, the response must contain exactly one element.
     *
     * @param assetType The type of asset to check for (e.g., "system", "dataset")
     * @param name The name of the asset to check (must not be null or blank)
     * @return true if the asset exists, false otherwise
     * @throws ApiCallException if the query to Informatica fails
     * @throws IllegalArgumentException if assetType or name is null or blank
     * @throws IllegalStateException if response contains hits but not exactly one
     */
    public boolean isAssetExisting(String assetType, String name) {
        // Input validation
        if (assetType == null || assetType.isBlank()) {
            log.error("Asset type cannot be null or blank");
            throw new IllegalArgumentException("Asset type cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            log.error("Asset name cannot be null or blank");
            throw new IllegalArgumentException("Asset name cannot be null or blank");
        }

        try {
            log.debug("Checking if {} exists: {}", assetType, name);
            AssetGetResponse response = getAssetByName(assetType, name);

            // Check if response has valid hits
            if (!response.hasValidHits()) {
                log.debug("No hits found for {}: {}", assetType, name);
                return false;
            }

            // Get the first hit (should be the only one due to exact name match)
            var firstHit = response.getHits().get(0);

            // Validate external identity
            if (firstHit.getExternalIdentity() == null) {
                log.warn("{} found but has no external identity: {}", assetType, name);
                return false;
            }

            // Validate summary and core name
            if (firstHit.getSummary() == null) {
                log.warn("{} found but has no summary: {}", assetType, name);
                return false;
            }

            String coreName = firstHit.getSummary().getCoreName();
            if (coreName == null) {
                log.warn("{} found but core name is null: {}", assetType, name);
                return false;
            }

            // Verify exact name match (case-sensitive)
            boolean exists = coreName.equals(name);

            if (exists) {
                log.debug("{} exists with matching core name: {}", assetType, name);
            } else {
                log.debug(
                        "{} found but core name '{}' does not match requested name '{}'",
                        assetType,
                        coreName,
                        name);
            }

            return exists;

        } catch (ApiCallException e) {
            log.error("Failed to check if {} exists: {}", assetType, name, e);
            throw e;
        } catch (IllegalStateException e) {
            log.error("Invalid response while checking if {} exists: {}", assetType, name, e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while checking if {} exists: {}", assetType, name, e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves the UUID of a given catalog source.
     *
     * @param catalogSourceName name of the catalog source
     * @return UUID of the catalog source
     */
    public String getCatalogSourceUUID(String catalogSourceName) {
        // Example:
        // "00000000-0000-0000-0000-000000000001://00000000-0000-0000-0000-000000000001~core.Resource"
        var externalIdentity = getCatalogSourceExternalId(catalogSourceName);
        // UUID is "00000000-0000-0000-0000-000000000001"
        return externalIdentity.split(":")[0];
    }

    /**
     * Retrieves the external identifier of a given catalog source.
     *
     * @param catalogSourceName name of the catalog source
     * @return external Id of the catalog source
     */
    public String getCatalogSourceExternalId(String catalogSourceName) {
        log.debug("getCatalogSourceExternalId {}", catalogSourceName);
        var res = getAssetByName("catalog source", catalogSourceName);
        if (!res.hasValidHits()) {
            log.error("Catalog source '{}' not found in Data Catalog", catalogSourceName);
            throw new IllegalStateException(
                    String.format(
                            "Catalog source '%s' not found in Data Catalog", catalogSourceName));
        }
        return res.getHits().get(0).getExternalIdentity();
    }

    /**
     * Validates that the response contains exactly one hit.
     *
     * @param response The asset response to validate
     * @param assetType The type of asset being queried
     * @param name The name of the asset being queried
     * @throws IllegalStateException if hits list is not null and does not contain exactly one
     *     element
     */
    private void validateSingleHit(AssetGetResponse response, String assetType, String name) {
        if (response.getHits() != null && response.getHits().size() != 1) {
            log.error(
                    "Expected exactly one hit for {} '{}', but got {}",
                    assetType,
                    name,
                    response.getHits().size());
            throw new IllegalStateException(
                    String.format(
                            "Expected exactly one hit for %s '%s', but got %d",
                            assetType, name, response.getHits().size()));
        }
    }
}

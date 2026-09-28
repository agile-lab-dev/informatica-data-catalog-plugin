package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.model.DataCollection;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Enriches DataContract objects with values retrieved from Informatica APIs.
 *
 * <p>This service is used after the initial mapping from other beans to resolve and populate fields
 * that require API calls to Informatica Marketplace. It acts as a post-processing step to obtain
 * correct values that cannot be determined from the source bean alone.
 *
 * <p><b>Primary Use Case:</b>
 *
 * <p>After mapping a DataContract from another bean (e.g., Excel, JSON, or external system), this
 * enricher resolves missing or API-dependent fields by querying Informatica services.
 *
 * <p><b>Enrichment Capabilities:</b>
 *
 * <ul>
 *   <li>Resolving collection identifier by name lookup
 *   <li>Retrieving system-generated attributes (creation dates, IDs)
 *   <li>Mapping custom attribute IDs to their values
 *   <li>Resolving stakeholder and owner information
 * </ul>
 *
 * <p><b>Typical Workflow:</b>
 *
 * <pre>{@code
 * // Step 1: Map from source bean
 * DataContract dataContract = mapper.mapFromExcel(excelRow);
 *
 * // Step 2: Enrich with API-resolved values
 * dataContractEnricher.populateIdentifierFromName(dataContract, dataContract.getBaseCharacteristics().getName());
 *
 * // Step 3: DataContract now has the correct Informatica identifier
 * String id = dataContract.getBaseCharacteristics().getIdentifier();
 * }</pre>
 *
 * <p><b>Design Pattern:</b>
 *
 * <p>This class follows the Enricher pattern - it enhances existing objects with additional data
 * from external sources without modifying the core mapping logic.
 *
 * @author Witboost Plugin Team
 * @since 1.0
 * @see DataContract
 * @see DataCollectionApiClient
 */
@Component
@Slf4j
public class DataContractResolverService {

    private final DataCollectionApiClient dataCollectionApiClient;
    private final CategoryService categoryService;

    public DataContractResolverService(
            DataCollectionApiClient dataCollectionApiClient,
            CategoryService categoryService) {
        this.dataCollectionApiClient = dataCollectionApiClient;
        this.categoryService = categoryService;
    }

    /**
     * Populates the DataContract identifier by searching for a collection using name, company,
     * domain, and subdomain.
     *
     * <p>This method performs a search in Informatica Marketplace to find a data collection
     * matching the provided criteria. It uses the category hierarchy (company, domain, subdomain)
     * to uniquely identify the collection when multiple collections with the same name exist.
     *
     * <p><b>Search Strategy:</b>
     *
     * <ul>
     *   <li>Extracts company, domain, and subdomain from DataContract.referenceContext
     *   <li>Searches for collections with matching name
     *   <li>Filters results by category matching the company/domain/subdomain hierarchy
     *   <li>Returns the unique collection or throws an exception if not found or ambiguous
     * </ul>
     *
     * <p><b>Note:</b> This method does not modify the input DataContract. Instead, it creates and
     * returns a new copy with the identifier populated.
     *
     * @param dataContract The DataContract containing referenceContext with company, domain,
     *     subdomain
     * @return A new DataContract with the populated identifier
     * @throws ApiCallException if the collection is not found, multiple matches found, or API call
     *     fails
     */
    public DataContract getDataContractEnriched(DataContract dataContract) {
        if (dataContract == null) {
            log.error("DataContract is null");
            throw new ApiCallException("DataContract cannot be null");
        }

                var categoryPath = dataContract.getMarketplaceCategoryPath();
                log.debug("Searching for collection in category path: {}", categoryPath);

                var dataProductCategory = categoryService.getCategoryByPath(categoryPath);
        String categoryId = dataProductCategory.getId();

                log.debug("Resolved category ID: {} for path {}", categoryId, categoryPath);

        String dataProductName = dataContract.getBaseCharacteristics().getName();

        // Search for collection by name AND categoryId to ensure uniqueness
        DataCollection collection =
                dataCollectionApiClient.getCollectionByNameAndCategoryId(
                        dataProductName, categoryId);

        if (collection == null) {
            log.error(
                    "Collection not found with name: {} in category: {}",
                    dataProductName,
                    categoryId);
            throw new ApiCallException(
                    String.format(
                            "Collection not found: name='%s', categoryPath='%s', categoryId='%s'",
                            dataProductName, categoryPath, categoryId));
        }

        log.debug(
                "Collection found: name='{}', id='{}', categoryId='{}'",
                collection.getName(),
                collection.getId(),
                collection.getCategory().getId());

        String identifier = collection.getId();

        // Create a copy using toBuilder() and update only the identifier
        DataContract newDataContract =
                dataContract.toBuilder()
                        .baseCharacteristics(
                                dataContract.getBaseCharacteristics().toBuilder()
                                        .identifier(identifier)
                                        .build())
                        .build();

        log.info(
                "Populated identifier: {} for collection: {} (category path: {})",
                identifier,
                dataProductName,
                categoryPath);
        return newDataContract;
    }
    //
    //    /**
    //     * Populates DataContract fields from a DataCollection API response.
    //     *
    //     * <p>Extracts and maps the following fields:</p>
    //     * <ul>
    //     *   <li>identifier → DataCollection.id</li>
    //     *   <li>name → DataCollection.name</li>
    //     *   <li>description → DataCollection.description</li>
    //     *   <li>status → DataCollection.status</li>
    //     *   <li>creationDate → DataCollection.systemAttributes.createdOn</li>
    //     * </ul>
    //     *
    //     * @param dataContract The DataContract to populate
    //     * @param dataCollection The Informatica DataCollection response
    //     * @return The populated DataContract
    //     */
    //    public DataContract populateFromDataCollection(DataContract dataContract,
    //                                                   DataCollection dataCollection) {
    //        log.debug("Populating DataContract from DataCollection: {}",
    // dataCollection.getName());
    //
    //        // Base characteristics
    //        DataContract.BaseCharacteristics baseChars = dataContract.getBaseCharacteristics();
    //        baseChars.setIdentifier(dataCollection.getId());
    //        baseChars.setName(dataCollection.getName());
    //        baseChars.setDescription(dataCollection.getDescription());
    //
    //        if (dataCollection.getStatus() != null) {
    //
    // baseChars.setStatus(mapInformaticaStatusToMarketplace(dataCollection.getStatus()));
    //        }
    //
    //        // System attributes - creation date
    //        if (dataCollection.getSystemAttributes() != null &&
    //            dataCollection.getSystemAttributes().getCreatedOn() != null) {
    //            String creationDate =
    // formatInstant(dataCollection.getSystemAttributes().getCreatedOn());
    //            dataContract.getAdditionalInformation().setCreationDate(creationDate);
    //        }
    //
    //        log.info("Populated basic fields from DataCollection");
    //        return dataContract;
    //    }
    //
    //    /**
    //     * Retrieves a DataCollection by name and populates the DataContract.
    //     *
    //     * <p>This is a convenience method that combines search and population in one call.</p>
    //     *
    //     * @param dataContract The DataContract to populate
    //     * @param collectionName The name of the collection to search for
    //     * @return The populated DataContract
    //     * @throws ApiCallException if the collection is not found or API call fails
    //     */
    //    public DataContract populateFromCollectionName(DataContract dataContract, String
    // collectionName) {
    //        log.debug("Retrieving and populating from collection: {}", collectionName);
    //
    //        DataCollectionsResponse response = dataCollectionApiClient.getAllCollections(
    //            collectionName, 0, 10, "all"
    //        );
    //
    //        Optional<DataCollection> collection = response.getDataCollections().stream()
    //            .filter(dc -> collectionName.equals(dc.getName()))
    //            .findFirst();
    //
    //        if (collection.isEmpty()) {
    //            log.error("Collection not found with name: {}", collectionName);
    //            throw new ApiCallException("Collection not found: " + collectionName);
    //        }
    //
    //        return populateFromDataCollection(dataContract, collection.get());
    //    }
    //
    //    /**
    //     * Retrieves a DataCollection by identifier and populates the DataContract.
    //     *
    //     * @param dataContract The DataContract to populate
    //     * @param collectionId The ID of the collection to retrieve
    //     * @return The populated DataContract
    //     * @throws ApiCallException if the API call fails
    //     */
    //    public DataContract populateFromCollectionId(DataContract dataContract, String
    // collectionId) {
    //        log.debug("Retrieving and populating from collection ID: {}", collectionId);
    //
    //        DataCollection collection = dataCollectionApiClient.getCollectionById(collectionId);
    //        return populateFromDataCollection(dataContract, collection);
    //    }
    //
    //    /**
    //     * Populates stakeholder information (Product Owner, Technical Owner).
    //     *
    //     * <p>Extracts stakeholder details from the DataCollection and maps them
    //     * to the appropriate owner fields in the DataContract.</p>
    //     *
    //     * @param dataContract The DataContract to populate
    //     * @param dataCollection The Informatica DataCollection response
    //     * @return The populated DataContract
    //     */
    //    public DataContract populateStakeholders(DataContract dataContract,
    //                                            DataCollection dataCollection) {
    //        log.debug("Populating stakeholders from DataCollection");
    //
    //        if (dataCollection.getStakeholdership() == null ||
    //            dataCollection.getStakeholdership().isEmpty()) {
    //            log.warn("No stakeholders found in DataCollection");
    //            return dataContract;
    //        }
    //
    //        // Map stakeholders to owners
    //        // Note: This is a simplified mapping - you may need to adjust based on roleId values
    //        for (DataCollection.Stakeholder stakeholder : dataCollection.getStakeholdership()) {
    //            String stakeholderId = stakeholder.getStakeholderId();
    //            String roleId = stakeholder.getRoleId();
    //
    //            // You'll need to define role mappings based on your Informatica configuration
    //            if (isProductOwnerRole(roleId)) {
    //                DataContract.Owner owner = new DataContract.Owner();
    //                owner.setInformaticaId(stakeholderId);
    //                dataContract.getBaseCharacteristics().setOwner(owner);
    //            } else if (isTechnicalOwnerRole(roleId)) {
    //                DataContract.Owner technicalOwner = new DataContract.Owner();
    //                technicalOwner.setInformaticaId(stakeholderId);
    //                dataContract.getBaseCharacteristics().setTechnicalOwner(technicalOwner);
    //            }
    //        }
    //
    //        return dataContract;
    //    }
    //
    //    // Helper methods
    //
    //    /**
    //     * Formats an Instant to a date string (yyyy-MM-dd).
    //     */
    //    private String formatInstant(Instant instant) {
    //        if (instant == null) {
    //            return null;
    //        }
    //        return DATE_FORMATTER.format(instant);
    //    }
    //
    //    /**
    //     * Maps Informatica status to marketplace status.
    //     * Informatica uses: ACTIVE, INACTIVE, etc.
    //     * Marketplace uses: Published, Unpublished
    //     */
    //    private String mapInformaticaStatusToMarketplace(String informaticaStatus) {
    //        if (informaticaStatus == null) {
    //            return "Published"; // default
    //        }
    //
    //        return switch (informaticaStatus.toUpperCase()) {
    //            case "ACTIVE", "PUBLISHED" -> "Published";
    //            case "INACTIVE", "UNPUBLISHED" -> "Unpublished";
    //            default -> "Published";
    //        };
    //    }
    //
    //    /**
    //     * Checks if a role ID corresponds to a Product Owner role.
    //     * TODO: Configure actual role IDs from your Informatica instance
    //     */
    //    private boolean isProductOwnerRole(String roleId) {
    //        // Replace with actual role ID from your Informatica configuration
    //        return "PRODUCT_OWNER".equals(roleId) || "OWNER".equals(roleId);
    //    }
    //
    //    /**
    //     * Checks if a role ID corresponds to a Technical Owner role.
    //     * TODO: Configure actual role IDs from your Informatica instance
    //     */
    //    private boolean isTechnicalOwnerRole(String roleId) {
    //        // Replace with actual role ID from your Informatica configuration
    //        return "TECHNICAL_OWNER".equals(roleId) || "DEVELOPER".equals(roleId);
    //    }
}

package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.datacatalog.openapi.model.EntityReference;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningStatus;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ValidationResult;

/***
 * Service to handle data catalog provisioning
 */
public interface DataCatalogService {

    /**
     * Provides an entity reference for a component
     *
     * @param componentId component id for which to return the entity reference
     * @return the entity reference for the component
     */
    EntityReference getEntityReference(String componentId);

    /**
     * Validate the provisioning request
     *
     * @param provisioningRequest request to validate
     * @return the outcome of the validation
     */
    ValidationResult validate(ProvisioningRequest provisioningRequest);

    /**
     * Provision the component present in the request
     *
     * @param provisioningRequest the request
     * @return the outcome of the provision
     */
    ProvisioningStatus provision(ProvisioningRequest provisioningRequest);

    /**
     * Unprovision the component present in the request
     *
     * @param provisioningRequest the request
     * @return the outcome of the unprovision
     */
    ProvisioningStatus unprovision(ProvisioningRequest provisioningRequest);
}

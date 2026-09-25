package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultResponse;
import com.witboost.plugin.informatica.marketplace.openapi.model.UpdateAclRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ValidationResult;

/***
 * Service to handle marketplace provisioning
 */
public interface MarketplaceService {

    ProvisioningResultResponse insertProvisioningResults(
            ProvisioningResultRequest provisioningResultRequest);

    String updateAcl(UpdateAclRequest updateAclRequest);

    String delete(ProvisioningResultRequest provisioningResultRequest);

    /**
     * Validates the descriptor for Marketplace publishing (currently: the target category exists).
     * Intended to be wired in Witboost as a remote policy at descriptor-validation time, since the
     * MarketplaceProxy contract has no validate that Witboost invokes automatically.
     */
    ValidationResult validate(ProvisioningRequest provisioningRequest);
}

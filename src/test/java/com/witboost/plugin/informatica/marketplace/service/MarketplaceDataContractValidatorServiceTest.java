package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The Marketplace data-contract validation must produce the same component-oriented messages as the
 * Data Catalog plugin (both delegate to the shared formatter), so errors are uniform across
 * plugins.
 */
class MarketplaceDataContractValidatorServiceTest {

    private static final String COMPONENT_ID = "urn:example:component:analytics:orders";

    private final DataContractValidatorService service;

    MarketplaceDataContractValidatorServiceTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        this.service = new DataContractValidatorService(validator);
    }

    @Test
    @DisplayName("reports the failing component by id/urn, like the Data Catalog plugin")
    void reportsComponentById() {
        DataContract dc = new DataContract();
        DeliveryTarget dt = new DeliveryTarget();
        dt.getBaseCharacteristics().setPortName("contratto");
        dt.getBaseCharacteristics().setPortTechnology("Snowflake");
        DataAsset asset = new DataAsset();
        asset.setComponentId(COMPONENT_ID);
        asset.getEntityInfo().setEntityName("contratto");
        asset.getEntityInfo()
                .setFeedingFrequency(null); // missing -> "feedingFrequency is mandatory"
        dt.getDataAssets().add(asset);
        dc.getDeliveryTargets().add(dt);

        Optional<FailedOperation> result = service.validate(dc);

        assertTrue(result.isPresent(), "Expected validation to fail");
        List<String> messages = result.get().problems().stream().map(Problem::getMessage).toList();
        assertTrue(
                messages.contains(
                        "[component " + COMPONENT_ID + "]: feedingFrequency is mandatory"),
                "Expected a component-id message uniform with Data Catalog, got: " + messages);
    }
}

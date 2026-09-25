package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ValidationResult;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

/**
 * Integration tests for MarketplaceServiceImpl.validate() using real Spring beans (parser, mappers,
 * data-contract validator). The category validator is mocked because it calls Informatica; mocking
 * it keeps the structural validation pipeline (parse -> map -> bean-validation) under test without
 * credentials.
 *
 * <p>Mirrors {@code DataCatalogValidateIntegrationTest} so both plugins' validate() are exercised
 * end-to-end.
 */
@SpringBootTest
@ActiveProfiles("test")
// Pin the publish-allowed environment to empty so validation is not skipped because of a leaked
// INFORMATICA_PUBLISH_ALLOWED_ENV in the shell (e.g. under `mvn clean test`).
@TestPropertySource(properties = "informatica.publish_allowed_environment=")
class MarketplaceValidateIntegrationTest {

    @Autowired private MarketplaceServiceImpl service;

    @MockitoBean private MarketplaceCategoryValidator marketplaceCategoryValidator;

    @MockitoBean private InformaticaDataMarketplaceService informaticaDataMarketplaceService;

    @Test
    @DisplayName("validate() returns true when descriptor is complete and the category exists")
    void validateReturnsTrueOnCompleteDescriptor() throws IOException {
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(Optional.empty());

        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml"));

        ValidationResult result = service.validate(req);

        assertTrue(result.getValid());
        assertTrue(result.getError().isEmpty());
    }

    @Test
    @DisplayName("validate() returns false when descriptor YAML is malformed")
    void validateReturnsFalseOnParseFailure() {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor("{ invalid yaml");

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertFalse(result.getError().get().getErrors().isEmpty());
    }

    @Test
    @DisplayName(
            "validate() reports the failing component by id/urn when a mandatory field is missing")
    void validateReportsComponentIdOnMissingMandatoryField() throws IOException {
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(Optional.empty());

        // Same descriptor minus a mandatory field on the output ports -> the structural validation
        // must
        // produce the same component-oriented message as the Data Catalog plugin (shared
        // formatter).
        String descriptor =
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml")
                        .replace("      feedingFrequency: Giornaliero\n", "");
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(descriptor);

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertTrue(
                result.getError().get().getErrors().stream()
                        .anyMatch(
                                e ->
                                        e.equals(
                                                "[component urn:example:component:analytics:orders]: feedingFrequency is mandatory")),
                "Expected the component-id locator in the error, got: "
                        + result.getError().get().getErrors());
    }

    @Test
    @DisplayName("validate() returns false when the Marketplace category does not exist")
    void validateReturnsFalseWhenCategoryMissing() throws IOException {
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(
                        Optional.of(
                                new Problem(
                                        "Category not found for company 'bad'. Accepted values: [example-company]")));

        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml"));

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertTrue(
                result.getError().get().getErrors().stream()
                        .anyMatch(e -> e.contains("Category not found for company")),
                "Expected the category error, got: " + result.getError().get().getErrors());
    }

    @Test
    @DisplayName(
            "validate() skips validation and returns true when publishing to Informatica is disabled")
    void validateSkippedWhenPublishDisabled() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(
                ResourceUtils.getContentFromResource(
                        "/descriptors/descriptor_publish_disabled.yml"));

        ValidationResult result = service.validate(req);

        assertTrue(result.getValid());
    }
}

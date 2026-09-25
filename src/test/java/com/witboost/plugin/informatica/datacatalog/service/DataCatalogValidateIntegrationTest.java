package com.witboost.plugin.informatica.datacatalog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ValidationResult;
import com.witboost.plugin.informatica.datacatalog.service.client.AssetApiUtil;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

/**
 * Integration tests for DataCatalogServiceImpl.validate() using real Spring beans.
 *
 * <p>InformaticaDataCatalogService and AssetApiUtil are mocked because validate() does not call
 * Informatica — mocking them avoids the need for API credentials. All other beans (mappers,
 * validators) are real, so the full parse→map→validate pipeline is exercised against actual
 * descriptors.
 */
@SpringBootTest
@ActiveProfiles("test")
// Force MID so the catalog-source / table-existence checks (mocked below) actually run, and pin the
// publish-allowed environment to empty so validation is not skipped because of a leaked
// INFORMATICA_PUBLISH_ALLOWED_ENV in the shell (e.g. under `mvn clean test`).
@TestPropertySource(
        properties = {
            "informatica.data-catalog.validation-level.level=MID",
            "informatica.publish_allowed_environment="
        })
class DataCatalogValidateIntegrationTest {

    @Autowired private DataCatalogServiceImpl service;

    @MockitoBean private InformaticaDataCatalogService informaticaDataCatalogService;

    @MockitoBean private AssetApiUtil assetApiUtil;

    @MockitoBean private TechnicalElementService technicalElementService;

    @Test
    @DisplayName("validate() returns true when descriptor is complete and valid")
    void validateReturnsTrueOnCompleteDescriptor() throws IOException {
        when(assetApiUtil.isAssetExisting(eq("catalog source"), any())).thenReturn(true);
        when(technicalElementService.hasDataElements(any(), any(), any(), any())).thenReturn(true);

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
        assertTrue(result.getError().isPresent());
        assertFalse(result.getError().get().getErrors().isEmpty());
    }

    @Test
    @DisplayName(
            "validate() returns false when output port uses a technology not supported by the configuration")
    void validateReturnsFalseOnUnsupportedTechnology() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(
                ResourceUtils.getContentFromResource(
                        "/descriptors/descriptor_unsupported_technology.yml"));

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertTrue(result.getError().isPresent());
        assertTrue(
                result.getError().get().getErrors().stream()
                        .anyMatch(e -> e.contains("unsupported-technology")),
                "Expected error about unsupported-technology, got: "
                        + result.getError().get().getErrors());
    }

    @Test
    @DisplayName("validate() returns false when catalog source is not found in Informatica")
    void validateReturnsFalseWhenCatalogSourceNotFound() throws IOException {
        when(assetApiUtil.isAssetExisting(eq("catalog source"), any())).thenReturn(false);

        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml"));

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertTrue(result.getError().isPresent());
        assertTrue(
                result.getError().get().getErrors().stream()
                        .anyMatch(e -> e.contains("not found in Informatica")),
                "Expected catalog source not found error, got: "
                        + result.getError().get().getErrors());
    }

    @Test
    @DisplayName(
            "validate() reports the failing component by id/urn when a mandatory field is missing")
    void validateReportsComponentIdOnMissingMandatoryField() throws IOException {
        // Real descriptor minus a mandatory field on the output ports -> structural validation
        // fails
        // before the live checks, and the message must point at the descriptor component (its urn).
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
    @DisplayName(
            "validate() skips validation and returns true when publishing to Informatica is disabled")
    void validateSkippedWhenPublishDisabled() throws IOException {
        // Descriptor with publishToInformatica:false that would otherwise fail validation
        // (missing mandatory referenceContext): the publish flag must short-circuit validate().
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(
                ResourceUtils.getContentFromResource(
                        "/descriptors/descriptor_publish_disabled.yml"));

        ValidationResult result = service.validate(req);

        assertTrue(result.getValid());
        assertTrue(result.getError().isEmpty());
    }
}

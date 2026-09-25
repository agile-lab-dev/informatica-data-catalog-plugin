package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.marketplace.exceptions.MarketplacePluginValidationException;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultResponse;
import com.witboost.plugin.informatica.marketplace.openapi.model.ValidationResult;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Guards that publish/unprovision failures propagate as exceptions (so the controller returns
 * 4xx/5xx and Witboost reports the operation as failed), instead of being swallowed into an HTTP
 * 200 response with status "FAILED".
 */
@ExtendWith(MockitoExtension.class)
class MarketplaceServiceImplTest {

    @Mock private ValidatorService<DataProduct> dataProductValidator;
    @Mock private ValidatorService<DataContract> dataContractValidator;
    @Mock private DataContractMapper dataContractMapper;
    @Mock private DeliveryTargetMapper deliveryTargetMapper;
    @Mock private DataAssetMapper dataAssetMapper;
    @Mock private InformaticaDataMarketplaceService informaticaDataMarketplaceService;
    @Mock private MarketplaceCategoryValidator marketplaceCategoryValidator;

    private MarketplaceServiceImpl service;

    @BeforeEach
    void setup() {
        // Manual construction to control the order of the two ValidatorService<?> mocks (same raw
        // type).
        service =
                new MarketplaceServiceImpl(
                        dataProductValidator,
                        dataContractValidator,
                        dataContractMapper,
                        deliveryTargetMapper,
                        dataAssetMapper,
                        informaticaDataMarketplaceService,
                        marketplaceCategoryValidator);
        ReflectionTestUtils.setField(service, "publishAllowedEnvironment", "");
    }

    private ProvisioningResultRequest request() throws IOException {
        ProvisioningResultRequest req = new ProvisioningResultRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));
        return req;
    }

    // ---- insertProvisioningResults ----

    @Test
    void insertPropagatesUpsertFailureInsteadOfReturningFailed() throws IOException {
        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(new DataContract());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Failed to insert Data Product 'X'"))
                .when(informaticaDataMarketplaceService)
                .upsertProductToDataMarketplace(any());

        assertThrows(RuntimeException.class, () -> service.insertProvisioningResults(request()));
    }

    @Test
    void insertThrowsValidationExceptionInsteadOfReturningFailed() throws IOException {
        when(dataProductValidator.validate(any()))
                .thenReturn(
                        Optional.of(
                                new FailedOperation(List.of(new Problem("company is mandatory")))));

        assertThrows(
                MarketplacePluginValidationException.class,
                () -> service.insertProvisioningResults(request()));
    }

    @Test
    void insertThrowsValidationExceptionWhenCategoryMissing() throws IOException {
        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(new DataContract());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(
                        Optional.of(
                                new Problem(
                                        "Category not found for company 'bad'. Accepted values: [example-company]")));

        var ex =
                assertThrows(
                        MarketplacePluginValidationException.class,
                        () -> service.insertProvisioningResults(request()));
        assertTrue(
                ex.getFailedOperation().problems().stream()
                        .anyMatch(p -> p.getMessage().contains("Category not found for company")),
                "Expected the descriptive category error to surface, got: "
                        + ex.getFailedOperation());
        // A missing category is a descriptor problem: it must not reach the upsert.
        verify(informaticaDataMarketplaceService, never()).upsertProductToDataMarketplace(any());
    }

    @Test
    void insertReturnsCompletedOnSuccess() throws IOException {
        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(new DataContract());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());

        ProvisioningResultResponse result = service.insertProvisioningResults(request());

        assertEquals("COMPLETED", result.getStatus());
    }

    @Test
    void insertReturnsCompletedWhenPublishNotRequired() {
        ProvisioningResultRequest req = new ProvisioningResultRequest();
        req.setDescriptor("specific:\n  publishToInformatica: false\n");

        ProvisioningResultResponse response = service.insertProvisioningResults(req);

        assertEquals("COMPLETED", response.getStatus());
        assertTrue(response.getResult().isPresent());
        assertTrue(
                response.getResult().get().contains("is set to false"), response.getResult().get());
    }

    @Test
    void deleteReturnsSkipReasonWhenPublishNotRequired() {
        ProvisioningResultRequest req = new ProvisioningResultRequest();
        req.setDescriptor("specific:\n  publishToInformatica: false\n");

        String result = service.delete(req);

        assertTrue(result.contains("is set to false"), result);
    }

    // ---- delete ----

    @Test
    void deletePropagatesFailureInsteadOfReturningError() throws IOException {
        var dataContract =
                DataContract.builder()
                        .baseCharacteristics(
                                DataContract.BaseCharacteristics.builder().name("dp").build())
                        .build();
        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(dataContract);
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());
        when(informaticaDataMarketplaceService.isProductInDataMarketplace("dp")).thenReturn(true);
        doThrow(new RuntimeException("delete failed"))
                .when(informaticaDataMarketplaceService)
                .deleteProduct(any());

        assertThrows(RuntimeException.class, () -> service.delete(request()));
    }

    // ---- validate (remote policy) ----

    private ProvisioningRequest validateRequest() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));
        return req;
    }

    @Test
    void validateReturnsValidWhenCategoryExists() throws IOException {
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(new DataContract());
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(Optional.empty());

        assertTrue(service.validate(validateRequest()).getValid());
    }

    @Test
    void validateReturnsInvalidWhenCategoryMissing() throws IOException {
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(new DataContract());
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(
                        Optional.of(
                                new Problem(
                                        "Category not found for company 'bad'. Accepted values: [example-company]")));

        ValidationResult result = service.validate(validateRequest());

        assertFalse(result.getValid());
        assertTrue(
                result.getError().get().getErrors().stream()
                        .anyMatch(e -> e.contains("Category not found for company")));
    }

    @Test
    void validateReturnsValidWhenPublishNotRequired() {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor("specific:\n  publishToInformatica: false\n");

        assertTrue(service.validate(req).getValid());
    }

    @Test
    void validateReturnsInvalidOnParseFailure() {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor("{ invalid yaml");

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertFalse(result.getError().get().getErrors().isEmpty());
    }

    @Test
    void validateFailsFastOnDataProductValidationError() throws IOException {
        when(dataProductValidator.validate(any()))
                .thenReturn(
                        Optional.of(
                                new FailedOperation(List.of(new Problem("company is mandatory")))));

        ValidationResult result = service.validate(validateRequest());

        assertFalse(result.getValid());
        assertTrue(result.getError().get().getErrors().contains("company is mandatory"));
        // Fail fast: an invalid descriptor must not reach mapping / data-contract / category
        // checks.
        verify(dataContractMapper, never()).toDataContractWithOutputPorts(any(), any(), any());
        verify(marketplaceCategoryValidator, never()).validateCategoryExists(any());
    }

    @Test
    void validateSurfacesStructuralErrorsAlongsideCategory() throws IOException {
        when(dataContractMapper.toDataContractWithOutputPorts(any(), any(), any()))
                .thenReturn(new DataContract());
        when(dataContractValidator.validate(any()))
                .thenReturn(
                        Optional.of(
                                new FailedOperation(
                                        List.of(
                                                new Problem(
                                                        "[component urn:dmb:cmp:x:0:contratto]: feedingFrequency is mandatory")))));
        when(marketplaceCategoryValidator.validateCategoryExists(any()))
                .thenReturn(
                        Optional.of(
                                new Problem(
                                        "Category not found for company 'bad'. Accepted values: [example-company]")));

        ValidationResult result = service.validate(validateRequest());

        assertFalse(result.getValid());
        var errors = result.getError().get().getErrors();
        assertTrue(
                errors.stream()
                        .anyMatch(
                                e ->
                                        e.contains(
                                                "[component urn:dmb:cmp:x:0:contratto]: feedingFrequency is mandatory")),
                "Expected the structural error to surface, got: " + errors);
        assertTrue(
                errors.stream().anyMatch(e -> e.contains("Category not found for company")),
                "Expected the category error to surface, got: " + errors);
    }
}

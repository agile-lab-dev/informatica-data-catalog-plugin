package com.witboost.plugin.informatica.datacatalog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.ValidationLevel;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogSourceConfig;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogUiConfig;
import com.witboost.plugin.informatica.datacatalog.config.ValidationLevelConfig;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningStatus;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ValidationResult;
import com.witboost.plugin.informatica.datacatalog.service.client.AssetApiUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataCatalogServiceImplTest {

    @Mock private ValidatorService<DataProduct> dataProductValidator;
    @Mock private ValidatorService<DataContract> dataContractValidator;
    @Mock private DataContractMapper dataContractMapper;
    @Mock private DeliveryTargetMapper deliveryTargetMapper;
    @Mock private DataAssetMapper dataAssetMapper;
    @Mock private InformaticaDataCatalogService informaticaDataCatalogService;
    @Mock private DataCatalogUiConfig uiConfig;
    @Mock private AssetApiUtil assetApiUtil;
    @Mock private DataCatalogSourceConfig dataCatalogSourceConfig;
    @Mock private ValidationLevelConfig validationLevelConfig;
    @Mock private TechnicalElementService technicalElementService;

    private DataCatalogServiceImpl service;

    @BeforeEach
    void setup() {
        // Costruzione manuale per garantire l'ordine corretto dei mock con stessa raw type
        // (ValidatorService<?>)
        service =
                new DataCatalogServiceImpl(
                        dataProductValidator,
                        dataContractValidator,
                        dataContractMapper,
                        deliveryTargetMapper,
                        dataAssetMapper,
                        informaticaDataCatalogService,
                        uiConfig,
                        assetApiUtil,
                        dataCatalogSourceConfig,
                        validationLevelConfig,
                        technicalElementService,
                        "");
        lenient().when(validationLevelConfig.resolve(any(), any())).thenReturn(ValidationLevel.MID);
    }

    @Test
    @DisplayName(
            "validate() should propagate DataContract validator errors to ValidationResult — orchestration test")
    void validatePropagatesDataContractValidatorErrors() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));

        var mockDataContract = mockDataContract();
        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        doReturn(mockDataContract)
                .when(dataContractMapper)
                .toDataContractWithOutputPorts(any(), any(), any());
        when(dataContractValidator.validate(any()))
                .thenReturn(
                        Optional.of(
                                new FailedOperation(
                                        List.of(
                                                new Problem(
                                                        "DataContract base characteristics name is required")))));

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertTrue(result.getError().isPresent());
        assertTrue(
                result.getError()
                        .get()
                        .getErrors()
                        .contains("DataContract base characteristics name is required"));
    }

    @Test
    @DisplayName("validate() should return false when catalog source is not found in Informatica")
    void validateReturnsFalseWhenCatalogSourceNotFound() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));

        var mockDtBaseChar = mock(DeliveryTarget.BaseCharacteristics.class);
        when(mockDtBaseChar.getPortTechnology()).thenReturn("SNOWFLAKE");
        var mockDt = mock(DeliveryTarget.class);
        when(mockDt.getBaseCharacteristics()).thenReturn(mockDtBaseChar);

        var mockDataContract = mockDataContract();
        when(mockDataContract.getDeliveryTargets()).thenReturn(List.of(mockDt));

        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        doReturn(mockDataContract)
                .when(dataContractMapper)
                .toDataContractWithOutputPorts(any(), any(), any());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());
        when(dataCatalogSourceConfig.findCatalogSourceByTechnology(any()))
                .thenReturn(Optional.of("EXAMPLE_CATALOG_SOURCE"));
        when(assetApiUtil.isAssetExisting(eq("catalog source"), eq("EXAMPLE_CATALOG_SOURCE")))
                .thenReturn(false);

        ValidationResult result = service.validate(req);

        assertFalse(result.getValid());
        assertTrue(result.getError().isPresent());
        assertTrue(
                result.getError().get().getErrors().stream()
                        .anyMatch(e -> e.contains("EXAMPLE_CATALOG_SOURCE")));
    }

    @Test
    @DisplayName("provision() should return FAILED with error details when validation fails")
    void provisionReturnFailedWithErrorDetailsOnValidationFailure() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));
        when(dataProductValidator.validate(any()))
                .thenReturn(
                        Optional.of(
                                new FailedOperation(List.of(new Problem("Field X is required")))));

        ProvisioningStatus status = service.provision(req);

        assertEquals(ProvisioningStatus.StatusEnum.FAILED, status.getStatus());
        assertNotNull(status.getResult());
        assertTrue(
                status.getResult().contains("Field X is required"),
                "Expected result to contain error details, got: " + status.getResult());
    }

    @Test
    @DisplayName(
            "provision() should return FAILED with exception message when an unexpected error occurs")
    void provisionReturnFailedWithExceptionMessageOnUnexpectedError() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));

        var mockDataContract = mockDataContract();
        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());
        doReturn(mockDataContract)
                .when(dataContractMapper)
                .toDataContractWithOutputPorts(any(), any(), any());
        when(informaticaDataCatalogService.upsertProductToDataCatalog(any()))
                .thenThrow(new RuntimeException("Informatica API unavailable"));

        ProvisioningStatus status = service.provision(req);

        assertEquals(ProvisioningStatus.StatusEnum.FAILED, status.getStatus());
        assertEquals("Informatica API unavailable", status.getResult());
    }

    @Test
    @DisplayName("unprovision() should return FAILED with error details when validation fails")
    void unprovisionReturnFailedWithErrorDetailsOnValidationFailure() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));
        when(dataProductValidator.validate(any()))
                .thenReturn(
                        Optional.of(
                                new FailedOperation(
                                        List.of(new Problem("Missing required field")))));

        ProvisioningStatus status = service.unprovision(req);

        assertEquals(ProvisioningStatus.StatusEnum.FAILED, status.getStatus());
        assertNotNull(status.getResult());
        assertTrue(
                status.getResult().contains("Missing required field"),
                "Expected result to contain error details, got: " + status.getResult());
    }

    @Test
    @DisplayName("validate() at MID should sync catalog source metadata before running live checks")
    void validateSyncsCatalogMetadataBeforeLiveChecksAtMid() throws IOException {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));

        var mockDtBaseChar = mock(DeliveryTarget.BaseCharacteristics.class);
        when(mockDtBaseChar.getPortTechnology()).thenReturn("SNOWFLAKE");
        var mockDt = mock(DeliveryTarget.class);
        when(mockDt.getBaseCharacteristics()).thenReturn(mockDtBaseChar);

        var mockDataContract = mockDataContract();
        when(mockDataContract.getDeliveryTargets()).thenReturn(List.of(mockDt));

        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        doReturn(mockDataContract)
                .when(dataContractMapper)
                .toDataContractWithOutputPorts(any(), any(), any());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());
        when(dataCatalogSourceConfig.findCatalogSourceByTechnology(any()))
                .thenReturn(Optional.of("EXAMPLE_CATALOG_SOURCE"));
        when(assetApiUtil.isAssetExisting(eq("catalog source"), eq("EXAMPLE_CATALOG_SOURCE")))
                .thenReturn(false);

        service.validate(req);

        InOrder inOrder = inOrder(informaticaDataCatalogService, assetApiUtil);
        inOrder.verify(informaticaDataCatalogService).syncCatalogSourcesMetadata();
        inOrder.verify(assetApiUtil)
                .isAssetExisting(eq("catalog source"), eq("EXAMPLE_CATALOG_SOURCE"));
    }

    @Test
    @DisplayName("validate() at LOW should not sync catalog source metadata (no live checks)")
    void validateDoesNotSyncCatalogMetadataAtLow() throws IOException {
        when(validationLevelConfig.resolve(any(), any())).thenReturn(ValidationLevel.LOW);

        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor(ResourceUtils.getContentFromResource("/descriptors/descriptor.yml"));

        when(dataProductValidator.validate(any())).thenReturn(Optional.empty());
        doReturn(mockDataContract())
                .when(dataContractMapper)
                .toDataContractWithOutputPorts(any(), any(), any());
        when(dataContractValidator.validate(any())).thenReturn(Optional.empty());

        service.validate(req);

        verify(informaticaDataCatalogService, never()).syncCatalogSourcesMetadata();
    }

    @Test
    @DisplayName(
            "provision() should short-circuit to COMPLETED carrying the skip reason when publish is not required")
    void provisionShortCircuitsWithReasonWhenPublishNotRequired() {
        ProvisioningRequest req = new ProvisioningRequest();
        req.setDescriptor("specific:\n  publishToInformatica: false\n");

        ProvisioningStatus status = service.provision(req);

        assertEquals(ProvisioningStatus.StatusEnum.COMPLETED, status.getStatus());
        assertNotNull(status.getResult());
        assertTrue(status.getResult().contains("is set to false"), status.getResult());
        verifyNoInteractions(informaticaDataCatalogService);
    }

    private DataContract mockDataContract() {
        var mockBaseChar = mock(DataContract.BaseCharacteristics.class);
        when(mockBaseChar.getName()).thenReturn("test-contract");
        var mockDataContract = mock(DataContract.class);
        when(mockDataContract.getBaseCharacteristics()).thenReturn(mockBaseChar);
        return mockDataContract;
    }
}

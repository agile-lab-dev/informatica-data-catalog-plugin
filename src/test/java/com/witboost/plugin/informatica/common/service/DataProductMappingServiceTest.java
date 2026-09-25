package com.witboost.plugin.informatica.common.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataProductMappingServiceTest {

    @Mock private DataContractMapper dataContractMapper;

    @Mock private DeliveryTargetMapper deliveryTargetMapper;

    @Mock private DataAssetMapper dataAssetMapper;

    @InjectMocks private DataProductMappingService service;

    private DataProduct dataProduct;
    private DataContract expectedDataContract;
    private List<DeliveryTarget> expectedDeliveryTargets;

    @BeforeEach
    void setUp() {
        dataProduct = new DataProduct();
        dataProduct.setId("dp-123");
        dataProduct.setName("Test Data Product");
        dataProduct.setDescription("Test Description");
        dataProduct.setComponents(new ArrayList<>());

        expectedDataContract = DataContract.builder().build();
        expectedDataContract.getBaseCharacteristics().setIdentifier("dp-123");
        expectedDataContract.getBaseCharacteristics().setName("Test Data Product");

        DeliveryTarget snowflakeTarget = new DeliveryTarget();
        snowflakeTarget.getBaseCharacteristics().setPortTechnology("Snowflake");
        snowflakeTarget.getBaseCharacteristics().setPortName("Test Output Port");

        expectedDeliveryTargets = List.of(snowflakeTarget);
    }

    @Test
    void toDataContract_ShouldDelegateToMapper() {
        // Given
        when(dataContractMapper.toDataContract(dataProduct)).thenReturn(expectedDataContract);

        // When
        DataContract result = service.toDataContract(dataProduct);

        // Then
        assertNotNull(result);
        assertEquals(expectedDataContract, result);
        verify(dataContractMapper, times(1)).toDataContract(dataProduct);
    }

    @Test
    void toOutputPorts_ShouldDelegateToMapper() {
        // Given
        when(deliveryTargetMapper.toOutputPortsGroupedByTechnology(any(), eq(dataAssetMapper)))
                .thenReturn(expectedDeliveryTargets);

        // When
        List<DeliveryTarget> result = service.toOutputPorts(dataProduct);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Snowflake", result.get(0).getBaseCharacteristics().getPortTechnology());
        verify(deliveryTargetMapper, times(1))
                .toOutputPortsGroupedByTechnology(any(), eq(dataAssetMapper));
    }

    @Test
    void mapDataProduct_ShouldReturnBothDataContractAndDeliveryTargets() {
        // Given
        when(dataContractMapper.toDataContract(dataProduct)).thenReturn(expectedDataContract);
        when(deliveryTargetMapper.toOutputPortsGroupedByTechnology(any(), eq(dataAssetMapper)))
                .thenReturn(expectedDeliveryTargets);

        // When
        DataProductMappingService.MappingResult result = service.mapDataProduct(dataProduct);

        // Then
        assertNotNull(result);
        assertEquals(expectedDataContract, result.dataContract());
        assertEquals(expectedDeliveryTargets, result.deliveryTargets());

        verify(dataContractMapper, times(1)).toDataContract(dataProduct);
        verify(deliveryTargetMapper, times(1))
                .toOutputPortsGroupedByTechnology(any(), eq(dataAssetMapper));
    }
}

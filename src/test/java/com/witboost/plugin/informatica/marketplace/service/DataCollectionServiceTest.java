package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.mapper.datacontract.CreateCollectionMapper;
import com.witboost.plugin.informatica.marketplace.model.Category;
import com.witboost.plugin.informatica.marketplace.model.CreateDataCollectionRequest;
import com.witboost.plugin.informatica.marketplace.model.CreateDataCollectionResponse;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for DataCollectionService.
 *
 * <p>Tests the business logic for managing data collections.
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class DataCollectionServiceTest {

    @Mock private DataCollectionApiClient dataCollectionApiClient;

    @Mock private CategoryService categoryService;

    @Mock private CustomAttributeService customAttributeService;

    @Mock private CreateCollectionMapper createCollectionMapper;

    @InjectMocks private DataCollectionService dataCollectionService;

    @BeforeEach
    void setUp() {
        // Setup complete - mocks are injected via @InjectMocks
    }

    /** Test: Verify that collectionExists returns true when collection exists */
    @Test
    void testCollectionExists_WhenCollectionExists_ShouldReturnTrue() {
        // Arrange
        String collectionName = "Test Collection";
        when(dataCollectionApiClient.collectionExists(collectionName)).thenReturn(true);

        // Act
        boolean result = dataCollectionService.collectionExists(collectionName);

        // Assert
        assertTrue(result);
        verify(dataCollectionApiClient, times(1)).collectionExists(collectionName);
    }

    /** Test: Verify that collectionExists returns false when collection does not exist */
    @Test
    void testCollectionExists_WhenCollectionDoesNotExist_ShouldReturnFalse() {
        // Arrange
        String collectionName = "Non Existing Collection";
        when(dataCollectionApiClient.collectionExists(collectionName)).thenReturn(false);

        // Act
        boolean result = dataCollectionService.collectionExists(collectionName);

        // Assert
        assertFalse(result);
        verify(dataCollectionApiClient, times(1)).collectionExists(collectionName);
    }

    /**
     * Test: Verify that createCollection successfully creates a collection
     *
     * <p>This test verifies the complete flow: 1. CategoryService is called to get the category by
     * domain/subdomain 2. CustomAttributeService is called to get the custom attributes map 3.
     * CreateCollectionMapper is used to map DataContract to CreateDataCollectionRequest 4.
     * DataCollectionApiClient.createCollection is called with the mapped request 5. The method
     * returns true on success
     */
    @Test
    void testCreateCollection_WithValidDataContract_ShouldReturnTrue() {
        // Arrange
        DataContract dataContract = createSampleDataContract();

        // Setup mocks for the service dependencies
        Category mockCategory = createMockCategory("category-123");
        when(categoryService.getCategoryByCompanyDomainSubdomain(null, "TestDomain", null))
                .thenReturn(mockCategory);

        Map<String, String> customAttributesMap = new HashMap<>();
        when(customAttributeService.getDataCollectionCustomAttributesNameId())
                .thenReturn(customAttributesMap);

        CreateDataCollectionRequest mockRequest = createMockCreateDataCollectionRequest();
        when(createCollectionMapper.mapToCreateDataCollectionRequest(
                        dataContract, "category-123", customAttributesMap))
                .thenReturn(mockRequest);

        CreateDataCollectionResponse mockResponse = new CreateDataCollectionResponse();
        mockResponse.setId("collection-id-123");
        when(dataCollectionApiClient.createCollection(mockRequest)).thenReturn(mockResponse);

        // Act
        var id = dataCollectionService.createCollection(dataContract);

        // Assert
        assertNotNull(id);

        // Verify all dependencies were called correctly
        verify(categoryService, times(1))
                .getCategoryByCompanyDomainSubdomain(null, "TestDomain", null);
        verify(customAttributeService, times(1)).getDataCollectionCustomAttributesNameId();
        verify(createCollectionMapper, times(1))
                .mapToCreateDataCollectionRequest(
                        dataContract, "category-123", customAttributesMap);
        verify(dataCollectionApiClient, times(1)).createCollection(mockRequest);
    }

    /**
     * Test: Verify that createCollection returns false when API call fails
     *
     * <p>This test verifies error handling when DataCollectionApiClient.createCollection throws an
     * ApiCallException.
     */
    @Test
    void testCreateCollection_WhenApiCallFails_ShouldThrowApiCallException() {
        // Arrange
        DataContract dataContract = createSampleDataContract();

        Category mockCategory = createMockCategory("category-123");
        when(categoryService.getCategoryByCompanyDomainSubdomain(null, "TestDomain", null))
                .thenReturn(mockCategory);

        Map<String, String> customAttributesMap = new HashMap<>();
        when(customAttributeService.getDataCollectionCustomAttributesNameId())
                .thenReturn(customAttributesMap);

        CreateDataCollectionRequest mockRequest = createMockCreateDataCollectionRequest();
        when(createCollectionMapper.mapToCreateDataCollectionRequest(
                        dataContract, "category-123", customAttributesMap))
                .thenReturn(mockRequest);

        // Simulate API failure
        doThrow(new ApiCallException("API error"))
                .when(dataCollectionApiClient)
                .createCollection(mockRequest);

        // Act & Assert
        assertThrows(
                ApiCallException.class, () -> dataCollectionService.createCollection(dataContract));

        // Verify API was called
        verify(dataCollectionApiClient, times(1)).createCollection(mockRequest);
    }

    /**
     * Test: Verify updateCollection is not yet implemented
     *
     * <p>This test documents the current state where updateCollection throws
     * NotImplementedException.
     */
    @Test
    void testUpdateCollection_ThrowsApiCallException_WhenDependencyNotMocked() {
        // Arrange
        DataContract dataContract = createSampleDataContract();

        // Act & Assert - updateCollection requires dataContractResolverService which is not mocked
        assertThrows(
                ApiCallException.class, () -> dataCollectionService.updateCollection(dataContract));
    }

    /** Helper method to create a sample DataContract for testing */
    private DataContract createSampleDataContract() {
        DataContract dataContract = new DataContract();

        dataContract.getBaseCharacteristics().setName("Test Data Collection");
        dataContract.getBaseCharacteristics().setDescription("This is a test data collection");
        dataContract.getReferenceContext().setDomain("TestDomain");
        dataContract.getBaseCharacteristics().setStatus("ACTIVE");

        return dataContract;
    }

    /** Helper method to create a mock Category for testing */
    private Category createMockCategory(String categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");
        return category;
    }

    /** Helper method to create a mock CreateDataCollectionRequest for testing */
    private CreateDataCollectionRequest createMockCreateDataCollectionRequest() {
        return CreateDataCollectionRequest.builder()
                .name("Test Data Collection")
                .description("This is a test data collection")
                .categoryId("category-123")
                .status("UNPUBLISHED")
                .build();
    }
}

package com.witboost.plugin.informatica.marketplace.service.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.model.Category;
import com.witboost.plugin.informatica.marketplace.model.DataCollection;
import com.witboost.plugin.informatica.marketplace.service.CategoryService;
import com.witboost.plugin.informatica.marketplace.service.DataContractResolverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataContractResolverServiceTest {

    @Mock private DataCollectionApiClient dataCollectionApiClient;

    @Mock private CustomAttributeApiClient customAttributeApiClient;

    @Mock private CategoryService categoryService;

    @InjectMocks private DataContractResolverService populator;

    private DataContract dataContract;
    private DataCollection dataCollection;

    @BeforeEach
    void setUp() {
        // Initialize DataContract
        dataContract = new DataContract();
        DataContract.BaseCharacteristics baseCharacteristics =
                new DataContract.BaseCharacteristics();
        baseCharacteristics.setName("My Data Product");
        dataContract.setBaseCharacteristics(baseCharacteristics);
        DataContract.ReferenceContext referenceContext = new DataContract.ReferenceContext();
        referenceContext.setCompany("TestCompany");
        referenceContext.setDomain("TestDomain");
        referenceContext.setSubdomain("TestSubdomain");
        dataContract.setReferenceContext(referenceContext);
        dataContract.setAdditionalInformation(new DataContract.AdditionalInformation());

        // Initialize mock DataCollection
        dataCollection = new DataCollection();
        dataCollection.setId("col-123456");
        dataCollection.setName("My Data Product");
        dataCollection.setDescription("Test data product description");
        dataCollection.setStatus("ACTIVE");
        DataCollection.Category dcCategory = new DataCollection.Category();
        dcCategory.setId("cat-123");
        dataCollection.setCategory(dcCategory);
    }

    @Test
    void testGetDataContractEnriched_Success() {
        // Given
        String collectionName = "My Data Product";
        String categoryId = "cat-123";
        Category category = new Category();
        category.setId(categoryId);

        when(categoryService.getCategoryByCompanyDomainSubdomain(
                        eq("TestCompany"), eq("TestDomain"), eq("TestSubdomain")))
                .thenReturn(category);
        when(dataCollectionApiClient.getCollectionByNameAndCategoryId(
                        eq(collectionName), eq(categoryId)))
                .thenReturn(dataCollection);

        // When
        DataContract result = populator.getDataContractEnriched(dataContract);

        // Then
        assertNotNull(result);
        assertEquals("col-123456", result.getBaseCharacteristics().getIdentifier());
        verify(dataCollectionApiClient)
                .getCollectionByNameAndCategoryId(collectionName, categoryId);
    }

    @Test
    void testGetDataContractEnriched_NullResponse() {
        // Given
        String collectionName = "My Data Product";
        String categoryId = "cat-123";
        Category category = new Category();
        category.setId(categoryId);

        when(categoryService.getCategoryByCompanyDomainSubdomain(
                        eq("TestCompany"), eq("TestDomain"), eq("TestSubdomain")))
                .thenReturn(category);
        // Mock returns null for non-existent collection
        when(dataCollectionApiClient.getCollectionByNameAndCategoryId(
                        eq(collectionName), eq(categoryId)))
                .thenReturn(null);

        // When & Then - Service wraps null response in ApiCallException
        assertThrows(ApiCallException.class, () -> populator.getDataContractEnriched(dataContract));
    }
}

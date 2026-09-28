package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.marketplace.model.CategoriesResponse;
import com.witboost.plugin.informatica.marketplace.model.Category;
import com.witboost.plugin.informatica.marketplace.service.client.CategoryApiClient;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    private static final String COMPANY = "Example Organization";
    // Real Informatica domain names contain commas; the lookup must match the full name.
    private static final String DOMAIN = "Cliente, Controparti e Marketing";
    private static final String SUBDOMAIN = "Anagrafica";

    @Mock private CategoryApiClient categoryApiClient;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryApiClient);
    }

    @Test
    void resolvesCategoryWhenDomainNameContainsCommas() {
        stubCategories(
                List.of(
                        category("company-id", null, COMPANY),
                        category("domain-id", "company-id", DOMAIN),
                        category("subdomain-id", "domain-id", SUBDOMAIN)));

        var result = categoryService.getCategoryByPath(List.of(COMPANY, DOMAIN, SUBDOMAIN));

        assertEquals("subdomain-id", result.getId());
    }

    @Test
    void domainNotFoundErrorQuotesNamesSoCommasAreNotAmbiguous() {
        stubCategories(
                List.of(
                        category("company-id", null, COMPANY),
                        category("domain-id", "company-id", DOMAIN)));

        var ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () ->
                                categoryService.getCategoryByPath(
                                        List.of(COMPANY, "Cliente", SUBDOMAIN)));

        // The accepted value must appear as a single quoted token, not split on its internal comma.
        assertTrue(
                ex.getMessage().contains("'Cliente'"),
                "Expected the missing category name in the error, got: " + ex.getMessage());
    }

    private void stubCategories(List<Category> items) {
        var response = new CategoriesResponse();
        response.setItems(items);
        when(categoryApiClient.getAllCategories("*", "all")).thenReturn(response);
    }

    private static Category category(String id, String parentId, String name) {
        var category = new Category();
        category.setId(id);
        category.setParentId(parentId);
        category.setName(name);
        return category;
    }
}

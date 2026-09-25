package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.model.Category;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MarketplaceCategoryValidatorTest {

    @Mock private CategoryService categoryService;

    private MarketplaceCategoryValidator validator;

    @BeforeEach
    void setup() {
        validator = new MarketplaceCategoryValidator(categoryService, true);
    }

    private static DataContract dataContract(String company, String domain, String subdomain) {
        DataContract dc = new DataContract();
        dc.setReferenceContext(
                DataContract.ReferenceContext.builder()
                        .company(company)
                        .domain(domain)
                        .subdomain(subdomain)
                        .build());
        return dc;
    }

    @Test
    void emptyWhenCategoryExists() {
        when(categoryService.getCategoryByCompanyDomainSubdomain("example-company", "Marketing", "CAM"))
                .thenReturn(new Category());

        assertTrue(
                validator
                        .validateCategoryExists(dataContract("example-company", "Marketing", "CAM"))
                        .isEmpty());
    }

    @Test
    void problemWhenCategoryNotFound() {
        when(categoryService.getCategoryByCompanyDomainSubdomain("bad", "Marketing", "CAM"))
                .thenThrow(new IllegalArgumentException("Category not found for company: bad"));

        Optional<Problem> result =
                validator.validateCategoryExists(dataContract("bad", "Marketing", "CAM"));

        assertTrue(result.isPresent());
        assertTrue(result.get().getMessage().contains("Category not found for company"));
    }

    @Test
    void emptyWhenInformaticaUnavailable() {
        // best-effort: a transient Informatica error must not fail validation
        when(categoryService.getCategoryByCompanyDomainSubdomain(any(), any(), any()))
                .thenThrow(new RuntimeException("Informatica unavailable"));

        assertTrue(
                validator
                        .validateCategoryExists(dataContract("example-company", "Marketing", "CAM"))
                        .isEmpty());
    }

    @Test
    void emptyAndNoCallWhenDisabled() {
        MarketplaceCategoryValidator disabled =
                new MarketplaceCategoryValidator(categoryService, false);

        assertTrue(
                disabled.validateCategoryExists(dataContract("example-company", "Marketing", "CAM")).isEmpty());
        verifyNoInteractions(categoryService);
    }
}

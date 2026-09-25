package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Checks that the Marketplace category (company/domain/subdomain) referenced by a descriptor exists
 * on Informatica.
 *
 * <p>Category resolution is a Marketplace concern, but the MarketplaceProxy plugin has no validate
 * endpoint that Witboost calls, so this is invoked from the Data Catalog provisioner's {@code
 * validate} — the only validate Witboost runs. The check (and its on/off switch) is kept here so
 * the Data Catalog side stays unaware of Marketplace config.
 */
@Slf4j
@Component
public class MarketplaceCategoryValidator {

    private final CategoryService categoryService;
    private final boolean enabled;

    public MarketplaceCategoryValidator(
            CategoryService categoryService,
            @Value("${informatica.marketplace.category-validation-enabled:true}") boolean enabled) {
        this.categoryService = categoryService;
        this.enabled = enabled;
    }

    /**
     * @return a {@link Problem} if the company/domain/subdomain category does not exist; empty if
     *     it exists, if this check is disabled, or if it could not run (best-effort: a transient
     *     Informatica error must not fail validation).
     */
    public Optional<Problem> validateCategoryExists(DataContract dataContract) {
        if (!enabled) {
            log.debug("Marketplace category validation is disabled, skipping");
            return Optional.empty();
        }
        var ref = dataContract.getReferenceContext();
        log.info(
                "Validating Marketplace category exists: company '{}', domain '{}', subdomain '{}'",
                ref.getCompany(),
                ref.getDomain(),
                ref.getSubdomain());
        try {
            categoryService.getCategoryByCompanyDomainSubdomain(
                    ref.getCompany(), ref.getDomain(), ref.getSubdomain());
            log.info(
                    "Marketplace category found: company '{}', domain '{}', subdomain '{}'",
                    ref.getCompany(),
                    ref.getDomain(),
                    ref.getSubdomain());
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.of(new Problem(e.getMessage()));
        } catch (Exception e) {
            log.warn(
                    "Skipping Marketplace category validation (could not query Informatica): {}",
                    e.getMessage());
            return Optional.empty();
        }
    }
}

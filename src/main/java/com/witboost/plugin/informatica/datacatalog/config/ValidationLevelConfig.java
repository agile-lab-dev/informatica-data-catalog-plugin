package com.witboost.plugin.informatica.datacatalog.config;

import com.witboost.plugin.informatica.common.model.ValidationLevel;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configures the {@link ValidationLevel} for Data Catalog provisioning.
 *
 * <p>Resolution order (most specific wins): descriptor field {@code specific.validationLevel} →
 * per-environment override → global default.
 */
@ConfigurationProperties(prefix = "informatica.data-catalog.validation-level")
public record ValidationLevelConfig(
        ValidationLevel level, Map<String, ValidationLevel> byEnvironment) {
    /** Fallback when neither descriptor, environment nor global default specify a level. */
    private static final ValidationLevel DEFAULT_LEVEL = ValidationLevel.LOW;

    /**
     * Resolves the effective validation level.
     *
     * @param environment the descriptor environment (may be null)
     * @param descriptorOverride the descriptor {@code specific.validationLevel} value (may be null)
     * @return the effective level
     */
    public ValidationLevel resolve(String environment, ValidationLevel descriptorOverride) {
        if (descriptorOverride != null) {
            return descriptorOverride;
        }
        if (environment != null && byEnvironment != null) {
            ValidationLevel perEnv = byEnvironment.get(environment);
            if (perEnv != null) {
                return perEnv;
            }
        }
        return level != null ? level : DEFAULT_LEVEL;
    }
}

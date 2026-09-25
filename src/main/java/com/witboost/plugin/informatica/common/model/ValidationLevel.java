package com.witboost.plugin.informatica.common.model;

/**
 * Cumulative validation depth for Data Catalog provisioning.
 *
 * <ul>
 *   <li>{@link #OFF} - no semantic validation (descriptor is only parsed).
 *   <li>{@link #LOW} - structural/parameter validation (bean constraints, supported technology);
 *       offline.
 *   <li>{@link #MID} - LOW + catalog source and table (technical data set) existence in
 *       Informatica.
 *   <li>{@link #HIGH} - MID + descriptor-vs-catalog column match.
 * </ul>
 *
 * Levels are cumulative: a higher level also runs all lower-level checks.
 */
public enum ValidationLevel {
    OFF,
    LOW,
    MID,
    HIGH;

    /**
     * @return true if this level is at least as strict as {@code other}.
     */
    public boolean atLeast(ValidationLevel other) {
        return this.ordinal() >= other.ordinal();
    }
}

package com.witboost.plugin.informatica.common.exceptions;

import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * Turns bean-validation violations on the mapped {@link DataContract} into {@link Problem}s whose
 * text points at the <b>descriptor component</b> the user wrote, not the internal model path.
 *
 * <p>Shared by both plugins so Data Catalog and Marketplace report validation errors uniformly. The
 * constraint message already names the descriptor key (e.g. "feedingFrequency is mandatory"); here
 * we prepend a locator identifying the failing component (its id/urn, falling back to its name,
 * then a positional hint) and column when relevant, instead of leaking paths like {@code
 * deliveryTargets[0].dataAssets[0].entityInfo.feedingFrequency}.
 */
public final class DataContractViolations {

    private DataContractViolations() {}

    public static List<Problem> toProblems(Set<ConstraintViolation<DataContract>> violations) {
        return violations.stream().map(DataContractViolations::toProblem).toList();
    }

    public static Problem toProblem(ConstraintViolation<DataContract> violation) {
        String locator = describeLocation(violation);
        return locator.isEmpty()
                ? new Problem(violation.getMessage())
                : new Problem("[" + locator + "]: " + violation.getMessage());
    }

    private static String describeLocation(ConstraintViolation<DataContract> violation) {
        // Bean Validation reports a list-element index on the node that follows the list property
        // (the node with isInIterable()==true); that index therefore belongs to the *previous*
        // node's
        // collection. So `deliveryTargets[0].dataAssets[0]...` yields: deliveryTargets(index=null),
        // dataAssets(index=0,inIterable) -> index 0 is the deliveryTargets element, etc.
        Integer dtIndex = null;
        Integer daIndex = null;
        Integer attrIndex = null;
        String previousName = null;
        for (Path.Node node : violation.getPropertyPath()) {
            if (node.isInIterable() && node.getIndex() != null && previousName != null) {
                switch (previousName) {
                    case "deliveryTargets" -> dtIndex = node.getIndex();
                    case "dataAssets" -> daIndex = node.getIndex();
                    case "attributes" -> attrIndex = node.getIndex();
                    default -> {
                        /* not a descriptor-level collection */
                    }
                }
            }
            previousName = node.getName();
        }
        if (dtIndex == null) {
            // data-product-level field (base characteristics / reference context / additional info)
            String ref = dataProductRef(violation.getRootBean());
            return ref == null ? "" : "data product " + ref;
        }

        DeliveryTarget dt = elementAt(violation.getRootBean().getDeliveryTargets(), dtIndex);
        DataAsset da = resolveDataAsset(dt, daIndex);

        StringBuilder locator =
                new StringBuilder("component ").append(componentRef(dt, da, dtIndex));

        if (attrIndex != null) {
            DataAsset.AttributeInfo attr =
                    da != null ? elementAt(da.getAttributes(), attrIndex) : null;
            String columnName = attr != null ? attr.getAttributeName() : null;
            locator.append(", column ")
                    .append(
                            columnName != null && !columnName.isBlank()
                                    ? "'" + columnName + "'"
                                    : "#" + (attrIndex + 1));
        }
        return locator.toString();
    }

    /**
     * Resolves the data asset a violation refers to. For base-characteristics violations (no
     * data-asset index) we still resolve the single asset of a non-aggregated delivery target so we
     * can name the component; for technology-aggregated delivery targets (many assets) we cannot,
     * and fall back later.
     */
    private static DataAsset resolveDataAsset(DeliveryTarget dt, Integer daIndex) {
        if (dt == null) return null;
        if (daIndex != null) return elementAt(dt.getDataAssets(), daIndex);
        List<DataAsset> assets = dt.getDataAssets();
        return assets != null && assets.size() == 1 ? assets.get(0) : null;
    }

    /**
     * Component identifier: the descriptor id/urn when available, otherwise its name, otherwise a
     * positional hint.
     */
    private static String componentRef(DeliveryTarget dt, DataAsset da, Integer dtIndex) {
        if (da != null && da.getComponentId() != null && !da.getComponentId().isBlank()) {
            return da.getComponentId();
        }
        String name = da != null ? entityName(da) : portName(dt);
        return name != null && !name.isBlank() ? "'" + name + "'" : "#" + (dtIndex + 1);
    }

    /** Data-product identifier: the descriptor id/urn when available, otherwise its name. */
    private static String dataProductRef(DataContract dc) {
        DataContract.BaseCharacteristics bc = dc.getBaseCharacteristics();
        if (bc == null) return null;
        if (bc.getIdentifier() != null && !bc.getIdentifier().isBlank()) {
            return bc.getIdentifier();
        }
        return bc.getName() != null && !bc.getName().isBlank() ? "'" + bc.getName() + "'" : null;
    }

    private static String entityName(DataAsset da) {
        return da.getEntityInfo() != null ? da.getEntityInfo().getEntityName() : null;
    }

    private static String portName(DeliveryTarget dt) {
        return dt != null && dt.getBaseCharacteristics() != null
                ? dt.getBaseCharacteristics().getPortName()
                : null;
    }

    private static <T> T elementAt(List<T> list, Integer index) {
        return list != null && index != null && index >= 0 && index < list.size()
                ? list.get(index)
                : null;
    }
}

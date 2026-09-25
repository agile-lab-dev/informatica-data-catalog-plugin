package com.witboost.plugin.informatica.common.mapper.datacontract;

/**
 * Enum representing the type of operation being performed on an Informatica asset.
 *
 * <p>This is used to populate the "Operation" field in Excel import templates.
 *
 * <ul>
 *   <li><b>CREATE</b> - Used when creating a new asset in Informatica
 *   <li><b>UPDATE</b> - Used when updating an existing asset in Informatica
 *   <li><b>DELETE</b> - Used when deleting an existing asset in Informatica
 * </ul>
 */
public enum Operation {
    /** Create operation - for new assets. */
    CREATE,

    /** Update operation - for existing assets. */
    UPDATE,

    /** Delete operation - for existing assets. */
    DELETE;

    /**
     * Returns the string representation for the Excel "Operation" field.
     *
     * @return "create", "update", or "delete" in camelCase format
     */
    public String getValue() {
        return switch (this) {
            case CREATE -> "Create";
            case UPDATE -> "Update";
            case DELETE -> "Delete";
        };
    }

    @Override
    public String toString() {
        return getValue();
    }
}

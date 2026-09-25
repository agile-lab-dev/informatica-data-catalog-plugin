package com.witboost.plugin.informatica.datacatalog.common;

import lombok.Getter;

public class Constants {
    public static final String STORAGE_KIND = "storage";
    public static final String OUTPUTPORT_KIND = "outputport";
    public static final String DATA_ASSET_KIND = "data-asset";

    @Getter
    public enum AssociationTypes {
        RESOURCE_TO_SYSTEM("com.infa.ccgf.models.governance.asscResourceSystem");

        private final String associationId;

        AssociationTypes(String associationId) {
            this.associationId = associationId;
        }
    }

    @Getter
    public enum ClassTypes {
        CATALOG_SOURCE("core.Resource");

        private final String classTypeId;

        ClassTypes(String classTypeId) {
            this.classTypeId = classTypeId;
        }
    }
}

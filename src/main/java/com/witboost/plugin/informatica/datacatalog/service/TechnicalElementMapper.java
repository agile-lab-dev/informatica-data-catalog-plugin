package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;

public class TechnicalElementMapper {

    public static TechnicalDataElement mapHitToTechnicalDataElement(AssetGetResponse.Hit hit) {
        return TechnicalDataElement.builder()
                .externalIdentity(hit.getExternalIdentity())
                .coreName(hit.getSummary().getCoreName())
                .coreLocation(hit.getSummary().getCoreLocation())
                .classType(hit.getSystemAttributes().getClassType())
                .coreIdentity(hit.getCoreIdentity())
                .catalogSourceName(hit.getSelfAttributes().getResourceName())
                .catalogSourceType(hit.getSelfAttributes().getResourceType())
                .build();
    }
}

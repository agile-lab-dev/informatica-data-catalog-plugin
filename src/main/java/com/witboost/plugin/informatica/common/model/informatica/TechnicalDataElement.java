package com.witboost.plugin.informatica.common.model.informatica;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TechnicalDataElement {
    private String externalIdentity;
    private String coreName;
    private String coreLocation;
    private String classType;
    private String coreIdentity;
    private String catalogSourceName;
    private String catalogSourceType;
}

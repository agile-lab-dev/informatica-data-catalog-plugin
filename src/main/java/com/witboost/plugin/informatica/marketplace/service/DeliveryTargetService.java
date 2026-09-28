package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.mapper.naming.NameBuilder;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.marketplace.common.Constants;
import com.witboost.plugin.informatica.marketplace.model.CreateDeliveryTargetRequest;
import com.witboost.plugin.informatica.marketplace.model.CreateDeliveryTargetResponse;
import com.witboost.plugin.informatica.marketplace.model.DataCollection;
import com.witboost.plugin.informatica.marketplace.model.UpdateDeliveryTargetRequest;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import com.witboost.plugin.informatica.marketplace.service.client.DeliveryTargetApiClient;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryTargetService {

    private final DeliveryTargetApiClient deliveryTargetApiClient;
    private final DataCollectionApiClient dataCollectionApiClient;

    public DeliveryTargetService(
            DeliveryTargetApiClient deliveryTargetApiClient,
            DataCollectionApiClient dataCollectionApiClient) {
        this.deliveryTargetApiClient = deliveryTargetApiClient;
        this.dataCollectionApiClient = dataCollectionApiClient;
    }

    public String createDeliveryTarget(
            String dataCollectionId, DataContract dataContract, DeliveryTarget deliveryTarget) {
        log.info(
                "Creating delivery target of port '{}' for data contract: {}",
                deliveryTarget.getBaseCharacteristics().getPortName(),
                dataContract.getBaseCharacteristics().getName());
        String deliveryTemplateId = getDeliveryTemplateId(dataCollectionId, deliveryTarget);

        // Create the delivery target request
        String deliveryTargetName =
                NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        CreateDeliveryTargetRequest request = new CreateDeliveryTargetRequest();
        request.setName(deliveryTargetName);
        request.setDescription(buildDeliveryTargetDescription(dataContract));
        request.setStatus(Constants.DELIVERY_TARGET_ACTIVE_STATUS);
        request.setDataCollectionId(dataCollectionId);
        request.setDeliveryTemplateId(deliveryTemplateId);
        // Optional: set isDefault if this should be the default delivery target
        request.setIsDefault(false);

        log.debug(
                "Creating delivery target with: name='{}', dataCollectionId='{}', templateId='{}'",
                deliveryTargetName,
                dataCollectionId,
                deliveryTemplateId);

        // Create the delivery target
        CreateDeliveryTargetResponse response =
                deliveryTargetApiClient.createDeliveryTarget(request);

        log.info(
                "Delivery target created successfully: ID='{}', externalId='{}', name='{}'",
                response.getId(),
                response.getExternalId(),
                deliveryTargetName);

        return response.getId();
    }

    public void updateDeliveryTarget(
            String dataCollectionId,
            DataContract dataContract,
            DeliveryTarget dataContractDeliveryTarget,
            String deliveryTargetId) {
        log.info(
                "Updating delivery target of port '{}' for data contract: {}",
                dataContractDeliveryTarget.getBaseCharacteristics().getPortName(),
                dataContract.getBaseCharacteristics().getName());
        String deliveryTemplateId =
                getDeliveryTemplateId(dataCollectionId, dataContractDeliveryTarget);
        var apiDeliveryTarget = deliveryTargetApiClient.getDeliveryTarget(deliveryTargetId, "all");

        // Create the delivery target request
        String deliveryTargetName =
                NameBuilder.getDataMarketplaceOutputPortName(
                        dataContract, dataContractDeliveryTarget);

        UpdateDeliveryTargetRequest requestUpdateSummary = new UpdateDeliveryTargetRequest();
        requestUpdateSummary.setOperation("replace");
        requestUpdateSummary.setSegment("summary");
        var summaryValueBuilder =
                UpdateDeliveryTargetRequest.SummaryValue.builder()
                        .description(buildDeliveryTargetDescription(dataContract));
        // Update delivery target name only if needed
        if (!deliveryTargetName.equals(apiDeliveryTarget.getName())) {
            summaryValueBuilder.name(deliveryTargetName);
        }
        requestUpdateSummary.setValue(summaryValueBuilder.build());

        boolean response =
                deliveryTargetApiClient.updateDeliveryTarget(
                        deliveryTargetId, requestUpdateSummary);
        if (!response) {
            log.warn("Delivery target '{}' summary  could not be updated", deliveryTargetName);
        }

        // Update delivery target template only if needed
        if (!deliveryTemplateId.equals(apiDeliveryTarget.getDeliveryTemplate().getId())) {
            UpdateDeliveryTargetRequest requestUpdateDeliveryTemplate =
                    new UpdateDeliveryTargetRequest();
            requestUpdateDeliveryTemplate.setOperation("replace");
            requestUpdateDeliveryTemplate.setSegment("deliveryTemplate");
            requestUpdateDeliveryTemplate.setValue(
                    UpdateDeliveryTargetRequest.DeliveryTemplateValue.builder()
                            .deliveryTemplateId(deliveryTemplateId)
                            .build());
            response =
                    deliveryTargetApiClient.updateDeliveryTarget(
                            deliveryTargetId, requestUpdateDeliveryTemplate);
            if (!response) {
                log.warn(
                        "Delivery target '{}' deliveryTemplate could not be updated",
                        deliveryTargetName);
            }
        }
    }

    public void updateDeliveryTargets(String dataCollectionId, DataContract dataContract) {
        var foundDataCollection =
                dataCollectionApiClient.getDataCollectionById(dataCollectionId, "all");
        List<DataCollection.DeliveryTarget> currentDeliveryTargets =
                Optional.ofNullable(foundDataCollection.getDeliveryTargets())
                        .orElseGet(Collections::emptyList);
        Map<String, DataCollection.DeliveryTarget> currentDeliveryTargetsByOutputPortName =
                currentDeliveryTargets.stream()
                        .collect(
                                Collectors.toMap(
                                        DataCollection.DeliveryTarget::getName,
                                        Function.identity()));
        // Delete obsolete delivery targets
        Set<String> toProvisionDeliveryTargetNames =
                dataContract.getMarketplaceDeliveryTargets().stream()
                        .filter(DeliveryTarget::isShoppable)
                        .map(dt -> NameBuilder.getDataMarketplaceOutputPortName(dataContract, dt))
                        .collect(Collectors.toSet());
        List<String> toDeleteDeliveryTargetNames =
                currentDeliveryTargetsByOutputPortName.keySet().stream()
                        .filter(name -> !toProvisionDeliveryTargetNames.contains(name))
                        .toList();
        if (!toDeleteDeliveryTargetNames.isEmpty()) {
            log.debug(
                    "The following delivery targets will be deleted: {}",
                    toDeleteDeliveryTargetNames);
            deleteDeliveryTargetsByName(toDeleteDeliveryTargetNames);
        }

        // For each delivery target to provision, update existing or create new
        for (var dt :
                dataContract.getMarketplaceDeliveryTargets().stream()
                        .filter(DeliveryTarget::isShoppable)
                        .toList()) {
            String deliveryTargetName =
                    NameBuilder.getDataMarketplaceOutputPortName(dataContract, dt);
            if (currentDeliveryTargetsByOutputPortName.containsKey(deliveryTargetName)) {
                // UPDATE
                var apiObj = currentDeliveryTargetsByOutputPortName.get(deliveryTargetName);
                updateDeliveryTarget(dataCollectionId, dataContract, dt, apiObj.getId());
            } else {
                // CREATE
                createDeliveryTarget(dataCollectionId, dataContract, dt);
            }
        }
    }

    /**
     * Deletes all delivery targets matching a list of names.
     *
     * @param deliveryTargetNames
     */
    public void deleteDeliveryTargetsByName(List<String> deliveryTargetNames) {
        Set<String> namesSet = new HashSet<>(deliveryTargetNames);
        var response = deliveryTargetApiClient.getAllDeliveryTargets(null, null);
        if (response.getItems() == null || response.getItems().isEmpty()) {
            log.warn("Response of get delivery targets is empty");
            return;
        }
        for (var deliveryTarget : response.getItems()) {
            if (namesSet.contains(deliveryTarget.getName())) {
                log.info(
                        "Found delivery target to delete with name '{}'", deliveryTarget.getName());
                deliveryTargetApiClient.deleteDeliveryTarget(deliveryTarget.getId());
            }
        }
    }

    private String getDeliveryTemplateId(String dataCollectionId, DeliveryTarget deliveryTarget) {
        String templateRefId = NameBuilder.getDataMarketplaceTemplateRefId(deliveryTarget);

        // Get the delivery template by refId
        var deliveryTemplate = deliveryTargetApiClient.getDeliveryTemplateByRefId(templateRefId);

        if (deliveryTemplate == null) {
            log.error("Delivery template with refId '{}' not found", templateRefId);
            throw new RuntimeException("Delivery template not found: " + templateRefId);
        }

        String deliveryTemplateId = deliveryTemplate.getId();
        log.info(
                "Found delivery template: refId='{}', ID='{}', name='{}'",
                templateRefId,
                deliveryTemplateId,
                deliveryTemplate.getName());

        if (dataCollectionId == null || dataCollectionId.isBlank()) {
            log.error("Data collection ID is null or blank - cannot create delivery target");
            throw new RuntimeException("Data collection ID is required to create delivery target");
        }
        return deliveryTemplateId;
    }

    private static String buildDeliveryTargetDescription(DataContract dataContract) {
        return "Delivery target for " + dataContract.getBaseCharacteristics().getName();
    }
}

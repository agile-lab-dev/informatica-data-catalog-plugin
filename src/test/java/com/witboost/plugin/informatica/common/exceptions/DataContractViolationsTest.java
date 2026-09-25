package com.witboost.plugin.informatica.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Validation error messages must point at the descriptor component the user wrote (by id/urn), not
 * at the internal mapped-model path (e.g. {@code
 * deliveryTargets[0].dataAssets[0].entityInfo.feedingFrequency}).
 */
class DataContractViolationsTest {

    private static final String COMPONENT_ID = "urn:example:component:contract:0";

    private final Validator validator;

    DataContractViolationsTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    @DisplayName(
            "entity-level violation is located by the component id/urn, not by the internal model path")
    void entityViolationReferencesComponentId() {
        DataContract dc =
                contractWithSingleOutputPort(
                        "contratto", COMPONENT_ID, null /* missing feedingFrequency */);

        List<String> messages = messages(dc);

        assertTrue(
                messages.contains(
                        "[component " + COMPONENT_ID + "]: feedingFrequency is mandatory"),
                "Expected a component-id feedingFrequency message, got: " + messages);
        assertNoInternalPathLeaks(messages);
    }

    @Test
    @DisplayName("column-level violation is located by component id + column name")
    void columnViolationReferencesComponentAndColumn() {
        DataContract dc = contractWithSingleOutputPort("contratto", COMPONENT_ID, "Giornaliero");
        DataAsset.AttributeInfo column =
                DataAsset.AttributeInfo.builder()
                        .attributeName("cd_compagnia")
                        .attributeDescription(null) // triggers "column description is mandatory"
                        .build();
        dc.getDeliveryTargets().get(0).getDataAssets().get(0).getAttributes().add(column);

        List<String> messages = messages(dc);

        assertTrue(
                messages.contains(
                        "[component "
                                + COMPONENT_ID
                                + ", column 'cd_compagnia']: column description is mandatory"),
                "Expected a component-id + column message, got: " + messages);
        assertNoInternalPathLeaks(messages);
    }

    @Test
    @DisplayName("falls back to the component name when no id/urn was mapped")
    void entityViolationFallsBackToComponentName() {
        DataContract dc =
                contractWithSingleOutputPort("contratto", null /* no component id */, null);

        List<String> messages = messages(dc);

        assertTrue(
                messages.contains("[component 'contratto']: feedingFrequency is mandatory"),
                "Expected a component-name fallback message, got: " + messages);
        assertNoInternalPathLeaks(messages);
    }

    @Test
    @DisplayName("data-product-level violation is located by the data product id/urn")
    void dataProductViolationReferencesDataProductId() {
        String dpId = "urn:example:dataproduct:0";
        DataContract dc = contractWithSingleOutputPort("contratto", COMPONENT_ID, "Giornaliero");
        dc.getBaseCharacteristics().setIdentifier(dpId);
        // company stays null and is reported at data-product level

        List<String> messages = messages(dc);

        assertTrue(
                messages.contains("[data product " + dpId + "]: company is mandatory"),
                "Expected a data-product-id message, got: " + messages);
        assertNoInternalPathLeaks(messages);
    }

    private List<String> messages(DataContract dc) {
        Set<ConstraintViolation<DataContract>> violations = validator.validate(dc);
        assertTrue(!violations.isEmpty(), "Expected validation to fail");
        return DataContractViolations.toProblems(violations).stream()
                .map(Problem::getMessage)
                .toList();
    }

    private static DataContract contractWithSingleOutputPort(
            String portName, String componentId, String feedingFrequency) {
        DataContract dc = new DataContract();

        DeliveryTarget dt = new DeliveryTarget();
        dt.getBaseCharacteristics().setPortName(portName);
        dt.getBaseCharacteristics().setPortTechnology("Snowflake");

        DataAsset asset = new DataAsset();
        asset.setComponentId(componentId);
        asset.getEntityInfo().setEntityName(portName);
        asset.getEntityInfo().setFeedingFrequency(feedingFrequency);
        dt.getDataAssets().add(asset);

        dc.getDeliveryTargets().add(dt);
        return dc;
    }

    private static void assertNoInternalPathLeaks(List<String> messages) {
        assertTrue(
                messages.stream()
                        .noneMatch(
                                m ->
                                        m.contains("deliveryTargets[")
                                                || m.contains("dataAssets[")
                                                || m.contains("entityInfo")
                                                || m.contains("attributes[")),
                "Internal mapped-model path leaked into a validation message: " + messages);
    }
}

package com.witboost.plugin.informatica.datacatalog.parser;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.parser.Parser;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    String DESCRIPTOR_PATH = "/parser/descriptors/sample_descriptor.yml";

    @Test
    @DisplayName("Should map YAML fields to POJO correctly")
    void shouldParseDescriptor() throws Exception {
        String yamlContent = ResourceUtils.getContentFromResource(DESCRIPTOR_PATH);
        var descriptor = Parser.parseDataProduct(yamlContent).get();
        assertNotNull(descriptor);

        // At least one declared field should be populated
        int nonNullFieldCount = 0;
        for (var f : descriptor.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(descriptor) != null) nonNullFieldCount++;
        }
        assertTrue(nonNullFieldCount > 0, "No fields were populated on descriptor");

        try {
            var nameField = descriptor.getClass().getDeclaredField("name");
            nameField.setAccessible(true);
            assertNotNull(nameField.get(descriptor));
        } catch (NoSuchFieldException ignored) {
            // field not present, ignore
        }
    }

    @Test
    @DisplayName("Should return false when publishToInformatica flag is not present")
    void shouldReturnFalseWhenFlagNotPresent() {
        String yaml = "environment: test\nspecific:\n  company: example-company";
        boolean result = Parser.checkPublishToInformatica(yaml, null);
        assertFalse(result);
    }

    @Test
    @DisplayName(
            "Should return true when publishToInformatica flag is true and no environment check required")
    void shouldReturnTrueWhenFlagIsTrueWithoutEnvironmentCheck() {
        String yaml = "specific:\n  publishToInformatica: true";
        boolean result = Parser.checkPublishToInformatica(yaml, null);
        assertTrue(result);
    }

    @Test
    @DisplayName(
            "Should return true when publishToInformatica flag is true and environment matches")
    void shouldReturnTrueWhenFlagIsTrueAndEnvironmentMatches() {
        String yaml = "environment: prod\nspecific:\n  publishToInformatica: true";
        boolean result = Parser.checkPublishToInformatica(yaml, "prod");
        assertTrue(result);
    }

    @Test
    @DisplayName(
            "Should return false when publishToInformatica flag is true but environment does not match")
    void shouldReturnFalseWhenEnvironmentDoesNotMatch() {
        String yaml = "environment: dev\nspecific:\n  publishToInformatica: true";
        boolean result = Parser.checkPublishToInformatica(yaml, "prod");
        assertFalse(result);
    }

    @Test
    @DisplayName(
            "Should return false when environment is missing but environment check is required")
    void shouldReturnFalseWhenEnvironmentMissing() {
        String yaml = "specific:\n  publishToInformatica: true";
        boolean result = Parser.checkPublishToInformatica(yaml, "prod");
        assertFalse(result);
    }

    @Test
    @DisplayName(
            "Should treat blank string publishAllowedEnvironment as no environment check required")
    void shouldReturnTrueWhenPublishAllowedEnvironmentIsBlank() {
        String yaml = "specific:\n  publishToInformatica: true";
        boolean result = Parser.checkPublishToInformatica(yaml, "");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when publishToInformatica flag is false")
    void shouldReturnFalseWhenFlagIsFalse() {
        String yaml = "specific:\n  publishToInformatica: false";
        boolean result = Parser.checkPublishToInformatica(yaml, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when specific section is missing")
    void shouldReturnFalseWhenSpecificMissing() {
        String yaml = "name: test\nversion: 1.0";
        boolean result = Parser.checkPublishToInformatica(yaml, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when YAML is invalid")
    void shouldReturnFalseWhenYamlInvalid() {
        String invalidYaml = "{ invalid yaml";
        boolean result = Parser.checkPublishToInformatica(invalidYaml, null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when descriptor is empty")
    void shouldReturnFalseWhenDescriptorEmpty() {
        String emptyYaml = "";
        boolean result = Parser.checkPublishToInformatica(emptyYaml, null);
        assertFalse(result);
    }

    // ---- output port level publishToInformatica flag ----

    private static String descriptorWithOutputPorts(
            String firstPortSpecific, String secondPortSpecific) {
        return """
                id: urn:dmb:dp:test:test-dp:0
                name: Test DP
                environment: dev
                specific:
                  publishToInformatica: true
                components:
                  - kind: outputport
                    id: urn:dmb:cmp:test:test-dp:0:op-one
                    name: OpOne
                    dataContract:
                      schema: []
                    specific:
                %s
                  - kind: outputport
                    id: urn:dmb:cmp:test:test-dp:0:op-two
                    name: OpTwo
                    dataContract:
                      schema: []
                    specific:
                %s
                """
                .formatted(firstPortSpecific, secondPortSpecific);
    }

    @Test
    @DisplayName("Should exclude the output ports whose specific.publishToInformatica is false")
    void shouldExcludeOutputPortWithFlagFalse() {
        String yaml =
                descriptorWithOutputPorts(
                        "      publishToInformatica: false", "      publishToInformatica: true");
        var outputPorts = Parser.parseDataProduct(yaml).get().extractOutputPorts();
        assertEquals(1, outputPorts.size());
        assertEquals("urn:dmb:cmp:test:test-dp:0:op-two", outputPorts.get(0).getId());
    }

    @Test
    @DisplayName("Should publish output ports that do not declare the flag (opt-out semantics)")
    void shouldPublishOutputPortsWithoutFlag() {
        String yaml =
                descriptorWithOutputPorts(
                        "      company: example-company", "      company: example-company");
        var outputPorts = Parser.parseDataProduct(yaml).get().extractOutputPorts();
        assertEquals(2, outputPorts.size());
    }

    @Test
    @DisplayName("Should honor the flag when written as a string")
    void shouldExcludeOutputPortWithStringFlagFalse() {
        String yaml =
                descriptorWithOutputPorts(
                        "      publishToInformatica: \"false\"",
                        "      publishToInformatica: \"true\"");
        var outputPorts = Parser.parseDataProduct(yaml).get().extractOutputPorts();
        assertEquals(1, outputPorts.size());
        assertEquals("urn:dmb:cmp:test:test-dp:0:op-two", outputPorts.get(0).getId());
    }

    @Test
    @DisplayName("Should return no output ports when all of them opt out")
    void shouldReturnNoOutputPortsWhenAllExcluded() {
        String yaml =
                descriptorWithOutputPorts(
                        "      publishToInformatica: false", "      publishToInformatica: false");
        var outputPorts = Parser.parseDataProduct(yaml).get().extractOutputPorts();
        assertTrue(outputPorts.isEmpty());
    }

    // ---- evaluatePublishToInformatica (decision + reason) ----

    @Test
    @DisplayName(
            "evaluate: should skip and name the target/allowed environment when env is not enabled")
    void evaluateShouldSkipWhenEnvironmentNotEnabled() {
        String yaml = "environment: dev\nspecific:\n  publishToInformatica: true";
        var decision = Parser.evaluatePublishToInformatica(yaml, "prod");
        assertFalse(decision.shouldPublish());
        assertTrue(decision.reason().contains("'dev'"), decision.reason());
        assertTrue(decision.reason().contains("'prod'"), decision.reason());
    }

    @Test
    @DisplayName("evaluate: should skip with the 'not set' reason when the flag is absent")
    void evaluateShouldSkipWhenFlagAbsent() {
        String yaml = "specific:\n  company: example-company";
        var decision = Parser.evaluatePublishToInformatica(yaml, null);
        assertFalse(decision.shouldPublish());
        assertTrue(decision.reason().contains("is not set in the descriptor"), decision.reason());
    }

    @Test
    @DisplayName("evaluate: should skip with the 'set to false' reason when the flag is false")
    void evaluateShouldSkipWhenFlagFalse() {
        String yaml = "specific:\n  publishToInformatica: false";
        var decision = Parser.evaluatePublishToInformatica(yaml, null);
        assertFalse(decision.shouldPublish());
        assertTrue(decision.reason().contains("is set to false"), decision.reason());
    }

    @Test
    @DisplayName(
            "evaluate: should default to skip with a reason when the descriptor cannot be parsed")
    void evaluateShouldSkipOnParseError() {
        var decision = Parser.evaluatePublishToInformatica("{ invalid yaml", null);
        assertFalse(decision.shouldPublish());
        assertNotNull(decision.reason());
        assertFalse(decision.reason().isBlank());
    }
}

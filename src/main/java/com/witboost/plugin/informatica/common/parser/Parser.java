package com.witboost.plugin.informatica.common.parser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.model.witboost.Component;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.util.Collections;

public final class Parser {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Parser.class);

    private static final ObjectMapper om = new ObjectMapper(new YAMLFactory());

    static {
        om.registerModule(new Jdk8Module());
    }

    /**
     * Outcome of the publish-to-Informatica decision, carrying both the boolean choice and a
     * human-readable reason. The reason is meant to be surfaced in operation results / logs so a
     * skipped publish is no longer silent.
     *
     * @param shouldPublish true if the publish to Informatica must be performed
     * @param reason human-readable explanation of the decision (especially why a publish is
     *     skipped)
     */
    public record PublishDecision(boolean shouldPublish, String reason) {}

    /**
     * Evaluates whether the data product must be published to Informatica and explains why.
     *
     * <p>A publish is skipped when:
     *
     * <ul>
     *   <li>an allowed environment is configured and the descriptor's {@code environment} does not
     *       match it;
     *   <li>the {@code specific.publishToInformatica} flag is {@code false} or not present;
     *   <li>the descriptor cannot be parsed (defaults to skip with a generic reason).
     * </ul>
     *
     * @param yamlDescriptor YAML content as String
     * @param publishAllowedEnvironment if not null nor empty, the only environment for which
     *     publishing is enabled
     * @return the decision together with its motivation
     */
    public static PublishDecision evaluatePublishToInformatica(
            String yamlDescriptor, String publishAllowedEnvironment) {
        try {
            JsonNode root = om.readTree(yamlDescriptor);
            if (publishAllowedEnvironment != null && !publishAllowedEnvironment.isBlank()) {
                JsonNode environment = root.get("environment");
                String env =
                        (environment == null || environment.isNull()) ? null : environment.asText();
                if (env == null || !publishAllowedEnvironment.equals(env)) {
                    return new PublishDecision(
                            false,
                            String.format(
                                    "Publishing to Informatica skipped: target environment '%s' is not enabled for publishing"
                                            + " (publishing is enabled only for environment '%s').",
                                    env, publishAllowedEnvironment));
                }
            }
            JsonNode specific = root.get("specific");
            JsonNode publishToInformatica =
                    (specific == null || specific.isNull())
                            ? null
                            : specific.get("publishToInformatica");
            if (publishToInformatica == null || publishToInformatica.isNull()) {
                return new PublishDecision(
                        false,
                        "Publishing to Informatica skipped: 'specific.publishToInformatica' is not set in the descriptor.");
            }
            if (!publishToInformatica.asBoolean()) {
                return new PublishDecision(
                        false,
                        "Publishing to Informatica skipped: 'specific.publishToInformatica' is set to false.");
            }
            return new PublishDecision(
                    true, "Publishing to Informatica is enabled for this data product.");
        } catch (Exception e) {
            logger.warn("Error checking publishToInformatica flag. Defaulting to skip", e);
            return new PublishDecision(
                    false,
                    "Publishing to Informatica skipped: unable to read the publish configuration from the descriptor.");
        }
    }

    /**
     * Checks if the publishToInformatica flag is set in the YAML descriptor and, optionally, that
     * the environment matches the allowed environment in the configuration.
     *
     * @param yamlDescriptor YAML content as String
     * @param publishAllowedEnvironment if not null nor empty, the allowed environment
     * @return true if the check condition is fulfilled, false otherwise. Returns false by default
     *     if check condition is not fulfilled or if an error occurs during parsing.
     */
    public static boolean checkPublishToInformatica(
            String yamlDescriptor, String publishAllowedEnvironment) {
        return evaluatePublishToInformatica(yamlDescriptor, publishAllowedEnvironment)
                .shouldPublish();
    }

    /**
     * Evaluates whether a single component (output port) must be published to Informatica, reading
     * its {@code specific.publishToInformatica} flag.
     *
     * <p>Unlike the data product level flag, here the flag is an <b>opt-out</b>: a component
     * without it is published, so existing descriptors keep working. Only an explicit {@code false}
     * excludes the component from validation and provisioning.
     *
     * @param component the raw component node taken from the descriptor's {@code components} list
     * @return true if the component must be published to Informatica
     */
    public static boolean isComponentPublishEnabled(JsonNode component) {
        if (component == null || component.isNull()) {
            return true;
        }
        JsonNode specific = component.get("specific");
        JsonNode flag =
                (specific == null || specific.isNull())
                        ? null
                        : specific.get("publishToInformatica");
        if (flag == null || flag.isNull()) {
            return true;
        }
        boolean publish =
                flag.isTextual()
                        ? !"false".equalsIgnoreCase(flag.asText().trim())
                        : flag.asBoolean(true);
        if (!publish) {
            JsonNode id = component.get("id");
            logger.info(
                    "Component '{}' skipped: 'specific.publishToInformatica' is set to false.",
                    id == null ? "unknown" : id.asText());
        }
        return publish;
    }

    /**
     * Reads the optional {@code specific.validationLevel} value from the descriptor as a raw
     * string. Returned uppercased and trimmed; the caller maps it to a known level.
     *
     * @param yamlDescriptor YAML content as String
     * @return the raw level string if present, empty otherwise (including on parse errors)
     */
    public static java.util.Optional<String> readValidationLevel(String yamlDescriptor) {
        try {
            JsonNode specific = om.readTree(yamlDescriptor).get("specific");
            if (specific == null || specific.isNull()) {
                return java.util.Optional.empty();
            }
            JsonNode level = specific.get("validationLevel");
            if (level == null || level.isNull() || !level.isTextual() || level.asText().isBlank()) {
                return java.util.Optional.empty();
            }
            return java.util.Optional.of(level.asText().trim().toUpperCase());
        } catch (Exception e) {
            logger.warn("Error reading validationLevel, ignoring descriptor override", e);
            return java.util.Optional.empty();
        }
    }

    /**
     * Parse content from a String and map it to the given target type.
     *
     * @param yamlDescriptor YAML content as String
     * @return parsed data product object or failed operation with details
     */
    public static Either<FailedOperation, DataProduct> parseDataProduct(String yamlDescriptor) {
        return Try.of(
                        () -> {
                            var dataProduct = om.readValue(yamlDescriptor, DataProduct.class);
                            dataProduct.setRawDataProduct(om.readTree(yamlDescriptor));
                            return dataProduct;
                        })
                .toEither()
                .mapLeft(
                        t -> {
                            String errorMessage =
                                    "Failed to deserialize the Yaml Descriptor. Details: "
                                            + t.getMessage();
                            logger.error(errorMessage, t);
                            return new FailedOperation(
                                    Collections.singletonList(new Problem(errorMessage, t)));
                        });
    }

    public static <U> Either<FailedOperation, Component<U>> parseComponent(
            JsonNode node, Class<U> specificClass) {
        return Try.of(
                        () -> {
                            JavaType javaType =
                                    om.getTypeFactory()
                                            .constructParametricType(
                                                    Component.class, specificClass);
                            var component = om.<Component<U>>readValue(node.toString(), javaType);
                            component.setRawComponent(node);
                            return component;
                        })
                .toEither()
                .mapLeft(
                        t -> {
                            String errorMessage =
                                    "Failed to deserialize the component. Details: "
                                            + t.getMessage();
                            logger.error(errorMessage, t);
                            return new FailedOperation(
                                    Collections.singletonList(new Problem(errorMessage, t)));
                        });
    }
}

package com.witboost.plugin.informatica.policies;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runs the CUE policies (the {@code policies/} folder) against the descriptors by invoking the
 * {@code cue} binary.
 *
 * <p><b>Manual</b>: gated by {@code -Dpolicy.tests=true}, so the routine CI {@code mvn test} skips
 * it and cannot break. To run it by hand:
 *
 * <pre>
 *   mvn test -Dtest=PolicyCueValidationTest -Dpolicy.tests=true
 * </pre>
 *
 * <p>The {@code cue} binary is resolved in this order: {@code -Dcue.bin=/path}, a {@code cue} on
 * the PATH, otherwise downloaded into {@code target/policy-cue/} from the GitHub releases (no
 * system install). If it cannot be obtained (offline) the test self-skips.
 */
@EnabledIfSystemProperty(named = "policy.tests", matches = "true")
@DisplayName("CUE policy validation (manual: -Dpolicy.tests=true)")
class PolicyCueValidationTest {

    private static final Logger log = LoggerFactory.getLogger(PolicyCueValidationTest.class);

    private static final Path PROJECT_ROOT = Path.of(System.getProperty("user.dir"));
    private static final Path POLICIES_DIR = PROJECT_ROOT.resolve("policies");
    private static final Path WORK_DIR = PROJECT_ROOT.resolve("target/policy-cue");
    private static final String CUE_VERSION = System.getProperty("cue.version", "0.11.1");

    private static final ObjectMapper YAML = new ObjectMapper(new YAMLFactory());
    private static final ObjectMapper JSON = new ObjectMapper();

    private static Path cueBin;

    @BeforeAll
    static void setUp() throws Exception {
        Assumptions.assumeTrue(
                Files.isDirectory(POLICIES_DIR), "policies/ folder not found: " + POLICIES_DIR);
        Files.createDirectories(WORK_DIR);
        cueBin = resolveCueBinary();
        Assumptions.assumeTrue(
                cueBin != null, "cue binary not available (offline?) — test skipped");
        log.info("Using cue: {}", cueBin);
    }

    @Test
    @DisplayName("The conformant descriptor passes all policies")
    void conformantDescriptorPassesAllPolicies() throws Exception {
        List<VetResult> results = runAllPolicies(resource("policies/descriptor_ok.yml"));
        List<VetResult> failures = results.stream().filter(r -> !r.passed()).toList();
        log.info("\n{}", report("descriptor_ok.yml", results));
        assertTrue(
                failures.isEmpty(),
                "Unexpected violations on the conformant descriptor:\n"
                        + failures.stream()
                                .map(VetResult::describe)
                                .reduce("", (a, b) -> a + "\n" + b));
    }

    @Test
    @DisplayName("The descriptor with violations is rejected by the expected policies")
    void nonConformantDescriptorIsRejected() throws Exception {
        List<VetResult> results = runAllPolicies(resource("policies/descriptor_ko.yml"));
        log.info("\n{}", report("descriptor_ko.yml", results));
        Set<String> failingPolicies =
                results.stream()
                        .filter(r -> !r.passed())
                        .map(r -> r.policy)
                        .collect(java.util.stream.Collectors.toSet());
        for (String expected :
                List.of(
                        "informatica_dataproduct_metadata_policy",
                        "informatica_outputport_identification_metadata_policy",
                        "informatica_outputport_access_metadata_policy",
                        "informatica_outputport_ingestion_metadata_policy",
                        "informatica_outputport_schema_metadata_policy")) {
            assertTrue(
                    failingPolicies.contains(expected),
                    "Expected policy '"
                            + expected
                            + "' to fail, but it passed. Failing: "
                            + failingPolicies);
        }
    }

    @Test
    @DisplayName("Report over the real descriptors (no assertion, logs only)")
    void reportOnRealDescriptors() throws Exception {
        Path descriptors = PROJECT_ROOT.resolve("src/test/resources/descriptors");
        try (var stream = Files.list(descriptors)) {
            List<Path> files = stream.filter(p -> p.toString().endsWith(".yml")).sorted().toList();
            StringBuilder sb =
                    new StringBuilder("\n===== Policy report over the real descriptors =====");
            for (Path d : files) {
                try {
                    sb.append("\n").append(report(d.getFileName().toString(), runAllPolicies(d)));
                } catch (Exception e) {
                    sb.append("\n")
                            .append(d.getFileName())
                            .append(": ERROR ")
                            .append(e.getMessage());
                }
            }
            log.info(sb.toString());
        }
    }

    // ---- core ----------------------------------------------------------------

    private List<VetResult> runAllPolicies(Path descriptorYaml) throws Exception {
        JsonNode root = YAML.readTree(Files.readString(descriptorYaml));
        String stem = descriptorYaml.getFileName().toString().replaceAll("\\.ya?ml$", "");
        List<VetResult> results = new ArrayList<>();

        // data-product-level policies -> applied to the descriptor root
        Path rootJson = writeJson(root, stem + "_root");
        for (Path policy : policies("informatica_dataproduct_")) {
            results.add(vet(policy, "dataproduct", rootJson));
        }

        // output-port-level policies -> applied to every kind=outputport component
        List<JsonNode> outputPorts = outputPorts(root);
        List<Path> opPolicies = policies("informatica_outputport_");
        for (int i = 0; i < outputPorts.size(); i++) {
            Path opJson = writeJson(outputPorts.get(i), stem + "_op" + i);
            String target = "outputport[" + i + "]";
            for (Path policy : opPolicies) {
                results.add(vet(policy, target, opJson));
            }
        }
        return results;
    }

    private VetResult vet(Path policy, String target, Path dataJson)
            throws IOException, InterruptedException {
        // cue requires the .cue extension: copy the policy into target/ with that extension
        Path cuePolicy = WORK_DIR.resolve(policy.getFileName() + ".cue");
        Files.copy(policy, cuePolicy, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        ProcessBuilder pb =
                new ProcessBuilder(
                        cueBin.toString(), "vet", dataJson.toString(), cuePolicy.toString());
        pb.redirectErrorStream(true);
        Process p = pb.start();
        String output = new String(p.getInputStream().readAllBytes());
        int code = p.waitFor();
        return new VetResult(policy.getFileName().toString(), target, code, output.strip());
    }

    private List<Path> policies(String prefix) throws IOException {
        try (var s = Files.list(POLICIES_DIR)) {
            return s.filter(p -> p.getFileName().toString().startsWith(prefix)).sorted().toList();
        }
    }

    private List<JsonNode> outputPorts(JsonNode root) {
        List<JsonNode> ops = new ArrayList<>();
        JsonNode components = root.get("components");
        if (components != null && components.isArray()) {
            for (JsonNode c : components) {
                if ("outputport".equals(c.path("kind").asText())) ops.add(c);
            }
        }
        return ops;
    }

    private Path writeJson(JsonNode node, String name) throws IOException {
        Path file = WORK_DIR.resolve(name + ".json");
        Files.writeString(file, JSON.writerWithDefaultPrettyPrinter().writeValueAsString(node));
        return file;
    }

    private Path resource(String relative) {
        return PROJECT_ROOT.resolve("src/test/resources").resolve(relative);
    }

    private static String report(String descriptor, List<VetResult> results) {
        StringBuilder sb = new StringBuilder("Descriptor: ").append(descriptor);
        for (VetResult r : results) {
            sb.append("\n  ")
                    .append(r.passed() ? "PASS" : "FAIL")
                    .append("  ")
                    .append(r.target)
                    .append("  ")
                    .append(r.policy);
            if (!r.passed() && !r.output.isBlank()) {
                sb.append("\n        ").append(r.output.replace("\n", "\n        "));
            }
        }
        return sb.toString();
    }

    private record VetResult(String policy, String target, int exitCode, String output) {
        boolean passed() {
            return exitCode == 0;
        }

        String describe() {
            return target + "  " + policy + (output.isBlank() ? "" : " -> " + output);
        }
    }

    // ---- cue binary resolution / download ------------------------------------

    private static Path resolveCueBinary() {
        String override = System.getProperty("cue.bin");
        if (override != null && Files.isExecutable(Path.of(override))) return Path.of(override);

        Path cached = WORK_DIR.resolve(binaryName());
        if (Files.isExecutable(cached)) return cached;

        if (isOnPath("cue")) return Path.of("cue");

        try {
            return download();
        } catch (Exception e) {
            log.warn("cue download failed: {}", e.toString());
            return null;
        }
    }

    private static boolean isOnPath(String cmd) {
        try {
            Process p = new ProcessBuilder(cmd, "version").redirectErrorStream(true).start();
            return p.waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static Path download() throws IOException, InterruptedException {
        String os =
                System.getProperty("os.name", "").toLowerCase().contains("mac")
                        ? "darwin"
                        : "linux";
        String rawArch = System.getProperty("os.arch", "").toLowerCase();
        String arch = (rawArch.contains("aarch64") || rawArch.contains("arm")) ? "arm64" : "amd64";
        String asset = "cue_v%s_%s_%s.tar.gz".formatted(CUE_VERSION, os, arch);
        URI uri =
                URI.create(
                        "https://github.com/cue-lang/cue/releases/download/v%s/%s"
                                .formatted(CUE_VERSION, asset));

        Path tarball = WORK_DIR.resolve(asset);
        log.info("Downloading cue from {}", uri);
        HttpClient client =
                HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpResponse<InputStream> resp =
                client.send(
                        HttpRequest.newBuilder(uri).GET().build(),
                        HttpResponse.BodyHandlers.ofInputStream());
        if (resp.statusCode() != 200) {
            throw new IOException("HTTP " + resp.statusCode() + " while downloading " + uri);
        }
        try (InputStream in = resp.body()) {
            Files.copy(in, tarball, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        // extract with tar (available on mac/linux), simpler than handling tar.gz in pure Java
        Process tar =
                new ProcessBuilder("tar", "-xzf", tarball.toString(), "-C", WORK_DIR.toString())
                        .redirectErrorStream(true)
                        .start();
        String tarOut = new String(tar.getInputStream().readAllBytes());
        if (tar.waitFor() != 0) throw new IOException("tar extraction failed: " + tarOut);

        Path bin = WORK_DIR.resolve(binaryName());
        if (!Files.exists(bin)) throw new IOException("cue binary not found after extraction");
        try {
            Files.setPosixFilePermissions(
                    bin,
                    EnumSet.of(
                            PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_READ,
                            PosixFilePermission.GROUP_EXECUTE,
                            PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_EXECUTE));
        } catch (UnsupportedOperationException ignored) {
            // non-POSIX filesystem: ignore
        }
        return bin;
    }

    private static String binaryName() {
        return System.getProperty("os.name", "").toLowerCase().contains("win") ? "cue.exe" : "cue";
    }
}

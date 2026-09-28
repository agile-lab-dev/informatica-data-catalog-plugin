# Witboost Informatica Plugin

This project is a Java 17 Spring Boot adapter that publishes a Witboost Data Product Descriptor to Informatica Data Catalog and Data Marketplace.

## Overview

The adapter exposes the Catalog and Marketplace contracts in `src/main/resources/openapi/` and maps one descriptor to both Informatica products:

| Witboost object | Data Catalog | Data Marketplace |
| --- | --- | --- |
| Data product | Top-level system | Collection |
| Output port | Flat output port: Dataset; nested output port: System | Delivery target and data asset when `shoppable` is true |
| Data contract | Workbook metadata | Data asset metadata and custom attributes |

The provisioning workflow is opinionated, while ordinary mapping differences are configured with YAML. Advanced naming, value transformation, catalog source resolution, and workbook mapping can be replaced with Spring beans.

## Requirements

- Java 17
- Maven 3.9 or newer
- An Informatica tenant for live provisioning tests

The repository includes `.java-version` for `jenv` users:

```bash
jenv local 17
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

## Building

Run formatting checks, tests, and packaging with:

```bash
mvn verify
```

Format Java and resource files locally with:

```bash
mvn spotless:apply
```

GitLab CI runs the same work in separate check, test, and build stages on Java 17. Surefire reports and the packaged JAR are retained as job artifacts.

## Configuration

Runtime configuration is stored in `src/main/resources/application.yml`. Credentials and tenant-specific values must come from deployment secrets or environment variables; no tenant identifier or credential is provided by default.

The default profile supports API endpoints, authentication settings, publication flags, descriptor topology, validation levels, technology-keyed Catalog sources, Marketplace categories, and custom attributes. Marketplace custom attribute mappings can provide the technical `informatica-id`; display names are not used as identifiers.

Catalog sources are optional per technology. Metadata synchronization runs only for configured and enabled sources.

### Descriptor input

The adapter receives the original Witboost Data Product Descriptor as YAML or JSON. It does not receive the internal Informatica model shown in the Java sources. The relevant shape is:

```yaml
id: urn:example:data-product:orders:1
name: Orders
description: Curated order data for analytics
version: 1.0.0
environment: development
kind: dataproduct
dataProductOwnerDisplayName: Example Owner
status: Draft
specific:
  publishToInformatica: true
  company: Example Organization
  businessDomain: Commercial
  businessSubdomain: Orders
  technicalOwners: example-team
  dataProductType: Source-aligned
  lifeCycleStatus: Draft
  sensitiveInfo: No
  confidentiality: Private
  memorizationType: No
  creationDate: 2026-01-01
  customAttributes:
    businessOwner: Example Owner
    dataClassification: Internal
components:
  - id: urn:example:component:orders:1
    kind: outputport
    name: Orders API
    description: Orders exposed for analytical consumption
    version: 1.0.0
    technology: example-technology
    outputPortType: table
    specific:
      publishToInformatica: true
      systemName: Orders System
      serverName: example-server
      databaseName: analytics
      schemaName: curated
      entityName: orders
      entityType: table
      isPii: false
      creationDate: 2026-01-01
    dataContract:
      schema:
        - name: order_id
          description: Stable order identifier
          dataType: string
        - name: order_date
          description: Order creation date
          dataType: date
```

The root `specific.publishToInformatica` flag is required and must be `true` for publication. If it is missing or `false`, the product is skipped. An optional component-level `specific.publishToInformatica: false` excludes only that output port. The environment can also be restricted with `INFORMATICA_PUBLISH_ALLOWED_ENV`.

Descriptor structure paths are configurable under `informatica.descriptor`. The standard defaults are `components`, `kind`, `id`, and `dataContract.schema`; only the root `specific.publishToInformatica` flag is fixed. An output port without children is imported as a Catalog Dataset. An output port with nested output-port children is imported as a Catalog System whose children are Datasets. Schema fields become Technical Data elements.

Marketplace publication is independent from Catalog publication. A node with `shoppable: true` creates one Delivery Target and one Data Asset; an explicit `shoppable: false` excludes that node from Marketplace while leaving it in Catalog. In a nested output port, parent and children are evaluated independently. A Data Product Collection is still created even when no node is shoppable.

The adapter uses these descriptor fields:

| Descriptor path | Purpose |
| --- | --- |
| `id`, `name`, `description`, `version` | Data product identity and Catalog/Marketplace names |
| `specific.company` | Root Marketplace category |
| `specific.businessDomain` | Second Marketplace category |
| `specific.businessSubdomain` | Third Marketplace category |
| `specific.customAttributes` | Generic values mapped to Marketplace custom attributes |
| `components[]` | Candidate output ports and data assets |
| `components[].technology` | Catalog source key and Marketplace delivery template key |
| `components[].specific.systemName`, `databaseName`, `schemaName`, `entityName` | Technical Catalog location |
| `components[].specific.dataContract.schema` | Dataset columns and data contract metadata |

Storage components can remain in the descriptor, but only output-port and data-asset components are published by this adapter.

### Customizing `application.yml`

Start from `src/main/resources/application.yml` and override values through a profile or environment variables. Keep credentials outside the file:

```yaml
informatica:
  api:
    base-url: https://example-idmc.example.com
    username: ${INFORMATICA_DCMP_USERNAME}
    password: ${INFORMATICA_DCMP_PASSWORD}
    category-validation-enabled: true
    mapping:
      categories:
        - descriptor-path: company
        - descriptor-path: businessDomain
        - descriptor-path: businessSubdomain
      custom-attributes:
        Business Owner:
          descriptor-path: businessOwner
          required: true
        Classification:
          descriptor-path: dataClassification
          default-value: Internal
          transformer: identity
  data-catalog:
    catalog-source:
      enable-metadata-sync: true
      sources:
        example-technology: EXAMPLE_CATALOG_SOURCE
        another-technology: ANOTHER_CATALOG_SOURCE
    validation-level:
      level: LOW
      by-environment:
        development: LOW
        production: HIGH
```

`mapping.categories` defines the category hierarchy in order. Each `descriptor-path` is relative to `specific`; the configured levels must exist in the descriptor and in Informatica. `mapping.custom-attributes` maps a configured key to a descriptor path. Prefer the technical Informatica ID explicitly:

```yaml
custom-attributes:
  Business Owner:
    informatica-id: com.infa.odin.models.custom.ca_example
    descriptor-path: businessOwner
    required: true
```

`required`, `default-value`, and `transformer` control validation and conversion. The built-in transformer is `identity`; custom transformers are Spring beans implementing `DescriptorValueTransformer`. Where Informatica provides custom-attribute discovery, the API can be used to verify the configured ID, but the display name is never the application identifier.

`data-catalog.catalog-source.sources` is a map keyed by the exact lower-case output-port technology. A missing source causes validation to fail for that output port. Set `enable-metadata-sync` to `false` when source synchronization is not part of the deployment.

Useful environment overrides include `INFORMATICA_BASE_URL`, `INFORMATICA_DCMP_USERNAME`, `INFORMATICA_DCMP_PASSWORD`, `INFORMATICA_MP_BASE_URL`, `INFORMATICA_DC_BASE_URL`, `INFORMATICA_DC_ENABLE_METADATA_SYNC`, `INFORMATICA_DC_VALIDATION_LEVEL`, and `INFORMATICA_PUBLISH_ALLOWED_ENV`.

## Publication controls

Set `specific.publishToInformatica: false` to disable publication for a data product. An output port can set the same flag to exclude only that output port. The output-port default is publication.

## Running locally

Provide credentials and API settings through environment variables, then start the application:

```bash
mvn spring-boot:run
```

Unit and deterministic tests do not require external services. Live integration tests are opt-in and require a separately configured Informatica tenant.

## Security and data handling

Do not commit credentials, tenant IDs, private URLs, or production descriptors. Treat credentials previously committed to repository history as compromised, rotate them, and scan the complete publication history before release.

Vendor API reference material is not part of the OSS distribution unless its redistribution license has been verified. Use the official Informatica documentation for current API details.

## License

This project is intended for release under the Apache License 2.0. Add the approved `LICENSE` file before publishing a release.

## About Witboost

[Witboost](https://www.witboost.com) is a data experience platform for productizing, governing, and discovering data products across technology platforms.

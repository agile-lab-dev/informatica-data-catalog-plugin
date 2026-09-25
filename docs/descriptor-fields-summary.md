# Descriptor Fields and Mapping

This document summarizes the descriptor fields consumed by the Informatica plugin and explains which parts of the mapping are configurable.

The descriptor is the original Witboost Data Product Descriptor. The plugin parses it and maps it to internal Informatica Data Catalog and Data Marketplace models. Callers should provide the Witboost descriptor, not the internal Informatica models.

## Mapping configurability

Mapping is configurable, but not entirely data-driven.

### Configurable through YAML

The following settings are controlled by `informatica.marketplace.mapping`:

- `categories`: the Marketplace category hierarchy. Each `descriptor-path` is resolved relative to the descriptor `specific` object and is evaluated in list order.
- `custom-attributes`: maps an Informatica custom attribute name to a descriptor path. Each mapping can define `required`, `default-value`, and `transformer`.

Example:

```yaml
informatica:
  marketplace:
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
```

Custom attribute IDs are resolved at runtime by Informatica attribute name. They are not hardcoded in the descriptor or in application configuration.

The built-in value transformer is `identity`. Additional transformers can be provided as Spring beans implementing `DescriptorValueTransformer`; their `name()` must match the configured `transformer` value.

### Fixed in the standard mapper

The core mapping between descriptor fields and Informatica models is implemented in Java (`DataContractMapper`, `DeliveryTargetMapper`, and `DataAssetMapper`). This includes:

- Data Product identity and status;
- Data Product reference context and additional information;
- Output Port and Data Asset structure;
- technical system, database, schema, and entity information;
- data contract columns;
- vocabulary normalization and default values;
- grouping Output Ports by technology into Marketplace Delivery Targets.

These mappings are not changed by the Marketplace YAML mapping. Advanced behavior can be changed by replacing or extending the relevant Spring mapper or service beans.

## Data Product fields

Fields without a prefix are at descriptor root. Fields under `specific` are shown with the `specific.` prefix.

| Field | Required | Notes and destination |
|---|---|---|
| `id` | Yes | Data Product identifier. Mapped to the Data Catalog system reference ID. |
| `name` | Yes | Data Product name. Mapped to the Data Catalog system and Marketplace collection. |
| `description` | Yes | Description for the Data Catalog system and Marketplace collection. |
| `dataProductOwnerDisplayName` | Yes | Marketplace Product Owner. |
| `version` | Yes | Data Product version. |
| `status` | No | `Published` is preserved; every other value is normalized to `Unpublished`. |
| `fullyQualifiedName` | No | Data Catalog long name. The product name is used when no FQN is available. |
| `specific.company` | Yes | First Marketplace category by default. |
| `specific.businessDomain` | Yes | Second Marketplace category by default. |
| `specific.businessSubdomain` | Yes | Third Marketplace category by default. |
| `specific.technicalOwners` | Yes | Technical owners in the Data Contract and Marketplace collection. |
| `specific.defaultDelivery` | Yes | Default delivery value for the Marketplace collection. |
| `specific.dataProductType` | Yes | Normalized to `Source-aligned`, `Aggregated`, or `Consumer-aligned`. |
| `specific.lifeCycleStatus` | Yes | Lifecycle value, such as `Draft`, `Active`, or `Deprecated`. |
| `specific.sensitiveInfo` | Yes | Normalized to `Si` or `No`. Boolean and common yes/no variants are accepted. |
| `specific.creationDate` | Yes | Data Product creation date. |
| `specific.certifiedUse` | No | Defaults to `Generic`. Supported values include `Generic`, `Analysis`, `AI Model`, and `Regoletory`. |
| `specific.confidentiality` | No | Defaults to `Private`. Supported values are `Public`, `Private`, and `Reserved`. |
| `specific.memorizationType` | No | Defaults to `Si`. Supported values are `Si`, `No`, and `In identification`. |
| `specific.suspensionDate` | No | Suspension date. |
| `specific.deprecationDate` | No | Deprecation date. |
| `specific.endDate` | No | End-of-validity date. |
| `specific.releaseDate` | No | Release date. |
| `specific.norms` | No | Applicable regulations or standards. |
| `specific.outsourcer` | No | External provider, when applicable. |
| `specific.quality` | No | Data quality label. |
| `specific.contacts` | No | Product contact information. |
| `specific.termsOfUse` | No | Usage terms and limitations. |
| `specific.metadataDetails` | No | Additional metadata details. |
| `specific.linkDocumentation` | No | Documentation URL. |
| `specific.linkObservabilityPort` | No | Observability URL. |
| `specific.linkDataQualityPort` | No | Data quality URL. |
| `specific.customAttributes` | No | Additional values available for Marketplace custom-attribute mapping. |

## Output Port fields

Output Ports are read from `components[]` entries whose `kind` is `outputport` or `data-asset`. Storage components may remain in the descriptor but are not published as Output Ports.

| Field | Required | Notes and destination |
|---|---|---|
| `id` | Yes | Component identifier. Used to identify the Data Catalog Data Asset. |
| `name` | Yes | Output Port name. |
| `description` | Yes | Output Port and Data Asset description. |
| `version` | Yes | Output Port version. |
| `technology` | Yes | Output Port technology. It also selects the Catalog source and Delivery Template. |
| `creationDate` | Yes | Output Port creation date. |
| `specific.systemName` | Yes | Data Catalog system. |
| `specific.databaseName` | Yes | Data Catalog database. |
| `specific.schemaName` | Yes | Data Catalog schema. |
| `specific.serverName` | No | Data Catalog server or host. |
| `specific.entityName` | Yes | Table or view name. |
| `specific.entityType` | Yes | Entity type, for example `table` or `view`. |
| `specific.feedingFrequency` | Yes | Normalized frequency, for example `Giornaliero`, `Mensile`, `Live`, or `On-demand`. |
| `specific.feedingType` | No | `PUSH` or `PULL`. |
| `specific.loadingMode` | No | `Merge`, `Upsert`, `Incremental`, or `Full`. |
| `specific.isPii` | Yes | PII flag for the Informatica Delivery Target. |
| `specific.managedCompanies` | No | Managed companies for the Data Catalog system. |
| `specific.managedData` | No | Managed data description for the Data Catalog system. |
| `specific.historicized` | No | Defaults to `false`. |
| `specific.manualProcess` | No | Defaults to `false`. |
| `specific.sensitiveData` | No | Defaults to `false`. |
| `specific.suspensionDate` | No | Output Port suspension date. |
| `specific.deprecationDate` | No | Output Port deprecation date. |
| `specific.qualityExpectations` | No | Quality expectations. |
| `specific.securityConsiderations` | No | Security considerations. |
| `specific.technicalSpecifications` | No | Technical specifications. |
| `specific.sla.refreshRate` | No | Normalized refresh rate, such as `Giornaliero` or `Mensile`. |
| `specific.sla.retentionRate` | No | Declared retention value. |
| `specific.retentionInfo` | No | Retention information for the Data Asset. |
| `specific.semanticLinks` | No | Semantic or glossary links. |

Output Ports are grouped by technology when creating Informatica Delivery Targets. Multiple Output Ports with the same technology become Data Assets under the same Delivery Target.

## Data contract columns

Columns are read from `dataContract.schema[]`.

| Field | Required | Notes and destination |
|---|---|---|
| `name` | Yes | Technical Element name. |
| `description` | Yes | Technical Element description. |
| `dataType` | Yes | Technical Element data type or domain. |
| `dataLength` | No | Column length. Invalid or missing values default to `0`. |
| `mandatory` | No | Defaults to `false`. |
| `primaryKey` | No | Defaults to `false`. |
| `sensitive` | No | Defaults to `false`. |
| `semanticLinks` | No | Semantic or glossary links. |
| `qualityControlLinks` | No | Links to quality controls. |

The `mandatory`, `primaryKey`, `sensitive`, `semanticLinks`, and `qualityControlLinks` values are read from the original column JSON and preserved during mapping.

## Publication and validation controls

These controls are separate from field mapping:

- `specific.publishToInformatica: true` enables publication for the Data Product.
- `components[].specific.publishToInformatica: false` excludes one Output Port.
- `informatica.publish_allowed_environment` can restrict publication by environment.
- `informatica.data-catalog.validation-level` controls offline and live validation depth.
- `informatica.data-catalog.catalog-source.sources` maps output-port technologies to Catalog sources.

See `README.md` and `src/main/resources/application.yml` for the complete runtime configuration.

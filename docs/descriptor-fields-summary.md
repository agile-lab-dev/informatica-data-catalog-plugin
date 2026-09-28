# Descriptor Fields and Mapping

This document summarizes the descriptor fields consumed by the Informatica plugin and explains which parts of the mapping are configurable.

The descriptor is the original Witboost Data Product Descriptor. The plugin parses it and maps it to internal Informatica Data Catalog and Data Marketplace models. Callers should provide the Witboost descriptor, not the internal Informatica models.

## Mapping configurability

Mapping is configurable, and the structural descriptor paths used by the neutral tree are configurable under `informatica.descriptor`. Children are always read from the local `components` field.

### Configurable through YAML

The following settings are controlled by `informatica.marketplace.mapping`:

- `categories`: the Marketplace category hierarchy. Each `descriptor-path` is resolved from the data-product root and evaluated in list order.
- `custom-attributes`: maps a technical Informatica attribute ID to a path from the data-product root. Each mapping can define `required`, `default-value`, and `transformer`.
- `informatica.descriptor`: configures discriminator, identifier, publication, shoppable, and schema paths relative to the node being processed.

Example:

```yaml
informatica:
  marketplace:
    mapping:
      categories:
        - descriptor-path: specific.company
        - descriptor-path: specific.businessDomain
        - descriptor-path: specific.businessSubdomain
      custom-attributes:
        com.infa.odin.models.custom.ca_business_owner:
          descriptor-path: specific.businessOwner
          required: true
        com.infa.odin.models.custom.ca_classification:
          descriptor-path: specific.dataClassification
          default-value: Internal
          transformer: identity
```

Path roots are explicit by configuration context:

| Configuration | Path root |
|---|---|
| `descriptor.data-product.*` | Data-product descriptor root |
| `descriptor.output-port.*` | Current output-port object |
| `descriptor.subcomponent.*` | Current subcomponent object |
| `marketplace.mapping.categories[]` | Data-product descriptor root |
| `marketplace.mapping.custom-attributes.*.descriptor-path` | Data-product descriptor root |

No path implicitly searches a parent or the descriptor root. Custom attribute map keys are sent directly as Informatica IDs; display-name discovery is not performed.

The built-in value transformer is `identity`. Additional transformers can be provided as Spring beans implementing `DescriptorValueTransformer`; their `name()` must match the configured `transformer` value.

### Fixed in the standard mapper

The core mapping between descriptor fields and Informatica models is implemented in Java (`DataContractMapper`, `DeliveryTargetMapper`, and `DataAssetMapper`). This includes:

- Data Product identity and status;
- Data Product reference context and additional information;
- Output Port and Data Asset structure;
- technical system, database, schema, and entity information;
- data contract columns;
- vocabulary normalization and default values;
- Catalog hierarchy: Data Product System, flat Output Port Dataset, nested Output Port System, child Dataset, and schema Technical Data element.
- Marketplace hierarchy: Data Collection for the Data Product and one Delivery Target/Data Asset for each shoppable node.

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

Output Ports are preserved independently. A flat output port is a Catalog Dataset. A nested output port is a Catalog System and each nested output port is a child Dataset. Marketplace Delivery Targets and Data Assets are created only for nodes with `shoppable: true`; parent and child flags are independent.

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

## Usage Examples

This section shows complete descriptor-to-Informatica mappings with concrete examples.

### Example 1: Simple Flat Output Port

**Input: Witboost Descriptor (minimal)**

```yaml
id: urn:dmb:dp:customers:customer-orders:1
name: Customer Orders Data Product
description: Order data for customer analytics
version: 1.0.0
dataProductOwnerDisplayName: Alice Johnson
environment: production
specific:
  company: Analytics
  businessDomain: Customer
  businessSubdomain: Orders
  technicalOwners: analytics-team
  creationDate: 2024-01-15
  dataProductType: Source-aligned
  lifeCycleStatus: Active
  sensitiveInfo: No
  
components:
  - kind: outputport
    id: urn:dmb:cmp:customers:customer-orders:snowflake-orders
    name: Snowflake Orders
    description: Customer order transactions
    version: 1.0.0
    technology: Snowflake
    dataContract:
      schema:
        - name: order_id
          dataType: INTEGER
          description: Unique order identifier
          dataLength: 10
          primaryKey: true
        - name: customer_name
          dataType: VARCHAR
          description: Customer full name
          dataLength: 100
        - name: order_date
          dataType: DATE
          description: Date order was placed
    specific:
      systemName: Sales System
      serverName: snowflake.company.com
      databaseName: ANALYTICS_DB
      schemaName: SALES
      entityName: orders
      entityType: Table
      feedingFrequency: Giornaliero
      feedingType: PUSH
      loadingMode: Full
      isPii: false
      historicized: true
      shoppable: true
```

**Configuration: application.yml**

```yaml
informatica:
  descriptor:
    data-product:
      kind-path: kind
      id-path: id
    output-port:
      kind-value: outputport
      shoppable-path: shoppable
    schema:
      path: dataContract.schema
```

**Output: Informatica Catalog Models**

```
DataContract {
  baseCharacteristics: {
    identifier: "urn:dmb:dp:customers:customer-orders:1"
    name: "Customer Orders Data Product"
    description: "Order data for customer analytics"
    productOwnerName: "Alice Johnson"
  }
  deliveryTargets: [
    DeliveryTarget {
      baseCharacteristics: {
        portName: "Snowflake Orders"
        portTechnology: "Snowflake"
        description: "Customer order transactions"
        version: "1.0.0"
      }
      catalogAssetType: DATASET  // Flat output port → DATASET
      shoppable: true
      dataAssets: [
        DataAsset {
          systemName: "Sales System"
          serverName: "snowflake.company.com"
          databaseName: "ANALYTICS_DB"
          schemaName: "SALES"
          entityName: "orders"
          attributes: [
            AttributeInfo { attributeName: "order_id", ... },
            AttributeInfo { attributeName: "customer_name", ... },
            AttributeInfo { attributeName: "order_date", ... }
          ]
        }
      ]
    }
  ]
  marketplaceDeliveryTargets: [
    // Same as deliveryTargets when shoppable: true
    DeliveryTarget { ... }
  ]
}
```

**Informatica Catalog Result:**
- System: "Customer Orders Data Product"
  - Dataset: "Snowflake Orders" (flat port = Dataset under Product System)
    - Technical Elements: order_id, customer_name, order_date

**Informatica Marketplace Result:**
- Data Collection: "Customer Orders Data Product"
  - Delivery Target: "Snowflake Orders"
    - Data Assets: 1 asset linking to Dataset in Catalog

---

### Example 2: Nested Output Ports (Hierarchical)

**Input: Witboost Descriptor with nested components**

```yaml
id: urn:dmb:dp:logistics:shipments:2
name: Shipment Logistics
description: End-to-end shipment tracking
version: 2.0.0
dataProductOwnerDisplayName: Bob Smith
environment: production
specific:
  company: Logistics
  businessDomain: Supply Chain
  businessSubdomain: Shipments
  technicalOwners: logistics-team
  creationDate: 2024-02-01
  dataProductType: Aggregated

components:
  - kind: outputport
    id: urn:dmb:cmp:logistics:shipments:container
    name: Shipment Container
    description: Container port with nested shipment data
    version: 2.0.0
    technology: BigQuery
    shoppable: true
    components:
      - kind: outputport
        id: urn:dmb:cmp:logistics:shipments:shipments
        name: Shipments
        description: Individual shipment records
        version: 2.0.0
        technology: BigQuery
        dataContract:
          schema:
            - name: shipment_id
              dataType: STRING
              description: Shipment identifier
              primaryKey: true
            - name: shipment_date
              dataType: DATE
              description: Shipment date
        specific:
          systemName: Logistics System
          serverName: bigquery.googleapis.com
          databaseName: LOGISTICS
          schemaName: SHIPMENTS
          entityName: shipments
          entityType: Table
          feedingFrequency: Giornaliero
          shoppable: true
        
      - kind: outputport
        id: urn:dmb:cmp:logistics:shipments:tracking
        name: Tracking
        description: Shipment tracking updates
        version: 2.0.0
        technology: BigQuery
        dataContract:
          schema:
            - name: tracking_id
              dataType: STRING
              description: Tracking identifier
              primaryKey: true
            - name: status
              dataType: STRING
              description: Current tracking status
        specific:
          systemName: Logistics System
          serverName: bigquery.googleapis.com
          databaseName: LOGISTICS
          schemaName: TRACKING
          entityName: tracking_events
          entityType: Table
          feedingFrequency: Live
          shoppable: false  // Parent shoppable, child not
```

**Configuration: application.yml (same as Example 1)**

**Output: Informatica Catalog Models**

```
DataContract {
  baseCharacteristics: {
    identifier: "urn:dmb:dp:logistics:shipments:2"
    name: "Shipment Logistics"
  }
  deliveryTargets: [
    DeliveryTarget {
      baseCharacteristics: { portName: "Shipment Container" }
      catalogAssetType: SYSTEM  // Container with children → SYSTEM
      shoppable: true
      dataAssets: [] // Container itself has no schema
    },
    DeliveryTarget {
      baseCharacteristics: { portName: "Shipments" }
      catalogAssetType: DATASET // Nested child port → DATASET
      shoppable: true
      dataAssets: [ DataAsset { entityName: "shipments", ... } ]
    },
    DeliveryTarget {
      baseCharacteristics: { portName: "Tracking" }
      catalogAssetType: DATASET // Nested child port → DATASET
      shoppable: false // Independent flag
      dataAssets: [ DataAsset { entityName: "tracking_events", ... } ]
    }
  ]
  marketplaceDeliveryTargets: [
    // Only shoppable: true nodes
    DeliveryTarget { portName: "Shipment Container", shoppable: true },
    DeliveryTarget { portName: "Shipments", shoppable: true }
    // Tracking is excluded because shoppable: false
  ]
}
```

**Informatica Catalog Result:**
- System: "Shipment Logistics"
  - System: "Shipment Container" (container = System)
    - Dataset: "Shipments" (child 1 = Dataset under System)
    - Dataset: "Tracking" (child 2 = Dataset under System)

**Informatica Marketplace Result:**
- Data Collection: "Shipment Logistics"
  - Delivery Target: "Shipment Container" (shoppable: true)
  - Delivery Target: "Shipments" (shoppable: true)
  - ~~Delivery Target: "Tracking"~~ (excluded: shoppable: false)

---

### Example 3: Marketplace Custom Attributes

**Input: Descriptor with custom attributes**

```yaml
id: urn:dmb:dp:finance:invoices:3
name: Finance Invoices
description: Invoice processing data
version: 3.0.0
dataProductOwnerDisplayName: Carol White
specific:
  company: Finance
  businessDomain: Accounting
  businessSubdomain: Invoices
  technicalOwners: finance-team
  creationDate: 2024-03-10
  customAttributes:
    Business Owner: carol.white@company.com
    Data Classification: Confidential
    Cost Center: FIN-2024

components:
  - kind: outputport
    id: urn:dmb:cmp:finance:invoices:postgres
    name: PostgreSQL Invoices
    version: 3.0.0
    technology: PostgreSQL
    shoppable: true
    dataContract:
      schema:
        - name: invoice_id
          dataType: VARCHAR
          description: Invoice identifier
          primaryKey: true
    specific:
      systemName: Finance System
      databaseName: FINANCE_DB
      schemaName: INVOICES
      entityName: invoices
      entityType: Table
      feedingFrequency: Giornaliero
      isPii: false
```

**Configuration: application.yml with custom attributes**

```yaml
informatica:
  marketplace:
    mapping:
      custom-attributes:
        attr-001:
          descriptor-path: specific.customAttributes.Business Owner
          required: true
        attr-002:
          descriptor-path: specific.customAttributes.Data Classification
          required: false
        attr-003:
          descriptor-path: specific.customAttributes.Cost Center
          default-value: UNASSIGNED
```

**Output: Informatica Marketplace**

```json
{
  "dataCollection": {
    "name": "Finance Invoices",
    "customAttributes": [
      {
        "id": "attr-001",
        "value": "carol.white@company.com"
      },
      {
        "id": "attr-002",
        "value": "Confidential"
      },
      {
        "id": "attr-003",
        "value": "FIN-2024"
      }
    ]
  },
  "deliveryTargets": [
    {
      "name": "PostgreSQL Invoices",
      "shoppable": true
    }
  ]
}
```

---

### Key Transformation Rules

| Input Pattern | Catalog Result | Marketplace Result |
|---|---|---|
| Output port without children | Dataset | Delivery Target (if shoppable) |
| Output port with children | System with child Datasets | Parent + shoppable children only |
| `shoppable: true` (default) | Included | Included |
| `shoppable: false` | Included | Excluded |
| Nested port shoppable ≠ parent | Independent evaluation | Parent and child filtered separately |
| Custom attributes provided | N/A | Map key used as technical Informatica ID |
| Missing dataLength | Defaults to 0 | — |
| No dataContract schema | Parent only, no assets | Can be container node |

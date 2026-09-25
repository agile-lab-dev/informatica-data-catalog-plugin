
Data element A single, atomic piece of information (a logical attribute) such as "Customer ID" or "Order Date". 
In Informatica Data Cloud a data element is a logical metadata artifact that represents a business concept and can be mapped to one or more physical fields/columns across sources and targets.  

Technical dataset The physical representation of data: a table, file, view or dataset in a source/target system, described by technical metadata (schema, column names/types, source system, location). It is what the platform catalogs, profiles and scans.  

Relation and usage in Informatica Data Cloud Technical datasets contain columns that are mapped to data elements. Data elements provide the business/semantic layer (glossary, reuse, lineage) while technical datasets provide the physical/technical layer (storage, schema, profiling, lineage).

## Adapter input

The adapter input is a Witboost Data Product Descriptor. The descriptor is not an Informatica API payload and is not the internal `DataContract` Java object. The adapter parses the root data product, reads `specific`, selects publishable entries from `components`, and maps the result to Catalog and Marketplace requests.

At minimum, a publishable descriptor provides:

- Root identity: `id`, `name`, `description`, `version`, `environment`, and `kind: dataproduct`.
- Publication and Marketplace context under `specific`: `publishToInformatica: true`, `company`, `businessDomain`, and `businessSubdomain`.
- At least one component with `kind: outputport` or `kind: data-asset`, a `technology`, and technical fields under `specific` such as `systemName`, `databaseName`, `schemaName`, `entityName`, and `entityType`.
- A component `dataContract.schema` list when Catalog column metadata is required.

`specific.customAttributes` is an open map for customer attributes. Its keys are descriptor-level names, not Informatica IDs. The application configuration maps those names to Informatica custom attribute names and resolves IDs from the tenant at runtime.

Example:

```yaml
specific:
	publishToInformatica: true
	company: Example Organization
	businessDomain: Commercial
	businessSubdomain: Orders
	customAttributes:
		Business Owner: Example Owner
components:
	- kind: outputport
		technology: example-technology
		specific:
			systemName: Orders System
			databaseName: analytics
			schemaName: curated
			entityName: orders
			entityType: table
		dataContract:
			schema:
				- name: order_id
					description: Stable order identifier
					dataType: string
```

The root publication flag is explicit: missing or `false` means skip. A component-level flag is an opt-out and only excludes that component. The full descriptor example and configuration reference are maintained in the repository README.
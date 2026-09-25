package com.witboost.plugin.informatica.marketplace.service.client;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.marketplace.model.CustomAttributesResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class CustomAttributeApiClientTest {

    @Autowired CustomAttributeApiClient customAttributeApiClient;

    @Test
    void getCustomAttributes() {
        CustomAttributesResponse response =
                customAttributeApiClient.getDataCollectionCustomAttributes();

        assertNotNull(response);
        assertNotNull(response.getItems());

        System.out.println("\n========================================");
        System.out.println("Custom Attributes Response:");
        System.out.println("========================================");
        System.out.println("Processing Time: " + response.getProcessingTime() + " ms");
        System.out.println("Offset: " + response.getOffset());
        System.out.println("Limit: " + response.getLimit());
        System.out.println("Total Count: " + response.getTotalCount());
        System.out.println("Class Type: " + response.getClassType());
        System.out.println("Items Count: " + response.getItems().size());
        System.out.println("\n--- Custom Attributes ---");

        response.getItems()
                .forEach(
                        item -> {
                            System.out.println("\n► Attribute: " + item.getName());
                            System.out.println("  ID: " + item.getCustomAttributeId());
                            System.out.println("  Status: " + item.getStatus());
                            System.out.println("  Mandatory: " + item.getMandatory());
                            System.out.println("  Searchable: " + item.getSearchable());
                            System.out.println("  Default Values: " + item.getDefaultValues());

                            if (item.getDatatype() != null) {
                                System.out.println("  Data Type:");
                                System.out.println("    Type: " + item.getDatatype().getType());
                                System.out.println(
                                        "    Properties: " + item.getDatatype().getProperties());

                                if (item.getDatatype().getDropDownOptions() != null
                                        && !item.getDatatype().getDropDownOptions().isEmpty()) {
                                    System.out.println("    Dropdown Options:");
                                    item.getDatatype()
                                            .getDropDownOptions()
                                            .forEach(
                                                    option -> {
                                                        System.out.println(
                                                                "      - Value: '"
                                                                        + option.getValue()
                                                                        + "', Label: '"
                                                                        + option.getLabel()
                                                                        + "'");
                                                    });
                                } else {
                                    System.out.println("    Dropdown Options: null");
                                }
                            }
                        });

        System.out.println("\n========================================\n");
    }

    @Test
    void getDataCollectionCustomAttributes() {
        CustomAttributesResponse response =
                customAttributeApiClient.getDataCollectionCustomAttributes();

        assertNotNull(response);
        assertEquals(CustomAttributesResponse.ClassType.DATA_COLLECTION, response.getClassType());

        System.out.println("\n✅ Data Collection Custom Attributes Retrieved:");
        System.out.println("   Total: " + response.getTotalCount());
        System.out.println("   Returned: " + response.getItems().size());
    }

    @Test
    void getAllCustomAttributes() {
        CustomAttributesResponse response =
                customAttributeApiClient.getAllCustomAttributes(
                        CustomAttributesResponse.ClassType.DATA_COLLECTION);

        assertNotNull(response);
        assertEquals(response.getItems().size(), response.getTotalCount());

        System.out.println("\n✅ All Custom Attributes Retrieved:");
        System.out.println("   Total Items: " + response.getTotalCount());
        System.out.println("   Items in Response: " + response.getItems().size());
    }

    @Test
    void getCustomAttributeByName() {
        // Get all attributes first to find a valid name
        CustomAttributesResponse allAttrs =
                customAttributeApiClient.getDataCollectionCustomAttributes();

        if (allAttrs.getItems() != null && !allAttrs.getItems().isEmpty()) {
            String searchName = allAttrs.getItems().get(0).getName();

            CustomAttributesResponse.CustomAttributeItem found =
                    customAttributeApiClient.getCustomAttributeByName(searchName);

            assertNotNull(found);
            assertEquals(searchName, found.getName());

            System.out.println("\n✅ Found Custom Attribute by Name:");
            System.out.println("   Name: " + found.getName());
            System.out.println("   ID: " + found.getCustomAttributeId());
        }
    }

    @Test
    void testGetCustomAttributeByName() {
        // Test with custom class type
        CustomAttributesResponse.CustomAttributeItem found =
                customAttributeApiClient.getCustomAttributeByName(
                        "TestAttribute", CustomAttributesResponse.ClassType.DATA_COLLECTION);

        // May be null if attribute doesn't exist
        if (found != null) {
            System.out.println("\n✅ Found: " + found.getName());
        } else {
            System.out.println("\n⚠️ Attribute 'TestAttribute' not found");
        }
    }
}

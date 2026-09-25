package com.witboost.plugin.informatica.marketplace.service.client;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.marketplace.model.CategoriesResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class CategoryApiClientTest {

    @Autowired CategoryApiClient categoryApiClient;

    @Test
    void getAllCategories() {
        // Create parameters for the getAllCategories API call
        String search = "*"; // Retrieve all categories
        String segments = "all"; // Return all category details

        // Call the API with the parameters
        CategoriesResponse categories = categoryApiClient.getAllCategories(search, segments);

        // Perform assertions
        assertNotNull(categories, "CategoriesResponse should not be null");
        assertNotNull(categories.getPageInfo(), "PageInfo should not be null");
        assertNotNull(categories.getItems(), "Items list should not be null");
    }
}

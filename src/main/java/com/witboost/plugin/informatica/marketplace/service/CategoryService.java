package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.marketplace.config.MarketplaceMappingProperties;
import com.witboost.plugin.informatica.marketplace.model.Category;
import com.witboost.plugin.informatica.marketplace.service.client.CategoryApiClient;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    private final CategoryApiClient categoryApiClient;
    private final MarketplaceMappingProperties mappingProperties;

    public CategoryService(CategoryApiClient categoryApiClient) {
        this(categoryApiClient, defaultMappingProperties());
    }

    @Autowired
    public CategoryService(
            CategoryApiClient categoryApiClient, MarketplaceMappingProperties mappingProperties) {
        this.categoryApiClient = categoryApiClient;
        this.mappingProperties = mappingProperties;
    }

    public Category getCategoryByPath(List<String> categoryNames) {
        var items = categoryApiClient.getAllCategories("*", "all").getItems();
                if (categoryNames == null
                                || categoryNames.size() != mappingProperties.getCategories().size()) {
            throw new IllegalArgumentException(
                                        "Descriptor category path must contain exactly "
                                                        + mappingProperties.getCategories().size()
                                                        + " configured levels");
                }
                for (int level = 0; level < categoryNames.size(); level++) {
                        if (categoryNames.get(level) == null || categoryNames.get(level).isBlank()) {
                                throw new IllegalArgumentException(
                                                "Descriptor category value is missing at configured level " + level);
                        }
        }

        var companyCategory =
                items.stream()
                        .filter(
                                cat ->
                                        cat.getName().equals(categoryNames.get(0))
                                                && cat.getParentId() == null)
                        .findFirst()
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Category not found for category '"
                                                        + categoryNames.get(0)
                                                        + "'. Accepted values: "
                                                        + names(
                                                                items.stream()
                                                                        .filter(
                                                                                c ->
                                                                                        c
                                                                                                        .getParentId()
                                                                                                == null))));
        Category current = companyCategory;
        for (int level = 1; level < mappingProperties.getCategories().size(); level++) {
            String categoryName = categoryNames.get(level);
            Category parent = current;
            current =
                    items.stream()
                            .filter(
                                    category ->
                                            category.getName().equals(categoryName)
                                                    && Objects.equals(
                                                            category.getParentId(), parent.getId()))
                            .findFirst()
                            .orElseThrow(
                                    () ->
                                            new IllegalArgumentException(
                                                    "Category not found for '"
                                                            + categoryName
                                                            + "' under '"
                                                            + parent.getName()
                                                            + "'"));
        }
        return current;
    }

    /**
     * A category is a child of {@code parentId} when the last segment of its parentId matches it.
     */
    private static boolean isChildOf(Category cat, String parentId) {
        if (cat.getParentId() == null) {
            return false;
        }
        String[] parts = cat.getParentId().split("/");
        return parts[parts.length - 1].equals(parentId);
    }

    private static String names(java.util.stream.Stream<Category> categories) {
        return categories
                .map(Category::getName)
                .sorted()
                .map(name -> "'" + name + "'")
                .toList()
                .toString();
    }

    private static MarketplaceMappingProperties defaultMappingProperties() {
        MarketplaceMappingProperties properties = new MarketplaceMappingProperties();
        properties.setCategories(
                List.of(
                        categoryLevel("company"),
                        categoryLevel("businessDomain"),
                        categoryLevel("businessSubdomain")));
        return properties;
    }

    private static MarketplaceMappingProperties.CategoryLevel categoryLevel(String path) {
        MarketplaceMappingProperties.CategoryLevel level =
                new MarketplaceMappingProperties.CategoryLevel();
        level.setDescriptorPath(path);
        return level;
    }
}

package com.tw.api.json;

import com.tw.domain.Category;

public class CategoryJSON {
    private String baseUri;
    private Category category;

    public CategoryJSON(String baseUri, Category category) {
        this.baseUri = baseUri;

        this.category = category;
    }

    public String getName() {
        return category.getName();
    }

    public PolicyJSON getPolicy() {
        return new PolicyJSON(category.getPolicy());
    }

    public String getUri() {
        return baseUri + "/categories/" + category.getId();
    }
}

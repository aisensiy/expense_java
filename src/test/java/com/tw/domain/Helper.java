package com.tw.domain;

public class Helper {
    public static Category createCategoryWithPolicy(String name, Policy policy) {
        Category category = new Category(name);
        category.policy = policy;
        return category;
    }
}

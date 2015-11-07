package com.tw.domain;

import java.util.List;

public class Helper {
    public static Category createCategoryWithPolicy(String name, Policy policy) {
        Category category = new Category(name);
        category.policy = policy;
        return category;
    }

    public static ExpenseRequest createRequestWithItems(ExpenseRequest expenseRequest, List<ExpenseRequestItem> expenseRequestItems) {
        expenseRequest.items = expenseRequestItems;
        return expenseRequest;
    }

    public static ExpenseRequestItem createRequestItemWithRecipe(ExpenseRequestItem expenseRequestItem, Recipe recipe) {
        expenseRequestItem.recipe = recipe;
        return expenseRequestItem;
    }
}

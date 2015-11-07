package com.tw.domain;

import java.sql.Timestamp;
import java.util.List;

public class Helper {
    public static Category createCategoryWithPolicy(String name, Policy policy) {
        Category category = new Category(name);
        category.policy = policy;
        return category;
    }

    public static ExpenseRequest createRequestWithItemsAndApprovement(int id, ExpenseRequest expenseRequest, List<ExpenseRequestItem> expenseRequestItems, Approvement approvement) {
        expenseRequest.items = expenseRequestItems;
        expenseRequest.id = id;
        expenseRequest.approvement = approvement;
        return expenseRequest;
    }

    public static ExpenseRequestItem createRequestItemWithRecipe(ExpenseRequestItem expenseRequestItem, Recipe recipe) {
        expenseRequestItem.recipe = recipe;
        return expenseRequestItem;
    }

    public static Approvement createApprovement(int id, int userId, Timestamp createdAt) {
        Approvement approvement = new Approvement(userId);
        approvement.id = id;
        return approvement;
    }
}

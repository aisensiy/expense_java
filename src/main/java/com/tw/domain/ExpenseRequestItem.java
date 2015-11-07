package com.tw.domain;

public class ExpenseRequestItem {
    private int expenseRequestId;
    private int amount;
    private int categoryId;
    private String description;
    public Recipe recipe;
    private int id;

    public ExpenseRequestItem(int amount, int categoryId, String description) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
    }

    public ExpenseRequestItem() {
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}

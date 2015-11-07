package com.tw.api.json;

import com.tw.domain.ExpenseRequestItem;

public class ExpenseRequestItemJSON {
    private ExpenseRequestItem item;

    public ExpenseRequestItemJSON(ExpenseRequestItem item) {

        this.item = item;
    }

    public RecipeJSON getRecipe() {
        return new RecipeJSON(item.getRecipe());
    }

    public int getAmount() {
        return item.getAmount();
    }

    public String getDescription() {
        return item.getDescription();
    }
}

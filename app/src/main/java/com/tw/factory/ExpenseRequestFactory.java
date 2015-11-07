package com.tw.factory;

import com.tw.domain.ExpenseRequest;
import com.tw.domain.ExpenseRequestItem;
import com.tw.domain.Recipe;
import com.tw.mapper.ExpenseRequestItemMapper;
import com.tw.mapper.ExpenseRequestMapper;
import com.tw.mapper.RecipeMapper;

import javax.inject.Inject;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class ExpenseRequestFactory {
    @Inject
    ExpenseRequestMapper expenseRequestMapper;

    @Inject
    ExpenseRequestItemMapper expenseRequestItemMapper;

    @Inject
    RecipeMapper recipeMapper;

    public ExpenseRequest build(int userId, Form form) {
        MultivaluedMap<String, String> map = form.asMap();
        Timestamp createdAt = Timestamp.valueOf(map.getFirst("createdAt"));
        int amount = Integer.parseInt(map.getFirst("amount"));
        ExpenseRequest expenseRequest = new ExpenseRequest(amount, createdAt);
        expenseRequestMapper.createExpenseRequest(userId, expenseRequest);

        for (int i = 0; i < map.get("itemAmount").size(); i++) {
            String itemDescription = map.get("itemDescription").get(i);
            int itemAmount = Integer.parseInt(map.get("itemAmount").get(i));
            int categoryId = Integer.parseInt(map.get("itemCategoryId").get(i));
            ExpenseRequestItem expenseRequestItem = new ExpenseRequestItem(itemAmount, categoryId, itemDescription);
            expenseRequestItemMapper.createExpenseItem(expenseRequest.getId(), expenseRequestItem);

            String recipeDescription = map.get("itemRecipeDescription").get(i);
            Recipe recipe = new Recipe(recipeDescription);
            recipeMapper.createRecipe(expenseRequestItem.getId(), recipe);
        }
        return expenseRequest;
    }

    public ExpenseRequest getExpenseRequest(int id) {
        return null;
    }
}

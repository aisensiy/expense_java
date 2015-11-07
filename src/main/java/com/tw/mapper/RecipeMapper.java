package com.tw.mapper;

import com.tw.domain.Recipe;

public interface RecipeMapper {
    int createRecipe(int expenseItemId, Recipe recipe);
}

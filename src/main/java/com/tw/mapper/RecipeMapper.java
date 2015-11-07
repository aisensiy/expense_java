package com.tw.mapper;

import com.tw.domain.Recipe;
import org.apache.ibatis.annotations.Param;

public interface RecipeMapper {
    int createRecipe(@Param("expenseRequestItemId") int expenseItemId, @Param("recipe") Recipe recipe);
}

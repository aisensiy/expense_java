package com.tw.api.json;

import com.tw.domain.Recipe;

public class RecipeJSON {
    private Recipe recipe;

    public RecipeJSON(Recipe recipe) {

        this.recipe = recipe;
    }

    public String getDescription() {
        return recipe.getDescription();
    }
}

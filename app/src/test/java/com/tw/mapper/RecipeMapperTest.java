package com.tw.mapper;

import com.tw.domain.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;

public class RecipeMapperTest extends MapperTestBase {

    private RecipeMapper mapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapper = sqlSession.getMapper(RecipeMapper.class);
    }

    @Test
    public void should_create_recipe() throws Exception {
        Recipe recipe = new Recipe("recipe");
        mapper.createRecipe(1, recipe);
        Assert.assertThat(recipe.getId(), not(0));
    }
}
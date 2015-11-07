package com.tw.mapper;

import com.tw.domain.Approvement;
import com.tw.domain.ExpenseRequest;
import com.tw.domain.ExpenseRequestItem;
import com.tw.domain.Recipe;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class ExpenseRequestMapperTest extends MapperTestBase {


    private ExpenseRequestMapper expenseRequestMapper;
    private RecipeMapper recipeMapper;
    private ExpenseRequestItemMapper expenseRequestItemMapper;
    private ApprovementMapper approvementMapper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expenseRequestMapper = sqlSession.getMapper(ExpenseRequestMapper.class);
        recipeMapper = sqlSession.getMapper(RecipeMapper.class);
        expenseRequestItemMapper = sqlSession.getMapper(ExpenseRequestItemMapper.class);
        approvementMapper = sqlSession.getMapper(ApprovementMapper.class);
    }

    @Test
    public void should_create_expense_request_with_0_expense_request_item() throws Exception {
        ExpenseRequest expenseRequest = new ExpenseRequest(1000, new Timestamp(1446875572259L));
        expenseRequestMapper.createExpenseRequest(1, expenseRequest);
        assertThat(expenseRequest.getId(), not(0));
    }

    @Test
    public void should_get_expense_request_with_items() throws Exception {
        ExpenseRequest expectedRequest = new ExpenseRequest(1000, new Timestamp(1446875572259L));
        expenseRequestMapper.createExpenseRequest(1, expectedRequest);
        ExpenseRequestItem expenseRequestItem = new ExpenseRequestItem(1000, 1, "item");
        expenseRequestItemMapper.createExpenseItem(expectedRequest.getId(), expenseRequestItem);
        Recipe expectedRecipe = new Recipe("recipe");
        recipeMapper.createRecipe(expenseRequestItem.getId(), expectedRecipe);

        ExpenseRequest request = expenseRequestMapper.getExpenseRequestById(expectedRequest.getId());
        assertThat(request.getItems().size(), is(1));
        ExpenseRequestItem requestItem = request.getItems().get(0);
        assertThat(requestItem.getAmount(), is(1000));
        assertThat(requestItem.getDescription(), is("item"));
        assertThat(requestItem.getCategoryId(), is(1));
        Recipe recipe = requestItem.getRecipe();
        assertThat(recipe.getDescription(), is("recipe"));
    }

    @Test
    public void should_get_expense_request_with_approvement() throws Exception {
        ExpenseRequest expectedRequest = new ExpenseRequest(1000, new Timestamp(1446875572259L));
        expenseRequestMapper.createExpenseRequest(1, expectedRequest);
        Approvement approvement = new Approvement(1);
        approvementMapper.createApprovement(expectedRequest.getId(), approvement);
        ExpenseRequest request = expenseRequestMapper.getExpenseRequestById(expectedRequest.getId());
        assertThat(request.getApprovement().getUserId(), is(1));
    }
}

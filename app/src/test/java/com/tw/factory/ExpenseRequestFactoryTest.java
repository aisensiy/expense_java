package com.tw.factory;

import com.tw.domain.ExpenseRequest;
import com.tw.domain.ExpenseRequestItem;
import com.tw.domain.Recipe;
import com.tw.mapper.ExpenseRequestItemMapper;
import com.tw.mapper.ExpenseRequestMapper;
import com.tw.mapper.RecipeMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseRequestFactoryTest {
    @Mock
    ExpenseRequestMapper expenseRequestMapper;
    @Mock
    ExpenseRequestItemMapper expenseRequestItemMapper;
    @Mock
    RecipeMapper recipeMapper;

    @InjectMocks
    ExpenseRequestFactory factory = new ExpenseRequestFactory();

    @Before
    public void setUp() throws Exception {
        expenseRequestMapper = mock(ExpenseRequestMapper.class);
        expenseRequestItemMapper = mock(ExpenseRequestItemMapper.class);
        recipeMapper = mock(RecipeMapper.class);
        initMocks(this);
    }

    @Test
    public void should_create_expense_request_without_items() throws Exception {
        ArgumentCaptor<ExpenseRequest> argumentExpenseRequestCaptor = ArgumentCaptor.forClass(ExpenseRequest.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("amount", "120");
        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());
        factory.build(1, new Form(map));
        verify(expenseRequestMapper).createExpenseRequest(eq(1), argumentExpenseRequestCaptor.capture());
    }

    @Test
    public void should_create_expense_request_with_items() throws Exception {
        ArgumentCaptor<ExpenseRequestItem> itemCaptor = ArgumentCaptor.forClass(ExpenseRequestItem.class);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());
        map.putSingle("amount", "120");
        map.put("itemDescription", asList("item1", "item2"));
        map.put("itemAmount", asList("500", "500"));
        map.put("itemCategoryId", asList("1", "2"));
        map.put("itemRecipeDescription", asList("recipe1", "recipe2"));
        factory.build(1, new Form(map));
        verify(expenseRequestItemMapper, times(2)).createExpenseItem(anyInt(), itemCaptor.capture());
        verify(recipeMapper, times(2)).createRecipe(anyInt(), recipeCaptor.capture());
    }


}
package com.tw.api;

import com.tw.api.json.ExpenseRequestJSON;
import com.tw.domain.ExpenseRequest;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.ExpenseRequestItemMapper;
import com.tw.mapper.ExpenseRequestMapper;
import com.tw.mapper.RecipeMapper;

import javax.ws.rs.POST;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class ExpenseRequestsApi {
    private final int userId;
    private ExpenseRequestFactory expenseRequestFactory;
    ExpenseRequestMapper expenseRequestMapper;
    ExpenseRequestItemMapper expenseRequestItemMapper;
    RecipeMapper recipeMapper;

    public ExpenseRequestsApi(int userId, ExpenseRequestFactory expenseRequestFactory) {
        this.userId = userId;
        this.expenseRequestFactory = expenseRequestFactory;
    }

    @POST
    public Response createExpenseRequest(Form form) {
        ExpenseRequest expenseRequest = expenseRequestFactory.build(userId, form);
        return Response.status(201).header("Location", new ExpenseRequestJSON(expenseRequest).getUri()).build();
    }
}

package com.tw.api;

import com.tw.api.json.ExpenseRequestJSON;
import com.tw.domain.ExpenseRequest;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.ExpenseRequestMapper;

import javax.ws.rs.POST;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class ExpenseRequestsApi {
    private final int userId;
    ExpenseRequestMapper expenseRequestMapper;

    public ExpenseRequestsApi(int userId, ExpenseRequestMapper expenseRequestMapper) {

        this.userId = userId;
        this.expenseRequestMapper = expenseRequestMapper;
    }

    @POST
    public Response createExpenseRequest(Form form) {
        ExpenseRequest expenseRequest = ExpenseRequestFactory.build(form);
        expenseRequestMapper.createExpenseRequest(1, expenseRequest);
        return Response.status(201).header("Location", new ExpenseRequestJSON(expenseRequest).getUri()).build();
    }
}

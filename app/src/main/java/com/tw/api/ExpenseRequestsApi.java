package com.tw.api;

import com.tw.api.json.ExpenseRequestJSON;
import com.tw.domain.ExpenseRequest;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.ApprovementMapper;
import com.tw.mapper.PaymentMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class ExpenseRequestsApi {
    private final int userId;
    private ExpenseRequestFactory expenseRequestFactory;
    private ApprovementMapper approvementMapper;
    private PaymentMapper paymentMapper;

    public ExpenseRequestsApi(int userId, ExpenseRequestFactory expenseRequestFactory, ApprovementMapper approvementMapper, PaymentMapper paymentMapper) {
        this.userId = userId;
        this.expenseRequestFactory = expenseRequestFactory;
        this.approvementMapper = approvementMapper;
        this.paymentMapper = paymentMapper;
    }

    @POST
    public Response createExpenseRequest(Form form) {
        ExpenseRequest expenseRequest = expenseRequestFactory.build(userId, form);
        return Response.status(201).header("Location", new ExpenseRequestJSON("", expenseRequest).getUri()).build();
    }

    @Path("{expenseRequestId}")
    public ExpenseRequestApi getExpenseRequest(@PathParam("expenseRequestId") int expenseRequestId) {
        return new ExpenseRequestApi("/users/" + userId, expenseRequestId, expenseRequestFactory, approvementMapper, paymentMapper);
    }
}

package com.tw.api;

import com.tw.api.json.ExpenseRequestJSON;
import com.tw.domain.ExpenseRequest;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.persistent.ExpenseRequestRepository;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class ExpenseRequestsApi {
    @Inject
    ExpenseRequestRepository expenseRequestRepository;

    @POST
    public Response createExpenseRequest(Form form) {
        ExpenseRequest expenseRequest = ExpenseRequestFactory.build(form);
        expenseRequestRepository.createExpenseRequest(expenseRequest);
        return Response.status(201).header("Location", new ExpenseRequestJSON(expenseRequest).getUri()).build();
    }
}

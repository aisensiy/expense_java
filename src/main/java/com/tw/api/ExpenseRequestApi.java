package com.tw.api;

import com.tw.api.json.ExpenseRequestJSON;
import com.tw.domain.ExpenseRequest;
import com.tw.factory.ExpenseRequestFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExpenseRequestApi {
    private String prefixUri;
    private final int expenseRequestId;
    private final ExpenseRequestFactory expenseRequestFactory;

    public ExpenseRequestApi(String prefixUri, int expenseRequestId, ExpenseRequestFactory expenseRequestFactory) {
        this.prefixUri = prefixUri;
        this.expenseRequestId = expenseRequestId;
        this.expenseRequestFactory = expenseRequestFactory;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExpenseRequest() {
        ExpenseRequest expenseRequest = expenseRequestFactory.getExpenseRequest(expenseRequestId);
        return Response.status(200).entity(new ExpenseRequestJSON(prefixUri, expenseRequest)).build();
    }
}

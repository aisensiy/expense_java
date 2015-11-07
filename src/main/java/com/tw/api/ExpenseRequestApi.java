package com.tw.api;

import com.tw.api.json.ExpenseRequestJSON;
import com.tw.domain.ExpenseRequest;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.ApprovementMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExpenseRequestApi {
    private String prefixUri;
    private final int expenseRequestId;
    private final ExpenseRequestFactory expenseRequestFactory;
    private ApprovementMapper approvementMapper;

    public ExpenseRequestApi(String prefixUri, int expenseRequestId, ExpenseRequestFactory expenseRequestFactory, ApprovementMapper approvementMapper) {
        this.prefixUri = prefixUri;
        this.expenseRequestId = expenseRequestId;
        this.expenseRequestFactory = expenseRequestFactory;
        this.approvementMapper = approvementMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExpenseRequest() {
        ExpenseRequest expenseRequest = expenseRequestFactory.getExpenseRequest(expenseRequestId);
        return Response.status(200).entity(new ExpenseRequestJSON(prefixUri, expenseRequest)).build();
    }

    @Path("approvement")
    public ApprovementApi getApprovementApi() {
        return new ApprovementApi(prefixUri + "/expenseRequests/" + expenseRequestId, expenseRequestId, expenseRequestFactory, approvementMapper);
    }
}

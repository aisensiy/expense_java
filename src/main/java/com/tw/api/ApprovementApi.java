package com.tw.api;

import com.tw.domain.Approvement;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.ApprovementMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApprovementApi {
    private final String prefixUri;
    private final int expenseRequestId;
    private ExpenseRequestFactory expenseRequestFactory;
    private final ApprovementMapper approvementMapper;

    public ApprovementApi(String prefixUri, int expenseRequestId, ExpenseRequestFactory expenseRequestFactory, ApprovementMapper approvementMapper) {

        this.prefixUri = prefixUri;
        this.expenseRequestId = expenseRequestId;
        this.expenseRequestFactory = expenseRequestFactory;
        this.approvementMapper = approvementMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createApprovement(Form form) {
        Approvement approvement = new Approvement(Integer.parseInt(form.asMap().getFirst("userId")));
        approvementMapper.createApprovement(expenseRequestId, approvement);
        return Response.status(201).build();
    }
}

package com.tw.api;

import com.tw.domain.Approvement;
import com.tw.domain.ExpenseRequest;
import com.tw.domain.Payment;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.ApprovementMapper;
import com.tw.mapper.PaymentMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApprovementApi {
    private final String prefixUri;
    private final int expenseRequestId;
    private ExpenseRequestFactory expenseRequestFactory;
    private final ApprovementMapper approvementMapper;
    private PaymentMapper paymentMapper;

    public ApprovementApi(String prefixUri, int expenseRequestId, ExpenseRequestFactory expenseRequestFactory,
                          ApprovementMapper approvementMapper, PaymentMapper paymentMapper) {

        this.prefixUri = prefixUri;
        this.expenseRequestId = expenseRequestId;
        this.expenseRequestFactory = expenseRequestFactory;
        this.approvementMapper = approvementMapper;
        this.paymentMapper = paymentMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createApprovement(Form form) {
        Approvement approvement = new Approvement(Integer.parseInt(form.asMap().getFirst("userId")));
        approvementMapper.createApprovement(expenseRequestId, approvement);
        return Response.status(201).build();
    }

    @POST
    @Path("payment")
    public Response createPayment(Form form) {
        ExpenseRequest expenseRequest = expenseRequestFactory.getExpenseRequest(expenseRequestId);
        paymentMapper.createPayment(expenseRequest.getApprovement().getId(), new Payment());
        return Response.status(201).build();
    }
}

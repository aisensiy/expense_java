package com.tw.api.json;

import com.tw.domain.ExpenseRequest;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExpenseRequestJSON {

    private String prefixUri;
    private ExpenseRequest expenseRequest;

    public ExpenseRequestJSON(String prefixUri, ExpenseRequest expenseRequest) {
        this.prefixUri = prefixUri;
        this.expenseRequest = expenseRequest;
    }

    public int getAmount() {
        return expenseRequest.getAmount();
    }

    public List<ExpenseRequestItemJSON> getItems() {
        return expenseRequest.getItems().stream().map(item -> new ExpenseRequestItemJSON(item)).collect(toList());
    }

    public String getUri() {
        return prefixUri + "/expenseRequests/" + expenseRequest.getId();
    }

    public ApprovementJSON getApprovement() {
        return new ApprovementJSON(expenseRequest.getApprovement());
    }
}

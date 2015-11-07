package com.tw.domain;

import java.sql.Timestamp;
import java.util.List;

public class ExpenseRequest {
    private int amount;
    private Timestamp createdAt;
    protected int id;
    List<ExpenseRequestItem> items;
    protected Approvement approvement;

    public ExpenseRequest(int amount, Timestamp createdAt) {

        this.amount = amount;
        this.createdAt = createdAt;
    }

    public ExpenseRequest() {
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }


    public List<ExpenseRequestItem> getItems() {
        return items;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPolicyAllowed() {
        return true;
    }

    public Approvement getApprovement() {
        return approvement;
    }
}

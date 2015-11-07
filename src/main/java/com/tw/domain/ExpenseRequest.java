package com.tw.domain;

import java.sql.Timestamp;
import java.util.List;

public class ExpenseRequest {
    private int amount;
    private Timestamp createdAt;
    private int id;
    List<ExpenseRequestItem> items;

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
}

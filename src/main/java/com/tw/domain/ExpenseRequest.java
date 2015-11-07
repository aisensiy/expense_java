package com.tw.domain;

import java.sql.Timestamp;

public class ExpenseRequest {
    private final int amount;
    private final Timestamp createdAt;

    public ExpenseRequest(int amount, Timestamp createdAt) {

        this.amount = amount;
        this.createdAt = createdAt;
    }
}

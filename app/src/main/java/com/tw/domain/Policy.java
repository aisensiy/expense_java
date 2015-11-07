package com.tw.domain;

import java.sql.Timestamp;

public class Policy {
    private int maxAmount;
    private Timestamp createdAt;
    private int id;

    public Policy(int maxAmount, Timestamp createdAt) {

        this.maxAmount = maxAmount;
        this.createdAt = createdAt;
    }

    public Policy() {
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getMaxAmount() {
        return maxAmount;
    }
}

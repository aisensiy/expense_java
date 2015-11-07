package com.tw.domain;

import java.sql.Timestamp;

public class Policy {
    private final int maxAmount;
    private final Timestamp createdAt;
    private int id;

    public Policy(int maxAmount, Timestamp createdAt) {

        this.maxAmount = maxAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
}

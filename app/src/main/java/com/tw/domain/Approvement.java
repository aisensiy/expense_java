package com.tw.domain;

import java.sql.Timestamp;

public class Approvement {
    private int userId;
    private Timestamp createdAt;
    protected int id;

    public Payment getPayment() {
        return payment;
    }

    protected Payment payment;

    public Approvement(int userId) {
        this.userId = userId;
    }

    public Approvement() {
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getUserId() {
        return userId;
    }
}

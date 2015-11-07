package com.tw.domain;

import java.sql.Timestamp;

public class Payment {
    private String id;
    private Timestamp createdAt;

    public String getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}

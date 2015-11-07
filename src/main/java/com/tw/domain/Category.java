package com.tw.domain;

public class Category {
    private String name;
    private int id;
    private int userId;
    Policy policy;

    public Category(String name) {

        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Policy getPolicy() {
        return policy;
    }
}

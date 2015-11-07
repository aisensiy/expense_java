package com.tw.domain;

public class Category {
    private String name;
    private String id;
    private int userId;

    public Category(String name) {

        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }
}

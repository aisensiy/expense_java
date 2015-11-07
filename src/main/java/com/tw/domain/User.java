package com.tw.domain;

public class User {
    private int id;

    private String role;

    public User(String role) {

        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
}

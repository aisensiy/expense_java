package com.tw.domain;

public class Recipe {
    private String description;
    private Integer id;

    public Recipe(String description) {

        this.description = description;
    }

    public Recipe() {
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

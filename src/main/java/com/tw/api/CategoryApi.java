package com.tw.api;

import com.tw.mapper.PolicyMapper;

import javax.ws.rs.Path;

public class CategoryApi {
    private final int userId;
    private final int categoryId;
    private final PolicyMapper policyMapper;

    public CategoryApi(int userId, int categoryId, PolicyMapper policyMapper) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.policyMapper = policyMapper;
    }

    @Path("policies")
    public PoliciesApi getPoliciesApi() {
        return new PoliciesApi(categoryId, policyMapper);
    }
}

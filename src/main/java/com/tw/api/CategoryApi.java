package com.tw.api;

import com.tw.api.json.CategoryJSON;
import com.tw.domain.Category;
import com.tw.mapper.CategoryMapper;
import com.tw.mapper.PolicyMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CategoryApi {
    private final int userId;
    private final int categoryId;
    private CategoryMapper categoryMapper;
    private final PolicyMapper policyMapper;

    public CategoryApi(int userId, int categoryId, CategoryMapper categoryMapper, PolicyMapper policyMapper) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.categoryMapper = categoryMapper;
        this.policyMapper = policyMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory() {
        Category category = categoryMapper.getCategoryById(categoryId);
        return Response.status(200).entity(new CategoryJSON("/users/" + userId, category)).build();
    }

    @Path("policies")
    public PoliciesApi getPoliciesApi() {
        return new PoliciesApi(categoryId, policyMapper);
    }
}

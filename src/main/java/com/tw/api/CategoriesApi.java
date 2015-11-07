package com.tw.api;

import com.tw.domain.Category;
import com.tw.mapper.CategoryMapper;
import com.tw.mapper.PolicyMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CategoriesApi {
    private int userId;
    private CategoryMapper categoryMapper;
    private PolicyMapper policyMapper;

    public CategoriesApi(int userId, CategoryMapper categoryMapper, PolicyMapper policyMapper) {

        this.userId = userId;
        this.categoryMapper = categoryMapper;
        this.policyMapper = policyMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Form form) {
        Category category = getCategoryFromForm(form);
        categoryMapper.createCategory(userId, category);
        return Response.status(201).build();
    }

    @Path("{categoryId}")
    public CategoryApi getCategoryApi(@PathParam("categoryId") int categoryId) {
        return new CategoryApi(userId, categoryId, policyMapper);
    }

    private Category getCategoryFromForm(Form form) {
        return new Category(form.asMap().getFirst("name"));
    }
}

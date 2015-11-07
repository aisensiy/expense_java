package com.tw.api;

import com.tw.domain.Category;
import com.tw.mapper.CategoryMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CategoriesApi {
    private int userId;
    private CategoryMapper categoryMapper;

    public CategoriesApi(int userId, CategoryMapper categoryMapper) {

        this.userId = userId;
        this.categoryMapper = categoryMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Form form) {
        Category category = getCategoryFromForm(form);
        categoryMapper.createCategory(userId, category);
        return Response.status(201).build();
    }

    private Category getCategoryFromForm(Form form) {
        return new Category(form.asMap().getFirst("name"));
    }
}

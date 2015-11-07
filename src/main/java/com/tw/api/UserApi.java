package com.tw.api;

import com.tw.api.json.UserJSON;
import com.tw.mapper.CategoryMapper;
import com.tw.mapper.PolicyMapper;
import com.tw.mapper.UserMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserApi {
    private int userId;
    private UserMapper userMapper;
    private CategoryMapper categoryMapper;
    private PolicyMapper policyMapper;

    public UserApi(int userId, UserMapper userMapper, CategoryMapper categoryMapper, PolicyMapper policyMapper) {
        this.userId = userId;
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
        this.policyMapper = policyMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        return Response.status(200).entity(new UserJSON(userMapper.getUserById(userId))).build();
    }

    @Path("expenseRequests")
    public ExpenseRequestsApi getExpenseRequestsApi() {
        return new ExpenseRequestsApi();
    }

    @Path("categories")
    public CategoriesApi categoriesApi() {
        return new CategoriesApi(userId, categoryMapper, policyMapper);
    }
}

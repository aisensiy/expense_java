package com.tw.api;

import com.tw.api.json.UserJSON;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class UserApi {
    private int userId;
    private Map<Class<?>, Object> mappers;
    private UserMapper userMapper;
    private ExpenseRequestMapper expenseRequestMapper;
    private CategoryMapper categoryMapper;
    private PolicyMapper policyMapper;
    private ExpenseRequestFactory expenseRequestFactory;
    private ApprovementMapper approvementMapper;

    public UserApi(int userId, UserMapper userMapper, CategoryMapper categoryMapper, PolicyMapper policyMapper, ExpenseRequestFactory expenseRequestFactory, ApprovementMapper approvementMapper) {
        this.userId = userId;
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
        this.policyMapper = policyMapper;
        this.expenseRequestFactory = expenseRequestFactory;
        this.approvementMapper = approvementMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        return Response.status(200).entity(new UserJSON(userMapper.getUserById(userId))).build();
    }

    @Path("expenseRequests")
    public ExpenseRequestsApi getExpenseRequestsApi() {
        return new ExpenseRequestsApi(userId, expenseRequestFactory, approvementMapper);
    }

    @Path("categories")
    public CategoriesApi categoriesApi() {
        return new CategoriesApi(userId, categoryMapper, policyMapper);
    }
}

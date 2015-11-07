package com.tw.api;

import com.tw.api.json.UserJSON;
import com.tw.domain.User;
import com.tw.mapper.UserMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserApi {
    private User user;
    private UserMapper userMapper;

    public UserApi(User user, UserMapper userMapper) {
        this.user = user;
        this.userMapper = userMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        return Response.status(200).entity(new UserJSON(user)).build();
    }

    @Path("expenseRequests")
    public ExpenseRequestsApi getExpenseRequestsApi() {
        return new ExpenseRequestsApi();
    }
}

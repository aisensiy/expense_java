package com.tw.api;

import com.tw.api.json.UserJSON;
import com.tw.domain.User;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("users")
public class UsersApi {
    @Inject
    private UserMapper userMapper;

    @Inject
    private CategoryMapper categoryMapper;

    @Inject
    private PolicyMapper policyMapper;

    @Inject
    private ExpenseRequestFactory expenseRequestFactory;

    @Inject
    private ExpenseRequestMapper expenseRequestMapper;

    @Inject
    private ExpenseRequestItemMapper expenseRequestItemMapper;

    @Inject
    private RecipeMapper recipeMapper;

    @Inject
    private ApprovementMapper approvementMapper;

    @Path("{userId}")
    public UserApi getUserApi(@PathParam("userId") int userId) {
        return new UserApi(userId, userMapper, categoryMapper, policyMapper, expenseRequestFactory, approvementMapper);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(Form form) {
        User user = getUserFromForm(form);
        userMapper.createUser(user);
        return Response.status(201).header("Location", new UserJSON(user).getUri()).build();
    }

    private User getUserFromForm(Form form) {
        MultivaluedMap<String, String> map = form.asMap();
        return new User(map.getFirst("role"));
    }
}

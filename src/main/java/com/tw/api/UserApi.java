package com.tw.api;

import javax.ws.rs.Path;

public class UserApi {
    @Path("expenseRequests")
    public ExpenseRequestsApi getExpenseRequestsApi() {
        return new ExpenseRequestsApi();
    }
}

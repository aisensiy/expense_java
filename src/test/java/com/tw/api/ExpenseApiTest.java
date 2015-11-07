package com.tw.api;

import com.tw.domain.ExpenseRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class ExpenseApiTest extends ApiTestBase {
    private ArgumentCaptor<ExpenseRequest> argumentExpenseRequestCaptor;

    @Test
    public void should_create_expense_with_no_item() throws Exception {
        argumentExpenseRequestCaptor = ArgumentCaptor.forClass(ExpenseRequest.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("userId", "1");
        map.putSingle("amount", "120");
        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());

        Response response = target("/users/1/expenseRequests").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(expenseRequestMapper).createExpenseRequest(eq(1), argumentExpenseRequestCaptor.capture());
    }


}
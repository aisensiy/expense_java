package com.tw.api;

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
    private ArgumentCaptor<Form> formArgumentCaptor;

    @Test
    public void should_create_expense() throws Exception {
        formArgumentCaptor = ArgumentCaptor.forClass(Form.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("amount", "120");
        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());

        Response response = target("/users/1/expenseRequests").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(expenseRequestFactory).build(eq(1), formArgumentCaptor.capture());
    }
}
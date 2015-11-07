package com.tw.api;

import com.tw.domain.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExpenseApiTest extends ApiTestBase {
    private ArgumentCaptor<Form> formArgumentCaptor;

    @Test
    public void should_create_expense() throws Exception {
        when(expenseRequestFactory.build(eq(1), anyObject())).thenReturn(new ExpenseRequest(1000, new Timestamp(1L)));
        formArgumentCaptor = ArgumentCaptor.forClass(Form.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("amount", "120");
        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());

        Response response = target("/users/1/expenseRequests").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(expenseRequestFactory).build(eq(1), formArgumentCaptor.capture());
    }

    @Test
    public void should_get_expense_request_with_items_and_recipes() throws Exception {
        List<ExpenseRequestItem> items = asList(
                Helper.createRequestItemWithRecipe(new ExpenseRequestItem(500, 1, "a"), new Recipe("aa")),
                Helper.createRequestItemWithRecipe(new ExpenseRequestItem(500, 1, "b"), new Recipe("bb"))
        );
        when(expenseRequestFactory.getExpenseRequest(eq(1))).thenReturn(
                Helper.createRequestWithItemsAndApprovement(1, new ExpenseRequest(1000, new Timestamp(0)), items, null)
        );

        Response response = target("/users/1/expenseRequests/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("amount"), is(1000));
        assertThat(map.get("uri"), is("/users/1/expenseRequests/1"));
        List list = (List) map.get("items");
        assertThat(list.size(), is(2));
        Map item = (Map) list.get(0);
        assertThat(item.get("amount"), is(500));
        assertThat(item.get("description"), is("a"));
    }

    @Test
    public void should_create_approvement() throws Exception {
        ArgumentCaptor<Approvement> approvementArgumentCaptor = ArgumentCaptor.forClass(Approvement.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("userId", "1");
        Response response = target("/users/1/expenseRequests/1/approvement").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(approvementMapper).createApprovement(eq(1), approvementArgumentCaptor.capture());
    }

    @Test
    public void should_get_expense_request_with_items_and_recipes_and_approvement() throws Exception {
        List<ExpenseRequestItem> items = asList(
                Helper.createRequestItemWithRecipe(new ExpenseRequestItem(500, 1, "a"), new Recipe("aa")),
                Helper.createRequestItemWithRecipe(new ExpenseRequestItem(500, 1, "b"), new Recipe("bb"))
        );
        when(expenseRequestFactory.getExpenseRequest(eq(1))).thenReturn(
                Helper.createRequestWithItemsAndApprovement(1, new ExpenseRequest(1000, new Timestamp(0)),
                        items,
                        Helper.createApprovement(1, 1, new Timestamp(10)))
        );

        Response response = target("/users/1/expenseRequests/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("amount"), is(1000));
        assertThat(map.get("uri"), is("/users/1/expenseRequests/1"));
        List list = (List) map.get("items");
        assertThat(list.size(), is(2));
        Map item = (Map) list.get(0);
        assertThat(item.get("amount"), is(500));
        assertThat(item.get("description"), is("a"));
        Map approvement = (Map) map.get("approvement");
        Map approvedBy = (Map) approvement.get("approvedBy");
        assertThat(approvedBy.get("id"), is(1));
        assertThat(approvedBy.get("uri"), is("/users/1"));
    }

    @Test
    public void should_create_payment_for_approvement() throws Exception {
        when(expenseRequestFactory.getExpenseRequest(anyInt()))
                .thenReturn(
                        Helper.createRequestWithItemsAndApprovement(1, new ExpenseRequest(1000, new Timestamp(0)),
                        null,
                        Helper.createApprovement(1, 1, new Timestamp(10)))
        );
        ArgumentCaptor<Payment> paymentArgumentCaptor = ArgumentCaptor.forClass(Payment.class);
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        Response response = target("/users/1/expenseRequests/1/approvement/payment").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(paymentMapper).createPayment(eq(1), paymentArgumentCaptor.capture());
    }
}
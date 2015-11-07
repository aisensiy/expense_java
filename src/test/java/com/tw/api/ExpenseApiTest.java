package com.tw.api;

import com.tw.domain.ExpenseRequest;
import com.tw.mapper.ExpenseRequestRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Application;

import static org.mockito.Mockito.mock;

public class ExpenseApiTest extends JerseyTest {
    private ExpenseRequestRepository expenseRequestRepository;
    private ArgumentCaptor<ExpenseRequest> argumentExpenseRequestCaptor;

    @Override
    public void setUp() throws Exception {
        expenseRequestRepository = mock(ExpenseRequestRepository.class);
        super.setUp();
    }

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.tw.api")
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(expenseRequestRepository).to(ExpenseRequestRepository.class);
                    }
                });
    }

//    @Test
//    public void should_create_expense_with_no_item() throws Exception {
//        argumentExpenseRequestCaptor = ArgumentCaptor.forClass(ExpenseRequest.class);
//        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
//        map.putSingle("userId", "1");
//        map.putSingle("amount", "120");
//        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());
//
//        Response response = target("/users/1/expenseRequests").request().post(Entity.form(new Form(map)));
//        assertThat(response.getStatus(), is(201));
//        verify(expenseRequestRepository).createExpenseRequest(argumentExpenseRequestCaptor.capture());
//    }
}
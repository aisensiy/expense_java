package com.tw.api;

import com.tw.domain.Policy;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

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

public class PolicyApiTest extends ApiTestBase {
    @Captor
    ArgumentCaptor<Policy> argumentCaptor;

    @Test
    public void should_create_policy() throws Exception {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("maxAmount", "1000");
        map.putSingle("createdAt", new Timestamp(1446864494418L).toString());
        Response response = target("/users/1/categories/1/policies").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(policyMapper).createPolicy(eq(1), argumentCaptor.capture());
    }
}

package com.tw.api;

import com.tw.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserApiTest extends ApiTestBase {

    @Captor
    private ArgumentCaptor<User> argumentUserCaptor;

    @Test
    public void should_create_user() throws Exception {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("role", "employee");
        Response response = target("/users").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(userMapper).createUser(argumentUserCaptor.capture());
        assertThat(argumentUserCaptor.getValue().getRole(), is("employee"));
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        User user = new User("manager");
        when(userMapper.getUserById(eq(1))).thenReturn(user);
        Response response = target("/users/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("role"), is("manager"));
    }
}

package com.tw.api;

import com.tw.api.exception.InternalExceptionMapper;
import com.tw.domain.Category;
import com.tw.mapper.CategoryMapper;
import com.tw.mapper.UserMapper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryApiTest extends JerseyTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private CategoryMapper categoryMapper;

    @Captor
    private ArgumentCaptor<Category> categoryArgumentCaptor;

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.tw.api")
                .register(InternalExceptionMapper.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(userMapper).to(UserMapper.class);
                        bind(categoryMapper).to(CategoryMapper.class);
                    }
                });
    }

    @Test
    public void should_create_category() throws Exception {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("name", "taxi fee");
        Response response = target("/users/1/categories").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(categoryMapper).createCategory(eq(1), categoryArgumentCaptor.capture());
        assertThat(categoryArgumentCaptor.getValue().getName(), is("taxi fee"));
    }
}

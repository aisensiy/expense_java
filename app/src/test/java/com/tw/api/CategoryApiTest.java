package com.tw.api;

import com.tw.domain.Category;
import com.tw.domain.Policy;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.tw.domain.Helper.createCategoryWithPolicy;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryApiTest extends ApiTestBase {

    @Captor
    private ArgumentCaptor<Category> categoryArgumentCaptor;


    @Test
    public void should_create_category() throws Exception {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("name", "taxi fee");
        Response response = target("/users/1/categories").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
        verify(categoryMapper).createCategory(eq(1), categoryArgumentCaptor.capture());
        assertThat(categoryArgumentCaptor.getValue().getName(), is("taxi fee"));
    }

    @Test
    public void should_get_category_with_latest_policy() throws Exception {
        Category category = createCategoryWithPolicy("abc", new Policy(1000, new Timestamp(0)));
        when(categoryMapper.getCategoryById(1)).thenReturn(category);
        Response response = target("/users/1/categories/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("name"), is("abc"));
        Map policy = (Map) map.get("policy");
        assertThat(policy.get("maxAmount"), is(1000));
    }

    @Test
    public void should_get_all_categories() throws Exception {
        when(categoryMapper.getCategories()).thenReturn(asList(
                createCategoryWithPolicy("abc", new Policy(1000, new Timestamp(0))),
                createCategoryWithPolicy("bbc", new Policy(1000, new Timestamp(0)))
        ));
        Response response = target("/users/1/categories").request().get();
        assertThat(response.getStatus(), is(200));
        List list = response.readEntity(List.class);
        assertThat(list.size(), is(2));
        assertThat(((Map) list.get(0)).get("name"), is("abc"));
    }
}

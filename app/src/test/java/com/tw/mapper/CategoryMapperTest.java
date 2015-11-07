package com.tw.mapper;

import com.tw.domain.Category;
import com.tw.domain.Policy;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class CategoryMapperTest extends MapperTestBase {

    private CategoryMapper categoryMapper;
    private PolicyMapper policyMapper;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        policyMapper = sqlSession.getMapper(PolicyMapper.class);
    }

    @Test
    public void should_create_category() throws Exception {
        Category expected = new Category("name");
        int userId = 1;
        categoryMapper.createCategory(userId, expected);
        assertThat(expected.getId(), not(0));
    }

    @Test
    public void should_get_category_with_latest_policy() throws Exception {
        int userId = 1;
        Category expectedCategory = new Category("abc");
        Timestamp createdAt = new Timestamp(1446875572259L / 1000 * 1000);
        Policy expectedPolicy = new Policy(1000, createdAt);

        categoryMapper.createCategory(userId, expectedCategory);
        policyMapper.createPolicy(expectedCategory.getId(), expectedPolicy);

        Category category = categoryMapper.getCategoryById(expectedCategory.getId());
        assertThat(category.getName(), is("abc"));
        assertThat(category.getPolicy().getCreatedAt(), is(createdAt));
    }
}

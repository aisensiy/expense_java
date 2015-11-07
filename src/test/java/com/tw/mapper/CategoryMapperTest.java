package com.tw.mapper;

import com.tw.domain.Category;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class CategoryMapperTest extends MapperTestBase {

    private CategoryMapper categoryMapper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        categoryMapper = sqlSession.getMapper(CategoryMapper.class);
    }

    @Test
    public void should_create_category() throws Exception {
        Category expected = new Category("name");
        int userId = 1;
        categoryMapper.createCategory(userId, expected);
        assertThat(expected.getId(), not(0));
    }
}

package com.tw.mapper;

import com.tw.domain.ExpenseRequestItem;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class ExpenseRequestItemMapperTest extends MapperTestBase {

    private ExpenseRequestItemMapper mapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapper = sqlSession.getMapper(ExpenseRequestItemMapper.class);
    }

    @Test
    public void should_create_item() throws Exception {
        ExpenseRequestItem item = new ExpenseRequestItem(1, 100, "abc");
        mapper.createExpenseItem(1, item);
        assertThat(item.getId(), not(0));
    }
}
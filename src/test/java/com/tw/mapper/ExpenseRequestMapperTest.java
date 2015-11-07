package com.tw.mapper;

import com.tw.domain.ExpenseRequest;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class ExpenseRequestMapperTest extends MapperTestBase {


    private ExpenseRequestMapper expenseRequestMapper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expenseRequestMapper = sqlSession.getMapper(ExpenseRequestMapper.class);
    }

    @Test
    public void should_create_expense_request_with_0_expense_request_item() throws Exception {
        ExpenseRequest expenseRequest = new ExpenseRequest(1000, new Timestamp(1446875572259L));
        expenseRequestMapper.createExpenseRequest(1, expenseRequest);
        assertThat(expenseRequest.getId(), not(0));
    }
}

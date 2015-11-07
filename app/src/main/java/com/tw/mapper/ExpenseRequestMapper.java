package com.tw.mapper;

import com.tw.domain.ExpenseRequest;
import org.apache.ibatis.annotations.Param;

public interface ExpenseRequestMapper {
    int createExpenseRequest(@Param("userId") int userId, @Param("expenseRequest") ExpenseRequest expenseRequest);

    ExpenseRequest getExpenseRequestById(@Param("id") int id);
}

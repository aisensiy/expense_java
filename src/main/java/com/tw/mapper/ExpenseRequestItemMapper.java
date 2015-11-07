package com.tw.mapper;

import com.tw.domain.ExpenseRequestItem;
import org.apache.ibatis.annotations.Param;

public interface ExpenseRequestItemMapper {
    int createExpenseItem(@Param("requestId") int expenseRequestId, @Param("item") ExpenseRequestItem item);
}

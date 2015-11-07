package com.tw.mapper;

import com.tw.domain.ExpenseRequest;
import com.tw.domain.ExpenseRequestItem;

public interface ExpenseRequestItemMapper {
    int createExpenseItem(int expenseRequestId, ExpenseRequestItem item);

}

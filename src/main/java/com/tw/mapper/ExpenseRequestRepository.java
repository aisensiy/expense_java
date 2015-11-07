package com.tw.mapper;

import com.tw.domain.ExpenseRequest;

public interface ExpenseRequestRepository {
    int createExpenseRequest(ExpenseRequest expenseRequest);
}

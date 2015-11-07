package com.tw.persistent;

import com.tw.domain.ExpenseRequest;

public interface ExpenseRequestRepository {
    int createExpenseRequest(ExpenseRequest expenseRequest);
}

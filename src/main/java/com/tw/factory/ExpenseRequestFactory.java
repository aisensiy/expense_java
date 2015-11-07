package com.tw.factory;

import com.tw.domain.ExpenseRequest;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class ExpenseRequestFactory {
    public static ExpenseRequest build(Form form) {
        MultivaluedMap<String, String> map = form.asMap();
        Timestamp createdAt = Timestamp.valueOf(map.getFirst("createdAt"));
        int amount = Integer.parseInt(map.getFirst("amount"));
        return new ExpenseRequest(amount, createdAt);
    }
}

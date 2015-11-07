package com.tw.api.json;

import com.tw.domain.Policy;

import java.sql.Timestamp;

public class PolicyJSON {
    private Policy policy;

    public PolicyJSON(Policy policy) {

        this.policy = policy;
    }

    public int getMaxAmount() {
        return policy.getMaxAmount();
    }

    public int getId() {
        return policy.getId();
    }

    public Timestamp getCreatedAt() {
        return policy.getCreatedAt();
    }
}

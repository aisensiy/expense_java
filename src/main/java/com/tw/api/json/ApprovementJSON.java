package com.tw.api.json;

import com.tw.domain.Approvement;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ApprovementJSON {
    private Approvement approvement;

    public ApprovementJSON(Approvement approvement) {

        this.approvement = approvement;
    }

    public Timestamp getCreatedAt() {
        return approvement.getCreatedAt();
    }

    public Map<String, Object> getApprovedBy() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", approvement.getUserId());
        map.put("uri", "/users/" + approvement.getId());
        return map;
    }
}
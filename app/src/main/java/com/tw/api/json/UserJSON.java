package com.tw.api.json;

import com.tw.domain.User;

public class UserJSON {
    private User user;

    public UserJSON(User user) {
        this.user = user;
    }

    public String getRole() {
        return user.getRole();
    }

    public String getUri() {
        return "/users/" + user.getId();
    }
}

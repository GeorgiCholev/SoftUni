package com.resellerapp.config.sessionUser;

import com.resellerapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout() {
        id = null;
        username = null;
    }

    public boolean isLoggedIn() {
        return this.id != null;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}

package com.likebookapp.model.sessionEntity;

import com.likebookapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    public void logIn(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logOut() {
        this.id = null;
        this.username = null;
    }

    public boolean isLoggedIn() {
        return this.id != null;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }
}

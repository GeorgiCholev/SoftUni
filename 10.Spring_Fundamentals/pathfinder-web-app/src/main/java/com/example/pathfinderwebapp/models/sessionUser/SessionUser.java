package com.example.pathfinderwebapp.models.sessionUser;

import com.example.pathfinderwebapp.models.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionUser {

    private Long id;

    private String username;

    private String fullName;

    private boolean isAdmin;

    public SessionUser() {
    }

    public void login(User user, boolean isAdmin) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.isAdmin = isAdmin;
    }

    public void logout() {
        this.id = null;
        this.username = null;
        this.fullName = null;
        this.isAdmin = false;
    }

    public boolean isLoggedIn() {
        return this.id != null;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }
}

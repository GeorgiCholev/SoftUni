package com.example.mobilelewebapp.models.sessionUser;

import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.models.entities.enums.RoleType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private boolean isAdmin;

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.isAdmin = user.getRoleType() == RoleType.ADMIN;
    }

    public void logout() {
        this.id = null;
        this.username = null;
        this.isAdmin = false;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean canModifyOfferWith(Long userId) {
        return this.isLoggedIn() && this.id.equals(userId);
    }
}

package com.example.mobilelewebapp.loggedInUser;

import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.utils.enums.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class LoggedInUser {

    private boolean loggedIn;
    private String fullName;
    private String email;
    private boolean isAdmin;
    private String imageUrl;

    public LoggedInUser() {
    }

    public void setUp(User user) {
        this.loggedIn = true;
        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmail();
        this.isAdmin = checkIfIsAdmin(user.getRoles());
        this.imageUrl = user.getImageUrl();
    }

    public void clear() {
        this.loggedIn = false;
        this.fullName = null;
        this.email = null;
        this.isAdmin = false;
        this.imageUrl = null;
    }

    private boolean checkIfIsAdmin(List<Role> roles) {
        for (Role role : roles) {
            if (role.getUserRole() == UserRole.ADMIN) {
                return true;
            }
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

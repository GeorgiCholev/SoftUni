package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.utils.enums.UserRole;

import java.util.Arrays;
import java.util.Collections;

public class UserRegister {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserRole[] userRoles;

    public UserRegister() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPasswordHash() {
        return password.hashCode();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRole[] userRoles) {
        this.userRoles = userRoles;
    }
}

package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.utils.enums.UserRole;

public class UserRegister {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserRole userRole;

    public UserRegister(String firstName, String lastName, String username, String password, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}

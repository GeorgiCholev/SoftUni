package com.example.gamestore.models.dto;

import java.util.regex.Pattern;

public class RegisterUserDTO {

    private static final String EMAIL_VALIDATION_REGEXP = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PASSWORD_VALIDATION_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    private String email;

    private String password;

    private String fullName;

    public RegisterUserDTO(String email, String password, String confirmPassword, String fullName) {
        setEmail(email);
        setPassword(password, confirmPassword);
        setFullName(fullName);
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if (Pattern.matches(EMAIL_VALIDATION_REGEXP, email)) {

            this.email = email;

        } else {

            throw new IllegalStateException("Incorrect email.");

        }
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {

            throw new IllegalStateException("Passwords must match!");

        } else if (!Pattern.matches(PASSWORD_VALIDATION_REGEXP, password)) {

            throw new IllegalStateException("Incorrect username / password");

        } else {

            this.password = password;

        }
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

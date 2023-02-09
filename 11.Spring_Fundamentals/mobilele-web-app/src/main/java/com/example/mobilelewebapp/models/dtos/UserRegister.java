package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.utils.validators.UniqueEmail;
import jakarta.validation.constraints.*;

public class UserRegister {

    // todo: add correct error messages for annotation, fix register.html
    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @Size(min = 5)
    private String password;

    @NotNull
    @Size(min = 5)
    private String confirmPassword;

    public UserRegister() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

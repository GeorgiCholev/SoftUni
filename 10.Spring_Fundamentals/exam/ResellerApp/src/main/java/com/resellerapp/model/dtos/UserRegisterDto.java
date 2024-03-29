package com.resellerapp.model.dtos;

import com.resellerapp.model.util.validations.passwordsMatch.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordsMatch
public class UserRegisterDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

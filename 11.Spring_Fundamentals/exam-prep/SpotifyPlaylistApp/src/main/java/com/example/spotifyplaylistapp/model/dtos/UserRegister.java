package com.example.spotifyplaylistapp.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.spotifyplaylistapp.util.constants.ErrorMessages.*;

public class UserRegister {

    @NotBlank(message = INVALID_USERNAME)
    @Size(min = 3, max = 20, message = INVALID_USERNAME)
    private String username;

    @NotNull(message = EMPTY_EMAIL)
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$",
            message = EMPTY_EMAIL)
    private String email;

    @NotNull(message = INVALID_PASSWORD)
    @Size(min = 3, max = 20, message = INVALID_PASSWORD)
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

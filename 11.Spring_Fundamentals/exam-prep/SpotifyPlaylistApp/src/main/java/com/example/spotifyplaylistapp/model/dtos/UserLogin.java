package com.example.spotifyplaylistapp.model.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.spotifyplaylistapp.util.constants.ErrorMessages.INVALID_PASSWORD;
import static com.example.spotifyplaylistapp.util.constants.ErrorMessages.INVALID_USERNAME;

public class UserLogin {

    @NotBlank(message = INVALID_USERNAME)
    @Size(min = 3, max = 20, message = INVALID_USERNAME)
    private String username;

    @NotNull(message = INVALID_PASSWORD)
    @Size(min = 3, max = 20, message = INVALID_PASSWORD)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

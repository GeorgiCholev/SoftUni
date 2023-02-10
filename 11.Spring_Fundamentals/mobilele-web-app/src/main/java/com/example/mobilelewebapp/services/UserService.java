package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.UserLogin;
import com.example.mobilelewebapp.models.dtos.UserRegister;
import com.example.mobilelewebapp.models.entities.User;

import java.util.Optional;

public interface UserService {
    boolean register(UserRegister userRegister);

    boolean checkEmailNotPresent(String email);

    Optional<User> findByEmail(String email);

    void logIn(UserLogin userLogin);

    void logOut();
}

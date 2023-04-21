package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.UserLoginDto;
import com.example.mobilelewebapp.validation.UniqueFieldError;
import com.example.mobilelewebapp.models.dtos.UserRegisterDto;
import com.example.mobilelewebapp.models.entities.User;

public interface UserService {
    User getByUsername(String username);

    User getByEmail(String email);

    User getById(Long id);

    UniqueFieldError uniqueFieldErrorOrRegister(UserRegisterDto dto);

    boolean login(UserLoginDto dto);

    void logout();

}

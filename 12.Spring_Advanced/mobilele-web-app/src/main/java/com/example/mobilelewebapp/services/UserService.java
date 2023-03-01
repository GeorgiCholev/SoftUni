package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.UserLoginDto;
import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.validation.UniqueFieldError;
import com.example.mobilelewebapp.models.dtos.UserRegisterDto;
import com.example.mobilelewebapp.models.entities.User;

import java.util.Set;

public interface UserService {
    User getByUsername(String username);

    User getByEmail(String email);

    User getById(Long id);

    UniqueFieldError uniqueFieldErrorOrRegister(UserRegisterDto dto);

    Set<Role> getUserRoles(Long id);

//    boolean login(UserLoginDto dto);
//
//    void logout();

}

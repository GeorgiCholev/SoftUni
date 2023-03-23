package com.example.pathfinderwebapp.services;

import com.example.pathfinderwebapp.models.dtos.UserLoginDto;
import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;

public interface UserService {
    boolean register(UserRegisterDto dto);

    boolean login(UserLoginDto dto);

    void logout();
}

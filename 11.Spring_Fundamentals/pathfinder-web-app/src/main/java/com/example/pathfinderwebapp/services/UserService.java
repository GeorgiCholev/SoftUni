package com.example.pathfinderwebapp.services;

import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;

public interface UserService {
    boolean register(UserRegisterDto dto);
}

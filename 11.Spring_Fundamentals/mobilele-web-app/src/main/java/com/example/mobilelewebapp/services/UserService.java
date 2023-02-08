package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.UserRegister;

public interface UserService {
    boolean register(UserRegister userRegister);
}

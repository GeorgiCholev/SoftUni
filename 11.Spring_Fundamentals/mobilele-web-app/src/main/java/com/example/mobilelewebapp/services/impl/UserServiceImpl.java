package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.dtos.UserRegister;
import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.repositories.UserRepository;
import com.example.mobilelewebapp.services.RoleService;
import com.example.mobilelewebapp.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public boolean register(UserRegister userRegister) {

        //todo: confirm passwords match and unique email

        Role roleUser = roleService.getRoleUser();

        User user = new User(userRegister, LocalDateTime.now(), roleUser);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkEmailNotPresent(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
}

package com.example.pathfinderwebapp.services.impl;

import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;
import com.example.pathfinderwebapp.models.entities.Role;
import com.example.pathfinderwebapp.models.entities.User;
import com.example.pathfinderwebapp.models.entities.enums.RoleType;
import com.example.pathfinderwebapp.repositories.UserRepository;
import com.example.pathfinderwebapp.services.RoleService;
import com.example.pathfinderwebapp.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public boolean register(UserRegisterDto dto) {
        User withUsername = userRepository.findByUsername(dto.getUsername()).orElse(null);
        if (withUsername != null) return false;

        Role userRole = roleService.getRole(RoleType.USER);
        dto.encodePasswords(passwordEncoder);
        User newlyRegistered = new User(dto, userRole);

        this.userRepository.save(newlyRegistered);

        return true;
    }
}

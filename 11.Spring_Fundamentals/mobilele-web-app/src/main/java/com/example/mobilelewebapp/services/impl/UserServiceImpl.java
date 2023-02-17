package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.loggedInUser.LoggedInUser;
import com.example.mobilelewebapp.models.dtos.UserLogin;
import com.example.mobilelewebapp.models.dtos.UserRegister;
import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.repositories.UserRepository;
import com.example.mobilelewebapp.services.RoleService;
import com.example.mobilelewebapp.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private LoggedInUser loggedInUser;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           PasswordEncoder passwordEncoder, LoggedInUser loggedInUser) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.loggedInUser = loggedInUser;
    }

    @Override
    public boolean register(UserRegister userRegister) {

        Role roleUser = roleService.getRoleUser();
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        User user = new User(userRegister, LocalDateTime.now(), roleUser);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkEmailNotPresent(String email) {
        return findByEmail(email).isEmpty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void logIn(UserLogin userLogin) {
        Optional<User> optUser = findByEmail(userLogin.getEmail());

        if (optUser.isEmpty()) {
            return;
        }

//        if () {}

        User entity = optUser.get();

        loggedInUser.setUp(entity);
    }

    @Override
    public void logOut() {
        loggedInUser.clear();
    }


}

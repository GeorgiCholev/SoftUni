package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.validation.UniqueFieldError;
import com.example.mobilelewebapp.models.dtos.UserRegisterDto;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.repositories.UserRepository;
import com.example.mobilelewebapp.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Set<Role> getUserRoles(Long id) {
        User user = this.getById(id);
        return user != null ? user.getRoles() : Collections.emptySet();
    }

    @Override
    public UniqueFieldError uniqueFieldErrorOrRegister(UserRegisterDto dto) {
        if (this.getByEmail(dto.getEmail()) != null) {
            return new UniqueFieldError(UniqueFieldError.UniqueFieldType.EMAIL);
        }

        if (this.getByUsername(dto.getUsername()) != null) {
            return new UniqueFieldError(UniqueFieldError.UniqueFieldType.USERNAME);
        }

        this.encodePasswordFor(dto);
        userRepository.save(new User(dto));
        return null;
    }

//    @Override
//    public boolean login(UserLoginDto dto) {
//        User user = this.getByUsername(dto.getUsername());
//        if (user == null) return false;
//
//        if (!this.passwordEncoder.matches(dto.getPassword(), user.getPassword())) return false;
//
//        return true;
//    }

//    @Override
//    public void logout() {
//        httpSession.invalidate();
//        currentUser.logout();
//    }

    private void encodePasswordFor(UserRegisterDto dto) {
        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
    }
}

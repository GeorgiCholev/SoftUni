package com.example.pathfinderwebapp.services.impl;

import com.example.pathfinderwebapp.models.dtos.UserLoginDto;
import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;
import com.example.pathfinderwebapp.models.entities.Role;
import com.example.pathfinderwebapp.models.entities.User;
import com.example.pathfinderwebapp.models.entities.enums.RoleType;
import com.example.pathfinderwebapp.models.sessionUser.SessionUser;
import com.example.pathfinderwebapp.repositories.UserRepository;
import com.example.pathfinderwebapp.services.RoleService;
import com.example.pathfinderwebapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final SessionUser sessionUser;
    private final HttpSession httpSession;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           PasswordEncoder passwordEncoder, SessionUser sessionUser,
                           HttpSession httpSession) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.sessionUser = sessionUser;
        this.httpSession = httpSession;
    }

    private User getWithUsernameOrNull(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    private boolean isAdmin(User user) {
        Set<Role> roles = user.getRoles();
        Role adminRole = roleService.getRole(RoleType.ADMIN);

        return roles.contains(adminRole);
    }

    @Override
    public boolean register(UserRegisterDto dto) {
        User withUsername = getWithUsernameOrNull(dto.getUsername());
        if (withUsername != null) return false;

        Role userRole = roleService.getRole(RoleType.USER);
        dto.encodePasswords(passwordEncoder);
        User newlyRegistered = new User(dto, userRole);

        this.userRepository.save(newlyRegistered);

        return true;
    }

    @Override
    @Transactional
    public boolean login(UserLoginDto dto) {
        User withUsername = getWithUsernameOrNull(dto.getUsername());
        if (withUsername == null) return false;
        if (!passwordEncoder.matches(dto.getPassword(), withUsername.getPassword())) return false;

        boolean isAdmin = this.isAdmin(withUsername);
        sessionUser.login(withUsername, isAdmin);
        return true;
    }

    @Override
    public void logout() {
        sessionUser.logout();
        httpSession.invalidate();
    }
}

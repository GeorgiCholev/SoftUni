package com.example.shoppinglistapp.services;

import com.example.shoppinglistapp.models.dtos.UserLoginDTO;
import com.example.shoppinglistapp.models.dtos.UserRegisterDTO;
import com.example.shoppinglistapp.models.entities.User;
import com.example.shoppinglistapp.models.sessionUser.CurrentUser;
import com.example.shoppinglistapp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final HttpSession httpSession;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 CurrentUser currentUser, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.httpSession = httpSession;
    }

    public boolean register(UserRegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
        return false;
        }

        encodePassword(dto);
        userRepository.save(new User(dto));
        return true;
    }

    private void encodePassword(UserRegisterDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null);

        if (user == null) return false;
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) return false;

        currentUser.login(user);
        return true;
    }

    public void logout() {
        httpSession.invalidate();
        currentUser.logout();
    }
}

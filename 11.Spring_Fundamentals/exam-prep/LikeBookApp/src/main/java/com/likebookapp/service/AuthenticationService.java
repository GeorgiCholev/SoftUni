package com.likebookapp.service;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.model.sessionEntity.CurrentUser;
import com.likebookapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    private final HttpSession session;

    public AuthenticationService(UserRepository userRepository, CurrentUser currentUser, HttpSession session) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.session = session;
    }


    public boolean register(UserRegisterDTO dto) {

        if (getUserByUsername(dto.getUsername()) != null) {
            return false;
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return false;
        }

        userRepository.save(new User(dto));
        return true;
    }

    public boolean login(UserLoginDTO dto) {
        User user = getUserByUsername(dto.getUsername());
        if (user == null) {
            return false;
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            return false;
        }

        currentUser.logIn(user);
        return true;
    }


    public void logout() {
        session.invalidate();
        currentUser.logOut();
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}

package com.example.gamestore.sevices.user;

import com.example.gamestore.exceptions.InvalidStateException;
import com.example.gamestore.models.User;
import com.example.gamestore.models.dto.RegisterUserDTO;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Outputs.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private User loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void acquireService(String[] data) {

        String service = data[0];

        switch (service) {
//          RegisterUser|email|password|confirmPassword|fullName
            case REGISTER_USER -> registerUser(Arrays.copyOfRange(data, 1, data.length));

//          LoginUser|email|password
            case LOG_IN_USER -> logInUser(Arrays.copyOfRange(data, 1, data.length));

//          Logout
            case LOG_OUT_USER -> logOutUser();
        }
    }

    private void logOutUser() {

        if (this.loggedInUser == null) {
            System.out.println(NO_USER_LOGGED_IN);
            return;
        }

        String loggedOutUserFullName = loggedInUser.getFullName();
        loggedInUser = null;

        System.out.printf((SUCCESSFUL_LOG_OUT_FORMAT), loggedOutUserFullName);
    }

    private void logInUser(String[] data) {
        String email = data[0];
        String password = data[1];

        try {

            Optional<User> byEmailAndPassword = this.userRepository.findByEmailAndPassword(email, password);
            if (byEmailAndPassword.isEmpty()) {
                throw new InvalidStateException(INCORRECT_EMAIL_OR_PASSWORD);
            }

            User user = byEmailAndPassword.get();

            if (user.equals(loggedInUser)) {
                throw new InvalidStateException(USER_ALREADY_LOGGED_IN);
            } else {
                loggedInUser = user;
            }

        } catch (InvalidStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.printf(SUCCESSFUL_LOG_IN_FORMAT, loggedInUser.getFullName());
    }

    private void registerUser(String[] data) {

        String email = data[0];
        String password = data[1];
        String confirmPassword = data[2];
        String fullName = data[3];

        RegisterUserDTO userRegisterDTO;
        try {
            validateEmailForUnique(email);

            validateFullNameForUnique(fullName);

            userRegisterDTO = new RegisterUserDTO(email, password, confirmPassword, fullName);
        } catch (InvalidStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        User user = modelMapper.map(userRegisterDTO, User.class);

        setFirstUserToAdmin(user);

        this.userRepository.save(user);

        System.out.printf((SUCCESSFUL_REGISTRATION_FORMAT), user.getFullName());
    }

    private void validateFullNameForUnique(String fullName) throws InvalidStateException {
        if (this.userRepository.existsUserByFullName(fullName)) {
            throw new InvalidStateException(String.format(MUST_BE_UNIQUE_FORMAT, "full name"));
        }
    }

    private void validateEmailForUnique(String email) throws InvalidStateException {

        if (this.userRepository.existsUserByEmail(email)) {
            throw new InvalidStateException(String.format(MUST_BE_UNIQUE_FORMAT, "email"));
        }
    }

    private void setFirstUserToAdmin(User user) {

        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

    }
}

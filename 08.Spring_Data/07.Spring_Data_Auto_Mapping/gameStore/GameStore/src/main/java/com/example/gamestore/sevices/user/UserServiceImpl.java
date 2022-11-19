package com.example.gamestore.sevices.user;

import com.example.gamestore.models.dto.RegisterUserDTO;
import com.example.gamestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void acquireService(String[] data) throws IllegalAccessException {

        String service = data[0];
        data = Arrays.copyOfRange(data, 1, data.length - 1);

        switch (service) {
            case "RegisterUser":

                registerUser(data);
//            RegisterUser|<email>|<password>|<confirmPassword>|<fullName>

                break;
            case "LoginUser":

                break;
            case "Logout":

                break;
            default:
                throw new IllegalAccessException("Service " + service + "is not provided");
        }
    }

    private void registerUser(String[] data) {

        String email = data[0];
        String password = data[1];
        String confirmPassword = data[2];
        String fullName = data[3];

        try {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO(email, password, confirmPassword, fullName);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }


    }

}

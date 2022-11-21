package com.example.gamestore.models.dto;

import com.example.gamestore.exceptions.InvalidStateException;

import java.util.regex.Pattern;

import static com.example.gamestore.constants.Outputs.*;

public class RegisterUserDTO {

    private static final String EMAIL_VALIDATION_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    private String email;

    private String password;

    private String fullName;

    public RegisterUserDTO(String email, String password, String confirmPassword, String fullName)
            throws InvalidStateException
    {
        setEmail(email);
        setPassword(password, confirmPassword);
        setFullName(fullName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidStateException {
        if (Pattern.matches(EMAIL_VALIDATION_REGEX, email)) {

            this.email = email;

        } else {

            throw new InvalidStateException(EMAIL_REQUIREMENTS_FAILED);

        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password, String confirmPassword) throws InvalidStateException {
        if (!password.equals(confirmPassword)) {

            throw new InvalidStateException(PASSWORDS_MUST_MATCH);

        } else if (!Pattern.matches(PASSWORD_VALIDATION_REGEX, password)) {

            throw new InvalidStateException(PASSWORD_REQUIREMENTS_FAILED);

        } else {

            this.password = password;

        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

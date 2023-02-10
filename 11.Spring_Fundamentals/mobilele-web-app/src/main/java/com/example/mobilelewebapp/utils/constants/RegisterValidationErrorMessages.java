package com.example.mobilelewebapp.utils.constants;

public class RegisterValidationErrorMessages {

    private RegisterValidationErrorMessages() {
    }

    public static final String EMAIL_NOT_PROVIDED = "Email must be provided.";
    public static final String EMAIL_NOT_VALID = "Email must be valid.";
    public static final String EMAIL_NOT_UNIQUE = "A user with this email already exists";
    public static final String FIRST_NAME_NOT_PROVIDED = "First name must be provided.";
    public static final String LAST_NAME_NOT_PROVIDED = "Last name must be provided.";
    public static final String NAME_SIZE_NOT_ALLOWED = "Name must be between 2 and 20 symbols.";
    public static final String PASSWORD_SIZE_NOT_ALLOWED = "Password must be more than 5 symbols.";
}

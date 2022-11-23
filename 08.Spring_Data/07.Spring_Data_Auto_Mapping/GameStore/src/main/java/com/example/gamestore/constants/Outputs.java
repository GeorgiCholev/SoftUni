package com.example.gamestore.constants;

public class Outputs {

    private Outputs() {
    }


    public static final String SERVICE_NOT_SUPPORTED_FORMAT = "Service %s is not supported";

    public static final String SUCCESSFUL_REGISTRATION_FORMAT = "%s was registered";

    public static final String EMAIL_REQUIREMENTS_FAILED = "EMAIL must contain @ sign and a period.";

    public static final String PASSWORD_REQUIREMENTS_FAILED = "Password length must be at least 6 symbols " +
            "and must contain at least 1 uppercase, 1 lowercase letter and 1 digit";

    public static final String INCORRECT_EMAIL_OR_PASSWORD = "Incorrect email / password";

    public static final String PASSWORDS_MUST_MATCH = "Passwords must match.";

    public static final String MUST_BE_UNIQUE_FORMAT = "A user with this %s already exists.";

    public static final String USER_ALREADY_LOGGED_IN = "This user is already logged in";

    public static final String SUCCESSFUL_LOG_IN_FORMAT = "Successfully logged in %s";

    public static final String SUCCESSFUL_LOG_OUT_FORMAT = "User %s successfully logged out";

    public static final String NO_USER_LOGGED_IN = "Cannot log out. No user was logged in.";

    public static final String TITLE_REQUIREMENTS_FAILED = "Title has to begin with an uppercase letter" +
            " and must have length between 3 and 100 symbols";

    public static final String NUMBER_FIELD_REQUIREMENTS_FAILED_FORMAT = "%s must be a positive number";

    public static final String TRAILER_REQUIREMENTS_FAILED = "Trailer must be from YouTube " +
            "and have ID of exactly 11 characters.";

    public static final String IMAGE_THUMBNAIL_REQUIREMENTS_FAILED = "Thumbnail must start with http:// or https://";

    public static final String DESCRIPTION_REQUIREMENTS_FAILED = "Description must be at least 20 symbols";

    public static final String SUCCESSFUL_GAME_ADDED_FORMAT = "Added %s";

    public static final String SUCCESSFUL_GAME_EDITED_FORMAT = "Edited %s";

    public static final String GAME_DOES_NOT_EXIST = "Game does not exist";

    public static final String FIELD_CAN_NOT_BE_MODIFIED = "Failed to modify field %s";
}

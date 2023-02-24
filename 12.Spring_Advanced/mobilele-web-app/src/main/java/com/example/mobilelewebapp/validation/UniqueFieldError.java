package com.example.mobilelewebapp.validation;

public class UniqueFieldError {

    private UniqueFieldType uniqueFieldType;

    private ErrorMessageType errorMessageType;

    public UniqueFieldError(UniqueFieldType uniqueFieldType) {
        this.uniqueFieldType = uniqueFieldType;
        this.setErrorMessageType(this.uniqueFieldType);
    }

    public boolean uniqueFieldIsEmail() {
        return uniqueFieldType == UniqueFieldType.EMAIL;
    }

    public boolean uniqueFieldIsUsername() {
        return uniqueFieldType == UniqueFieldType.USERNAME;
    }

    public String getErrorMessage() {
        return errorMessageType.message;
    }

    private void setErrorMessageType(UniqueFieldType uniqueFieldType) {
        switch (uniqueFieldType) {
            case EMAIL -> this.errorMessageType = ErrorMessageType.EMAIL_ERROR;
            case USERNAME -> this.errorMessageType = ErrorMessageType.USERNAME_ERROR;
        }
    }

    public enum UniqueFieldType {
        EMAIL, USERNAME
    }

    public enum ErrorMessageType {

        EMAIL_ERROR("A user with this email already exists."),
        USERNAME_ERROR("A user with this username already exists.");

        private final String message;

        ErrorMessageType(String message) {
            this.message = message;
        }

    }
}

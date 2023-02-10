package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.utils.customValidations.passwordsMatch.PasswordsMatch;
import com.example.mobilelewebapp.utils.customValidations.uniqueEmail.UniqueEmail;
import jakarta.validation.constraints.*;

import static com.example.mobilelewebapp.utils.constants.RegisterValidationErrorMessages.*;

@PasswordsMatch
public class UserRegister {

    @NotBlank(message = EMAIL_NOT_PROVIDED)
    @Email(message = EMAIL_NOT_VALID)
    @UniqueEmail
    private String email;

    @NotBlank(message = FIRST_NAME_NOT_PROVIDED)
    @Size(min = 2, max = 20, message = NAME_SIZE_NOT_ALLOWED)
    private String firstName;

    @NotBlank(message = LAST_NAME_NOT_PROVIDED)
    @Size(min = 2, max = 20, message = NAME_SIZE_NOT_ALLOWED)
    private String lastName;

    @NotNull
    @Size(min = 5, message = PASSWORD_SIZE_NOT_ALLOWED)
    private String password;

    private String confirmPassword;

    public UserRegister() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

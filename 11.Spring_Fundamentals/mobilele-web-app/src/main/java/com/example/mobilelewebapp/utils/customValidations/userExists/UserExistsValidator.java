package com.example.mobilelewebapp.utils.customValidations.userExists;

import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserExistsValidator implements ConstraintValidator<UserExists, Object> {

    private final UserService userService;

    @Autowired
    public UserExistsValidator(UserService userService) {
        this.userService = userService;
    }

    private String emailField;
    private String passwordField;
    private String message;

    @Override
    public void initialize(UserExists constraintAnnotation) {
        this.emailField = constraintAnnotation.emailField();
        this.passwordField = constraintAnnotation.passwordField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object email = beanWrapper.getPropertyValue(emailField);
        Object password = beanWrapper.getPropertyValue(passwordField);

        boolean valid;

        if (email instanceof String && password != null) {

            Optional<User> optUser = userService.findByEmail((String) email);
            if (optUser.isEmpty()) {
                valid = false;
            } else {
                valid = optUser.get().getPasswordHashCode() == password.hashCode();
            }
        } else {
            valid = false;
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(emailField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}

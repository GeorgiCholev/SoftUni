package com.resellerapp.model.util.validations.passwordsMatch;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    private String passwordField;
    private String confirmPasswordField;
    private String message;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        this.passwordField = constraintAnnotation.passwordField();
        this.confirmPasswordField = constraintAnnotation.confirmPasswordField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper =  PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object password = beanWrapper.getPropertyValue(passwordField);
        Object confirmPassword = beanWrapper.getPropertyValue(confirmPasswordField);

        boolean valid = Objects.equals(password, confirmPassword);

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmPasswordField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}

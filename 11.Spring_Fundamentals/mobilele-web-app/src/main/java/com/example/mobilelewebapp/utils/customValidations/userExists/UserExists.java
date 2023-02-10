package com.example.mobilelewebapp.utils.customValidations.userExists;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserExistsValidator.class)
public @interface UserExists {

    String emailField() default "email";

    String passwordField() default "password";

    String message() default "Invalid username or password.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

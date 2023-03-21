package com.example.pathfinderwebapp.validation.passwordsMatch;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordsMatchValidator.class)
public @interface PasswordsMatch {

    String passwordField() default "password";

    String confirmPasswordField() default "confirmPassword";

    String message() default "Passwords should match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

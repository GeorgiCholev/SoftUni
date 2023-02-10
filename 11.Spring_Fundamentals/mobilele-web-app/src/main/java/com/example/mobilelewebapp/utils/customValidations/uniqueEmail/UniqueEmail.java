package com.example.mobilelewebapp.utils.customValidations.uniqueEmail;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.mobilelewebapp.utils.constants.RegisterValidationErrorMessages.EMAIL_NOT_UNIQUE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default EMAIL_NOT_UNIQUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

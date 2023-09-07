package com.forum.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Constraint(validatedBy=RetypePasswordValidator.class)
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface RetypePassword {

    String message() default "{passwordsDoNotMatch}";

    Class[] groups() default {};
    
    Class[] payload() default {};
}

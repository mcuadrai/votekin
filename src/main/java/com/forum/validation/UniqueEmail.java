package com.forum.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Constraint(validatedBy=UniqueEmailValidator.class)
@NotBlank(message="{blankEmail}")
@Email
@Size(min=4, max=250, message="{emailSizeError}")
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueEmail {

    String message() default "{duplicateEmail}";

    Class[] groups() default {};
    
    Class[] payload() default {};
}

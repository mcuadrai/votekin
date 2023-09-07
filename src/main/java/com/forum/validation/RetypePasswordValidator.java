package com.forum.validation;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.forum.commands.ResetPasswordCommand;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class RetypePasswordValidator
	implements ConstraintValidator<RetypePassword, ResetPasswordCommand> {
	
	@Override
	public void initialize(RetypePassword arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(ResetPasswordCommand resetPasswordCommand, ConstraintValidatorContext context) {
		
		if (Objects.equals(resetPasswordCommand.getPassword(),
				resetPasswordCommand.getRetypePassword()))
			return true;
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("{passwordsDoNotMatch}")
			.addPropertyNode("retypePassword").addConstraintViolation();
		
		return false;
	}
}

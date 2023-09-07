package com.forum.validation;

import org.springframework.stereotype.Component;

import com.forum.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class EmailExistsValidator
	implements ConstraintValidator<EmailExists, String> {
	
	private UserRepository userRepository;
	
	public EmailExistsValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void initialize(EmailExists arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		
		return userRepository.findByEmail(email).isPresent();
	}
}

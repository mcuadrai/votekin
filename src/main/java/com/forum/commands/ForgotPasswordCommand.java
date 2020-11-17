package com.forum.commands;

import com.forum.validation.EmailExists;

public class ForgotPasswordCommand {
	
	@EmailExists
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

package com.forum.commands;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




public class ResetPasswordCommand {
	
	@NotEmpty
	@Size(min=6, max=32)
	private String password;
	
	@NotEmpty
	@Size(min=6, max=32)
	private String retypePassword;
	
	
	private String resetPasswordCode;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}
	
	
}

package com.forum.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.forum.domain.User;
import com.forum.validation.Password;
import com.forum.validation.UniqueEmail;

public class UserCommand {
	
	public static interface SignupValidation {}
	public static interface UpdateValidation {}
	
	private Long id;
	
	@UniqueEmail(groups = SignupValidation.class)
	private String email;
	
	@NotBlank(groups = {SignupValidation.class, UpdateValidation.class})
	@Size(min=1, max=100, groups = {SignupValidation.class, UpdateValidation.class})
	private String name;
	
	@Password(groups = SignupValidation.class)
	private String password;
	
	private String foto;
	
	private String avatarG;
	private String avatarM;
	private String avatarS;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	public String getAvatarG() {
		return avatarG;
	}
	public void setAvatarG(String avatarG) {
		this.avatarG = avatarG;
	}
	public String getAvatarM() {
		return avatarM;
	}
	public void setAvatarM(String avatarM) {
		this.avatarM = avatarM;
	}
	public String getAvatarS() {
		return avatarS;
	}
	public void setAvatarS(String avatarS) {
		this.avatarS = avatarS;
	}
	public User toUser() {
		
		User user = new User();
		
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		
		return user;
	}
}

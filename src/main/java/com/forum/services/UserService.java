package com.forum.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.boot.context.event.ApplicationReadyEvent;

import com.forum.commands.ForgotPasswordCommand;
import com.forum.commands.UserCommand;
import com.forum.domain.User;

public interface UserService {


	void afterApplicationReady(ApplicationReadyEvent event);
	void verify(String verificationCode);
	void resendVerificationMail(User user) throws MessagingException;
	void forgotPassword(ForgotPasswordCommand forgotPasswordCommand);
	void resetPassword(String resetPasswordCode, String password);
	User fetchById(Long userId);
	void update(User oldUser, UserCommand userCommand);
	void resetPassword(Long idUser, String password);
	void signup(User user);
	User fetchByUserName(String username);
	List<User> fetchByName(String username);
	void savePhoto(Long id, String uniqueFilename, String uniqueMediumAvatarFilename, String uniqueSmallAvatarFilename, String uniqueDebateAvatarFilename);
	void signup(User user, boolean isSocial, String imageUrl);
	void addPreferedFilter(String choices);
	void addThemePrefered(String theme);
	
	
}

package com.forum.services;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.forum.commands.ForgotPasswordCommand;
import com.forum.commands.UserCommand;
import com.forum.domain.Ciudadano;
import com.forum.domain.Role;
import com.forum.domain.User;
import com.forum.mail.MailSender;
import com.forum.repositories.UserRepository;
import com.forum.utils.MyUtils;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	private static Log log = LogFactory.getLog(UserServiceImpl.class);

	private PasswordEncoder passwordEncoder;
	private static UserRepository userRepository;
	private MailSender mailSender;
	private String applicationUrl;

	@Autowired
	private IUploadFileService uploadFileService;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailSender mailSender,
			@Value("${applicationUrl}") String applicationUrl) {

		UserServiceImpl.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
		this.applicationUrl = applicationUrl;

		log.info("Inside UserServiceImpl construct");
	}

	@PostConstruct
	public void init() {

		log.info("Inside Post construct");
	}

	@Override
	@EventListener
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void afterApplicationReady(ApplicationReadyEvent event) {

		log.info("Inside afterApplicationReady ");
		// inserción de administrador al inicio de la aplicación!!!

		// User user = new User();
		//
		// if (!userRepository.findByEmail(adminEmail).isPresent()) {
		//
		// user.setEmail(adminEmail);
		// user.setName(adminName);
		//// user.setPassword(passwordEncoder.encode(adminPassword));
		// user.getRoles().add(Role.ADMIN);
		//
		// userRepository.save(user);
		// }
	}

	@Override
	public void signup(User user) {
		signup(user, false, null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void signup(User user, boolean isSocial, String imageUrl) {

		// User user = userCommand.toUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setVerificationCode(UUID.randomUUID().toString());

		// TODO revisar esta opcion

		user.setEnabled(false);

		if (isSocial) {
			user.setEnabled(true);
			user.getRoles().add(new Role(user.getId(), "VERIFIED"));

			try {
				
				System.out.println("Image URL: "+imageUrl);
				
				File foto = uploadFileService.downloadImageFromUrl(imageUrl);

				//String uniqueGAvatarFilename = null;
				String uniqueMediumAvatarFilename = null;
				String uniqueDebateAvatarFilename = null;
				String uniqueSmallAvatarFilename = null;

				String uniqueFilename = null;

				uniqueSmallAvatarFilename = uploadFileService.copy(foto, 45, 45); // foto pequeña
				uniqueDebateAvatarFilename = uploadFileService.copy(foto, 60, 60); // foto debate original
				uniqueMediumAvatarFilename = uploadFileService.copy(foto, 63, 63); // foto recientes
				uniqueFilename = uploadFileService.copy(foto, 186, 182); // foto perfil
				
				Ciudadano ciudadano = new Ciudadano();
				ciudadano.setFotoAvatar(uniqueFilename);
				ciudadano.setFotoAvatarMedium(uniqueMediumAvatarFilename);
				ciudadano.setFotoAvatarSmall(uniqueSmallAvatarFilename);
				ciudadano.setFotoAvatarDebate(uniqueDebateAvatarFilename);

				user.setCiudadano(ciudadano);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			user.setEnabled(false);
			user.getRoles().add(new Role(user.getId(), "UNVERIFIED"));
		}
		
		boolean usernameNotSet = true;
		String postFix = "";
		do {
			
			String userName = "@"+user.getName().split(" ")[0]+postFix;
			Optional<User> userOpt = userRepository.findByAccountName(userName);
			if(!userOpt.isPresent()) {
				user.setAccountName(userName);
				usernameNotSet = false;
			}else {
				String uuid = UUID.randomUUID().toString(); 
				postFix = uuid.substring(uuid.length()-4, uuid.length());
			}
			
		}while(usernameNotSet);

		userRepository.save(user);
		
		if (!isSocial) {
			MyUtils.afterCommit(() -> {

				// MyUtils.login(user);
				try {

					sendVerificationMail(user);

				} catch (MessagingException e) {

					log.warn("Sending verification mail to " + user.getEmail() + " failed", e);
				}
			});
		}

	}

	private void sendVerificationMail(User user) throws MessagingException {

		String verificationLink = applicationUrl + "/users/" + user.getVerificationCode() + "/verify";

		mailSender.send(user.getEmail(), MyUtils.getMessage("email.verifySubject"),
				MyUtils.getMessage("email.verifyBody", verificationLink));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void verify(String verificationCode) {

		User user = userRepository.findByVerificationCode(verificationCode);

		MyUtils.validate(user.getRoles().contains(new Role("UNVERIFIED")), "message.alreadyVerified");
		// MyUtils.validate(verificationCode.equals(user.getVerificationCode()),
		// "wrongVerificationCode");

		user.getRoles().remove(new Role(user.getId(), MyUtils.UNVERIFIED));
		user.getRoles().add(new Role(user.getId(), MyUtils.VERIFIED));
		user.setVerificationCode(null);
		user.setEnabled(true);

		userRepository.save(user);
		MyUtils.afterCommit(() -> MyUtils.login(user));
	}

	@Override
	public void resendVerificationMail(User user) throws MessagingException {

		MyUtils.validate(user != null, "message.userNotFound");
		MyUtils.validate(isAdminOrSelfLoggedIn(user), "message.notPermitted");
		MyUtils.validate(user.getRoles().contains(new Role(MyUtils.UNVERIFIED)), "message.alreadyVerified");

		sendVerificationMail(user);
	}

	private boolean isAdminOrSelfLoggedIn(User user) {

		Optional<User> currentUser = MyUtils.currentUser();

		if (!currentUser.isPresent())
			return false;

		User cUser = currentUser.get();

		// if (cUser.getRoles().contains(Role.ADMIN))
		// return true;

		if (cUser.getId().equals(user.getId()))
			return true;

		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void forgotPassword(ForgotPasswordCommand forgotPasswordCommand) {

		User user = userRepository.findByEmail(forgotPasswordCommand.getEmail()).get();

		user.setResetPasswordCode(UUID.randomUUID().toString());
		userRepository.save(user);
		MyUtils.afterCommit(() -> mailResetPasswordLink(user));
	}

	private void mailResetPasswordLink(User user) {

		String resetPasswordLink = applicationUrl + "/reset-password/" + user.getResetPasswordCode();

		try {
			mailSender.send(user.getEmail(), MyUtils.getMessage("email.resetPasswordSubject"),
					MyUtils.getMessage("email.resetPasswordBody", resetPasswordLink));
		} catch (MessagingException e) {
			log.warn("Error sending reset password mail to " + user.getEmail(), e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void resetPassword(String resetPasswordCode, String password) {

		Optional<User> user = userRepository.findByResetPasswordCode(resetPasswordCode);

		MyUtils.validate(user.isPresent(), "message.wrongResetPasswordCode");
		User u = user.get();

		u.setPassword(passwordEncoder.encode(password));
		u.setResetPasswordCode(null);

		userRepository.save(u);
	}

	@Override
	public User fetchById(Long userId) {

		Optional<User> user = userRepository.findById(userId);
		MyUtils.validate(user.isPresent(), "message.userNotFound");

		user.get().setEditable(isAdminOrSelfLoggedIn(user.get()));
		if (!user.get().isEditable())
			user.get().setEmail("Confidential");

		return user.get();
	}

	@Override
	public User fetchByUserName(String username) {

		Optional<User> user = userRepository.findByEmail(username);
		MyUtils.validate(user.isPresent(), "message.userNotFound");

		user.get().setEditable(isAdminOrSelfLoggedIn(user.get()));
		if (!user.get().isEditable())
			user.get().setEmail("Confidential");

		return user.get();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(User oldUser, UserCommand userCommand) {

		// MyUtils myUtils = new MyUtils(messageSource);

		MyUtils.validate(oldUser != null, "message.userNotFound");
		MyUtils.validate(isAdminOrSelfLoggedIn(oldUser), "message.notPermitted");

		oldUser.setName(userCommand.getName());
		//
		User currentUser = MyUtils.currentUser().get();
		// if (currentUser.getRoles().contains(Role.ADMIN))
		// oldUser.setRoles(userCommand.getRoles());

		userRepository.save(oldUser);

		MyUtils.afterCommit(() -> {
			if (currentUser.getId().equals(oldUser.getId()))
				MyUtils.login(oldUser);
		});
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void resetPassword(Long idUser, String password) {

		Optional<User> user = userRepository.findById((long) idUser);
		User u = user.get();
		u.setPassword(passwordEncoder.encode(password));
		u.setResetPasswordCode(null);

		userRepository.save(u);

	}

	// TODO cambar a servicio de Ciudadano
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void savePhoto(Long userId, String foto, String fotoMedium, String fotoSmall, String fotoAvatarDebate) {
		Optional<User> user = userRepository.findById((long) userId);

		User currentUser = MyUtils.currentUser().get();

		User newUser = user.get();
		if (newUser.getCiudadano() == null) {
			Ciudadano ciudadano = new Ciudadano();
			ciudadano.setFotoAvatar(foto);
			ciudadano.setFotoAvatarMedium(fotoMedium);
			ciudadano.setFotoAvatarSmall(fotoSmall);
			ciudadano.setFotoAvatarDebate(fotoAvatarDebate);

			newUser.setCiudadano(ciudadano);
		} else {
			newUser.getCiudadano().setFotoAvatar(foto);
			newUser.getCiudadano().setFotoAvatarMedium(fotoMedium);
			newUser.getCiudadano().setFotoAvatarSmall(fotoSmall);
			newUser.getCiudadano().setFotoAvatarDebate(fotoAvatarDebate);
		}

		userRepository.save(newUser);
		MyUtils.afterCommit(() -> {
			if (currentUser.getId().equals(newUser.getId()))
				MyUtils.login(newUser);
		});

	}

	@Override
	public List<User> fetchByName(String name) {
		// TODO Auto-generated method stub

		List<User> users = userRepository.findByNameIgnoreCaseContaining(name);

		return users;
	}
	
	@Override
	public void addPreferedFilter(String choices) {
		User currentUser =  MyUtils.currentUser().get();
		User user = userRepository.getOne(currentUser.getId());
		user.setFilters(choices);
		userRepository.save(user);
		
		 Authentication authentication =  new UsernamePasswordAuthenticationToken(user, user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("VERIFIED")));

         
         
        
        //log.debug("Logging in with {}", authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@Override
	public void addThemePrefered(String theme) {
		User currentUser =  MyUtils.currentUser().get();
		User user = userRepository.getOne(currentUser.getId());
		user.setThemeFilters(theme);
		userRepository.save(user);
		
		 Authentication authentication =  new UsernamePasswordAuthenticationToken(user, user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("VERIFIED")));

         
         
        
        //log.debug("Logging in with {}", authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public static UserRepository getUserRepository() {
		return userRepository;
	}

	// @Override
	// @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	// public void updatePhoto(User oldUser, UserCommand userCommand) {
	//
	// //MyUtils myUtils = new MyUtils(messageSource);
	//
	// MyUtils.validate(oldUser != null, "message.userNotFound");
	// MyUtils.validate(isAdminOrSelfLoggedIn(oldUser), "message.notPermitted");
	//
	// Optional<User> user = userRepository.findById((long) idUser);
	//
	// oldUser.getCiudadano()
	////
	// userRepository.save(oldUser);
	//
	// MyUtils.afterCommit(() -> {
	// if (currentUser.getId().equals(oldUser.getId()))
	// MyUtils.login(oldUser);
	// });
	// }

}
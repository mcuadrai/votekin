package com.forum.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.ResetPasswordCommand;
import com.forum.commands.UserCommand;
import com.forum.commands.UserCommand.UpdateValidation;
import com.forum.domain.User;
import com.forum.responsemodels.ResponseModel;
import com.forum.responsemodels.ResponseModel.Status;
import com.forum.services.IUploadFileService;
import com.forum.services.UserService;
//import com.forum.services.UserService;
import com.forum.utils.MyUtils;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	UserDetailsService userDetails;
	
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/{verificationCode}/verify")
	public String verify(@PathVariable String verificationCode, RedirectAttributes redirectAttributes) {
		
		userService.verify(verificationCode);
		MyUtils.flash(redirectAttributes,"success","message.verificationSuccessful");
		
		return "redirect:/login";
	}
	
	@GetMapping("/{userId}/resend-verification-mail")
	public String resendVerificationMail(@PathVariable("userId") User user, RedirectAttributes redirectAttributes) throws MessagingException {
		
		userService.resendVerificationMail(user);
		MyUtils.flash(redirectAttributes,"success", "message.verificationMailResent");
		return "redirect:/";
	}
	
	@GetMapping("/{userId}")
	public String getById(@PathVariable Long userId, Model model) {
		
		model.addAttribute(userService.fetchById(userId));
		return "user";
	}
	
	@GetMapping("/{userId}/edit")
	public String edit(@PathVariable("userId") User user, Model model) {
		
		model.addAttribute(user);
		return "user-edit";
	}
	
	
	@PostMapping("/{userId}/edit")
	public String update(@PathVariable("userId") User oldUser,
			@Validated(UpdateValidation.class)
			@ModelAttribute("user") UserCommand userCommand,
			BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale) {
		
		if (result.hasErrors())
			return "user-edit";
		
		userService.update(oldUser, userCommand);
		redirectAttributes.addFlashAttribute("success", messageSource.getMessage("message.updateUserSuccess", null, locale));
		return "redirect:/";
	}
	
	@GetMapping("/change-password")
	public String changePassword(@PathVariable("userId") User user, Model model) {
		
		model.addAttribute(new ResetPasswordCommand());
		return "reset-password";
	}
	
	@PostMapping("/change-password")
	public String doChangePassword( 
			@Validated ResetPasswordCommand resetPasswordCommand,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		User user =  MyUtils.currentUser().get();
		
		if (!Objects.equals(resetPasswordCommand.getPassword(), resetPasswordCommand.getRetypePassword()))
			//TODO revisar si se muesta el mensaje
			//result.rejectValue("retypePassword", "passwordsDoNotMatch");
		    MyUtils.flash(redirectAttributes, "error", "message.passwordsDoNotMatch");
		if (result.hasErrors())
			return "reset-password";
		
		userService.resetPassword(user.getId(), resetPasswordCommand.getPassword());
		MyUtils.flash(redirectAttributes, "success", "message.passwordChanged");
		return "redirect:/";
	}
	
	
	
	@GetMapping("/change-email")
	public String changeEmail(@PathVariable("userId") UserCommand user, Model model) {
		
		model.addAttribute(user);
		return "reset-password";
	}
	
	@GetMapping("/{userId}/setting")
	public String settingAndPrivacy(@PathVariable("userId") Long userId,  Model model) {
		
		User user =	 userService.fetchById(userId);
		model.addAttribute("user", user);
		return "setting_privacy";
	}
	
	@PostMapping("/{userId}/photo")
	public String updatePhoto(
			@ModelAttribute("userId") User user,
			BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, 
			RedirectAttributes flash,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {
			return "setting_privacy";
		}


		if (!foto.isEmpty()) {

			if (user.getId() != null && user.getId() > 0 &&
				user.getCiudadano() != null &&
				user.getCiudadano().getFotoAvatar() != null && user.getCiudadano().getFotoAvatar().length() > 0
					) {

				uploadFileService.delete(user.getCiudadano().getFotoAvatar());
				uploadFileService.delete(user.getCiudadano().getFotoAvatarMedium());
				uploadFileService.delete(user.getCiudadano().getFotoAvatarSmall());
				uploadFileService.delete(user.getCiudadano().getFotoAvatarDebate());
			}
			

//			String uniqueGAvatarFilename = null;
			String uniqueMediumAvatarFilename = null;
			String uniqueDebateAvatarFilename = null;
			String uniqueSmallAvatarFilename  = null;
			
			String uniqueFilename = null;
			
			try {
				uniqueSmallAvatarFilename  = uploadFileService.copy(foto,45,45);   //foto pequeña
				uniqueDebateAvatarFilename = uploadFileService.copy(foto,60,60);  //foto debate original
				uniqueMediumAvatarFilename = uploadFileService.copy(foto,63,63);   //foto recientes 
				uniqueFilename             = uploadFileService.copy(foto,186,182); //foto perfil
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", messageSource.getMessage("message.user.photo.upload.success", null, locale) + "'" + uniqueFilename + "'");

			
			userService.savePhoto(user.getId(), uniqueFilename, uniqueMediumAvatarFilename, uniqueSmallAvatarFilename, uniqueDebateAvatarFilename);
			flash.addFlashAttribute("success", messageSource.getMessage("message.user.photo.edit.success", null, locale));
			

		}
		
		
		return "redirect:/users/"+user.getId() +"/setting";
	}
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	
	@PostMapping(value="/userList")
	public @ResponseBody String getUsersList(@RequestParam String name) {
		
		List<User> users = userService.fetchByName(name);
		
		String response = ResponseModel.getResponse(Status.SUCCESS, users, "");
		
		return response;
	}


}

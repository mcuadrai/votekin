package com.forum.controllers;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.ForgotPasswordCommand;
import com.forum.services.UserService;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
	
	private static Log log = LogFactory.getLog(ForgotPasswordController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	public String forgotPassword(Model model) {
		
		model.addAttribute(new ForgotPasswordCommand());
		return "forgot-password";
	}	

	@PostMapping
	public String doForgotPassword(
			@Validated ForgotPasswordCommand forgotPasswordCommand,
			BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale) {
		
		if (result.hasErrors())
			return "forgot-password";
		
		userService.forgotPassword(forgotPasswordCommand);
		redirectAttributes.addFlashAttribute("success", messageSource.getMessage("message.forgotPasswordMailSent", null, locale));
		return "redirect:/login";
	}	
}

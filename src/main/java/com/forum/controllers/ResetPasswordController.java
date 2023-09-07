package com.forum.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.ResetPasswordCommand;
import com.forum.services.UserService;
//import com.forum.services.UserService;
import com.forum.utils.MyUtils;

import jakarta.validation.Valid;

@Controller

public class ResetPasswordController {

	//private static Log log = LogFactory.getLog(ResetPasswordController.class);

	@Autowired
	private UserService userService;
	
	

	@GetMapping("/reset-password/{resetPasswordCode}")
	public String resetPassword(@PathVariable String resetPasswordCode, Model model) {

		ResetPasswordCommand resetPasswordCommand = new ResetPasswordCommand();
		resetPasswordCommand.setResetPasswordCode(resetPasswordCode);
		
		model.addAttribute("resetPasswordCommand", resetPasswordCommand);
		return "reset-password";
	}

	@PostMapping("/reset-password/create")
	public String doResetPassword(
			@Valid @ModelAttribute("resetPasswordCommand") ResetPasswordCommand resetPasswordCommand,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model) {

		
		if (result.hasErrors())
			return "reset-password";

		if (!Objects.equals(resetPasswordCommand.getPassword(), resetPasswordCommand.getRetypePassword())) {
			MyUtils.sendViewMessage(model, "error", "message.passwordsDoNotMatch");
			return "reset-password";
		}
		
		userService.resetPassword(resetPasswordCommand.getResetPasswordCode(), resetPasswordCommand.getPassword());
		MyUtils.flash(redirectAttributes, "success", "message.passwordChanged");
		return "redirect:/login";
	}
}

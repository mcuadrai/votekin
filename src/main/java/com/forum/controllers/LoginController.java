package com.forum.controllers;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.UserCommand;
import com.forum.domain.User;
import com.forum.services.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
	private MessageSource messageSource;
    
    
//    @GetMapping({"/"})
//	public String index(@RequestParam(value = "error", required = false) String error,
//			@RequestParam(value = "logout", required = false) String logout, Model model,
//			Principal principal,
//			Authentication authentication,
//			RedirectAttributes flash, Locale locale) {
//
//		
//		
//		    
//		if (principal != null) {
//			//User userSession = (User) authentication.getPrincipal();
//			
//			//TODO no funciona el pintado del mensaje en la página 
//			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
//			return "redirect:/opinion/recentposts";
//		}
//            
//		
//		if (error != null) {
//			//TODO tal vez buscar el usuario, y ver si tiene el rol asignado y mostrar mensaje.
//			//model.addAttribute("error",messageSource.getMessage("Incorrect email or password, or email is not verified, please try again!:: "+ error);
//			Object[] parameters = new Object[1]; 
//			parameters[0] = ".";
//			model.addAttribute("error",  messageSource.getMessage("message.login.error", parameters, locale));
//		}
//
//		if (logout != null) {
//			model.addAttribute("success",  messageSource.getMessage("message.logout.success", null, locale));
//		}
//				
//		UserCommand user = new UserCommand();
//		model.addAttribute("user", user);
//		
//		return "index";
//	}

    
	@GetMapping({"/","/login"})
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model,
			Principal principal,
			Authentication authentication,
			RedirectAttributes flash, Locale locale) {

		
		
		    
		if (principal != null) {
			//User userSession = (User) authentication.getPrincipal();
			
			//TODO no funciona el pintado del mensaje en la página 
			flash.addFlashAttribute("info", "Ya ha iniciado sesin anteriormente");
			return "redirect:/opinion/recentposts";
		}
            
		
		if (error != null) {
			//TODO tal vez buscar el usuario, y ver si tiene el rol asignado y mostrar mensaje.
			//model.addAttribute("error",messageSource.getMessage("Incorrect email or password, or email is not verified, please try again!:: "+ error);
			Object[] parameters = new Object[1]; 
			parameters[0] = ".";
			model.addAttribute("error",  messageSource.getMessage("message.login.error", parameters, locale));
			flash.addFlashAttribute("error", messageSource.getMessage("message.login.error", parameters, locale));
			return "login";
		}

		if (logout != null) {
			model.addAttribute("success",  messageSource.getMessage("message.logout.success", null, locale));
		}
				
		UserCommand user = new UserCommand();
		model.addAttribute("user", user);
		
		return "login";
	}
	
	@GetMapping({"/signup"})
	public String signup(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model,
			Principal principal,
			Authentication authentication,
			RedirectAttributes flash, Locale locale) {

		
		
		    
		if (principal != null) {
			//User userSession = (User) authentication.getPrincipal();
			
			//TODO no funciona el pintado del mensaje en la página 
			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
			return "redirect:/opinion/recentposts";
		}
            
		
		if (error != null) {
			//TODO tal vez buscar el usuario, y ver si tiene el rol asignado y mostrar mensaje.
			//model.addAttribute("error",messageSource.getMessage("Incorrect email or password, or email is not verified, please try again!:: "+ error);
			Object[] parameters = new Object[1]; 
			parameters[0] = ".";
			model.addAttribute("error",  messageSource.getMessage("message.login.error", parameters, locale));
		}

		if (logout != null) {
			model.addAttribute("success",  messageSource.getMessage("message.logout.success", null, locale));
		}
				
		UserCommand user = new UserCommand();
		model.addAttribute("user", user);
		
		return "signup";
	}

	 //@Validated(SignupValidation.class) @ModelAttribute("user") UserCommand user,
	
	@PostMapping("/signup")
	public String doSignup(
			@Valid User user,
			BindingResult result,
			Model model, Locale locale) {

		// log.debug("entrando a doSignup");

		if (result.hasErrors()) {
			model.addAttribute("errorSignup", "true");
			return "login";
		}

		userService.signup(user);
		model.addAttribute("success", messageSource.getMessage("message.signupSuccess",null, locale));
		
		return "login";
	}

}

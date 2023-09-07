package com.forum.controllers;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forum.utils.MyUtils;

@Controller
public class HelloController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	
	@RequestMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("name",
				MyUtils.getMessage("text",
						"http://below18.example.com",
						"http://above18.example.com"));
		
		//throw new RuntimeException("Una excepcion");
		
		return "hello";
	}
	
	@RequestMapping("/hello/{id}")
	public String helloId(Model model,
			@PathVariable int id,
			Optional<String> name) {
		
		model.addAttribute("id", id);
		model.addAttribute("name", name.orElse("No Name"));
		
		return "hello-id";
	}

	
//	@RequestMapping("/hello")
//	public ModelAndView hello() {
//		
//        ModelAndView mav = new ModelAndView("hello"); // the logical view name
//		mav.addObject("name", "Sanjay");
//		return mav;
//	}
	
	@RequestMapping({"/home"})
	public String home(Authentication authentication) {
		
		if(authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		} else 
			return "redirect:/login";
		
		//return "home";
		return "redirect:/opinion/recentposts";
	}
	
	
	
	
}

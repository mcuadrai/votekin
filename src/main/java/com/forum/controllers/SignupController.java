package com.forum.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.forum.services.UserService;

@Controller
public class SignupController {
	
	private static Log log = LogFactory.getLog(SignupController.class);

	@Autowired
	private UserService userService;

	
	

}

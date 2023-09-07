package com.forum.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.mail.MailSender;

import jakarta.mail.MessagingException;

@RestController
public class MailController {
	
	private MailSender mailSender;

	public MailController(MailSender smtp) {
		this.mailSender = smtp;
	}

	@RequestMapping("/mail")
	public String mail() throws MessagingException {
		
		mailSender.send("trupti.green@gmail.com", "A test mail", "Body of the test mail");
		
		return "Mail sent";
	}
}

package com.forum.mail;

import jakarta.mail.MessagingException;

public interface MailSender {
	
	void send(String to, String subject, String body) throws MessagingException;
}

package com.BGAnimation.persistLayer;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailHandler {

    final static String SYSTEM_EMAIL = "csci4050@gmail.com";
    final static String SYSTEM_NAME = "CSCI 4050";
    final static String PASSWORD = "thisisapassword";
	    
	public static void newUserEmail(String email, String name, int code) {
		String subject = "Welcome to BGAnimation!";
		String body = "Hello there " + name + "!\n\n" 
				+ "We're excited for you to join the BGAnimationInsider Squad! " 
				+ "Please enter code " + code + " under my profile to activate your account."
				+ "\n\nEnjoy!\nTeam BGA";
		sendMail(email, subject, body, name);
	}
    
	public static void sendMail(String email, String subject, String body, String name) {
		Email e = EmailBuilder.startingBlank()
		    .from(SYSTEM_NAME, SYSTEM_EMAIL)
		    .to(name, email)
		    .withSubject(subject)
		    .withPlainText(body)
		    .buildEmail();
		
		MailerBuilder.withSMTPServer("smtp.gmail.com", 25, SYSTEM_EMAIL, PASSWORD)
		    .buildMailer()
		    .sendMail(e, true);
	}
}

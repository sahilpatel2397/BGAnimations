package com.BGAnimation.persistLayer;

import java.util.Date;
import java.util.Properties; 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.BGAnimation.objectLayer.BookingOrder;
import com.BGAnimation.objectLayer.Ticket;
import com.BGAnimation.objectLayer.User;

public class EmailHandler {

    final static String SYSTEM_EMAIL = "bganimation@wallou.net";
    final static String SYSTEM_NAME = "BGAnimation Group";
    final static String PASSWORD = "wearebga";
    
	public static void newUserEmail(String email, String name, int code) {
		String subject = "Welcome to BGAnimation!";
		String body = "Hello there " + name + "!\n\n" 
				+ "We're excited for you to join the BGAnimationInsider Squad! " 
				+ "Please enter code " + code + " under my profile to activate your account."
				+ "\n\nEnjoy!\nTeam BGA";
		sendMail(email, subject, body);
	}
	
	public static void sendBookingConfirmation(User u, BookingOrder b) {
		String subject = "BGAnimation Booking Order #" + b.getBookingId();
		String body = "Hi " + u.getFirstName() + ",\n" 
				+ "Thank you for your recent purchase. Here are the details for your records:\n\n";
		for (Ticket t : b.getAllTickets()) {
			body += t.toString();
		}
		body += "\n\nEnjoy your movie!\nTeam BGA";
		sendMail(u.getEmail(), subject, body);
	}

    public static void sendMail(String email, String subject, String body) {
	Session s = Session.getInstance(initProperty(), initAuth());
	
	try {
	    MimeMessage m = new MimeMessage(s);
	    
	    m.addHeader("Content-type", "text/HTML; charset=UTF-8");
	    m.addHeader("format", "flowed");
	    m.addHeader("Content-Transfer-Encoding", "8bit");

	    m.setFrom(new InternetAddress(SYSTEM_EMAIL, SYSTEM_NAME));
	    m.setReplyTo(InternetAddress.parse(SYSTEM_EMAIL, false));
	    m.setSubject(subject, "UTF-8");
	    m.setText(body, "UTF-8");
	    m.setSentDate(new Date());
	    m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));		    
	
	    Transport.send(m);
	    System.out.println("Message successfully sent");
	} catch (Exception e) {
	    System.out.println("An error occured while attempting to send the email");
	    System.out.println(e);
	}
    }

    public static Properties initProperty() {
	Properties p = new Properties();
	p.put("mail.smtp.host", "smtp.gmail.com");
	p.put("mail.smtp.port", "587");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.starttls.enable", "true");
	return p;
    }

    public static Authenticator initAuth() {
	return new Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(SYSTEM_EMAIL, PASSWORD);
	    }
	};
    }
}
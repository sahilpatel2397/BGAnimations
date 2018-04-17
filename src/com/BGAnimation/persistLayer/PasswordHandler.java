package com.BGAnimation.persistLayer;

/**
 * Code adapted from @Michael Wiggins 
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordHandler {

    protected static String[] encryptPassword(String passwordToHash) 
        throws NoSuchAlgorithmException, NoSuchProviderException {
            
        String generatedPassword = null;
        String salt = generateSalt();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        String data[] = {generatedPassword, salt};
        return data;
    }
    
    protected static String getUserEncryptedPassword(String providedPassword, String salt) 
    		throws NoSuchAlgorithmException, NoSuchProviderException {
    	String generatedPassword = null;
    	try {
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		md.update(salt.getBytes());
            byte[] bytes = md.digest(providedPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	
    	return generatedPassword;
    }
    
    private static String generateSalt() 
        throws NoSuchAlgorithmException, NoSuchProviderException {
            
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
    
    protected static String generateRandomPassword() {
    	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        int password_length = random.nextInt((15 - 8) + 1) + 8;
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < password_length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
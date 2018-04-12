package com.BGAnimation.objectLayer;

import java.util.ArrayList;

public class Card {
	
	
	private String creditCard;
	private String type;
	private String expirationDate;
	private String billingAddress;
	private int securityCode;

	public Card(String creditCard, String type, String expirationDate, String billingAddress, int securityCode) {
	
		this.creditCard = creditCard;
		this.type = type;
		this.expirationDate = expirationDate;
		this.billingAddress = billingAddress;
		this.securityCode = securityCode;

	}




	public String getCreditCard() {
		return creditCard;
	}

	



	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getExpirationDate() {
		return expirationDate;
	}




	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}




	public String getBillingAddress() {
		return billingAddress;
	}




	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}




	public int getSecurityCode() {
		return securityCode;
	}




	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	
	
	public Card() {

	}

 	


	
}



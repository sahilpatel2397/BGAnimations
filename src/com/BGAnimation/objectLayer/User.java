package com.BGAnimation.objectLayer;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String avatarUrl;
	private int isBanned;
	private int isAdmin;
	private String password;
	private int activationCode;
	private int promotion;

	public User() {
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public int getIsBanned() {
		return isBanned;
	}

	public void setBanned(int isBanned) {
		this.isBanned = isBanned;
	}

	public int getIsAdmin(){
		return isAdmin;
	}
	
	public int getSendPromotions(){
		return this.promotion;
	}
	
	public void setSendPromotions(int promotion){
		this.promotion = promotion;
	}
	
	public void setIsAdmin(int admin){
		this.isAdmin = admin;
	}
	
	public User(int userID, String firstName, String lastName, String email, String address, int isBanned, int isAdmin) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.isBanned = isBanned;
		this.isAdmin = isAdmin;
	}
	
	public User(String firstName, String lastName, String email, String address, String password, int isBanned, int isAdmin, int activationCode, int promotion) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.activationCode = activationCode;
		this.promotion = promotion;
		this.isAdmin = isAdmin;
		this.isBanned = isBanned;
	}






	
}



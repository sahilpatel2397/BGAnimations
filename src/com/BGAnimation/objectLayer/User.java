package com.BGAnimation.objectLayer;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String avatarUrl;
	private boolean isBanned;
	private boolean isAdmin;
	private String password;

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

	public boolean getIsBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public boolean getIsAdmin(){
		return isAdmin;
	}
	
	public User(int userID, String firstName, String lastName, String email, String address, boolean isBanned, boolean isAdmin) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.isBanned = isBanned;
		this.isAdmin = isAdmin;
	}

	public User(String firstName, String lastName, String email, String address, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.password = password;
	}






	
}


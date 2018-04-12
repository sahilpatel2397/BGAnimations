package com.BGAnimation.objectLayer;

import java.util.ArrayList;
import java.util.Date;


public class Ticket {


	private int ticketNum;
	private int movieID;
	private Date showTime;
	private int locationOfSeat;
	private int bookingOrderId;
	private int movieId;
	private String ticketType;
	private float purchasePrice;
	
	public Ticket(int ticketNum, Date showTime, int locationOfSeat, String ticketType, float purchasePrice, int bookingOrderId, int movieId) {

		this.ticketNum = ticketNum;
		this.movieID = movieID;
		this.showTime = showTime;
		this.locationOfSeat = locationOfSeat;
		this.ticketType = ticketType;
		this.purchasePrice = purchasePrice;
		this.bookingOrderId = bookingOrderId;
		this.movieId = movieId;
	}

	public Ticket() {

	}


	public int getTicketNum() {
		return ticketNum;
	}




	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}




	public int getMovieID() {
		return movieID;
	}




	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}




	public Date getShowTime() {
		return showTime;
	}




	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}




	public int getLocationOfSeat() {
		return locationOfSeat;
	}




	public void setLocationOfSeat(int locationOfSeat) {
		this.locationOfSeat = locationOfSeat;
	}




	public String getTicketType() {
		return ticketType;
	}




	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}




	public float getPurchasePrice() {
		return purchasePrice;
	}




	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}




	



 	


	
}



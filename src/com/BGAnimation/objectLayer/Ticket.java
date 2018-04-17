package com.BGAnimation.objectLayer;

import java.util.Date;

public class Ticket {

	private int ticketNum;
	private int movieID;
	private Date showTime;
	private int seatLocation;
	private int bookingOrderId;
	private int movieId;
	private String ticketType;
	private float purchasePrice;
	
	public Ticket(int ticketNum, Date showTime, int seatLocation, String ticketType, float purchasePrice, int bookingOrderId, int movieId) {

		this.ticketNum = ticketNum;
		this.movieId = movieID;
		this.showTime = showTime;
		this.seatLocation = seatLocation;
		this.ticketType = ticketType;
		this.purchasePrice = purchasePrice;
		this.bookingOrderId = bookingOrderId;
		this.setMovieId(movieId);
	}

	public Ticket() {

	}

	public int getTicketId() {
		return ticketNum;
	}
	
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public int getSeatLocation() {
		return seatLocation;
	}
	
	public int getBookingOrderId() {
		return bookingOrderId;
	}

	public void setseatLocation(int seatLocation) {
		this.seatLocation = seatLocation;
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

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
}

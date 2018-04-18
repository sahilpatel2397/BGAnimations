package com.BGAnimation.objectLayer;

import java.util.Date;

public class Hall {
	private int totalSeats;
	private Date showtime;
	private int numSeatsRemaining;
	private int showId;
	private int hallId;
	
	public Hall(int totalSeats, Date showtime, int numSeatsRemaining, int showId) {
		this.totalSeats = totalSeats;
		this.showtime = showtime;
		this.numSeatsRemaining = numSeatsRemaining;
		this.showId = showId;
	}
	
	public Hall(int hallId, int totalSeats, Date showtime, int numSeatsRemaining, int showId) {
		this.totalSeats = totalSeats;
		this.showtime = showtime;
		this.numSeatsRemaining = numSeatsRemaining;
		this.showId = showId;
		this.hallId = hallId;
	}
	
	public Hall() { }
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public Date getShowtime() {
		return showtime;
	}
	
	public int getNumSeatsRemaining() {
		return numSeatsRemaining;
	}
	
	public int getShowId() {
		return showId;
	}
	
	public int getHallId() {
		return hallId;
	}
	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}
	
	public void setNumSeatsRemaining(int numSeatsRemaining) {
		this.numSeatsRemaining = numSeatsRemaining;
	}
	
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
}

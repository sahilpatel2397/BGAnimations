package com.BGAnimation.objectLayer;

public class Seat {
	private int seatId;
	private boolean isReserved;
	private int showId;
	private int hallId;
	
	public Seat(boolean isReserved, int showId, int hallId) {
		this.isReserved = isReserved;
		this.showId = showId;
		this.hallId = hallId;
	}
	
	public Seat(int seatId, boolean isReserved, int showId, int hallId) {
		this.seatId = seatId;
		this.isReserved = isReserved;
		this.showId = showId;
		this.hallId = hallId;
	}
	
	public Seat() { }
	
	public int getSeatId() {
		return seatId;
	}
	
	public boolean getSeatReservation() {
		return isReserved;
	}
	
	public int getShowId() {
		return showId;
	}
	
	public int getHallId() {
		return hallId;
	}
	
	public void setSeatReservation(boolean isReserved) {
		this.isReserved = isReserved;
	}
	
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
}

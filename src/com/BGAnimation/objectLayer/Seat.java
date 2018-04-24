package com.BGAnimation.objectLayer;

public class Seat {
	private int seatId;
	private int seatNum;
	private boolean isReserved;
	private int showId;
	
	public Seat(boolean isReserved, int showId, int seatNum) {
		this.isReserved = isReserved;
		this.showId = showId;
		this.seatNum = seatNum;
	}
	
	public Seat(int seatId, boolean isReserved, int showId, int seatNum) {
		this.seatId = seatId;
		this.isReserved = isReserved;
		this.showId = showId;
		this.seatNum = seatNum;
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
	
	public int getSeatNum() {
		return seatNum;
	}
	
	public void setSeatReservation(boolean isReserved) {
		this.isReserved = isReserved;
	}
	
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
	public void setHallId(int seatNum) {
		this.seatNum = seatNum;
	}
}

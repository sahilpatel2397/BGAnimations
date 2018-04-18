package com.BGAnimation.objectLayer;

import java.util.Date;

public class Showtime {

	private int showId;
	private int hallId;
	private Date time;
	private int numSeats;
	private int movieId;
	
	public Showtime() { }
	
	public Showtime(int hallId, Date time, int numSeats, int movieId) {
		this.hallId = hallId;
		this.time = time;
		this.numSeats = numSeats;
		this.movieId = movieId;
	}
	
	public int getHallId() {
		return hallId;
	}
	
	public Date getTime() {
		return time;
	}
	
	 public int getNumSeats() {
		 return numSeats;
	 }
	 
	 public int getMovieId() {
		 return movieId;
	 }
	 
	 public int getShowId() {
		 return showId;
	 }
	 
	 public void setHallId(int hallId) {
		 this.hallId = hallId;
	 }
	 
	 public void setTime(Date time) {
		 this.time = time;
	 }
	 
	 public void setNumSeats(int numSeats) {
		 this.numSeats = numSeats;
	 }
	 
	 public void setMovieId(int movieId) {
		 this.movieId = movieId;
	 }
	
}



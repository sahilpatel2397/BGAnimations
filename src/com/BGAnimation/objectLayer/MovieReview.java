package com.BGAnimation.objectLayer;

public class MovieReview{
	private int reviewId;
	private int userId;
	private String review;
	private int movie_id;
	
	public MovieReview(){
		
	}
	
	public MovieReview(int reviewId, int userId, String review, int movie_id){
		this.reviewId = reviewId;
		this.userId = userId;
		this.review = review;
		this.movie_id = movie_id;
	}
	
	public String getReview(){
		return review;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public int getReviewId() {
		return reviewId;
	}
	
	public int getMovieId() {
		return movie_id;
	}
	
	public void setReview(String s) {
		this.review = s;
	}
	
	public void setUserId(int id) {
		this.userId = id;
	}
	
	public void setReviewId(int id) {
		this.reviewId = id;
	}
	
	public void setMovieId(int id) {
		this.movie_id = id;
	}
	
}
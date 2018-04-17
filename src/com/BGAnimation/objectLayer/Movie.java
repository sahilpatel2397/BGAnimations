package com.BGAnimation.objectLayer;

public class Movie{

	private int movieId;
	private String title;
	private String director;
	private String cast;
	private String genre;
	private String description;
	private String bannerURL;
	private float ratings;
	private String mpaaRating;
	
	public Movie(){
		
	}
	
	public Movie(int movieId, String title, String director, String cast, String genre, String description, String bannerURL, float ratings, String mpaaRating){
		this.movieId = movieId;
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.genre = genre;
		this.description = description;
		this.bannerURL = bannerURL;
		this.ratings = ratings;
		this.mpaaRating = mpaaRating;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDirector(){
		return director;
	}
	
	public String getCast(){
		return cast;
	}
	
	public String getGenre(){
		return genre;
	}
	public String getDescription(){
		return description;
	}
	public String getbannerURL(){
		return bannerURL;
	}
	
	public float getRatings(){
		return ratings;
	}
	
	public String getMPAARating(){
		return mpaaRating;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setDirector(String director){
		this.director = director;
	}
	
	public void setCast(String cast){
		this.cast = cast;
	}
	
	public void setGenre(String genre){
		this.genre = genre;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setRatings(float ratings){
		this.ratings = ratings;
	}
	
	public void setMPAARating(String mpaaRating){
		this.mpaaRating = mpaaRating;
	}

	public int getMovieId() {
		return movieId;
	}
}

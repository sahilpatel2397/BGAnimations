import java.util.ArrayList;

public class Movie {

	
	private int movieID;
	private String title;
	private String director;
	private String cast;
	private String genre;
	private String description;
	private String bannerUrl;
	private float ratings; 

	public Movie() {

	}


	public Movie(int movieID, String title, String director, String cast, String genre, String description, String bannerUrl, float ratings){
		this.movieID = movieID;
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.genre = genre;
		this.description = description;
		this.bannerUrl = bannerUrl;
		this.ratings = ratings;
	}


	public int getMovieID() {
		return movieID;
	}


	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getCast() {
		return cast;
	}


	public void setCast(String cast) {
		this.cast = cast;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBannerUrl() {
		return bannerUrl;
	}


	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}


	public float getRatings() {
		return ratings;
	}


	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

}



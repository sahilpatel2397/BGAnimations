class MovieReview{
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
	
	
}
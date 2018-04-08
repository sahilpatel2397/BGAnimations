import java.util.ArrayList;

public class Ticket {


	private int ticketNum;
	private int movieID;
	private String showTime;
	private int locationOfSeat;
	private String ticketType;
	private float purchasePrice;




	
	public Ticket(int ticketNum, int movieID, String showTime, int locationOfSeat, String ticketType,
			float purchasePrice) {

		this.ticketNum = ticketNum;
		this.movieID = movieID;
		this.showTime = showTime;
		this.locationOfSeat = locationOfSeat;
		this.ticketType = ticketType;
		this.purchasePrice = purchasePrice;
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




	public String getShowTime() {
		return showTime;
	}




	public void setShowTime(String showTime) {
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






public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String avatarUrl;
	private boolean isBanned;

	private ArrayList<BookingOrder> bookingOrders;
	private ArrayList<Card> cards;

	public User() {

	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public User(int userID, String firstName, String lastName, String email, String address, String avatarUrl, boolean isBanned, ArrayList<BookingOrder> bookingOrders, ArrayList<Card> cards) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.avatarUrl = avatarUrl;
		this.isBanned = isBanned;
		this.bookingOrders = bookingOrders;
		this.cards = cards;
	}


	public ArrayList<BookingOrder> getBookingOrders() {
		return bookingOrders;
	}

	public ArrayList<BookingOrder> getCards() {
		return cards;
	}








	
}



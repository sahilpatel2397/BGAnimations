import java.util.ArrayList;

public class Admin extends User{

	ArrayList<User> usersArrayList;
	ArrayList<BookingOrder> salesReport;
	

	public Admin(int userID, String firstName, String lastName, String email, String address, String avatarUrl, boolean isBanned) {
		super(int userID, String firstName, String lastName, String email, String address, String avatarUrl, boolean isBanned);
	}

	public void banUser(User u) {
		u.setBanned(true);
	}

	public void unbanUser(User u) {
		u.setBanned(false);
	}

	public void updateMovieAttribute(Movie m) {
		//Finish this function later
	}

	public void modifyPrice(Ticket t, float price) {
		t.setPrice(price);
	}

	public void modifyPromotion(Booking b, String promoCode) {
		b.setPromoCode(promoCode);
	}

	public void processRefund(Booking b) {
		b.remove()
	}

	public ArrayList<User> sendMassEmail(BGAnimationPersistImp b) {
		this.usersArrayList = b.getAllUsersWithoutAdmins();
		usersArrayList;
	}

	public ArrayList<BookingOrder> showSalesReport(BGAnimationPersistImpb b) {
		this.salesReport = b.getSalesReport();
		return salesReport;
	}

	public void deleteReview(Review r, BGAnimationPersistImpb b) {
		b.deleteReview(r);
	}

	public void modifyMovieAttribute(Movie m) {
		//Do this later
	}

}



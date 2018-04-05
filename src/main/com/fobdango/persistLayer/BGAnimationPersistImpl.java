import java.sql.User;
import java.sql.SQLException;
import Time;

public class BGAnimationsPersistImpl {

	/** Get data from the users table **/

	public static void addUser(User u) throws SQLException {
		String query = "INSERT INTO users " + 
		"(firstName, lastName, email, address, avatarUrl)" +
		" VALUES ('" + u.getFirstName() + "', '" + u.getLastName() + "', '" + 
		u.getEmail() + "', '" + u.getAddress() + "', '" + u.getAvatarUrl() +"');";
		
		DBAccessInterface.create(query);
	}

	public static void updateUser(User u) throws SQLException {
		String query = "UPDATE users "+
			"SET firstName = '"+u.getFirstName()+"', "+
				"lastName = '"+u.getLastName()+"', "+
				"email = '"+u.getEmail()+"', "+
				"address = '"+u.getAddress()+"', "+
				"avatarUrl = '"+u.getAvatarUrl()+
				"' WHERE userId = '"+u.getUserId()+";";
				
		DBAccessInterface.create(query);
	}

	public static void deleteUser(User u) throws SQLException {
		String query = "DELETE FROM users WHERE users.userId = " + u.getUserId() + ";";
		DBAccessInterface.delete(query);
	}

	public static boolean getUserBanStatus(User u) throws SQLException {
		String query = "SELECT isBanned FROM users WHERE users.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getBoolean("isBanned");
		}
	}

	public static void banUser(User u) throws SQLException {
		String query = "UPDATE users SET isBanned = True WHERE users.userId = " + 
			u.getUserId()+";";
		DBAccessInterface.create(query);
	}

	public static void unbanUser(User u) throws SQLException {
		String query = "UPDATE users SET isBanned = False WHERE users.userId = " + 
			u.getUserId()+";";
		DBAccessInterface.create(query);
	}

	public static String getUserFirstName(User u) throws SQLException {
		String query = "SELECT firstName FROM users WHERE users.userId = " + 
			u.getUserId()+";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("firstName");
		}
	}

	public static String getUserLastName(User u) throws SQLException {
		String query = "SELECT lastName FROM users WHERE users.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("lastName");
		}
	}

	public static String getUserEmail(User u) throws SQLException {
		String query = "SELECT email FROM users WHERE users.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("email");
		}
	}

	public static String getUserAddress(User u) throws SQLException {
		String query = "SELECT address FROM users WHERE users.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("address");
		}
	}

	public static String getUserAvatarUrl(User u) throws SQLException {
		String query = "SELECT avatarUrl FROM users WHERE users.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("avatarUrl");
		}
	}
	
	public static ArrayList<int> getAllAdministrators() throws SQLException {
		String query = "SELECT userId FROM users WHERE users.isAdmin = TRUE;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<int> admins = new ArrayList<int>();
		
		while(rs.next()) {
			admins.add(rs.getInt("userId"));
		}
		return admins;
	}
	
	public static void setAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = TRUE where users.userId = " +
			u.getUserId()+";";
		DBAccessInterface.create(query);
	}
	
	public static void removeAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = FALSE where users.userId = " +
			u.getUserId()+";";
		DbAccessInterface.create(query);
	}
	
	/** Get data from creditcard table **/
	
	public static ArrayList<String> getAllCardNums(User u) throws SQLException {
		String query = "SELECT creditcard FROM creditcard, users WHERE " +
			"creditcard.users_userId = " + u.getUserId() +";"; 
		
		ResultSet rs = DBAccessInterface.retrieve(query);	
		ArrayList<String> cards = new ArrayList<String>();
		
		while(rs.next()) {
			cards.add(rs.getString("creditcard"));
		}
		
		return cards;
	}
	
	public static String getCardType(User u, Card c) throws SQLException {
		String query = "SELECT type FROM creditcard, users WHERE " +
			"creditcard.users_userId = " + u.getUserId() + 
			" AND creditcard.creditcard = " + c.getCreditCardNum()+";";
		
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("type");
		}
	}
	
	public static Time getExpirationDate(Card c) 
		throws SQLException {
		String query = "SELECT expirationDate FROM creditcard WHERE " + 
			"creditcard.creditcard = " + c.getCreditCardNum() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getTime("expirationDate");
		}
	}
	
	public static String getBillingAddress(Card c) 
		throws SQLException {
		String query = "SELECT billingAddress FROM creditcard WHERE " +
		"creditcard.creditcard = " + c.getCreditCardNum() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("billingAddress");
		}
	}
	
	public static String int getSecurityCode(Card c) 
		throws SQLException {
		String query = "SELET securityCode FROM creditcard WHERE " + 
		"creditcard.creditcard = " + c.getCreditCardNum() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);	
		
		while(rs.next()) {
			return rs.getString("securityCode");
		}
	}
	
	public static void addCard(User u, Card c) throws SQLException {
		String query = "INSERT INTO creditcard " + 
		"(creditcard, type, expirationDate, billingAddress, securityCode, userId)" +
		" VALUES ('" + c.getCreditCardNum() + "', '" + c.getCardType() + "', '" + 
		c.getExpirationDate() + "', '" + c.getBillingAddress() + "', '" + 
		c.getSecurityCode() + "', '" + c.getUserId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void removeCard(Card c) throws SQLException {
		String query = "DELETE FROM creditcard WHERE creditcard.creditcard = " 
		+ c.getCreditCardNum() + ";";
		
		DBAccessInterface.delete(query);
	}
	
	// @Stephen
	public static ArrayList<User> getAllUsersWithoutAdmins() throws SQLException {
		String query = "SELECT users"
	}

	/** Get data from bookingOrder **/

	public static Time getBookingDate(BookingOrder b) 
		throws SQLException {
		String query = "SELECT date FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getTime("date");
		}
	}
	
	public static int getNumTicketsInOrder(BookingOrder b) throws SQLException {
		String query = "SELECT numTickets FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getInt("numTickets");
		}
	}
	
	public static String getPromoCode(BookingOrder b) throws SQLException {
		String query = "SELECT promoCode FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("promoCode");
		}
	}
	
	public static float getSubtotal(BookingOrder b) throws SQLException {
		String query = "SELECT subtotal FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getFloat("subtotal");
		}
	}
	
	public static float getTax(BookingOrder b) throws SQLException {
		String query = "SELECT tax FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getFloat("tax");
		}
	}
	
	public static float getTotal(BookingOrder b) throws SQLException {
		String query = "SELECT total FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getFloat("total");
		}
	}
	
	public static int getCreditCard(BookingOrder b) throws SQLException {
		String query = "SELECT creditcard FROM bookingOrder WHERE " + 
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getInt("creditcard");
		}
	}
	
	public static void createNewBookingOrder(User u, Card c, BookingOrder b) throws SQLException {
		String query = "INSERT INTO bookingOrder " + 
		"(date, numTickets, promoCode, subtotal, tax, total, creditcard, userId)" +
		" VALUES ('" + b.getBookingDate() + "', '" + b.getNumTicketsInOrder() 
		+ "', '" + b.getPromoCode() + "', '" + b.getSubtotal() + "', '" + 
		b.getTax() + "', '" + b.getTotal() + "', '" + c.getCreditCardNum() 
		+ "', '" + u.getUserId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteBookingOrder(BookingOrder b) throws SQLException {
		String query = "DELETE FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		DBAccessInterface.delete(query);
	}
	
	public static void updateBookingOrder(User u, Card c, BookingOrder b) 
		throws SQLException {
		String query = "UPDATE bookingOrder "+
			"SET date = '"+b.getNumTicketsInOrder()+"', "+
				"numTickets = '"+b.getNumTicketsInOrder()+"', "+
				"promoCode = '"+b.getPromoCode()+"', "+
				"subtoatl = '"+b.getSubtotal()+"', "+
				"tax = '"+b.getTax()+"', "+
				"total = '"+b.getTotal()+"', "+
				"creditcard = '"+c.getCreditCardNum+"', "+
				"userId = '" + u.getUserId()+
				"' WHERE bookingId = '"+ b.getBookingId()+";";
				
		DBAccessInterface.create(query);
	}
	
	/** Get data from movie **/
	
	public static String getMovieTitle(Movie m) throws SQLException {
		String query = "SELECT title FROM movie WHERE " +
		"movie.movieId = " + m.getMovieId() + ";";
		
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("title");
		}
	}
	
	public static void updateMovie(Movie m) throws SQLException {
		String query = "UPDATE movie "+
			"SET title = '"+m.getMovieTitle()+"', "+
				"director = '"+m.get+"', "+
				"cast = '"+b.getPromoCode()+"', "+
				"genre = '"+b.getSubtotal()+"', "+
				"description = '"+b.getTax()+"', "+
				"bannerUrl = '"+b.getTotal()+"', "+
				"ratings = '" + u.getUserId()+
				"' WHERE bookingId = '"+ b.getBookingId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static String getMovieDirector(Movie m) throws SQLException {
		
	}
	
	public static String getMovieCast(Movie m) throws SQLException {
		
	}
	
	public static String getMovieGenre(Movie m) throws SQLException {
		
	}
	
	public static String getMovieDescription(Movie m) throws SQLException {
		
	}
	
	public static String getMovieBannerUrl(Movie m) throws SQLException {
		
	}
	
	public static float getMovieRating(Movie m) throws SQLException {
		
	}
	
	public static void addNewMovie(Movie m) throws SQLException {
		
	}
	
	public static void deleteMovie(Movie m) throws SQLException {
		
	}
	
	/** Gets data from ticket **/
	
	public static int getTicketMovieId(Ticket t) throws SQLException {
		
	}
	
	public static Time getTicketShowtime(Ticket t) throws SQLException {
		
	}
	
	public static int getTicketSeatNum(Ticket t) throws SQLException {
		
	}
	
	public static String getTicketType(Ticket t) throws SQLException {
		
	}
	
	public static float getTicketPurchasePrice(Ticket t) throws SQLException {
		
	}
	
	public static int getTicketBookingOrderId(Ticket t) throws SQLException {
		
	}
	
	public static int getTicketMovieId(Ticket t) throws SQLException {
		
	}
	
	public static void updateTicket(Ticket t) throws SQLException {
		
	}
	
	public static void createNewTicket(Ticket t) throws SQLException {
		
	}
	
	public static void deleteTicket(Ticket t) throws SQLException {
		
	}
	
	/** Gets data from showtime **/
	
	public static int getShowtimeHallId(Showtime s) throws SQLException {
		
	}
	
	public static Time getShowtimeTime(Showtime s) throws SQLException {
		
	}
	
	public static int getShowtimeNumSeats(Showtime s) throws SQLException {
		
	}
	
	public static int getShowtimeMovieId(Showtime s) throws SQLException {
		
	}
	
	public static void updateShowtime(Showtime s) throws SQLException {
		
	}
	
	public static void createNewShowtime(Showtime s) throws SQLException {
		
	}
	
	public static void deleteShowtime(Showtime s) throws SQLException {
		
	}
	
	/** Gets data from movieReviews **/
	
	public static int getReviewUserId(Review r) throws SQLException {
		
	}
	
	public static String getReview(Review r) throws SQLException {
		
	}
	
	public static int getReviewMovieId(Review r) throws SQLException {
		
	}
	
	public static void updateReview(Review r) throws SQLException {
		
	}
	
	public static void createNewReview(Review r) throws SQLException {
		
	}
	
	public static void deleteReview(Review r) throws SQLException {
		
	}
	
	/** Get data from hall **/
	
	public static int getHallTotalSeats(Hall h) throws SQLException {
		
	}
	
	public static Time getHallShowtimes(Hall h) throws SQLException {
		
	}
	
	public static int getHallNumSeatsRemaining(Hall h) throws SQLException {
		
	}
	
	public static int getHallShowId(Hall h) throws SQLException {
		
	}
	
	public static void updateHall(Hall h) throws SQLException {
		
	}
	
	public static void createNewHall(Hall h) throws SQLException {
		
	}
	
	public static void deleteHall(Hall h) throws SQLException {
		
	}
	
	/** Get data from seats **/
	
	public static boolean isSeatReserved(Seat s) throws SQLException {
		
	}
	
	public static String getSeatShowId(Seat s) throws SQLException {
		
	}
	
	public static int getSeatHallId(Seat s) throws SQLException {
		
	}
	
	public static void updateSeat(Seat s) throws SQLException {
		
	}
	
	public static void createNewSeat(Seat s) throws SQLException {
		
	}
	
	public static void deleteSeat(Seat s) throws SQLException {
		
	}
}
package com.BGAnimation.persistLayer;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.BGAnimation.objectLayer.*;

public class BGAnimationPersistImpl {
	
	private static final String DB_ERR_MSG = "Data was not returned from " +
		" the database. Please try the query again.";

	/** Get data from the user table **/
	
	public static boolean authenticateUser(String email, String password) 
		throws SQLException, RuntimeException, NoSuchAlgorithmException,
		NoSuchProviderException {
		
		//String encryptedPassword = PasswordHandler.getSecurePassword(password);
		String query = "SELECT password FROM user WHERE user.email=\""+email+"\";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			if (rs.getString("password").equals(password)) {
				return true;
			} else {
				System.out.println("ENTERS!@@#");
				return false;
			}
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static User getUser(String username) throws SQLException, RuntimeException {
		String query = "SELECT userId, firstName, lastName, email, address, " +
		" isBanned, isAdmin FROM user WHERE user.email = \"" + username + "\";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new User(rs.getInt("userId"), rs.getString("firstName"),
				rs.getString("lastName"), rs.getString("email"),
				rs.getString("address"), 
				rs.getInt("isBanned"), rs.getInt("isAdmin"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}

	public static void addUser(User u) 
			throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
		Random rand = new Random();
		int activationCode = rand.nextInt(30000) + 1000;
		String query = "INSERT INTO user" + 
		"(firstName, lastName, email, address, isBanned, isAdmin, "+
		"password, sendPromotions, activationCode, isActivated)" +
		" VALUES('" + u.getFirstName() + "', '" + u.getLastName() + "', '" + 
		u.getEmail() + "', '" + u.getAddress() + "', '"+u.getIsBanned() + "', '"+
		u.getIsAdmin() + "', '"+u.getPassword() + "', '"+ u.getSendPromotions() + "', "
		+activationCode + ", " + 0+ ");";
		DBAccessInterface.create(query);
		EmailHandler.newUserEmail(u.getEmail(), u.getFirstName(), activationCode);
	}
	
	public static void checkUser(String email) 
			throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
		String query = "SELECT email FROM user WHERE email ='"+email+"';";
		ResultSet rs = DBAccessInterface.retrieve(query);
	}
	
	public static boolean verifyUserActivation(User u, int providedCode) 
			throws SQLException, RuntimeException {
		String query = "SELECT activationCode FROM user WHERE user.userId = '" +
			u.getUserID() + "';";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			if (rs.getInt("activationCode") == providedCode) {
				query = "UPDATE user SET isActivated = 1 WHERE user.userId = '" + 
			u.getUserID() +";"; // this should set isActivated to true
				DBAccessInterface.create(query);
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}

	public static void updateUser(User u) throws SQLException {
		String query = "UPDATE user "+
			"SET firstName = '"+u.getFirstName()+"', "+
				"lastName = '"+u.getLastName()+"', "+
				"email = '"+u.getEmail()+"', "+
				"address = '"+u.getAddress()+"', "+
				"isBanned= '"+u.getIsBanned()+"', "+
				"isAdmin = '"+u.getIsAdmin()+
				"' WHERE userId = '"+u.getUserID()+";";
				
		DBAccessInterface.create(query);
	}

	public static void deleteUser(User u) throws SQLException {
		String query = "DELETE FROM user WHERE user.userId = " + u.getUserID() + ";";
		DBAccessInterface.delete(query);
	}

	public static boolean getUserBanStatus(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT isBanned FROM user WHERE user.userId = " +
			u.getUserID() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return rs.getBoolean("isBanned");
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}

	public static void banUser(User u) throws SQLException {
		String query = "UPDATE user SET isBanned = True WHERE user.userId = " + 
			u.getUserID()+";";
		DBAccessInterface.create(query);
	}

	public static void unbanUser(User u) throws SQLException {
		String query = "UPDATE user SET isBanned = False WHERE user.userId = " + 
			u.getUserID()+";";
		DBAccessInterface.create(query);
	}
	
	public static ArrayList<Integer> getAllAdministrators() throws SQLException {
		String query = "SELECT userId FROM user WHERE user.isAdmin = TRUE;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Integer> admins = new ArrayList<Integer>();
		
		while(rs.next()) {
			admins.add(rs.getInt("userId"));
		}
		return admins;
	}
		
	public static ArrayList<User> getAlluserWithoutAdmins() throws SQLException {
		String query = "SELECT userId, firstName, lastName, email, address, "+
		"avatarUrl, isBanned, isAdmin FROM user WHERE isAdmin = 0;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<User> users = new ArrayList<User>();
		
	/*	while(rs.next()) {
			int userId = rs.getInt("userId");
			users.add(new User(userId, rs.getString("firstName"),
				rs.getString("lastName"), rs.getString("email"), 
				rs.getString("address"), rs.getString("avatarUrl"),
				rs.getBoolean("isBanned"), getAllBookingOrdersWithUser(userId),
				getAllCreditCardsWithUser(userId));
		}
	*/	
		return users;
	}
	
	public static void setAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = TRUE where user.userId = " +
			u.getUserID()+";";
		DBAccessInterface.create(query);
	}
	
	public static void removeAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = FALSE where user.userId = " +
			u.getUserID()+";";
		DBAccessInterface.create(query);
	}
	
	/** Get data from creditcard table **/
	
	public static ArrayList<Card> getAllCardForUser(User u) throws SQLException {
		String query = "SELECT creditcard, type, expirationDate, " +
		"billingAddress, securityCode, user_userId FROM user JOIN creditcard " +
		"ON user.userId = creditcard.user_userId WHERE userId = " +u.getUserID() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);	
		ArrayList<Card> cards = new ArrayList<Card>();
		
	/*	while(rs.next()) {
			cards.add(new Card(rs.getString("creditcard"), rs.getString("type"),
				rs.getDate("expirationDate"), rs.getString("billingAddress"),
				rs.getInt("securityCode"), rs.getInt("user_userId")));
		}
	*/	
		return cards;
	}

	public static void addCard(User u, Card c) throws SQLException {
		String query = "INSERT INTO creditcard " + 
		"(creditcard, type, expirationDate, billingAddress, securityCode, userId)" +
		" VALUES ('" + c.getCreditCard() + "', '" + c.getType() + "', '" + 
		c.getExpirationDate() + "', '" + c.getBillingAddress() + "', '" + 
		c.getSecurityCode() + "', '" + u.getUserID() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void removeCard(Card c) throws SQLException {
		String query = "DELETE FROM creditcard WHERE creditcard.creditcard = " 
		+ c.getCreditCard() + ";";
		
		DBAccessInterface.delete(query);
	}
/*	
	public static ArrayList<BookingOrder> getAllBookingOrdersWithUser(int userId) 
		throws SQLException {
		String query = "SELECT bookingId, date, numTickets, promoCode, subtotal, " +
		"tax, total, creditcard, user_userId WHERE user_userId = " + userId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<BookingOrder> bo = new ArrayList<BookingOrder>();
		
		while(rs.next()) {
			bo.add(new BookingOrder(rs.getInt("bookingId"), rs.getDate("date"),
				rs.getInt("numTickets"), rs.getString("promoCode"),
				rs.getFloat("subtotal"), rs.getFloat("tax"), 
				rs.getFloat("total"), rs.getString("creditcard"),
				rs.getInt("user_userId")));
		}
		
		return bo;
	}
	
	public static ArrayList<Card> getAllCreditCardsWithUser(int userId) 
		throws SQLException {
		String query = "SELECT creditcard, type, expirationDate, billingAddress, " +
		"securityCode, user_userId WHERE user_userId = " + userId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Card> c = new ArrayList<Card>();
		
		while(rs.next()) {
			c.add(new Card(rs.getString("creditcard"), rs.getString("type"),
				rs.getString("expirationDate"), rs.getString("billingAddress"),
				rs.getInt("securityCode")));
		}
		
		return c;
	}

	
	
	public static BookingOrder getBookingOrder(int bookingId) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT date, numTickets, subtotal, tax, " +
		"total, creditcard, users_userId, promoCodes_code FROM bookingOrder " + 
		"WHERE bookingOrder.bookingId = " + bookingId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new BookingOrder(bookingId, rs.getDate("date"),
				rs.getInt("numTickets"), rs.getFloat("subtotal"),
				rs.getFloat("tax"),	rs.getFloat("total"), 
				rs.getString("creditcard"), rs.getInt("users_userId"), 
				rs.getString("promoCodes_code"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
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
			"SET date = '"+b.getDate()+"', "+
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
	
	public static void addNewMovie(Movie m) throws SQLException {
		String query = "INSERT INTO movie " + 
		"(title, director, cast, genre, description, bannerUrl, ratings)" +
		" VALUES ('" + m.getTitle() + "', '" + m.getDirector() + 
		"', '" + m.getCast() + "', '" + m.getGenre() + "', '" + 
		m.getDescription() + "', '" + m.getbannerUrl() + "', '" + 
		m.getRatings() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteMovie(Movie m) throws SQLException {
		String query = "DELETE FROM movie WHERE " +
		"movie.movieId = " + m.getMovieId() + ";";
		
		DBAccessInterface.delete(query);
	}
	
	public static Movie getMovie(int movieId) 
		throws SQLException, RuntimeException {
		String query = "SELECT movieId, title, director, cast, genre, " +
		"description, bannerUrl, userRatings, mpaaRating FROM movie " +
		"WHERE movie.movieId = " movieId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Movie(movieId, rs.getString("title"),
				rs.getString("director"), rs.getSeat("cast"),
				rs.getString("genre"), rs.getString("description"),
				rs.getString("bannerUrl"), rs.getFloat("userRatings"),
				rs.getString("mpaaRating"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	

	
	public static Ticket getTicket(int ticketId) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT ticketId, showtime, seat, ticketType, " +
		"purchasePrice, bookingOrder_bookingId, movie_movieId FROM ticket " +
		"WHERE ticket.ticketId = " + ticketId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);

		if (rs.next()) {
			return new Ticket(ticketId, rs.getDate("showtime"),
				rs.getInt("seat"), rs.getString("ticketType"),
				rs.getFloat("purchasePrice"), rs.getInt("bookingOrder_bookingId"),
				rs.getInt("movie_movieId"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static void updateTicket(Ticket t) throws SQLException {
		String query = "UPDATE ticket "+
			"SET showtime = '"+t.getShowtime()+"', "+
				"seat = '"+t.getSeat()+"', "+
				"ticketType = '"+t.getTicketType()+"', "+
				"purchasePrice = '"+t.getPurchasePrice()+"', "+
				"bookingOrder_bookingId = '"+t.getBookingOrder_bookingId()+"', "+
				"movie_movieId = '"+ t.getMovie_movieId()+
				"' WHERE ticketId = '"+ t.getTicketId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewTicket(Ticket t) throws SQLException {
		String query = "INSERT INTO ticket " + 
		"(showtime, seat, ticketType, purchasePrice, bookingOrder_bookingId, " +
		"movie_movieId)" +
		" VALUES ('" + t.getShowtime() + "', '" + t.getSeat() + "', '" + 
		t.getTicketType() + "', '" + t.getPurchasePrice() + "', '" + 
		t.getBookingOrder_bookingId() + "', '" + t.movie_movieId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteTicket(Ticket t) throws SQLException {
		String query = "DELETE FROM ticket WHERE " +
		"ticket.ticketId = " + t.getTicketId()+ ";";
		
		DBAccessInterface.delete(query);
	}
	
	
	
	public static ArrayList<Showtime> getShowtimesForMovie(int movieId) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT showId FROM showtime JOIN movie "+
		"ON showtime.movie_movieId = movie.movieId WHERE movie.movieId = " +
		movieId +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Showtime> st = new ArrayList<Showtime>();
		
		while(rs.next()) {
			st.add(getShowtime(rs.getInt("showId")));
		}
		
		return st;
	}
	
	public static Showtime getShowtime(int showtimeId) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT hallId, time, numSeats, movie_movieId "+
		"FROM showtime WHERE showtime.showId = " + showtimeId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Showtime(showtimeId, rs.getInt("hallId"),
				rs.getDate("time"), rs.getInt("numSeats"),
				rs.getInt("movie_movieId"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static void updateShowtime(Showtime s) throws SQLException {
		String query = "UPDATE showtime "+
			"SET hallId = '"+s.getHallId()+"', "+
				"time = '"+s.getTime()+"', "+
				"numSeats = '"+s.getNumSeats()+"', "+
				"movie_movieId = '"+ s.getMovie_movieId()+
				"' WHERE showId = '"+ s.getShowId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewShowtime(Showtime s) throws SQLException {
		String query = "INSERT INTO showtime " + 
		"(hallId, time, numSeats, movie_movieId) " +
		" VALUES ('" + s.getHallId() + "', '" + s.getTime() + "', '" + 
		s.getNumSeats() + "', '" + s.getMovieId_movieId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteShowtime(Showtime s) throws SQLException {
		String query = "DELETE FROM showtime WHERE " +
		"showtime.getshowId = " + s.getShowId()+ ";";
		
		DBAccessInterface.delete(query);
	}
	
	
	
	public static ArrayList<MovieReview> getReviewsForMovie(Movie m) throws SQLException {
		String query = "SELECT reviewId, userId, review, movie_movieId FROM " +
		"movieReview WHERE movieReview.movie_movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<MovieReview> r = new ArrayList<Review>();
		
		while (rs.next()) {
			r.add(new MovieReview(rs.getInt("reviewId"), rs.getInt("userId"),
				rs.getString("review")));
		}
		
		return r;
	}
	
	public static void updateReview(MovieReview r) throws SQLException {
		String query = "UPDATE movieReview "+
			"SET userId = '"+r.getUserId()+"', "+
				"review = '"+r.getReview()+"', "+
				"movie_movieId = '"+ s.getMovie_movieId()+
				"' WHERE reviewId = '"+ r.getReviewId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewReview(MovieReview r) throws SQLException {
		String query = "INSERT INTO movieReview " + 
		"(userId, review, movie_movieId) " +
		" VALUES ('" + r.getUserId() + "', '" + r.getReview() + "', '" + 
		r.getMovie_movieId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteReview(Review r) throws SQLException {
		String query = "DELETE FROM movieReview WHERE " +
		"movieReview.reviewId = " + r.getReviewId()+ ";";
		
		DBAccessInterface.delete(query);
	}
	
	
	
	public static Hall getHall(int hallId) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT totalSeats, showtimes, numSeatsRemaining, " +
		"showtime_showId FROM hall WHERE hall.hallId = " + hallId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Hall(hallId, rs.getInt("totalSeats"),
				rs.getDate("showtimes"), rs.getInt("numSeatsRemaining"),
				rs.getInt("showtime_showId"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static void updateHall(Hall h) throws SQLException {
		String query = "UPDATE hall "+
			"SET totalSeats = '"+h.getTotalSeats()+"', "+
				"showtimes = '"+h.getShowtimes()+"', "+
				"numSeatsRemaining = '"+ h.getNumSeatsRemaining()+
				"' WHERE hallId = '"+ h.hallId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewHall(Hall h) throws SQLException {
		String query = "INSERT INTO hall " + 
		"(totalSeats, showtimes, numSeatsRemaining, showtime_showId) " +
		" VALUES ('" + h.getTotalSeats() + "', '" + h.getShowtimes() + "', '" + 
		h.getNumSeatsRemaining() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteHall(Hall h) throws SQLException {
		String query = "DELETE FROM hall WHERE " +
		"hall.hallId = " + h.hallId()+ ";";
		
		DBAccessInterface.delete(query);
	}
	
	
	
	public static Seat getSeat(int seatId) throws SQLException, RuntimeException {
		String query = "SELECT isReserved, showId, hall_hallId FROM seat " +
		"WHERE seat.seatId = " + seatId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Seat(seatId, rs.getBoolean("isReserved"),
				rs.getInt("showId"), rs.getInt("hall_hallId"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static void updateSeat(Seat s) throws SQLException {
		String query = "UPDATE hall "+
			"SET isReserved = '"+s.getIsReserved()+"', "+
				"showId = '"+s.getShowId()+"', "+
				"hall_hallId = '"+ s.getHallId()+
				"' WHERE seatId = '"+ s.getSeatId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewSeat(Seat s) throws SQLException {
		String query = "INSERT INTO seat " + 
		"(isReserved, showId, hall_hallId) " +
		" VALUES ('" + s.getIsReserved() + "', '" + s.getShowId() + "', '" + 
		s.getHall_hallId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteSeat(Seat s) throws SQLException {
		String query = "DELETE FROM seat WHERE " +
		"seat.seatId = " + s.getSeatId()+ ";";
		
		DBAccessInterface.delete(query);
	}
*/
}
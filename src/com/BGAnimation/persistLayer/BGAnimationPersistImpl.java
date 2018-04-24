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
		String query = "SELECT password, salt FROM user WHERE user.email=\""+email+"\";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			String encryptedPassword = PasswordHandler.getUserEncryptedPassword(password, rs.getString("salt"));
			if (rs.getString("password").equals(encryptedPassword)) {
				return true;
			} else {
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
		String encryptedPassword[] = PasswordHandler.encryptPassword(u.getPassword());
		String query = "INSERT INTO user" + 
		"(firstName, lastName, email, address, isBanned, isAdmin, "+
		"password, sendPromotions, activationCode, isActivated, salt)" +
		" VALUES('" + u.getFirstName() + "', '" + u.getLastName() + "', '" + 
		u.getEmail() + "', '" + u.getAddress() + "', '"+u.getIsBanned() + "', '"+
		u.getIsAdmin() + "', '"+ encryptedPassword[0] + "', '"+ u.getSendPromotions() + "', "
		+activationCode + ", " + 0 + ", '" + encryptedPassword[1] + "');";
		DBAccessInterface.create(query);
		EmailHandler.newUserEmail(u.getEmail(), u.getFirstName(), activationCode);
	}
	
	public static boolean checkUser(String email) 
			throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
		String query = "SELECT email FROM user WHERE email ='"+email+"';";
		ResultSet rs = DBAccessInterface.retrieve(query);
		if (!rs.next() ) {
		    return false;
		} 
		else{
			return true;
		}
	}
	
	public static boolean isActivated(User u) throws SQLException, RuntimeException {
		String query = "SELECT isActivated FROM user WHERE email = '" + 
				u.getEmail() + "';";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			if (rs.getInt("isActivated") == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static boolean checkIfUserExists(String email) 
			throws SQLException {
		String query = "SELECT email FROM user WHERE user.email = '" +
				email + "';";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) { // User exists
			return true;
		} else { // User doesn't exist
			return false;
		}
	}
	
	public static boolean verifyUserActivation(User u, int providedCode) 
			throws SQLException, RuntimeException {
		String query = "SELECT activationCode FROM user WHERE email = '" +
			u.getEmail() + "';";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			if (rs.getInt("activationCode") == providedCode) {
				query = "UPDATE user SET isActivated = 1 WHERE email = '" + 
						u.getEmail() +"';";
				DBAccessInterface.create(query);
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}

	public static void updateUser(User u) 
			throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
		String encryptedPasswordData[] = PasswordHandler.encryptPassword(u.getPassword());
		String query = "UPDATE user "+
			"SET firstName = '"+u.getFirstName()+"', "+
				"lastName = '"+u.getLastName()+"', "+
				"email = '"+u.getEmail()+"', "+
				"address = '"+u.getAddress()+"', "+
				"isBanned= '"+u.getIsBanned()+"', "+
				"isAdmin = '"+u.getIsAdmin()+"', "+
				"password = '" +u.getPassword()+"', "+
				"sendPromotions = '"+u.getSendPromotions()+"', "+
				"isActivated = '"+u.getIsActivated()+"', "+
				"password = '" + encryptedPasswordData[0] + "', " +
				"salt = '" + encryptedPasswordData[1] +
				"' WHERE email = "+u.getEmail()+";";
				
		DBAccessInterface.create(query);
	}

	public static void deleteUser(User u) throws SQLException {
		String query = "DELETE FROM user WHERE email = " + u.getEmail() + ";";
		DBAccessInterface.delete(query);
	}

	public static boolean getUserBanStatus(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT isBanned FROM user WHERE email = " +
			u.getEmail() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return rs.getBoolean("isBanned");
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}

	public static void banUser(User u) throws SQLException {
		String query = "UPDATE user SET isBanned = True WHERE email = " + 
			u.getEmail()+";";
		DBAccessInterface.create(query);
	}

	public static void unbanUser(User u) throws SQLException {
		String query = "UPDATE user SET isBanned = False WHERE email = " + 
			u.getEmail()+";";
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
		
		while(rs.next()) {
			users.add(new User(rs.getInt("userId"),
						rs.getString("firstName"),
						rs.getString("lastName"), 
						rs.getString("email"), 
						rs.getString("address"),
						rs.getInt("isBanned"),
						rs.getInt("isAdmin")));
		}	
		return users;
	}
	
	public static void setAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = TRUE where email = " +
			u.getEmail()+";";
		DBAccessInterface.create(query);
	}
	
	public static void removeAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = FALSE where email = " +
			u.getEmail()+";";
		DBAccessInterface.create(query);
	}
	
	/** Get data from creditcard table **/
	
	// @Stephen
	public static ArrayList<Card> getAllCreditCardsWithUser(User u) throws SQLException {
		String query = "SELECT creditcard, type, expirationDate, " +
		"billingAddress, securityCode, user_userId FROM user JOIN creditcard " +
		"ON user.userId = creditcard.user_userId WHERE userId = " +u.getUserID() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);	
		ArrayList<Card> cards = new ArrayList<Card>();
		
		while(rs.next()) {
			cards.add(new Card(rs.getString("creditcard"), rs.getString("type"),
				rs.getDate("expirationDate"), rs.getString("billingAddress"),
				rs.getInt("securityCode")));
		}	
		return cards;
	}
	
	/*
	public static ArrayList<Card> getAllCreditCardsWithUser(User u) 
			throws SQLException {
			User u = new User(userId);
			return getAllCreditCardsWithUser(u);
		} */

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

	// @Stephen
	public static ArrayList<BookingOrder> getAllBookingOrdersWithUser(User u) 
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
	
	public static ArrayList<BookingOrder> getAllBookingOrders() throws SQLException {
		String query = "SELECT bookingId, date, numTickets, promoCode, subtotal, " + 
				"tax, total, creditcard, user_userId;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<BookingOrder> orders = new ArrayList<BookingOrder>();
		
		while(rs.next()) {
			orders.add(new BookingOrder(rs.getInt("bookingId"), rs.getDate("date"),
					rs.getInt("numTickets"), rs.getString("promoCode"),
					rs.getFloat("subtotal"), rs.getFloat("tax"), 
					rs.getFloat("total"), rs.getString("creditcard"),
					rs.getInt("user_userId")));
		}
		
		return orders;
	}
	
	// @Stephen
	public static BookingOrder getBookingOrder(int bookingId) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT date, numTickets, subtotal, tax, " +
		"total, creditcard, users_userId, promoCodes_code FROM bookingOrder " + 
		"WHERE bookingOrder.bookingId = " + bookingId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new BookingOrder(bookingId, rs.getDate("date"),
				rs.getInt("numTickets"), rs.getString("promoCodes_code"),
				rs.getFloat("subtotal"), rs.getFloat("tax"),rs.getFloat("total"), 
				rs.getString("creditcard"), rs.getInt("users_userId"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static void createNewBookingOrder(User u, Card c, BookingOrder b) throws SQLException {
		String query = "INSERT INTO bookingOrder " + 
		"(date, numTickets, promoCode, subtotal, tax, total, creditcard, userId)" +
		" VALUES ('" + b.getBookingDate() + "', '" + b.getNumTicketsInOrder() 
		+ "', '" + b.getPromoCode() + "', '" + b.getSubtotal() + "', '" + 
		b.getTax() + "', '" + b.getSubtotal() + "', '" + c.getCreditCard() 
		+ "', '" + u.getUserID() + "');";
		
		DBAccessInterface.create(query);
	}
	
	// @Stephen
	public static void deleteBookingOrder(BookingOrder b) throws SQLException {
		String query = "DELETE FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		DBAccessInterface.delete(query);
	}
	
	// @Stephen
	public static void updateBookingOrder(User u, Card c, BookingOrder b) 
		throws SQLException {
		String query = "UPDATE bookingOrder "+
			"SET date = '"+b.getBookingDate()+"', "+
				"numTickets = '"+b.getNumTicketsInOrder()+"', "+
				"promoCode = '"+b.getPromoCode()+"', "+
				"subtoatl = '"+b.getSubtotal()+"', "+
				"tax = '"+b.getTax()+"', "+
				"total = '"+b.getSubtotal()+"', "+
				"creditcard = '"+c.getCreditCard()+"', "+
				"userId = '" + u.getUserID()+
				"' WHERE bookingId = '"+ b.getBookingId()+";";
				
		DBAccessInterface.create(query);
	}

	public static void updateMovie(Movie m) throws SQLException {
		String query = "UPDATE movie "+
			"SET title = '"+m.getTitle()+"', "+
				"director = '"+m.getDirector() +"', "+
				"cast = '"+m.getCast()+"', "+
				"genre = '"+m.getGenre()+"', "+
				"description = '"+m.getDescription()+"', "+
				"bannerUrl = '"+m.getbannerURL()+"', "+
				"ratings = '" + m.getRatings()+
				"' WHERE title = "+ m.getTitle()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void addNewMovie(Movie m) throws SQLException {
		String query = "INSERT INTO movie " + 
		"(title, director, cast, genre, description, bannerUrl, ratings)" +
		" VALUES ('" + m.getTitle() + "', '" + m.getDirector() + 
		"', '" + m.getCast() + "', '" + m.getGenre() + "', '" + 
		m.getDescription() + "', '" + m.getbannerURL() + "', '" + 
		m.getRatings() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteMovie(Movie m) throws SQLException {
		String query = "DELETE FROM movie WHERE " +
		"title = " + m.getTitle() + ";";
		
		DBAccessInterface.delete(query);
	}
	
	public static Movie getMovie(String movieName) 
		throws SQLException, RuntimeException {
		String query = "SELECT movieId, title, director, cast, genre, " +
		"description, bannerUrl, userRatings, mpaaRating FROM movie " +
		"WHERE title = " + movieName + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Movie(rs.getInt("movieId"), movieName,
				rs.getString("director"), rs.getString("cast"),
				rs.getString("genre"), rs.getString("description"),
				rs.getString("bannerUrl"), rs.getFloat("userRatings"),
				rs.getString("mpaaRating"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static ArrayList<Movie> getMoviesByGenre(String genre)throws SQLException, RuntimeException {
		String query = "SELECT movieId, title, director, cast, genre, " +
		"description, bannerUrl, userRatings, mpaaRating FROM movie " +
		"WHERE genre = " + genre + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		while (rs.next()) {
			movieList.add(new Movie(rs.getInt("movieId"), rs.getString("title"),
				rs.getString("director"), rs.getString("cast"),
				rs.getString("genre"), rs.getString("description"),
				rs.getString("bannerUrl"), rs.getFloat("userRatings"),
				rs.getString("mpaaRating")));
		} 
		return movieList;
	}
/*	
	public static ArrayList<Movie> getAllMovies() throws SQLException {
		String query = "SELECT movieId FROM movie WHERE TRUE;"; // for this query, give me all the movie IDs
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		while(rs.next()) {
			movies.add(getMovie(rs.getInt("movieId")));
		}
		
		return movies;
	}
*/	
	// @Stephen
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
	
	public static ArrayList<Ticket> getAllTickets() throws SQLException {
		String query = "SELECT ticketId FROM ticket WHERE TRUE;"; // just return all the ticket IDs
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		while(rs.next()) {
			tickets.add(getTicket(rs.getInt("ticketId")));
		}
		
		return tickets;
	}
	
	// @Stephen
	public static ArrayList<Ticket> getAllTicketsInBookingOrder(BookingOrder b) throws SQLException {
		String query = "SELECT ticketId FROM ticket JOIN bookingOrder " +
				"ON bookingOrder.bookingId = ticket.bookingOrder_bookingId " +
				"WHERE bookingOrder.bookingId = '" + b.getBookingId() + "';"; // just return all the ticket IDs
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		while(rs.next()) {
			tickets.add(getTicket(rs.getInt("ticketId")));
		}
		
		return tickets;
	}
	
	// @Stephen
	public static void updateTicket(Ticket t) throws SQLException {
		String query = "UPDATE ticket "+
			"SET showtime = '"+t.getShowTime()+"', "+
				"seat = '"+t.getSeatLocation()+"', "+
				"ticketType = '"+t.getTicketType()+"', "+
				"purchasePrice = '"+t.getPurchasePrice()+"', "+
				"bookingOrder_bookingId = '"+t.getBookingOrderId()+"', "+
				"movie_movieId = '"+ t.getMovieId()+
				"' WHERE ticketId = '"+ t.getTicketId()+";";
				
		DBAccessInterface.create(query);
	}
	
	// @Stephen - since we're no longer using ids, get rid of bookingOrder_bookingId? idk
	public static void createNewTicket(Ticket t) throws SQLException {
		String query = "INSERT INTO ticket " + 
		"(showtime, seat, ticketType, purchasePrice, bookingOrder_bookingId, " +
		"movie_movieId)" +
		" VALUES ('" + t.getShowTime() + "', '" + t.getSeatLocation() + "', '" + 
		t.getTicketType() + "', '" + t.getPurchasePrice() + "', '" + 
		t.getBookingOrderId() + "', '" + t.getMovieId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	// @Stephen
	public static void deleteTicket(Ticket t) throws SQLException {
		String query = "DELETE FROM ticket WHERE " +
		"ticket.ticketId = " + t.getTicketId()+ ";";
		
		DBAccessInterface.delete(query);
	}

	public static ArrayList<Showtime> getShowtimesForMovie(Movie m) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT showId FROM showtime JOIN movie "+
		"ON showtime.movie_movieId = movie.movieId WHERE movie.movieTitle = " +
		m.getTitle() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Showtime> st = new ArrayList<Showtime>();
		
		while(rs.next()) {
			st.add(getShowtime(rs.getInt("showId")));
		}
		
		return st;
	}
	
	// @Stephen
	public static Showtime getShowtime(int showtimeId) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT hallId, time, numSeats, numSeatsRemaining, movie_movieId "+
		"FROM showtime WHERE showtime.showId = " + showtimeId + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Showtime(rs.getInt("hallId"),
				rs.getDate("time"), rs.getInt("numSeats"), rs.getInt("numSeatsRemaining"),
				rs.getInt("movie_movieId"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	// @Stephen
	public static void updateShowtime(Showtime s) throws SQLException {
		String query = "UPDATE showtime "+
			"SET hallId = '"+s.getHallId()+"', "+
				"time = '"+s.getTime()+"', "+
				"numSeats = '"+s.getNumSeats()+"', "+
				"movie_movieId = '"+ s.getMovieId()+
				"' WHERE showId = '"+ s.getShowId()+";";
				
		DBAccessInterface.create(query);
	}
	
	// @Stephen - since we're not using Ids anymore
	public static void createNewShowtime(Showtime s) throws SQLException {
		String query = "INSERT INTO showtime " + 
		"(hallId, time, numSeats, movie_movieId) " +
		" VALUES ('" + s.getHallId() + "', '" + s.getTime() + "', '" + 
		s.getNumSeats() + "', '" + s.getMovieId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	// @Stephen
	public static void deleteShowtime(Showtime s) throws SQLException {
		String query = "DELETE FROM showtime WHERE " +
		"showtime.getshowId = " + s.getShowId()+ ";";
		
		DBAccessInterface.delete(query);
	}
	public static ArrayList<MovieReview> getReviewsForMovie(Movie m) throws SQLException {
		String query = "SELECT reviewId, email, review, movie_movieId FROM " +
		"movieReview WHERE movieReview.title = " + m.getTitle() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<MovieReview> r = new ArrayList<MovieReview>();
		
		while (rs.next()) {
			r.add(new MovieReview(rs.getInt("reviewId"), rs.getInt("email"),
				rs.getString("review"), rs.getInt("movie_movieId")));
		}
		
		return r;
	}
	
	// @Stephen
	public static void updateReview(MovieReview r) throws SQLException {
		String query = "UPDATE movieReview "+
			"SET email = '"+r.getEmail()+"', "+
				"review = '"+r.getReview()+"', "+
				"movie_movieId = '"+ r.getMovieId()+
				"' WHERE reviewId = '"+ r.getReviewId()+";";
				
		DBAccessInterface.create(query);
	}
	
	// This is where I stopped for determining what needs IDs and what doesn't
	
	public static void createNewReview(MovieReview r) throws SQLException {
		String query = "INSERT INTO movieReview " + 
		"(email, review, movie_movieId) " +
		" VALUES ('" + r.getEmail() + "', '" + r.getReview() + "', '" + 
		r.getMovieId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteReview(MovieReview r) throws SQLException {
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
	
	public static ArrayList<Hall> getAllHalls() throws SQLException {
		String query = "SELECT hallId FROM hall WHERE TRUE;"; // return all the hallIds
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Hall> halls = new ArrayList<Hall>();
		
		while(rs.next()) {
			halls.add(getHall(rs.getInt("hallId")));
		}
		
		 return halls;
	}
	
	public static void updateHall(Hall h) throws SQLException {
		String query = "UPDATE hall "+
			"SET totalSeats = '"+h.getTotalSeats()+"', "+
				"showtimes = '"+h.getShowtime()+"', "+
				"numSeatsRemaining = '"+ h.getNumSeatsRemaining()+
				"' WHERE hallId = '"+ h.getHallId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewHall(Hall h) throws SQLException {
		String query = "INSERT INTO hall " + 
		"(totalSeats, showtimes, numSeatsRemaining, showtime_showId) " +
		" VALUES ('" + h.getTotalSeats() + "', '" + h.getShowtime() + "', '" + 
		h.getNumSeatsRemaining() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteHall(Hall h) throws SQLException {
		String query = "DELETE FROM hall WHERE " +
		"hall.hallId = " + h.getHallId()+ ";";
		
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
			"SET isReserved = '"+s.getSeatReservation()+"', "+
				"showId = '"+s.getShowId()+"', "+
				"hall_hallId = '"+ s.getHallId()+
				"' WHERE seatId = '"+ s.getSeatId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewSeat(Seat s) throws SQLException {
		String query = "INSERT INTO seat " + 
		"(isReserved, showId, hall_hallId) " +
		" VALUES ('" + s.getSeatReservation() + "', '" + s.getShowId() + "', '" + 
		s.getHallId() + "');";
		
		DBAccessInterface.create(query);
	}
	
	public static void deleteSeat(Seat s) throws SQLException {
		String query = "DELETE FROM seat WHERE " +
		"seat.seatId = " + s.getSeatId()+ ";";
		
		DBAccessInterface.delete(query);
	} 
	
	/* Get all data from promocodes table */
	
	public static void addNewPromotion(Promotion p) throws SQLException {
		String query = "INSERT INTO promocodes (code, percentOff) VALUES('"+
				p.getPromocode() + "', " + p.getPercentOff() +");";
		DBAccessInterface.create(query);
	}
	
	public static void updatePromotion(Promotion p) throws SQLException {
		String query = "UPDATE promocodes SET code = " + p.getPromocode() + 
				", percentOff = " + p.getPercentOff() + " WHERE " +
				"promocodes.code = '" + p.getPromocode() + "';";
		DBAccessInterface.create(query);
	}
	
	public static Promotion getPromotion(String promocode) 
			throws SQLException, RuntimeException {
		String query = "SELECT code, percentOff FROM promocodes WHERE "+
			"promocodes.code = '" + promocode + "';";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		if (rs.next()) {
			return new Promotion(rs.getString("code"), rs.getInt("percentOff"));
		} else {
			throw new RuntimeException(DB_ERR_MSG);
		}
	}
	
	public static void deletePromo(String promoCode)throws SQLException {
		String query = "DELETE FROM promocodes WHERE promocodes.code = '" +
				promoCode + "';";
		DBAccessInterface.create(query);
	}
	
	public static ArrayList<Promotion> getAllPromotions() throws SQLException {
		String query = "SELECT code, percentOff FROM promocodes WHERE TRUE;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<Promotion> promos = new ArrayList<Promotion>();
		
		while(rs.next()) {
			promos.add(new Promotion(rs.getString("code"), rs.getInt("percentOff")));
		}
		
		return promos;
	}
}

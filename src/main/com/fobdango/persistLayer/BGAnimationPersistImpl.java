import java.sql.User;
import java.sql.SQLException;
import Date;

public class BGAnimationsPersistImpl {
	
	private static final String DB_ERR_MSG = "Data was not returned from " +
		" the database. Please try the query again.";

	/** Get data from the user table **/
	
	// @Stephen
	public static User getUser(int userId) throws SQLException, RuntimeException {
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		User u = null;
		
		while(rs.next()) {
			u = new User(rs.getInt("userId"), rs.getString("firstName"),
				rs.getString("lastName"), rs.getString("email"),
				rs.getString("address"), rs.getString("avatarUrl"),
				rs.getBoolean("isBanned"), rs.getBoolean("isAdmin"));
		}
		
		if (u == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return u;
		}
	}

	public static void addUser(User u) throws SQLException {
		String query = "INSERT INTO user " + 
		"(firstName, lastName, email, address, avatarUrl)" +
		" VALUES ('" + u.getFirstName() + "', '" + u.getLastName() + "', '" + 
		u.getEmail() + "', '" + u.getAddress() + "', '" + u.getAvatarUrl() +"');";
		
		DBAccessInterface.create(query);
	}

	public static void updateUser(User u) throws SQLException {
		String query = "UPDATE user "+
			"SET firstName = '"+u.getFirstName()+"', "+
				"lastName = '"+u.getLastName()+"', "+
				"email = '"+u.getEmail()+"', "+
				"address = '"+u.getAddress()+"', "+
				"avatarUrl = '"+u.getAvatarUrl()+"',"+
				"isBanned= '"+u.getIsBanned()+"', "+
				"isAdmin = '"+u.getIsAdmin()+
				"' WHERE userId = '"+u.getUserId()+";";
				
		DBAccessInterface.create(query);
	}

	public static void deleteUser(User u) throws SQLException {
		String query = "DELETE FROM user WHERE user.userId = " + u.getUserId() + ";";
		DBAccessInterface.delete(query);
	}

	public static boolean getUserBanStatus(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT isBanned FROM user WHERE user.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		boolean result = null;
		
		while(rs.next()) {
			result = rs.getBoolean("isBanned");
		}
		
		if (result == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}

	public static void banUser(User u) throws SQLException {
		String query = "UPDATE user SET isBanned = True WHERE user.userId = " + 
			u.getUserId()+";";
		DBAccessInterface.create(query);
	}

	public static void unbanUser(User u) throws SQLException {
		String query = "UPDATE user SET isBanned = False WHERE user.userId = " + 
			u.getUserId()+";";
		DBAccessInterface.create(query);
	}
	
	public static ArrayList<int> getAllAdministrators() throws SQLException {
		String query = "SELECT userId FROM user WHERE user.isAdmin = TRUE;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<int> admins = new ArrayList<int>();
		
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
			int userId = rs.getInt("userId");
			users.add(new User(userId, rs.getString("firstName"),
				rs.getString("lastName"), rs.getString("email"), 
				rs.getString("address"), rs.getString("avatarUrl"),
				rs.getBoolean("isBanned"), getAllBookingOrdersWithUser(userId),
				getAllCreditCardsWithUser(userId));
		}
		
		return users;
	}
	
	public static void setAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = TRUE where user.userId = " +
			u.getUserId()+";";
		DBAccessInterface.create(query);
	}
	
	public static void removeAdministrator(User u) throws SQLException {
		String query = "UPDATE isAdmin SET isAdmin = FALSE where user.userId = " +
			u.getUserId()+";";
		DbAccessInterface.create(query);
	}
	
	/** Get data from creditcard table **/
	
	// @Stephen
	public static ArrayList<Card> getAllCardForUser(User u) throws SQLException {
		String query = "";"
		ResultSet rs = DBAccessInterface.retrieve(query);	
		ArrayList<Card> cards = new ArrayList<Card>();
		
		while(rs.next()) {
			cards.add(new Card(rs.getString("creditcard"), rs.getString("type"),
				rs.getDate("expirationDate"), rs.getString("billingAddress"),
				rs.getInt("securityCode"), rs.getInt("users_userId")));
		}
		
		return cards;
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

	/** Get data from bookingOrder **/

	// @Stephen
	public static BookingOrder getBookingOrder(int bookingId) 
		throws SQLException, RuntimeException {
		
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		BookingOrder bo = null;
		
		while(rs.next()) {
			bo = new BookingOrder(bookingId, rs.getDate("date"),
				rs.getInt("numTickets"), rs.getFloat("subtotal"),
				rs.getFloat("tax"),	rs.getFloat("total"), 
				rs.getString("creditcard"), rs.getInt("users_userId"), 
				rs.getString("promoCodes_code"));
		}
		if (bo == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return bo;
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
	
	/** Get data from movie **/
	
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
	
	// @Stephen
	public static Movie getMovie(int movieId) 
		throws SQLException, RuntimeException {
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Movie m = null;
		
		while(rs.next()) {
			m = new Movie(movieId, rs.getString("title"),
				rs.getString("director"), rs.getSeat("cast"),
				rs.getString("genre"), rs.getString("description"),
				rs.getString("bannerUrl"), rs.getFloat("userRatings"),
				rs.getString("mpaaRating"));
		}
		
		if (m == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return m;
		}
		
	}
	
	/** Gets data from ticket **/
	
	// @Stephen
	public static Ticket getTicket(int ticketId) 
		throws SQLException, RuntimeException {
		
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Ticket t = null;
		
		while(rs.next()) {
			t = new Ticket(ticketId, rs.getDate("showtime"),
				rs.getInt("seat"), rs.getString("ticketType"),
				rs.getFloat("purchasePrice"), rs.getInt("bookingOrder_bookingId"),
				rs.getInt("movie_movieId"));
		}
		
		if (t == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return t;
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
	
	/** Gets data from showtime **/
	
	// @Stephen
	public static Showtime getShowtime(int showtimeId) 
		throws SQLException, RuntimeException {
		
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Showtime s = null;
		
		while(rs.next()) {
			s = new Showtime(showtimeId, rs.getInt("hallId"),
				rs.getDate("time"), rs.getInt("numSeats"),
				rs.getInt("movie_movieId"));
		}
		
		if (s == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return s;
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
	
	/** Gets data from movieReview **/
	
	public static ArrayList<MovieReview> getReviewsForMovie(Movie m) throws SQLException {
		String query = "SELECT reviewId, userId, review, movie_movieId FROM " +
		"movieReview WHERE movieReview.movie_movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		ArrayList<MovieReview> r = new ArrayList<Review>();
		
		while (rs.next()) {
			r.add(new MovieReview(rs.getInt("reviewId"), rs.getInt("userId"),
				rs.getString("review")))
		}
		
		return r;
	}
	
	public static void updateReview(Review r) throws SQLException {
		String query = "UPDATE movieReview "+
			"SET userId = '"+r.getUserId()+"', "+
				"review = '"+r.getReview()+"', "+
				"movie_movieId = '"+ s.getMovie_movieId()+
				"' WHERE reviewId = '"+ r.getReviewId()+";";
				
		DBAccessInterface.create(query);
	}
	
	public static void createNewReview(Review r) throws SQLException {
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
	
	/** Get data from hall **/
	
	// @Stephen
	public static Hall getHall(int hallId) 
		throws SQLException, RuntimeException {
		
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Hall h = null;
		
		while(rs.next()) {
			h = new Hall(hallId, rs.getInt("totalSeats"),
				rs.getDate("showtimes"), rs.getInt("numSeatsRemaining"),
				rs.getInt("showtime_showId"));
		}
		
		if (h == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return h;
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
	
	/** Get data from seats **/
	
	// @Stephen
	public static Seat getSeat(int seatId) throws SQLException, RuntimeException {
		String query = "";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Seat s = null;
		
		while(rs.next()) {
			s = new Seat(seatId, rs.getBoolean("isReserved"),
				rs.getInt("showId"), rs.getInt("hall_hallId"));
		}
		
		if (s == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return s;
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
}
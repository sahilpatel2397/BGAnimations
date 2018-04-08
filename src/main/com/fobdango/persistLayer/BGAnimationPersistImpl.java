import java.sql.User;
import java.sql.SQLException;
import Date;

public class BGAnimationsPersistImpl {
	
	private static final String DB_ERR_MSG = "Data was not returned from " +
		" the database. Please try the query again.";

	/** Get data from the user table **/

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
				"avatarUrl = '"+u.getAvatarUrl()+
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

	public static String getUserFirstName(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT firstName FROM user WHERE user.userId = " + 
			u.getUserId()+";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("firstName");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}

	public static String getUserLastName(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT lastName FROM user WHERE user.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("lastName");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}

	public static String getUserEmail(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT email FROM user WHERE user.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("email");
		}
		
		if (result == null) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}

	public static String getUserAddress(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT address FROM user WHERE user.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("address");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}

	public static String getUserAvatarUrl(User u) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT avatarUrl FROM user WHERE user.userId = " +
			u.getUserId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("avatarUrl");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
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
	
	public static ArrayList<String> getAllCardNums(User u) throws SQLException {
		String query = "SELECT creditcard FROM creditcard, user WHERE " +
			"creditcard.user_userId = " + u.getUserId() +";"; 
		
		ResultSet rs = DBAccessInterface.retrieve(query);	
		ArrayList<String> cards = new ArrayList<String>();
		
		while(rs.next()) {
			cards.add(rs.getString("creditcard"));
		}
		
		return cards;
	}
	
	public static String getCardType(User u, Card c) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT type FROM creditcard, user WHERE " +
			"creditcard.user_userId = " + u.getUserId() + 
			" AND creditcard.creditcard = " + c.getCreditCardNum()+";";
		
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("type");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static Date getExpirationDate(Card c) throws SQLException {
		String query = "SELECT expirationDate FROM creditcard WHERE " + 
			"creditcard.creditcard = " + c.getCreditCardNum() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Date result = new Date(0);
		
		while(rs.next()) {
			result = rs.getDate("expirationDate");
		}
		
		return result;
	}
	
	public static String getBillingAddress(Card c) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT billingAddress FROM creditcard WHERE " +
		"creditcard.creditcard = " + c.getCreditCardNum() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("billingAddress");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static String int getSecurityCode(Card c) 
		throws SQLException, RuntimeException {
			
		String query = "SELET securityCode FROM creditcard WHERE " + 
		"creditcard.creditcard = " + c.getCreditCardNum() +";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("securityCode");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
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
	
	protected static ArrayList<BookingOrder> getAllBookingOrdersWithUser(int userId) 
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
	
	protected static ArrayList<Card> getAllCreditCardsWithUser(int userId) 
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

	/** Get data from bookingOrder **/

	public static Date getBookingDate(BookingOrder b) 
		throws SQLException {
		String query = "SELECT date FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		Date result = new Date(0);
		
		while(rs.next()) {
			result = rs.getDate("date");
		}
		
		return result;
	}
	
	public static int getNumTicketsInOrder(BookingOrder b) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT numTickets FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		int result = -1;
		
		while(rs.next()) {
			result = rs.getInt("numTickets");
		}
		
		if (result == -1) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static String getPromoCode(BookingOrder b) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT promoCode FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			result = rs.getString("promoCode");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static float getSubtotal(BookingOrder b) 
		throws SQLException, RuntimeException {
		
		String query = "SELECT subtotal FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		float result = -1.0;
		
		while(rs.next()) {
			result = rs.getFloat("subtotal");
		}
		
		if (result == -1.0) {
			throw new RuntimeException("Data was not returned.");
		} else {
			return result;
		}
	}
	
	public static float getTax(BookingOrder b) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT tax FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		float result = -1.0;
		
		while(rs.next()) {
			return rs.getFloat("tax");
		}
		
		if (result == -1.0) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static float getTotal(BookingOrder b)
		throws SQLException, RuntimeException {
			
		String query = "SELECT total FROM bookingOrder WHERE " +
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		float result = -1.0;
		
		while(rs.next()) {
			return rs.getFloat("total");
		}
		
		if (result == -1.0) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static int getCreditCard(BookingOrder b)
		throws SQLException, RuntimeException {
			
		String query = "SELECT creditcard FROM bookingOrder WHERE " + 
		"bookingOrder.bookingId = " + b.getBookingId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		int result = -1;
		
		while(rs.next()) {
			return rs.getInt("creditcard");
		}
		
		if (result == -1) {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
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
	
	public static String getMovieTitle(Movie m) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT title FROM movie WHERE " +
		"movie.movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			return rs.getString("title");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
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
	
	public static String getMovieDirector(Movie m) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT director FROM movie WHERE " + 
		"movie.movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			return rs.getString("title");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static String getMovieCast(Movie m) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT cast FROM movie WHERE + "
		"movie.movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			return rs.getString("title");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static String getMovieGenre(Movie m) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT genre FROM movie WHERE + "
		"movie.movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		String result = "";
		
		while(rs.next()) {
			return rs.getString("title");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static String getMovieDescription(Movie m) 
		throws SQLException, RuntimeException {
			
		String query = "SELECT description FROM movie WHERE + "
		"movie.movieId = " + m.getMovieId() + ";";
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("title");
		}
		
		if (result == "") {
			throw new RuntimeException(DB_ERR_MSG);
		} else {
			return result;	
		}
	}
	
	public static String getMovieBannerUrl(Movie m) throws SQLException {
		String query = "SELECT bannerUrl FROM movie WHERE + "
		"movie.movieId = " + m.getMovieId() + ";";
		
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("title");
		}
	}
	
	public static float getMovieRating(Movie m) throws SQLException {
		String query = "SELECT ratings FROM movie WHERE + "
		"movie.movieId = " + m.getMovieId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getFloat("title");
		}
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
	
	/** Gets data from ticket **/
	
	
	public static Date getTicketShowtime(Ticket t) throws SQLException {
		String query = "SELECT showtime FROM ticket WHERE + "
		"ticket.ticketId = " + m.getTicketId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getDate("title");
		}
	}
	
	public static int getTicketSeatNum(Ticket t) throws SQLException {
		String query = "SELECT seat FROM ticket WHERE + "
		"ticket.ticketId = " + m.getTicketId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getInt("title");
		}
	}
	
	public static String getTicketType(Ticket t) throws SQLException {
		String query = "SELECT ticketType FROM ticket WHERE + "
		"ticket.ticketId = " + m.getTicketId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getString("title");
		}
	}
	
	public static float getTicketPurchasePrice(Ticket t) throws SQLException {
		String query = "SELECT purchasePrice FROM ticket WHERE + "
		"ticket.ticketId = " + m.getTicketId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getFloat("title");
		}
	}
	
	public static int getTicketBookingOrderId(Ticket t) throws SQLException {
		String query = "SELECT bookingOrder_bookingId FROM ticket WHERE + "
		"ticket.ticketId = " + m.getTicketId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		
		while(rs.next()) {
			return rs.getInt("title");
		}
	}
	
	public static int getTicketMovieId(Ticket t) throws SQLException {
		String query = "SELECT movie_movieId FROM ticket WHERE + "
		"ticket.ticketId = " + m.getTicketId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);
		int result = -1;
		
		while(rs.next()) {
			return rs.getInt("title");
		}
		
		return result;
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
	
	public static int getShowtimeHallId(Showtime s) throws SQLException {
		String query = "SELECT hallId FROM showtime WHERE + "
		"showtime.showId = " + s.showId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);	
	}
	
	public static Date getShowtimeDate(Showtime s) throws SQLException {
		String query = "SELECT time FROM showtime WHERE + "
		"showtime.showId = " + s.showId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);	
	}
	
	public static int getShowtimeNumSeats(Showtime s) throws SQLException {
		String query = "SELECT numSeats FROM showtime WHERE + "
		"showtime.showId = " + s.showId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);	
	}
	
	public static int getShowtimeMovieId(Showtime s) throws SQLException {
		String query = "SELECT movie_movieId FROM showtime WHERE + "
		"showtime.showId = " + s.showId() + ";";
				
		ResultSet rs = DBAccessInterface.retrieve(query);	
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
		"showtime.showId = " + s.getShowId()+ ";";
		
		DBAccessInterface.delete(query);
	}
	
	/** Gets data from movieReview **/
	
	public static int getReviewUserId(Review r) throws SQLException {
		String query = "SELECT userId FROM movieReview WHERE + "
		"movieReview.reviewId = " + r.ReviewId() + ";";
	}
	
	public static String getReview(Review r) throws SQLException {
		String query = "SELECT review FROM movieReview WHERE + "
		"movieReview.reviewId = " + r.ReviewId() + ";";
	}
	
	public static int getReviewMovieId(Review r) throws SQLException {
		String query = "SELECT movie_movieId FROM movieReview WHERE + "
		"movieReview.reviewId = " + r.ReviewId() + ";";
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
		
	}
	
	public static void deleteReview(Review r) throws SQLException {
		
	}
	
	/** Get data from hall **/
	
	public static int getHallTotalSeats(Hall h) throws SQLException {
		
	}
	
	public static Date getHallShowtimes(Hall h) throws SQLException {
		
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
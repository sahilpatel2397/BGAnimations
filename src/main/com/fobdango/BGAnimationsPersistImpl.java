import java.sql.User;
import java.sql.SQLException;

public class BGAnimationsPersistImpl {

	/** Get data from the Users table **/

	public static boolean addUser(User u) throws SQLException {
		String query = "INSERT INTO users " + 
		"(firstName, lastName, email, address, avatarUrl)" +
		" VALUES ('" + u.getFirstName() + "', '" + u.getLastName() + "', '" + 
		u.getEmail() + "', '" + u.getAddress() + "', '" + u.getAvatarUrl() +"');"
		
		int result = DBAccessInterface.create(query);
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static User updateUser(User u) throws SQLException {

	}

	public static User deleteUser(User u) throws SQLException {

	}

	public static User banUser(User u) throws SQLException {

	}

	public static User getUserFirstName(User u) throws SQLException {

	}

	public static User getUserLastName(User u) throws SQLException {

	}

	public static User getUserEmail(User u) throws SQLException {

	}

	public static User getUserAddress(User u) throws SQLException {

	}

	public static User getUserAvatarUrl(User u) throws SQLException {

	}

	public static User getUserId(User u) throws SQLException {

	}

	public staic User getUserBanStatus(User u) throws SQLException {

	}
	
	public static Card getCardNo(User u, Card c){
		
	}
	
	public static Car

}

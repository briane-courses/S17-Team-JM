package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Org;
import model.User;
import model.UserType;
import utils.db.Query;

/**
 * This class handles all SQL statements needed for the User Transfer Objects
 * @author Mavic Reccion
 *
 */
public class UserService {
	
	/**
	 * - This method searches for a user in the database using email
	 * - Mainly used for logging in
	 * - If User is null, then User is not authorized (not Organization Representative or Admin)
	 * @param email
	 * @return User
	 */
	public static User searchUser(String email) {
		User user = null;
		
		String query = 
				"SELECT " + User.COL_EMAIL + ", "
						+ User.COL_IDNUMBER+ ", "
						+ User.COL_USERTYPE+ ", "
						+ User.COL_USERTYPE+ ", "
						+ Org.COL_ORGCODE
				+ " FROM " + User.TABLE_NAME
				+ " natural join " + Org.TABLE_NAME
				+ " WHERE " + User.COL_EMAIL+" =  ? ;";
		
		ArrayList<Object> input = new ArrayList<Object>();
		
		input.add(email);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			if(r.next()) {
				user = new User();
				user.setEmail(r.getString(User.COL_EMAIL));
				user.setUserID(r.getInt(User.COL_IDNUMBER));
				user.setUserType(UserType.getUserType(r.getString(User.COL_USERTYPE)));
				user.setOrgcode(r.getString(Org.COL_ORGCODE));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	/**
	 * This method searches for a user in the database specified by the userID
	 * @param userID
	 * @return User
	 */
	public static User searchUser(int userID) {
		User user = null;
		
		String query = 
				"SELECT * FROM " + User.TABLE_NAME +
				" WHERE " + User.COL_IDNUMBER +" =  ? ;";
		
		ArrayList<Object> input = new ArrayList<Object>();
		
		input.add(userID);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			if(r.next()) {
				user = new User();
				user.setEmail(r.getString(User.COL_EMAIL));
				user.setUserID(r.getInt(User.COL_IDNUMBER));
				user.setUserType(UserType.getUserType(r.getString(User.COL_USERTYPE)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	/**
	 * This method retrieves all users specified by the type of user (e.g. ORGREP, ADMIN)
	 * @param userType
	 * @return User
	 */
	public static ArrayList<User> getUsersPerType(UserType userType) {
		ArrayList<User> orgReps = new ArrayList<User>();
		ArrayList<Object> input = new ArrayList<Object>();
		User user = null;
		
		String query = 
				"SELECT * FROM " + User.TABLE_NAME +
				" WHERE " + User.COL_USERTYPE + " = ?";
		
		input.add(userType);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				user = new User();
				user.setEmail(r.getString(User.COL_EMAIL));
				user.setUserID(r.getInt(User.COL_IDNUMBER));
				user.setUserType(UserType.getUserType(r.getString(User.COL_USERTYPE)));
				
				orgReps.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orgReps;
	}
}

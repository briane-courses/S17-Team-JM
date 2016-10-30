package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.User;
import model.UserType;

public class UserService {
	
	private UserService(){
	}
	
	public static User searchUser(String email) {
		User user = null;
		Query q = null;
		ResultSet r = null;
		
		ArrayList<Object> input = new ArrayList<Object>();
		
		input.add(email);
		try {
			
			q = Query.getInstance();

			r = q.runQuery(
					"SELECT * FROM " + User.TABLE_NAME +
					" WHERE "+User.COL_EMAIL+" =  ? ;"
					, input);
			
			if(r.next()) {
				user = new User();
				user.setEmail(r.getString(User.COL_EMAIL));
				user.setUserID(r.getInt(User.COL_IDNUMBER));
				user.setUserType(UserType.getUserType(r.getString(User.COL_USERTYPE)));
			}
			
		} catch (SQLException e) {
			System.err.println("Error in getting [Class:Query] instance");
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				System.err.println("Error in closing [Class:Query] instance");
				e.printStackTrace();
			}
		}
		return user;
	}

}

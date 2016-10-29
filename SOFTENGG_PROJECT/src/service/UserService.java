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
		
		String query = 
				"SELECT * FROM " + User.TABLE_NAME +
				" WHERE "+User.COL_EMAIL+" =  ? ;";
		
		ArrayList<Object> input = new ArrayList<Object>();
		
		input.add(email);
		
		Query q = Query.getInstance("root", "p@ssword", "jdbc:mysql://localhost:3306/adm_db");
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

}

package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Org;
import utils.db.Query;

public class OrgService {
	
	public static Org searchOrg(int userID) {
		Org org = null;
		
		String query = 
				"SELECT * FROM " + Org.TABLE_NAME +
				" WHERE " + Org.COL_IDNUMBER +" =  ? ;";
		
		ArrayList<Object> input = new ArrayList<Object>();
		
		input.add(userID);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			if(r.next()) {
				org = new Org();
				org.setUserID(r.getInt(Org.COL_IDNUMBER));
				org.setOrgcode(r.getString(Org.COL_ORGCODE));
				org.setOrgname(r.getString(Org.COL_ORGNAME));
				org.setLogoURL(r.getString(Org.COL_LOGOURL));
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
		return org;
	}

}

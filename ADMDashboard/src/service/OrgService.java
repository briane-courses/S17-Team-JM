package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Org;
import utils.db.Query;

/**
 * This class handles all SQL statements needed for the Org Transfer Objects
 * @author Mavic Reccion
 *
 */
public class OrgService {

	
	/**
	 * This method retrieves all the organizations found in the database
	 * @return ArrayList<Org>
	 */
	public static ArrayList<Org> getAllOrgs() {
		System.out.println("[METHOD] getAllOrgs");
		
		ArrayList<Org> orgList = new ArrayList<Org>();
		Org org = null;
		
		String query = "SELECT * FROM " + Org.TABLE_NAME;
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				org = new Org();
				org.setUserID(r.getInt(Org.COL_IDNUMBER));
				org.setOrgcode(r.getString(Org.COL_ORGCODE).toUpperCase());
				org.setOrgname(r.getString(Org.COL_ORGNAME));
				
				orgList.add(org);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return orgList;
	}
	
	/**
	 * - This method searches for a particular organization in the database using the ID of the current user
	 * - Mainly used for login
	 * @param userID
	 * @return
	 */
	public static Org searchOrg(int userID) {
		System.out.println("[METHOD] searchOrg " + userID);
		
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
	
	/**
	 * - This method searches for organizations having which have the same substring as searchString
	 * 		in their organization code or organization name
	 * - Mainly used for login
	 * @param searchString 
	 * @return ArrayList<Org>
	 */
	public static ArrayList<Org> searchOrgs(String searchString) {
		System.out.println("[METHOD] searchOrg " + searchString);
		
		ArrayList<Org> orgList = new ArrayList<Org>();
		ArrayList<Object> input = new ArrayList<Object>();
		Org org = null;
		
		String query = "SELECT * "
				+ " FROM " + Org.TABLE_NAME
				+ " WHERE LOWER(" + Org.COL_ORGCODE + ") LIKE LOWER(?) OR "
				+ " LOWER(" + Org.COL_ORGNAME + ") LIKE LOWER(?)";
		
		input.add("%" + searchString + "%");
		input.add("%" + searchString + "%");
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				org = new Org();
				org.setUserID(r.getInt(Org.COL_IDNUMBER));
				org.setOrgcode(r.getString(Org.COL_ORGCODE));
				org.setOrgname(r.getString(Org.COL_ORGNAME));
				
				orgList.add(org);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return orgList;
	}

}

package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EventType_Requirements;
import model.Requirement;
import utils.db.Query;

public class RequirementService {

	
	public static ArrayList<Requirement> getRequirements(int eventTypeID) {
		ArrayList<Requirement> reqList = new ArrayList<Requirement>();
		Requirement req = null;
		ArrayList<Object> input = new ArrayList<Object>();
		
		String query = "SELECT * FROM " +
					Requirement.TABLE_NAME + " NATURAL JOIN " + EventType_Requirements.TABLE_NAME +
					" WHERE " + EventType_Requirements.COL_EVENTTYPEID + " = ?";
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		input.add(eventTypeID);
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				req = new Requirement();
				req.setReqID(r.getInt(Requirement.COL_REQID));
				req.setReqName(r.getString(Requirement.COL_REQNAME));
				
				reqList.add(req);
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
		
		return reqList;
	}

}

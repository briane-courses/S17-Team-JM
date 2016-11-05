package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.CalendarDate;
import model.EventDate;
import model.Org;

public class CalendarService {
	public static  ArrayList<CalendarDate> getEventsByOrg(String org, int year, int month){
		ArrayList<CalendarDate> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarDate date = null;
		
		String query = "SELECT id FROM things " +
				   "WHERE "+Org.COL_ORGNAME+"= ? " +
				   "AND MONTH("+EventDate.COL_DATE+") = ? " +
				   "AND YEAR("+EventDate.COL_DATE+") = ? " +
				   "ORDER BY "+EventDate.COL_DATE+";";
		
		input.add(org);
		input.add(month);
		input.add(year);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				eventType = new EventType();
				eventType.setEventtypeID(r.getInt(EventType.COL_EVENTTYPEID));
				eventType.setEventtype(r.getString(EventType.COL_EVENTTYPE));
				
				result.add(date);
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
	}
}

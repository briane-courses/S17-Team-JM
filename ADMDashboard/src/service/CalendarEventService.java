package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CalendarEvent;
import model.Event;
import utils.converter.DatatypeConverter;
import utils.db.Query;

public class CalendarEventService {
	public static void getEventsByOrg(String orgcode, int year, int month){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " inner join " + CalendarEvent.TABLE_EVENTDATE
				+ " on "+CalendarEvent.TABLE_EVENT+"."+CalendarEvent.COL_EVENTID+" = "+CalendarEvent.TABLE_EVENTDATE+"."+CalendarEvent.COL_EVENTID
				+ " where "+CalendarEvent.COL_ORGCODE+"= ? " 
				+ " and MONTH("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " and YEAR("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		input.add(orgcode);
		input.add(month);
		input.add(year);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				event = new CalendarEvent();
				event.setEverything(
						r.getInt(CalendarEvent.COL_EVENTID), 
						orgcode, 
						r.getString(CalendarEvent.COL_EVENTNAME), 
						r.getString(CalendarEvent.COL_EVENTTYPE), 
						r.getString(CalendarEvent.COL_EVENTDESC),
						DatatypeConverter.toStatus(r.getString(CalendarEvent.COL_POSTACTSTATUS)), 
						DatatypeConverter.toCalendar(r.getDate(CalendarEvent.COL_DATE)), 
						DatatypeConverter.toCalendar(r.getDate(CalendarEvent.COL_POSTACTDEADLINE)));
				result.add(event);
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

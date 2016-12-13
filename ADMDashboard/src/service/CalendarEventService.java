package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import factory.CalendarEventFactory;
import model.Event;
import model.Status;
import model.calendar.CalendarEvent;
import model.datetime.SimpleDate;
import utils.converter.DatatypeConverter;
import utils.db.Query;
import utils.generator.RandomHexGenerator;

public class CalendarEventService {
	
	public static ArrayList<CalendarEvent> getAllEvents(){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";

		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
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
		return result;
	}

	public static CalendarEvent getEvent(Integer eventID){
		CalendarEvent result = null;
		ArrayList<Object> input = new ArrayList<>();
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where "+CalendarEvent.COL_EVENTID+"= ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		input.add(eventID);
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				result = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
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
		return result;
	}
	
	public static ArrayList<CalendarEvent> getAllEvents(Status status){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where "+CalendarEvent.COL_POSTACTSTATUS+"= ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";

		input.add(status);
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				/*
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						RandomHexGenerator.newHex());
				*/
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
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
		return result;
	}
	
	public static ArrayList<CalendarEvent> getEvents(String orgcode, Status status){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		Calendar temp = null;
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where "+CalendarEvent.COL_ORGCODE+" = ? "
				+ " and "+CalendarEvent.COL_POSTACTSTATUS+" = ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		//System.out.println(query);
		
		input.add(orgcode);
		input.add(status);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				/*
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						RandomHexGenerator.newHex());
				*/
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
				
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
		return result;
	}
	
	public static boolean moveEventDate(Integer eventID, SimpleDate date){
		boolean result = false;
		String query = null;
		ArrayList<Object> input = new ArrayList<>();
		query = "update "+Event.TABLE_NAME+
				" set "+
				" "+Event.COL_POSTACTDEADLINE+" = ? "+
				" where "+Event.COL_EVENTID+" = ?";
		
		input.add(date.toString());
		input.add(eventID);
		
		Query q = Query.getInstance();
		
		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static boolean updateEvent(CalendarEvent event){
		boolean result = false;
		String query = null;
		ArrayList<Object> input = new ArrayList<>();
		
			query = "update "+Event.TABLE_NAME+
					" set "+Event.COL_EVENTNAME+" = ?,"+
					" "+Event.COL_POSTACTDEADLINE+" = ?, "+
					" "+Event.COL_EVENTDESC+" = ?, "+
					" "+Event.COL_POSTACTSTATUS+" = ?, "+
					" "+Event.COL_ORGCODE+" = ? "+
					" where "+Event.COL_EVENTID+" = ?";
		
		input.add(event.getTitle());
		input.add(event.getStart());
		input.add(event.getDescription());
		input.add(event.getStatus());
		input.add(event.getOrg());
		input.add(event.getId());
		
		Query q = Query.getInstance();
		
		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static ArrayList<CalendarEvent> getEventsByMonth(SimpleDate date){
		//TODO: implement date
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where not("+CalendarEvent.COL_POSTACTSTATUS+" = ?) " 
				+ " and month("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " and year("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";

		input.add(Status.DONE);
		input.add(date.getMonth());
		input.add(date.getYear());
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			while(r.next()) {
				/*
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						RandomHexGenerator.newHex());
				*/
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
				
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
		return result;
	}
	
	public static ArrayList<CalendarEvent> getEventsByMonth(Status status, SimpleDate date){
		//TODO: implement date
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where "+CalendarEvent.COL_POSTACTSTATUS+" = ? " 
				+ " and month("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " and year("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		input.add(status);
		input.add(date.getMonth());
		input.add(date.getYear());
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			while(r.next()) {
				/*
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						RandomHexGenerator.newHex());
				*/
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
				
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
		return result;
	}
	
	public static ArrayList<CalendarEvent> getEventsByMonth(String orgcode, SimpleDate date){
		//TODO: implement date
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where "+CalendarEvent.COL_ORGCODE+" = ? "
				+ " and not ("+CalendarEvent.COL_POSTACTSTATUS+" = ?) " 
				+ " and month("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " and year("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		input.add(orgcode);
		input.add(Status.DONE);
		input.add(date.getMonth());
		input.add(date.getYear());
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			while(r.next()) {
				/*
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						RandomHexGenerator.newHex());
				*/
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
				
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
		return result;
	}
	
	public static ArrayList<CalendarEvent> getEventsByMonth(String orgcode, Status status, SimpleDate date){
		//TODO: implement date
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " where "+CalendarEvent.COL_ORGCODE+"= ? "
				+ " and "+CalendarEvent.COL_POSTACTSTATUS+" = ? " 
				+ " and month("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " and year("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		input.add(orgcode);
		input.add(status);
		input.add(date.getMonth());
		input.add(date.getYear());
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			while(r.next()) {
				/*
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						RandomHexGenerator.newHex());
				*/
				event = CalendarEventFactory.getInstance().createEvent(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME),
						r.getString(CalendarEvent.COL_ORGCODE),
						r.getString(CalendarEvent.COL_EVENTDESC),
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						r.getString(CalendarEvent.COL_POSTACTSTATUS),
						"#4CAF50");
				
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
		return result;
	}
}

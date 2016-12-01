<<<<<<< HEAD:ADMDashboard/src/service/EventService.java
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.Event;
import model.Status;
import utils.db.Query;

public class EventService {

	public static ArrayList<Event> getUpcomingDeadlines(String orgcode, int minDays, int maxDays) {
		System.out.println("[METHOD] getUpcomingDeadlines " + maxDays);
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " < date_add(CURDATE(), INTERVAL ? DAY)" + "	AND "
				+ Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL ? DAY)" + " AND " + Event.COL_ORGCODE
				+ " = ? " + " AND " + Event.COL_POSTACTSTATUS + " = ? " + " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(maxDays);
		input.add(minDays);
		input.add(orgcode);
		input.add(Status.PENDING);
		Query q = Query.getInstance();
		ResultSet r = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				cal = Calendar.getInstance();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));// converts
																				// date
																				// string
																				// to
																				// a
																				// calendar
																				// object
				event.setPostact_deadline(cal); // no postact status
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}

	public static ArrayList<String> convertDates(ArrayList<Event> events) {
		System.out.println("[METHOD] convertDates");
		ArrayList<String> dates = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");

		for (int i = 0; i < events.size(); i++) {
			dates.add(formatter.format(events.get(i).getPostact_deadline().getTime()));
			// System.out.println(dates.get(i));
		}
		return dates;

	}

	public static ArrayList<Event> getOverdueDeadlines(String orgcode) {
		System.out.println("[METHOD] getOverdueDeadlines");
		ArrayList<Event> overdueEvents = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.COL_POSTACTDEADLINE + " < curdate()"
				+ " AND " + Event.COL_ORGCODE + " = ? " + " AND " + Event.COL_POSTACTSTATUS + " = ? " + " ORDER BY "
				+ Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);

		Query q = Query.getInstance();
		ResultSet r = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				cal = Calendar.getInstance();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));// converts
																				// date
																				// string
																				// to
																				// a
																				// calendar
																				// object
				event.setPostact_deadline(cal); // no postact status
				overdueEvents.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
		 * System.out.println("RETURN"); for(int i = 0 ; i< events.size(); i++)
		 * System.out.println(events.get(i).getEventname());
		 */
		return overdueEvents;
	}

	public static ArrayList<Event> getOtherDeadlines(String orgcode) {
		System.out.println("[METHOD] getOtherDeadlines ");
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL 14 DAY)" + " AND "
				+ Event.COL_ORGCODE + " = ? " + " AND " + Event.COL_POSTACTSTATUS + " = ? " + " ORDER BY "
				+ Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);
		Query q = Query.getInstance();
		ResultSet r = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				cal = Calendar.getInstance();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));// converts
																				// date
																				// string
																				// to
																				// a
																				// calendar
																				// object
				event.setPostact_status(Status.PENDING);
				event.setPostact_deadline(cal); // no postact status
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}

}
=======
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.Event;
import model.Status;

import db.Query;

public class EventService {
	

	public static ArrayList<Event> getUpcomingDeadlines(String orgcode, int minDays, int maxDays){
		System.out.println("[METHOD] getUpcomingDeadlines " + maxDays);
		ArrayList <Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>(); 
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME  
						+ " WHERE " + Event.COL_POSTACTDEADLINE + " >= curdate()"
						+ "	AND " + Event.COL_POSTACTDEADLINE + " < date_add(CURDATE(), INTERVAL ? DAY)"
						+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL ? DAY)"
						+ " AND " + Event.COL_ORGCODE + " = ? "
						+ " AND " + Event.COL_POSTACTSTATUS + " = ? "
						+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(maxDays);
		if (maxDays == 8)
			minDays--;
		input.add(minDays);
		input.add(orgcode);
		input.add(Status.PENDING);
		Query q = Query.getInstance();
		ResultSet r = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal  = Calendar.getInstance();	
		try {
			r = q.runQuery(query, input);
			while(r.next()) {		
				event = new Event();
				cal  = Calendar.getInstance();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));//converts date string to a calendar object
				event.setPostact_deadline(cal);	//no postact status
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}
	
	public static ArrayList<String> convertDates(ArrayList<Event> events) 
	{
		System.out.println("[METHOD] convertDates");
		ArrayList<String> dates = new ArrayList<String>();
		SimpleDateFormat formatter=new SimpleDateFormat("mm-dd-yyyy");
		
		for(int i = 0; i < events.size() ; i++ )
		{
			dates.add(formatter.format(events.get(i).getPostact_deadline().getTime()));
			//System.out.println(dates.get(i));
		}
		return dates;
		
	}
	
	public static ArrayList<Event> getOverdueDeadlines(String orgcode) {
		System.out.println("[METHOD] getOverdueDeadlines");
		ArrayList <Event> overdueEvents = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
						+ " WHERE " + Event.COL_POSTACTDEADLINE + " < curdate()"
						+ " AND " + Event.COL_ORGCODE + " = ? "
					    + " AND " + Event.COL_POSTACTSTATUS + " = ? "
						+ " ORDER BY " + Event.COL_POSTACTDEADLINE;
		
		input.add(orgcode);
		input.add(Status.PENDING);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal  = Calendar.getInstance();
		try {
			r = q.runQuery(query, input);
			while(r.next()) {		
				event = new Event();
				cal  = Calendar.getInstance();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));//converts date string to a calendar object
				event.setPostact_deadline(cal);	//no postact status
				overdueEvents.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
		System.out.println("RETURN");
		for(int i = 0 ; i< events.size(); i++)
			System.out.println(events.get(i).getEventname());*/
		return overdueEvents;
	}
	
	public static ArrayList<Event> getOtherDeadlines(String orgcode){
		System.out.println("[METHOD] getOtherDeadlines ");
		ArrayList <Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>(); 
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME  
						+ " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
						+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL 14 DAY)"
						+ " AND " + Event.COL_ORGCODE + " = ? "
						+ " AND " + Event.COL_POSTACTSTATUS + " = ? "
						+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);
		Query q = Query.getInstance();
		ResultSet r = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal  = Calendar.getInstance();	
		try {
			r = q.runQuery(query, input);
			while(r.next()) {		
				event = new Event();
				cal  = Calendar.getInstance();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));//converts date string to a calendar object
				event.setPostact_status(Status.PENDING);
				event.setPostact_deadline(cal);	//no postact status
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}
	
}

>>>>>>> refs/remotes/SOFENGG-T1AY1617/US02:EventService.java

package service;

import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.Event;
import model.Status;
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Event;
import model.EventType;
import model.Status;
import utils.converter.DatatypeConverter;
>>>>>>> refs/remotes/origin/US10
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
<<<<<<< HEAD
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
=======
>>>>>>> refs/remotes/origin/US10
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
<<<<<<< HEAD
				cal = Calendar.getInstance();
=======
>>>>>>> refs/remotes/origin/US10
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
<<<<<<< HEAD
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));// converts
																				// date
																				// string
																				// to
																				// a
																				// calendar
																				// object
				event.setPostact_deadline(cal); // no postact status
=======
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
>>>>>>> refs/remotes/origin/US10
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
<<<<<<< HEAD
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
=======
>>>>>>> refs/remotes/origin/US10
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
<<<<<<< HEAD
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
=======
>>>>>>> refs/remotes/origin/US10
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
<<<<<<< HEAD
				cal = Calendar.getInstance();
=======
>>>>>>> refs/remotes/origin/US10
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
<<<<<<< HEAD
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));// converts
																				// date
																				// string
																				// to
																				// a
																				// calendar
																				// object
				event.setPostact_deadline(cal); // no postact status
=======
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
>>>>>>> refs/remotes/origin/US10
				overdueEvents.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
<<<<<<< HEAD
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
=======
>>>>>>> refs/remotes/origin/US10
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
<<<<<<< HEAD
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
=======
>>>>>>> refs/remotes/origin/US10
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
<<<<<<< HEAD
				cal = Calendar.getInstance();
=======
>>>>>>> refs/remotes/origin/US10
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
<<<<<<< HEAD
				cal.setTime(df.parse(r.getString(Event.COL_POSTACTDEADLINE)));// converts
																				// date
																				// string
																				// to
																				// a
																				// calendar
																				// object
				event.setPostact_status(Status.PENDING);
				event.setPostact_deadline(cal); // no postact status
=======
				event.setPostact_status(Status.PENDING);
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
>>>>>>> refs/remotes/origin/US10
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
<<<<<<< HEAD
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
=======
>>>>>>> refs/remotes/origin/US10
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}
<<<<<<< HEAD
=======
	
	public ArrayList<Event> searchEvents(String searchString) {
		System.out.println("[METHOD] searchEvents " + searchString);
		
		ArrayList<Event> eventList = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		
		String query = "SELECT *"
				+ " FROM " + Event.TABLE_NAME + " NATURAL JOIN " + EventType.TABLE_NAME
				+ " WHERE LOWER(" + Event.COL_EVENTNAME + ") LIKE LOWER(?) OR "
				+ " LOWER(" + Event.COL_EVENTDESC + ") LIKE LOWER(?) OR "
				+ " LOWER(" + Event.COL_ORGCODE + ") LIKE LOWER(?)";
		
		input.add("%" + searchString + "%");
		input.add("%" + searchString + "%");
		input.add("%" + searchString + "%");
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				event = new Event();
				
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setEventtype(r.getString(EventType.COL_EVENTTYPE));
				event.setPostact_status(DatatypeConverter.toStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
				
				eventList.add(event);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return eventList;
	}
>>>>>>> refs/remotes/origin/US10

}

package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Event;
import model.EventType;
import model.Status;
import model.datetime.SimpleDate;
import utils.converter.DatatypeConverter;
import utils.db.Query;

public class EventService {

	public static ArrayList<Event> getUpcomingDeadlines(String orgcode, Integer minDays, Integer maxDays) {
		System.out.println("[METHOD] getUpcomingDeadlines " + maxDays);
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE " + Event.COL_POSTACTDEADLINE + " >= curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " < date_add(CURDATE(), INTERVAL ? DAY)" 
				+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL ? DAY)" + " AND " + Event.COL_ORGCODE + " = ? " 
				+ " AND (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )" 
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(maxDays);
		if(maxDays == 7)
			minDays--;
		input.add(minDays);
		input.add(orgcode);
		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);
		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.getStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				events.add(event);
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
		return events;
	}

	public static ArrayList<Event> getUpcomingDeadlines(Integer minDays, Integer maxDays) {
		System.out.println("[METHOD] getUpcomingDeadlines " + maxDays + " (No orgcode)");
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE " + Event.COL_POSTACTDEADLINE + " >= curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " < date_add(CURDATE(), INTERVAL ? DAY)" 
				+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL ? DAY)"
				+ " AND (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )" 
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(maxDays);
		if(maxDays == 7)
			minDays--;
		input.add(minDays);
		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);
		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.getStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				events.add(event);
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
		return events;
	}

	public static ArrayList<String> convertDates(ArrayList<Event> events) {
		System.out.println("[METHOD] convertDates");
		ArrayList<String> dates = new ArrayList<String>();

		int year, month, day;

		for(int i = 0; i < events.size(); i ++) {
			year = events.get(i).getPostact_deadline().getYear();
			month = events.get(i).getPostact_deadline().getMonth();
			day = events.get(i).getPostact_deadline().getDay();
			dates.add(SimpleDate.toString(year, month, day));
		}


		return dates;
	}

	public static ArrayList<Integer> setDeadlineType(ArrayList<Event> events) {
		System.out.println("[METHOD] setDeadlineType");
		ArrayList<Integer> deadlineType = new ArrayList<Integer>();
		ArrayList<Event> eventsDueIn1 = EventService.getOverdueDeadlines();
		ArrayList<Event> eventsDueIn2 = EventService.getUpcomingDeadlines(0, 14);
		ArrayList<Event> otherEventsDue = EventService.getOtherDeadlines();
		
		for(int i = 0; i < events.size(); i++)
		{
			for(int j = 0; j < eventsDueIn1.size(); j++)
			{
				if (events.get(i).getPostact_deadline().toString().equals(eventsDueIn1.get(j).getPostact_deadline().toString())
						&& events.get(i).getEventID() == eventsDueIn1.get(j).getEventID())
				{
					deadlineType.add(1);
				}
			}
			
			for(int j = 0; j < eventsDueIn2.size(); j++)
			{
				if (events.get(i).getPostact_deadline().toString().equals(eventsDueIn2.get(j).getPostact_deadline().toString())
						&& events.get(i).getEventID() == eventsDueIn2.get(j).getEventID())
				{
					deadlineType.add(2);
				}
			}
			
			for(int j = 0; j < otherEventsDue.size(); j++)
			{
				if (events.get(i).getPostact_deadline().toString().equals(otherEventsDue.get(j).getPostact_deadline().toString())
						&& events.get(i).getEventID() == otherEventsDue.get(j).getEventID())
				{
					deadlineType.add(3);
				}
			}
		}
		return deadlineType;
	}

	private static ArrayList<Event> getOtherDeadlines() {
		System.out.println("[METHOD] getOtherDeadlines (No orgcode)");
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL 14 DAY)" 
				+ " AND (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )" 
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);

		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.getStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				events.add(event);
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
		return events;
	
	}

	public static ArrayList<Event> getOverdueDeadlines() {
		System.out.println("[METHOD] getOverdueDeadlines (No orgcode)");
		ArrayList<Event> overdueEvents = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE " + Event.COL_POSTACTDEADLINE + " < curdate()"
				+ " AND (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )" 
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);

		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.getStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				overdueEvents.add(event);
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
		return overdueEvents;
	}
	
	public static ArrayList<Event> getOverdueDeadlines(String orgcode) {
		System.out.println("[METHOD] getOverdueDeadlines");
		ArrayList<Event> overdueEvents = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE " + Event.COL_POSTACTDEADLINE + " < curdate()"
				+ " AND " + Event.COL_ORGCODE + " = ? " 
				+ " AND (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )" 
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);

		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.getStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				overdueEvents.add(event);
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
		return overdueEvents;
	}

	public static ArrayList<Event> getOtherDeadlines(String orgcode) {
		System.out.println("[METHOD] getOtherDeadlines ");
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL 14 DAY)" 
				+ " AND " + Event.COL_ORGCODE + " = ? " 
				+ " AND (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )" 
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);

		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.getStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				events.add(event);
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
		return events;
	}

	public static ArrayList<Event> searchEvents(String searchString) {
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
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));

				eventList.add(event);
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
		return eventList;
	}
	
	public static void updateDeadline(Integer eventID, String newDate) {
		System.out.println("[METHOD] updateDeadline " + eventID);

		Event event = new Event();
		ArrayList<Object> input = new ArrayList<Object>();
		Event result = null;

		String query = "UPDATE "+ Event.TABLE_NAME 
				+ " SET " + Event.COL_POSTACTDEADLINE + " = ?" 
				+ " WHERE  " + Event.COL_EVENTID + " = ? ";
		
		input.add(newDate);
		input.add(eventID);

		Query q = Query.getInstance();
		try {
			q.runInsertUpdateDelete(query, input);
			
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
	}
	
	public static Event searchEventDetails(int eventID) {
		System.out.println("[METHOD] searchEvents " + eventID);

		Event event = new Event();
		ArrayList<Object> input = new ArrayList<Object>();
		Event result = null;

		String query = "SELECT *"
				+ " FROM " + Event.TABLE_NAME 
				+ " WHERE  " + Event.COL_EVENTID + " = ? ";
		
		input.add(eventID);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				result = new Event();

				result.setEventID(r.getInt(Event.COL_EVENTID));
				result.setOrgcode(r.getString(Event.COL_ORGCODE));
				result.setEventname(r.getString(Event.COL_EVENTNAME));
				result.setPostact_status(DatatypeConverter.toStatus(r.getString(Event.COL_POSTACTSTATUS)));
				result.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				event = result;
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
		return event;
	}

	public static ArrayList<Event> getAllPendingNotPassedEvents() {
		System.out.println("[METHOD] getAllPendingNotPassedEvents ");
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME 
				+ " WHERE (" + Event.COL_POSTACTSTATUS + " = ? OR " +  Event.COL_POSTACTSTATUS + " = ? )"
				+ " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(Status.PENDING);
		input.add(Status.NOT_PASSED);
		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE).toUpperCase());
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(DatatypeConverter.toStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(new SimpleDate(r.getDate(Event.COL_POSTACTDEADLINE).toString()));
				events.add(event);
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
		return events;
	}

}


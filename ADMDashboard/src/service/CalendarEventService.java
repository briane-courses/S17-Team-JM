package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import factory.CalendarEventFactory;
import model.Status;
import model.calendar.CalendarEvent;
import model.datetime.SimpleDate;
import utils.converter.DatatypeConverter;
import utils.db.Query;
import utils.generator.RandomHexGenerator;

public class CalendarEventService {
	
<<<<<<< HEAD
	public static ArrayList<CalendarEvent> getAllEvents(Status status){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
=======
	public static ArrayList<CalendarEvent> getAllEvents(){
		ArrayList<CalendarEvent> result = new ArrayList<>();
>>>>>>> 025278646cd2c4c40fe62ccf7e43b55bf12f04ab
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " natural join " + CalendarEvent.TABLE_EVENTDATE
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";

<<<<<<< HEAD
		input.add(status);
=======
>>>>>>> 025278646cd2c4c40fe62ccf7e43b55bf12f04ab
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
<<<<<<< HEAD
			r = q.runQuery(query, input);
=======
			r = q.runQuery(query);
>>>>>>> 025278646cd2c4c40fe62ccf7e43b55bf12f04ab
			
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
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						RandomHexGenerator.newHex());
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

	
<<<<<<< HEAD
	public static ArrayList<CalendarEvent> getEventsByOrg(String orgcode, Status status){
=======
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
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						RandomHexGenerator.newHex());
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
>>>>>>> 025278646cd2c4c40fe62ccf7e43b55bf12f04ab
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
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						RandomHexGenerator.newHex());
				
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
<<<<<<< HEAD
				+ " where "+CalendarEvent.COL_ORGCODE+"= ? " 
=======
				+ " where "+CalendarEvent.COL_ORGCODE+"= ? "
				+ " and "+CalendarEvent.COL_POSTACTSTATUS+" = ? " 
				+ " and month("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
				+ " and year("+CalendarEvent.COL_POSTACTDEADLINE+") = ? " 
>>>>>>> 025278646cd2c4c40fe62ccf7e43b55bf12f04ab
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
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						null, 
						RandomHexGenerator.newHex());
				
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

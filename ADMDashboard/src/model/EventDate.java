package model;

import java.io.Serializable;
import java.util.Calendar;

public class EventDate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "event_date";
	public static final String COL_EVENTDATEID = "eventdateID";
	public static final String COL_EVENTID = "eventID";
	public static final String COL_DATE = "date";
	
	private int eventDateID;
	private int eventID;
	private Calendar date;
	
	public EventDate() {}

	public EventDate(int eventDateID, int eventID, Calendar date) {
		super();
		this.eventDateID = eventDateID;
		this.eventID = eventID;
		this.date = date;
	}

	public int getEventDateID() {
		return eventDateID;
	}

	public void setEventDateID(int eventDateID) {
		this.eventDateID = eventDateID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
}

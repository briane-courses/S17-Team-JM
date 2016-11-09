package model;

import java.io.Serializable;
import java.util.Calendar;

import model.Status;

public class CalendarEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4601364874099281325L;


	public static final String TABLE_EVENTDATE = EventDate.TABLE_NAME;
	public static final String COL_DATE = EventDate.COL_DATE;
	
	public static final String TABLE_EVENT = Event.TABLE_NAME;
	public static final String COL_EVENTID = Event.COL_EVENTID;
	public static final String COL_ORGCODE = Event.COL_ORGCODE;
	public static final String COL_EVENTNAME = Event.COL_EVENTNAME;
	public static final String COL_EVENTTYPE = Event.COL_EVENTTYPE;
	public static final String COL_EVENTDESC = Event.COL_EVENTDESC;
	public static final String COL_POSTACTSTATUS = Event.COL_POSTACTSTATUS;
	public static final String COL_POSTACTDEADLINE = Event.COL_POSTACTDEADLINE;
	
	private int eventID;
	private String orgcode;
	private String eventname;
	private String eventtype;
	private String eventdesc;
	private Status postact_status;
	private Calendar postact_deadline;
	private Calendar date;
	
	public CalendarEvent(){}
	
	public CalendarEvent(int eventID, String orgcode, String eventname, String eventtype,
			String eventdesc, Status postact_status, Calendar date, Calendar postact_deadline){
		this.eventID = eventID;
		this.orgcode = orgcode;
		this.eventname = eventname;
		this.eventtype = eventtype;
		this.date = date;
		this.postact_status = postact_status;
		this.postact_deadline = postact_deadline;
		this.eventdesc = eventdesc;
	}
	
	public void setEverything(int eventID, String orgcode, String eventname, String eventtype,
			String eventdesc, Status postact_status, Calendar date, Calendar postact_deadline){
		this.eventID = eventID;
		this.orgcode = orgcode;
		this.eventname = eventname;
		this.eventtype = eventtype;
		this.date = date;
		this.postact_status = postact_status;
		this.postact_deadline = postact_deadline;
		this.eventdesc = eventdesc;
	}
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public Status getPostact_status() {
		return postact_status;
	}
	public void setPostact_status(Status postact_status) {
		this.postact_status = postact_status;
	}
	public Calendar getPostact_deadline() {
		return postact_deadline;
	}
	public void setPostact_deadline(Calendar postact_deadline) {
		this.postact_deadline = postact_deadline;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getEventdesc() {
		return eventdesc;
	}

	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}
	
}

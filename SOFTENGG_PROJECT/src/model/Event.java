package model;

import java.io.Serializable;
import java.util.Calendar;

public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "event";
	public static final String COL_EVENTID = "eventID";
	public static final String COL_ORGCODE = "orgcode";
	public static final String COL_EVENTNAME = "eventname";
	public static final String COL_EVENTTYPE = "eventtype";
	public static final String COL_EVENTDESC = "eventdesc";
	public static final String COL_POSTACTSTATUS = "postact_status";
	public static final String COL_POSTACTDEADLINE = "postact_deadline";
	
	private int eventID;
	private String orgcode;
	private String eventname;
	private String eventtype;
	private String eventdesc;
	private Status postact_status;
	private Calendar postact_deadline;
	
	public Event() {}

	public Event(int eventID, String orgcode, String eventname, String eventtype, String eventdesc,
			Status postact_status, Calendar postact_deadline) {
		super();
		this.eventID = eventID;
		this.orgcode = orgcode;
		this.eventname = eventname;
		this.eventtype = eventtype;
		this.eventdesc = eventdesc;
		this.postact_status = postact_status;
		this.postact_deadline = postact_deadline;
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

	public String getEventdesc() {
		return eventdesc;
	}

	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
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
}

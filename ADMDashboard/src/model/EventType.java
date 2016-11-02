package model;

import java.io.Serializable;

public class EventType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "event_type";
	public static final String COL_EVENTTYPEID = "eventtypeID";
	public static final String COL_EVENTTYPE = "eventtype";
	
	private int eventtypeID;
	private String eventtype;
	
	public EventType() {}

	public EventType(int eventtypeID, String eventtype) {
		super();
		this.eventtypeID = eventtypeID;
		this.eventtype = eventtype;
	}

	public int getEventtypeID() {
		return eventtypeID;
	}

	public void setEventtypeID(int eventtypeID) {
		this.eventtypeID = eventtypeID;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	
}

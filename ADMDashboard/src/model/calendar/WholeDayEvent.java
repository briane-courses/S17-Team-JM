package model.calendar;

public class WholeDayEvent extends CalendarEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9186345782319366114L;

	private String end;
	
	public WholeDayEvent(){}
	
	public WholeDayEvent(int id, String title, String org, String description, String start, String end, String status, String color) {
		setId(id);
		setTitle(title);
		setStart(start);
		this.end = end;
		setColor(color);
		setOrg(org);
		setDescription(description);
		setStatus(status);
	}
	
	public void setEverything(int id, String title, String org, String description, String start, String end, String status, String color) {
		setId(id);
		setTitle(title);
		setStart(start);
		this.end = end;
		setColor(color);
		setOrg(org);
		setDescription(description);
		setStatus(status);
	}
	


	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}

package model.calendar;

public class WholeDayEvent extends CalendarEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9186345782319366114L;

	private String end;
	
	public WholeDayEvent(){}
	
	public WholeDayEvent(int id, String title, String start, String end, String color) {
		setId(id);
		setTitle(title);
		setStart(start);
		setEnd(end);
		setColor(color);
	}
	
	public void setEverything(int id, String title, String start, String end, String color) {
		setId(id);
		setTitle(title);
		setStart(start);
		setEnd(end);
		setColor(color);
	}
	


	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}

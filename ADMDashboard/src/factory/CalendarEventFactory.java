package factory;

import model.calendar.CalendarEvent;
import model.calendar.WholeDayEvent;

public class CalendarEventFactory {

	private static CalendarEventFactory instance = null;
	
	private CalendarEventFactory(){}
	
	public static CalendarEventFactory getInstance(){
		if(instance == null)
			instance = new CalendarEventFactory();
		return instance;
	}
	
	public CalendarEvent createEvent(int id, String title, String start, String end, String color){
		if(end != null)
			if(end.equals("")){
				return new CalendarEvent(id, title, start, color);
			}
		return new WholeDayEvent(id, title, start, end, color);
	}
	
}

package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.EventType;

public class EventTypeService {
	
	public static ArrayList<EventType> getAllEventType() {
		ArrayList<EventType> eventTypeList = new ArrayList<EventType>();
		EventType eventType = null;
		
		String query = "SELECT * FROM " + EventType.TABLE_NAME;
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				eventType = new EventType();
				eventType.setEventtypeID(r.getInt(EventType.COL_EVENTTYPEID));
				eventType.setEventtype(r.getString(EventType.COL_EVENTTYPE));
				
				eventTypeList.add(eventType);
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
		
		return eventTypeList;
	}

}

package servlet.sub;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CalendarEvent;
import service.CalendarEventService;
import servlet.AbstractServlet;

public class AjaxUserCalendarServlet extends AbstractServlet{


	public static final String URL = "/AjaxUserCalendar";
	
	@Override
	public String getUrl() {
		return URL;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CalendarEvent> events = CalendarEventService.getEventsByOrg(orgcode)
		//events.add(new CalendarEvent(1, "TEST", "2016-11-15", "2016-11-16", "#333"));
		String json = new Gson().toJson(events);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(json);
		response.getWriter().write(json);
	}

}

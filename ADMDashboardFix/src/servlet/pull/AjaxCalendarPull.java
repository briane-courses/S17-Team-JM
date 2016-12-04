package servlet.pull;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import factory.CalendarEventFactory;
import model.Org;
import model.Status;
import model.calendar.CalendarEvent;
import model.datetime.SimpleDate;
import service.CalendarEventService;
import servlet.MasterServlet;
import utils.session.SessionManager;

public class AjaxCalendarPull{


	public static final String URL = "/PullAjaxCalendar";
	
	private AjaxCalendarPull() {}

	private static void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CalendarEvent> events = new ArrayList<>();
		String type = null;
		String orgCode = null;
		String tempDate = null;
		String arrDate[] = null;
		SimpleDate date = null;
		
		// needs orgcode of logged in user to be stored at log in
		type = request.getParameter("type");
		tempDate = request.getParameter("date");
		if(tempDate != null)
			date = new SimpleDate(tempDate);
		switch(type){
		case "admin":
			orgCode = (String) SessionManager.getAttribute(request, Org.COL_ORGCODE);
			if(orgCode != null)
				events = CalendarEventService.getAllEvents(Status.DONE);
			break;
		case "user":
			orgCode = (String) SessionManager.getAttribute(request, Org.COL_ORGCODE);
			orgCode = "imes";
			if(orgCode != null && date != null)
				events = CalendarEventService.getEventsByMonth(orgCode, Status.DONE, date);
			break;
		case "rep":
			orgCode = (String) SessionManager.getAttribute(request, Org.COL_ORGCODE);
			if(orgCode != null && date != null)
				events = CalendarEventService.getEventsByMonth(orgCode, Status.DONE, date);
			break;
		default:
			events = CalendarEventService.getAllEvents(Status.DONE);
		}
		

		//events.add(CalendarEventFactory.getInstance().createEvent(1, "TEST", "2016-11-15", null, "#333"));
		
		String json = new Gson().toJson(events);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(json);
		response.getWriter().write(json);
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

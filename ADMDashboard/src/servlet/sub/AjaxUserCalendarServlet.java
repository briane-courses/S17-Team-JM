package servlet.sub;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Org;
import model.Status;
import model.User;
import model.calendar.CalendarEvent;
import service.CalendarEventService;
import service.OrgService;
import servlet.MasterServlet;
import utils.SessionManager;

public class AjaxUserCalendarServlet{


	public static final String URL = "/AjaxUserCalendar";
	
	private AjaxUserCalendarServlet() {}

	private static void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CalendarEvent> events;
		
		String orgcode = null;
		
		// needs orgcode of logged in user to be stored at log in
		/*
		orgcode = (String) SessionManager.getAttribute(request, User.COL_ORGCODE);
		if(orgcode != null)
			events = CalendarEventService.getEventsByOrg(orgcode, Status.DONE);
		else
			events = CalendarEventService.getAllEvents(Status.DONE);
		*/

		events = CalendarEventService.getEventsByOrg("imes", Status.DONE);
		//events.add(new CalendarEvent(1, "TEST", "2016-11-15", "2016-11-16", "#333"));
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

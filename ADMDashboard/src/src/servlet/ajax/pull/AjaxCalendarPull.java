package servlet.ajax.pull;

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
import model.UserType;
import model.calendar.CalendarEvent;
import model.datetime.SimpleDate;
import service.CalendarEventService;
import service.UserService;
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
		UserType type = null;
		String orgCode = null;
		String tempDate = null;
		SimpleDate date = null;
		
		// needs orgcode of logged in user to be stored at log in
		type = UserType.getUserType((String) request.getSession().getAttribute(User.COL_USERTYPE));
		orgCode = (String) request.getSession().getAttribute(Org.COL_ORGCODE);
		tempDate = request.getParameter("date");

		System.out.println(type);
		if(tempDate != null)
			date = new SimpleDate(tempDate);
		if(type == null || orgCode == null)
		System.out.println("Error: User is not logged in!");
		else
		switch(type){
		case ADMIN:
			events = CalendarEventService.getEventsByMonth(date);
			break;
		case ORGREP:
			System.out.println(orgCode);
				events = CalendarEventService.getEventsByMonth(orgCode, date);
			break;
		default:
			System.out.println("Error: ajax type mismatch!");
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

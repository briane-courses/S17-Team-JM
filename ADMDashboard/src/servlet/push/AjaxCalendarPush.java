package servlet.push;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Org;
import model.Status;
import model.calendar.CalendarEvent;
import model.datetime.SimpleDate;
import service.CalendarEventService;
import servlet.MasterServlet;
import servlet.pull.AjaxCalendarPull;
import utils.session.SessionManager;

public class AjaxCalendarPush {
public static final String URL = "/PushAjaxCalendar";
	
	private AjaxCalendarPush() {}

	private static void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eventID = null;
		String title = null;
		String tempDate = null;
		SimpleDate date = null;
		boolean validInt = false;
		
		eventID = request.getParameter("id");
		title = request.getParameter("title");
		tempDate = request.getParameter("start");
		date = new SimpleDate(tempDate);

		System.out.println("Recieved Event: "+title+" "+eventID+" "+date.toString()+" ");
		if(eventID != null)
			if(!eventID.equals(""))
				if(CalendarEventService.updateAddEvent(Integer.parseInt(eventID), title, date)){
					System.out.println("Success!");
					request.getRequestDispatcher(AjaxCalendarPull.URL).forward(request, response);
				}
	}
	
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
}

package servlet.ajax.push;

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
import servlet.ajax.pull.AjaxCalendarPull;
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
		String type = null;
		SimpleDate date = null;
		boolean validInt = false;
		
		eventID = request.getParameter("id");
		title = request.getParameter("title");
		tempDate = request.getParameter("start");
		type = request.getParameter("type");
		date = new SimpleDate(tempDate);
		System.out.println(type);
		System.out.println("Recieved Event: "+title+" "+eventID+" "+date.toString()+" ");
		
		if(eventID != null && title != null && tempDate != null && type != null)
			switch(type){
			case "moveEvent": 
				if(!eventID.equals(""))
				if(CalendarEventService.moveEventDate(Integer.parseInt(eventID), date)){
					System.out.println("Success!");
					request.getRequestDispatcher(AjaxCalendarPull.URL).forward(request, response);
				}
				break;
			case "editEvent": 
				CalendarEvent tempEvent = CalendarEventService.getEvent(Integer.parseInt(eventID));
				if(tempEvent != null){
					tempEvent.setStart(date.toString());
					CalendarEventService.updateEvent(tempEvent);
				}
				break;
			default:
				System.out.println("UNHANDLED TYPE ERROR: "+type);
			}
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
}

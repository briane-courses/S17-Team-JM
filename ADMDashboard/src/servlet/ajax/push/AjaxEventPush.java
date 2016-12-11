package servlet.ajax.push;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.CalendarEventFactory;
import model.datetime.SimpleDate;
import service.CalendarEventService;
import servlet.MasterServlet;

public class AjaxEventPush {
	public static final String URL = "/PushAjaxEvent";
	private AjaxEventPush(){}
	private static void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	private static void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDate date = null;
		String tempDate = null;
		String eventID = null;
		

		eventID = request.getParameter("id");
		tempDate = request.getParameter("date");
		
		if(eventID != null && tempDate != null){
			date = new SimpleDate(tempDate);
			if(date != null){
				System.out.println("Recieved Event: "+eventID+" "+date.toString()+" ");
				if(CalendarEventService.moveEventDate(Integer.parseInt(eventID), date))
					System.out.println("Success!");
			}
		}
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
}

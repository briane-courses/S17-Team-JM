package servlet.sub;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Event;
import service.EventService;
import servlet.MasterServlet;

public class EventDetailsServlet {
       
	public static final String URL = "/EventDetailsServlet";
    public EventDetailsServlet() { }

    
    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EVENT DETAILS SERVLET post");
		
		String eventId = request.getParameter(Event.COL_EVENTID);
		String eventName = request.getParameter(Event.COL_EVENTNAME);
		System.out.println(eventId + " | " + eventName);
		Event event = EventService.searchEventDetails(Integer.parseInt(eventId));
		PrintWriter pw = response.getWriter();
		Gson gson = null;
		gson = new Gson();
		String eventname = gson.toJson(event.getEventname());
		gson = new Gson();
		String eventOrgcCode = gson.toJson(event.getOrgcode().toUpperCase());
		gson = new Gson();
		ArrayList<Event> eventList = new ArrayList<Event>();
		eventList.add(event);
		String deadline = gson.toJson((EventService.convertDates(eventList)).get(0));
		request.getSession().setAttribute("deadline", deadline);
		System.out.println("DEADLINE: " + deadline);
		String allJson = "[ " 
			+ eventname + ","
			+ eventOrgcCode + ","
			+ deadline + "]";
				
		pw.write(allJson);
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

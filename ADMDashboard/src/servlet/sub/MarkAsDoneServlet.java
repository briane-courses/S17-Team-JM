package servlet.sub;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import service.EventService;
import servlet.MasterServlet;

public class MarkAsDoneServlet {
	
	public static final String URL = "/MarkAsDoneServlet";
    public MarkAsDoneServlet() { }

    
    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MARK AS DONE SERVLET post");
		
		String eventId = request.getParameter(Event.COL_EVENTID);
		System.out.println("eventID: " + eventId);
		EventService.markAsDone(Integer.parseInt(eventId));
		PrintWriter pw = response.getWriter();
		pw.write(1);
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

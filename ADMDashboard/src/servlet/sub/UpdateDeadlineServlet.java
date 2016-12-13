
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

public class UpdateDeadlineServlet {

	public static final String URL = "/UpdateDeadlineServlet";
	public UpdateDeadlineServlet() { }


	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UPDATE DEADLINE SERVLET get");
		String eventId = request.getParameter(Event.COL_EVENTID);
		String newDate = request.getParameter("newDate");
		System.out.println("NEW DEADLINE: " + newDate);
		EventService.updateDeadline(Integer.parseInt(eventId), newDate);

		request.getRequestDispatcher("/HomeAdminServlet").forward(request, response);;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UPDATE DEADLINE SERVLET post");
		String eventId = request.getParameter(Event.COL_EVENTID);
		String newDate = request.getParameter("newDate");
		System.out.println("NEW DEADLINE: " + newDate);
		EventService.updateDeadline(Integer.parseInt(eventId), newDate);

		request.getRequestDispatcher("/HomeAdminServlet").forward(request, response);;
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}




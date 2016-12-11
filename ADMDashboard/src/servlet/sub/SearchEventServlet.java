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

/**
 * Servlet implementation class SearchEventServlet
 */
public class SearchEventServlet{

	public static final String URL = "/SearchEventServlet";
	
	private SearchEventServlet(){}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("SEARCHEVENT SERVLET");
		
		String searchString = request.getParameter("search");
		ArrayList<Event> eventList = EventService.searchEvents(searchString);
		
		PrintWriter pw = response.getWriter();
		
		Gson gson = new Gson();
		String json = gson.toJson(eventList);

		pw.write(json);
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

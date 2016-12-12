package servlet.sub;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Event;
import model.Org;
import service.EventService;
import servlet.MasterServlet;

/**
 * Servlet implementation class ViewEventsServlet
 */
public class ViewEventsServlet{

	public static final String URL = "/ViewEventsServlet";

	private ViewEventsServlet(){}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("VIEWEVENTS SERVLET");
		
		String orgcode = request.getParameter(Org.COL_ORGCODE);
		
		PrintWriter pw = response.getWriter();
		Gson gson = null;
		
		String error = null;
		
		//for overdue deadlines
		ArrayList<Event> overdueDeadlines = EventService.getOverdueDeadlines(orgcode);
		ArrayList<String> overdueList = EventService.convertDates(overdueDeadlines);
		if(overdueDeadlines.size() == 0)
			error = "No overdue deadlines.";
		System.out.println(overdueDeadlines);
		
		gson = new Gson();
		String overdueEventsJson = gson.toJson(overdueDeadlines);
		gson = new Gson();
		String overdueDatesJson = gson.toJson(overdueList);
		gson = new Gson();
		String noOverdueStringJson = gson.toJson(error);

		/////////////////////////  for due in 1 week /////////////////////////
		ArrayList<Event> pendingPostActList1 = EventService.getUpcomingDeadlines(orgcode, 0, 8);
		ArrayList<String> due1DateList = EventService.convertDates( pendingPostActList1);
		if(pendingPostActList1.size() == 0)
			error = "No deadlines due in 1 week.";
		
		gson = new Gson();
		String due1weekEventsJson = gson.toJson(pendingPostActList1);
		gson = new Gson();
		String due1weekDatesJson = gson.toJson(due1DateList);
		gson = new Gson();
		String nodue1weekStringJson = gson.toJson(error);

		/////////////////////////  for due in 2 weeks /////////////////////////
		ArrayList<Event> pendingPostActList2 = EventService.getUpcomingDeadlines(orgcode, 7, 14);
		ArrayList<String> due2DateList = EventService.convertDates( pendingPostActList2);
		if(pendingPostActList2.size() == 0)
			error = "No deadlines due in 2 weeks.";
		
		gson = new Gson();
		String due2weekEventsJson = gson.toJson(pendingPostActList2);
		gson = new Gson();
		String due2weekDatesJson = gson.toJson(due2DateList);
		gson = new Gson();
		String nodue2weekStringJson = gson.toJson(error);

		/////////////////////////  other deadlines /////////////////////////
		ArrayList<Event> otherDeadlines = EventService.getOtherDeadlines(orgcode);
		ArrayList<String> otherDueDateList = EventService.convertDates(otherDeadlines);
		if(otherDeadlines.size() == 0)
			error = "No deadlines due in 2 weeks.";

		gson = new Gson();
		String dueNextEventsJson = gson.toJson(otherDeadlines);
		gson = new Gson();
		String dueNextDatesJson = gson.toJson(otherDueDateList);
		gson = new Gson();
		String nodueNextStringJson = gson.toJson(error);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String allJson = "[" + overdueEventsJson + ","		// 0
							+ overdueDatesJson + ","		// 1
							+ noOverdueStringJson + ","		// 2
							+ due1weekEventsJson + ","		// 3
							+ due1weekDatesJson + ","		// 4
							+ nodue1weekStringJson + ","	// 5
							+ due2weekEventsJson + ","		// 6
							+ due2weekDatesJson + ","		// 7
							+ nodue2weekStringJson + ","	// 8
							+ dueNextEventsJson + ","		// 9
							+ dueNextDatesJson + ","		// 10
							+ nodueNextStringJson + "]";	// 11
		pw.write(allJson);
		
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

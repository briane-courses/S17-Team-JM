package servlet.sub;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Event;
import model.Org;
import model.User;
import model.UserType;
import service.EventService;
import service.OrgService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class HomeAdminServlet
 */
public class HomeAdminServlet{

	public static final String URL = "/HomeAdminServlet";

	private HomeAdminServlet(){}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("HOME ADMIN SERVLET get");

		User user = null;
		String logoURL = "";

		// get cookies for userID; assume admin exists
		Cookie[] cookies = request.getCookies();

		System.out.println("cookies: " + cookies.length);

		for(int i = 0; i < cookies.length; i ++) {
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				user = UserService.searchUser(Integer.parseInt(cookies[i].getValue()));
			}
			else if(cookies[i].getName().equals("logoURL")) {
				logoURL = cookies[i].getValue();
			}
		}

		// for side bar menu
		request.getSession().setAttribute(Org.COL_LOGOURL, logoURL);				// logo
		request.getSession().setAttribute(Org.COL_ORGCODE, UserType.ADMIN + "");	// name
		System.out.println(user.getEmail());
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());			// email


		//get NotPassed and Pending Events
		ArrayList<Event> eventList = EventService.getAllPendingNotPassedEvents();
		request.getSession().setAttribute("eventList", eventList);
		ArrayList<String> eventStrDates = EventService.convertDates(eventList);
		request.getSession().setAttribute("eventStrDates", eventStrDates);
		ArrayList<Integer> deadlineType = EventService.setDeadlineType(eventList);
		request.getSession().setAttribute("deadlineType", deadlineType);
		request.getRequestDispatcher("/homepage_admin.jsp").forward(request, response);

	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("HOME ADMIN SERVLET post");



		/*//ajax
		PrintWriter pw = response.getWriter();
		Gson gson = null;
		gson = new Gson();
		String eventsJson = gson.toJson(eventList);
		gson = new Gson();
		String datesJson = gson.toJson(eventStrDates);
		gson = new Gson();
		String deadlineTypeJson = gson.toJson(deadlineType);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String allJson = "[" + eventsJson + ","		// 0
				+ datesJson + ","		// 1
				+ deadlineTypeJson 		// 2
				+ "]";					// 3
		pw.write(allJson);

*/
		doGet(request, response);

	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

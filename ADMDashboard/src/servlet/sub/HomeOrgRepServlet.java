package servlet.sub;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import model.EventType;
import model.Org;
import model.Requirement;
import model.User;
import service.EventService;
import service.EventTypeService;
import service.OrgService;
import service.RequirementService;
import service.UserService;
import servlet.MasterServlet;
import utils.session.SessionManager;

/**
 * Servlet implementation class HomeOrgRepServlet
 */
public class HomeOrgRepServlet {
	
	public static final String URL = "/HomeOrgRepServlet";
	
    private HomeOrgRepServlet() { }

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HOMEORGREP SERVLET");
		
		/*
		Org org = null;
		User user = null;
		String logoURL = "";
		
		// get cookies for userID; assume orgrep exists
		Cookie[] cookies = request.getCookies();
		
		System.out.println("cookies: " + cookies.length);
		
		for(int i = 0; i < cookies.length; i ++) {
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				org = OrgService.searchOrg(Integer.parseInt(cookies[i].getValue()));
				user = UserService.searchUser(Integer.parseInt(cookies[i].getValue()));
			}
			else if(cookies[i].getName().equals("logoURL")) {
				logoURL = cookies[i].getValue();
			}
		}
		*/
		System.out.println("logoURL : " + SessionManager.getAttribute(request, "logoURL"));
		System.out.println("orgcode : " + SessionManager.getAttribute(request, User.COL_ORGCODE));
		System.out.println("email : " + SessionManager.getAttribute(request, User.COL_EMAIL));
		
		// for side bar menu
		/*
		request.getSession().setAttribute(Org.COL_LOGOURL, org.getLogoURL());	// logo
		request.getSession().setAttribute(Org.COL_ORGCODE, org.getOrgcode());	// orgcode
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());		// email
		*/
		// these are set at log in -Kenji
		
		//for overdue deadlines
		ArrayList<Event> overdueDeadlines = EventService.getOverdueDeadlines("imes"); //replace with orgcode
		SessionManager.setAttribute(request, "overdueDeadlines", overdueDeadlines);
		ArrayList<String> overdueList = EventService.convertDates( overdueDeadlines);
		SessionManager.setAttribute(request, "overdueList", overdueList);
		if(overdueDeadlines.size() == 0)
			SessionManager.setAttribute(request, "noOverdueDeadlines", "No overdue deadlines.");
		System.out.println(overdueDeadlines);
		
		// for due in 2 weeks 
		ArrayList<Event> pendingPostActList2 = EventService.getUpcomingDeadlines("imes",7, 14); //replace with orgcode
		SessionManager.setAttribute(request, "pendingPostActList2", pendingPostActList2);
		ArrayList<String> due2DateList = EventService.convertDates( pendingPostActList2);
		SessionManager.setAttribute(request, "due2DateList", due2DateList);
		if(pendingPostActList2.size() == 0)
			SessionManager.setAttribute(request, "noPendingPostActList2", "No deadlines due in 2 weeks.");
		
		//for due in 1 week
		ArrayList<Event> pendingPostActList1 = EventService.getUpcomingDeadlines("imes", 0, 8); //replace with orgcode
		SessionManager.setAttribute(request, "pendingPostActList1", pendingPostActList1);		
		ArrayList<String> due1DateList = EventService.convertDates( pendingPostActList1);
		SessionManager.setAttribute(request, "due1DateList", due1DateList);
		if(pendingPostActList1.size() == 0)
			SessionManager.setAttribute(request, "noPendingPostActList1", "No deadlines due in 1 week.");
		
		//Other Deadlines
		ArrayList<Event> otherDeadlines = EventService.getOtherDeadlines("imes"); //replace with orgcode
		SessionManager.setAttribute(request, "otherDeadlines", otherDeadlines);		
		ArrayList<String> otherDueDateList = EventService.convertDates(otherDeadlines);
		SessionManager.setAttribute(request, "otherDueDateList", otherDueDateList);
		
		
		// for activity drop down
		ArrayList<EventType> eventTypeList = EventTypeService.getAllEventType();
		SessionManager.setAttribute(request, "eventTypeList", eventTypeList);
		
		// for initial requirements of activity (will change using ajax in RequirementsServlet)
		ArrayList<Requirement> reqList = RequirementService.getRequirements(eventTypeList.get(0).getEventtypeID());
		SessionManager.setAttribute(request, "reqList", reqList);
			
		// send request to jsp
		request.getRequestDispatcher("/homepage_orgrep.jsp").forward(request, response);
		// response.sendRedirect("homepage_orgrep.jsp");
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}

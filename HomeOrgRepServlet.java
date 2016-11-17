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

/**
 * Servlet implementation class HomeOrgRepServlet
 */
public class HomeOrgRepServlet {
	
	public static final String URL = "/HomeOrgRepServlet";
	
    private HomeOrgRepServlet() { }

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HOMEORGREP SERVLET GET");
		
		Org org = null;
		User user = null;
		
		// get cookies for userID; assume orgrep exists
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i ++) {
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				org = OrgService.searchOrg(Integer.parseInt(cookies[i].getValue()));
				user = UserService.searchUser(Integer.parseInt(cookies[i].getValue()));
			}
		}
		
		// for side bar menu
		request.getSession().setAttribute(Org.COL_LOGOURL, org.getLogoURL());	// logo
		request.getSession().setAttribute(Org.COL_ORGCODE, org.getOrgcode());	// orgcode
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());		// email
		
		//for overdue deadlines
		ArrayList<Event> overdueDeadlines = EventService.getOverdueDeadlines(org.getOrgcode()); //replace with orgcode
		request.getSession().setAttribute("overdueDeadlines", overdueDeadlines);
		ArrayList<String> overdueList = EventService.convertDates( overdueDeadlines);
		request.getSession().setAttribute("overdueList", overdueList);
		if(overdueDeadlines.size() == 0)
			request.getSession().setAttribute("noOverdueDeadlines", "No overdue deadlines.");
		System.out.println(overdueDeadlines);
		
		// for due in 2 weeks 
		ArrayList<Event> pendingPostActList2 = EventService.getUpcomingDeadlines(org.getOrgcode(),7, 14); //replace with orgcode
		request.getSession().setAttribute("pendingPostActList2", pendingPostActList2);
		ArrayList<String> due2DateList = EventService.convertDates( pendingPostActList2);
		request.getSession().setAttribute("due2DateList", due2DateList);
		if(pendingPostActList2.size() == 0)
			request.getSession().setAttribute("noPendingPostActList2", "No deadlines due in 2 weeks.");
		
		//for due in 1 week
		ArrayList<Event> pendingPostActList1 = EventService.getUpcomingDeadlines(org.getOrgcode(), 0, 8); //replace with orgcode
		request.getSession().setAttribute("pendingPostActList1", pendingPostActList1);		
		ArrayList<String> due1DateList = EventService.convertDates( pendingPostActList1);
		request.getSession().setAttribute("due1DateList", due1DateList);
		if(pendingPostActList1.size() == 0)
			request.getSession().setAttribute("noPendingPostActList1", "No deadlines due in 1 week.");
		
		//Other Deadlines
		ArrayList<Event> otherDeadlines = EventService.getOtherDeadlines(org.getOrgcode()); //replace with orgcode
		request.getSession().setAttribute("otherDeadlines", otherDeadlines);		
		ArrayList<String> otherDueDateList = EventService.convertDates(otherDeadlines);
		request.getSession().setAttribute("otherDueDateList", otherDueDateList);
		if(otherDeadlines.size() == 0)
			request.getSession().setAttribute("noOtherDeadlines", "No other deadlines.");
		
		// for activity drop down
		ArrayList<EventType> eventTypeList = EventTypeService.getAllEventType();
		request.getSession().setAttribute("eventTypeList", eventTypeList);
		
		// for initial requirements of activity (will change using ajax in RequirementsServlet)
		ArrayList<Requirement> reqList = RequirementService.getRequirements(eventTypeList.get(0).getEventtypeID());
		request.getSession().setAttribute("reqList", reqList);
			
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

	/*for(int i = 0; i< pendingPostActList.size() ; i++){	
	System.out.println(pendingPostActList.get(i).getEventname());
	System.out.println(pendingPostActList.get(i).getPostact_deadline());	
	}*/

}

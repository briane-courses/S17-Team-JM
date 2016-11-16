package servlet.sub;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EventType;
import model.Org;
import model.Requirement;
import model.User;
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

		System.out.println("HOMEORGREP SERVLET");
		
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
		
		// FUCKING TEMPORARY
//		org = OrgService.searchOrg(1);
//		user = UserService.searchUser(1);
		
		// for side bar menu
		request.getSession().setAttribute(Org.COL_LOGOURL, org.getLogoURL());	// logo
		request.getSession().setAttribute(Org.COL_ORGCODE, org.getOrgcode());	// orgcode
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());		// email
		
		// for upcoming deadlines
		
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


}

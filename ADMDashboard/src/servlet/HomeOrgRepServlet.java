package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
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

/**
 * Servlet implementation class HomeOrgRepServlet
 */
@WebServlet("/HomeOrgRepServlet")
public class HomeOrgRepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeOrgRepServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Org org = null;
		User user = null;
		
		// get cookies for userID; assume user exists
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
		
		// for upcoming deadlines
		
		// for activity drop down
		ArrayList<EventType> eventTypeList = EventTypeService.getAllEventType();
		request.getSession().setAttribute("eventTypeList", eventTypeList);
		
		// for initial requirements of activity
		ArrayList<Requirement> reqList = RequirementService.getRequirements(eventTypeList.get(0).getEventtypeID());
		request.getSession().setAttribute("reqList", reqList);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

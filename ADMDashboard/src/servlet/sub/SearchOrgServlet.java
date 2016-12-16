package servlet.sub;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Org;
import model.User;
import model.UserType;
import service.OrgService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class SearchOrgServlet
 */
public class SearchOrgServlet{

	public static final String URL = "/SearchOrgServlet";
	
	private SearchOrgServlet(){}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SEARCHORG SERVLET");
		
		String searchString = request.getParameter("search");
		
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
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());			// email
		
		// for organization list
		ArrayList<Org> orgList = OrgService.searchOrgs(searchString);
		System.out.println("orgList = " + orgList.size());
		
		// if no org found
		if(orgList.size() == 0) {
			String noOrgFound = "0 results";
			request.getSession().setAttribute("noOrgFound", noOrgFound);
			request.getSession().setAttribute("orgList", null);
		} else {
			request.getSession().setAttribute("noOrgFound", "");
			request.getSession().setAttribute("orgList", orgList);
		}
		
		// send request to jsp
		request.getRequestDispatcher("/orglist_admin.jsp").forward(request, response);
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

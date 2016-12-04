package servlet.sub;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Org;
import model.User;
import service.OrgService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class OrglistAdminServlet
 */
public class OrglistAdminServlet {
	
	public static final String URL = "/OrglistAdminServlet";
	
    private OrglistAdminServlet() { }
    
    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ORGLISTADMIN SERVLET");
    	
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
		
		System.out.println("logoURL : " + logoURL);
		System.out.println("orgcode : " + org.getOrgcode());
		System.out.println("email : " + user.getEmail());
		
		// for side bar menu
		request.getSession().setAttribute(Org.COL_LOGOURL, logoURL);	// logo
		request.getSession().setAttribute(Org.COL_ORGCODE, org.getOrgcode());	// orgcode
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());		// email
		
		// for organization list
		ArrayList<Org> orgList = OrgService.getAllOrgs();
		request.getSession().setAttribute("orgList", orgList);
		
		// send request to jsp
		request.getRequestDispatcher("/orglist_admin.jsp").forward(request, response);
    	
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

package servlet.sub;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Org;
import model.User;
import model.UserType;
import service.UserService;
import servlet.MasterServlet;
import servlet.ajax.pull.AjaxCalendarPull;

/**
 * Servlet implementation class CalendarOrgRepServlet
 */
public class CalendarOrgRepServlet{

	public static final String URL = "/CalendarOrgRepServlet";
	
	private CalendarOrgRepServlet(){}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		doPost(request, response);
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = null;
		Cookie[] cookies = request.getCookies();
		String logoURL = "";
		
		for(int i = 0; i < cookies.length; i ++) {
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				user = UserService.searchUser(Integer.parseInt(cookies[i].getValue()));
			}
			else if(cookies[i].getName().equals("logoURL")) {
				logoURL = cookies[i].getValue();
			}
		}
		
		System.out.println("logoURL : " + logoURL);
		System.out.println("orgcode : " + user.getOrgcode());
		System.out.println("email : " + user.getEmail());

		request.getSession().setAttribute(Org.COL_LOGOURL, logoURL);	// logo
		request.getSession().setAttribute(Org.COL_ORGCODE, user.getOrgcode());	// orgcode
		request.getSession().setAttribute(User.COL_EMAIL, user.getEmail());		// email
		
		if(user.getUserType().toString().equals(UserType.ADMIN + ""))
			request.getRequestDispatcher("/admincalendar.jsp").forward(request, response);
		else if(user.getUserType().toString().equals(UserType.ORGREP + ""))
			request.getRequestDispatcher("/calendar.jsp").forward(request, response);
		else
			System.out.println("FAIL");
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

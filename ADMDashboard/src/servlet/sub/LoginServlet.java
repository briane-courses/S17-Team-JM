package servlet.sub;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserType;
import service.UserService;
import servlet.AbstractServlet;
import servlet.MasterServlet;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet  extends AbstractServlet{

	public static final String URL = "/LoginServlet";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LOGINSERVLET POST");
		
		// retrieve attributes from index.jsp
		String email = request.getParameter(User.COL_EMAIL);
		
		// match attributes to the db
		User user = UserService.searchUser(email);
		System.out.println(user);
		
		// if user exists, go to admin/orgrep servlet
		if(user != null) {
			
			// CREATE COOKIE
			Cookie userIDcookie = new Cookie(User.COL_IDNUMBER, user.getUserID() + "");
			userIDcookie.setMaxAge(60 * 60 * 24); 	// set age of cookie to 1 day
			response.addCookie(userIDcookie); 		// add cookie to list of cookies
			
			// REDIRECT
			if(user.getUserType().toString().equals(UserType.ORGREP + "")) {
				request.getRequestDispatcher("HomeOrgRepServlet").forward(request, response);
			} else if(user.getUserType().toString().equals(UserType.ADMIN + "")) {
				request.getRequestDispatcher("HomeAdminServlet").forward(request, response); 
			}
			
		} else {
			// if user does not exist, make error via ajax
			
			System.out.println("USER UNAUTHORIZED");
			
			// for now, let's go back to start servlet
			request.getRequestDispatcher("sample.jsp").forward(request, response);
			
		}
	}

	@Override
	public String getUrl() {
		return URL;
	}

}

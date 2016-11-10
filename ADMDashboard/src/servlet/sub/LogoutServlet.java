package servlet.sub;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.AbstractServlet;
import servlet.MasterServlet;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet  extends AbstractServlet{
	
	public static final String URL = "/LogoutServlet";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LOGOUT SERVLET");

		// get cookies
		Cookie[] cookies = request.getCookies();

		// if cookies exist, kill them
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setValue("");
	            cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				System.out.println("Killing Cookie");
			}
		}
		
		// kill session
		request.getSession().invalidate();
		
		// go back to login page
		request.getRequestDispatcher("StartServlet").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public String getUrl() {
		return URL;
	}
	

}

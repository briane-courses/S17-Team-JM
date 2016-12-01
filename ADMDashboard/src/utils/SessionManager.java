package utils;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;

public class SessionManager {
	
	// add to login servlet
	public static User beginSession(HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		User user;
		String email = request.getParameter(User.COL_EMAIL);
		String logoURL = request.getParameter("logoURL");
		// check if valid
		if(email == null || logoURL == null)
			return null;
		
		// get user
		user = UserService.searchUser(email);
		if(user == null)
			return null;
		
		// save important details to session
		session.setAttribute(User.COL_IDNUMBER, user.getUserID());
		session.setAttribute("logoURL", logoURL);
		session.setAttribute(User.COL_ORGCODE, user.getOrgcode());
		
		// save session id (for security we will only save the session id and not USERID or others)
		response.addCookie(new Cookie("SSID", session.getId()));		// add cookie to list of cookies	
		
		return user;
	}
	
	// add to logout servlet
	public static void endSession(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies;
		HttpSession session = request.getSession();
		
		// invalidate session
		session.invalidate();
		
		// get cookies
		cookies = request.getCookies();

		// kill cookies
		if (cookies != null) {
			for (Cookie c : cookies) {
				c.setValue("");
				c.setPath("/");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
	}
	
	// checks if the current session stored in the client is equal to the session in server
	// please use at the start of all webpages to check whether a user is logged in	/ out
	//        or user is using an old session
	public static boolean isSessionValid(HttpServletRequest request, String name){
		Cookie[] cookies;
		HttpSession session = request.getSession();
		cookies = request.getCookies();
		
		if(session == null || cookies == null)
			return false;
		
		for(Cookie c: cookies)
		if(c.getName().equals("SSID")) 
			if(c.getValue().equals(session.getId()))
				 return true;
		return false;
	}
	
	// get attribute
	public static Object getAttribute(HttpServletRequest request, String name){
		Cookie[] cookies;

		cookies = request.getCookies();
		
		for(Cookie c: cookies)
		if(c.getName().equals(request)) 
			if(!c.getValue().equals(""))
				return c.getValue();

		if(request.getSession() != null)
			return request.getSession().getAttribute(name);
		return null;
	}
	
	// get all attribute names
	public static Enumeration<String> getAttributeNames(HttpServletRequest request){
		if(request.getSession() != null)
			return request.getSession().getAttributeNames();
		return null;
	}
	
	// to add attributes
	public static void setAttribute(HttpServletRequest request, String name, Object attribute){
		if(request.getSession() != null)
			request.getSession().setAttribute(name, attribute);
	}
	
}


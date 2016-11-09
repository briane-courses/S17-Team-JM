package servlet;

import servlet.sub.*;

public enum ServletUrl {

	CalendarOrgRep(CalendarOrgRepServlet.URL, 
			new CalendarOrgRepServlet()),
	HomeAdmin(HomeAdminServlet.URL, 
			new HomeAdminServlet()),
	HomeOrgRep(HomeOrgRepServlet.URL, 
			new HomeOrgRepServlet()),
	Login(LoginServlet.URL, 
			new LoginServlet()),
	Logout(LogoutServlet.URL, 
			new LogoutServlet()),
	Requirements(RequirementsServlet.URL, 
			new RequirementsServlet()),
	Start(StartServlet.URL, 
			new StartServlet());
	
	/* ---- Do not change beyond this point ---- */
	
	private String url;
	private AbstractServlet servlet;
	
	ServletUrl(String url, AbstractServlet servlet){
		this.url = url;
		this.servlet = servlet;
	}
	
	public static AbstractServlet getServlet(String url) {
		for (ServletUrl s : ServletUrl.values()) {
		      if(s.url.equalsIgnoreCase(url))
		    	  return s.servlet;
		  }
		return null;
	}
}

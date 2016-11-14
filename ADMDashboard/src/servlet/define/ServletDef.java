package servlet.define;

import servlet.AbstractServlet;
import servlet.sub.*;

public enum ServletDef {
	
	AjaxUserCalendar(new AjaxUserCalendarServlet()),
	CalendarOrgRep(new CalendarOrgRepServlet()),
	HomeAdmin(new HomeAdminServlet()),
	HomeOrgRep(new HomeOrgRepServlet()),
	Login(new LoginServlet()),
	Logout(new LogoutServlet()),
	Requirements(new RequirementsServlet()),
	Start(new StartServlet());
			
	/* ---- Do not change beyond this point ---- */
	
	private AbstractServlet servlet;
	
	ServletDef(AbstractServlet servlet){
		this.servlet = servlet;
	}
	
	public String getUrl(){return servlet.getUrl(); }
	public AbstractServlet getServlet(){ return servlet; }
	
	public static AbstractServlet searchServlet(String url) {
		for (ServletDef s : ServletDef.values()) {
		      if(s.getUrl().equalsIgnoreCase(url))
		    	  return s.servlet;
		  }
		return null;
	}
	
}

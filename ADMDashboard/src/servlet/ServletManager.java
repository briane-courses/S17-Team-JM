package servlet;

import java.util.HashMap;

import servlet.define.ServletDef;

public class ServletManager {

	private static ServletManager instance = null;
	
	private HashMap<String, AbstractServlet> servletMap;
	
	private ServletManager(){
		servletMap = new HashMap<>();
	}
	
	public static synchronized ServletManager getInstance(){
		if(instance == null)
			instance = new ServletManager();
		return instance;
	}
	
	public AbstractServlet getServlet(String url){
		AbstractServlet result;
		if((result = servletMap.get(url)) == null){
			if((result = ServletDef.searchServlet(url)) != null)
				servletMap.put(url, result);
			else
				System.err.println("ERROR: Servlet URL: '"+url+"' does not exist!");
		}
		return result;
	}
	
}

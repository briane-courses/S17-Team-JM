package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.sub.*;

@WebServlet(urlPatterns = {
		CalendarOrgRepServlet.URL,
		HomeAdminServlet.URL,
		HomeOrgRepServlet.URL,
		LoginServlet.URL,
		LogoutServlet.URL,
		RequirementsServlet.URL,
		StartServlet.URL
		})
		
public class MasterServlet extends HttpServlet {
	
	public static int TYPE_GET = 0;
	public static int TYPE_POST = 1;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2764423969467803721L;

	public MasterServlet() { 
		super(); 
		}

		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response, TYPE_GET);
		}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response, TYPE_POST);
		}
		
	private void process(HttpServletRequest request, HttpServletResponse response, int type) {
		try {
			AbstractServlet servlet = ServletManager.getInstance().getServlet(request.getServletPath());
			if(servlet != null){
				servlet.process(request, response, type);
			} else {
				System.err.println("ERROR: Servlet not implemented!");
			}
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

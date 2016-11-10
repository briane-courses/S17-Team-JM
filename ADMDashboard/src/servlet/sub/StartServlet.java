package servlet.sub;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.AbstractServlet;
import servlet.MasterServlet;

/**
 * Servlet implementation class StartServlet
 */
public class StartServlet extends AbstractServlet{

	public static final String URL = "/StartServlet";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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

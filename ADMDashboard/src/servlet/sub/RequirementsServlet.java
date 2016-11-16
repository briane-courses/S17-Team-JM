package servlet.sub;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.EventType;
import model.Requirement;
import service.RequirementService;
import servlet.MasterServlet;

/**
 * Servlet implementation class RequirementsServlet
 */
public class RequirementsServlet {

	public static final String URL = "/RequirementsServlet";
	
	private RequirementsServlet() { }

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// for ajax
		PrintWriter pw = response.getWriter();

		int eventTypeID = Integer.parseInt(request.getParameter(EventType.COL_EVENTTYPEID));
		ArrayList<Requirement> reqList = RequirementService.getRequirements(eventTypeID);
				
		Gson gson = new Gson();
		String json = gson.toJson(reqList);
				
		pw.write(json);
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

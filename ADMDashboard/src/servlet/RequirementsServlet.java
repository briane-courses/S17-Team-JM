package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.EventType;
import model.Requirement;
import service.RequirementService;

/**
 * Servlet implementation class RequirementsServlet
 */
@WebServlet("/RequirementsServlet")
public class RequirementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequirementsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// for ajax
		PrintWriter pw = response.getWriter();

		int eventTypeID = Integer.parseInt(request.getParameter(EventType.COL_EVENTTYPEID));
		ArrayList<Requirement> reqList = RequirementService.getRequirements(eventTypeID);
		
		Gson gson = new Gson();
		String json = gson.toJson(reqList);
		
		pw.write(json);
	}

}

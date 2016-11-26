package servlet.sub;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.MasterServlet;
import utils.session.SessionManager;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet {

	public static final String URL = "/LogoutServlet";

	public LogoutServlet() {
	}

	private static void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LOGOUT SERVLET");

		// kill session
		SessionManager.endSession(request, response);
		request.getRequestDispatcher("StartServlet").forward(request, response);

		// go back to login page
		/*
		if (cookies == null) {
			System.out.println("returning... if");
			request.getRequestDispatcher("StartServlet").forward(request, response);
			return;
		} else {
			System.out.println("returning...");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		*/

	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type)
			throws ServletException, IOException {
		if (type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}

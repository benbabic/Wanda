package fr.irit.wanda.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.irit.wanda.security.SecurityManager;

/**
 * Servlet implementation class adresse
 */
@WebServlet("/Test")
public class ANAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ANAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		SecurityManager securityManager = SecurityManager.getInstance();
		securityManager.getMonitor().logConnectionAttempt(request);

		if (action != null) {
			if (action.equals("grant")) {
				System.out.println(action);
			} else if (action.equals("ungrant")) {
				System.out.println(action);
			} else if (action.equals("list")) {
				System.out.println(action);
			} else if (action.equals("download")) {
				System.out.println(action);
			} else {
				response.setContentType("text/html");
				response.getWriter().println("Unknown Command.");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}

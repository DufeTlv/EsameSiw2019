package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DammiImpostazioni
 */
@WebServlet("/DammiImpostazioni")
public class DammiImpostazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getSession().setAttribute("username", request.getParameter("username"));
		
		if(req.getSession().getAttribute("username") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("loginfailed.html");
			rd.forward(req, resp);
		}else {
			
			//getServletContext().setAttribute("username", getServletContext().getAttribute("username"));
			req.getSession().setAttribute("username", req.getSession().getAttribute("username"));
			
			RequestDispatcher rd = req.getRequestDispatcher("settings.jsp");
			rd.forward(req, resp);
		}
	}

}

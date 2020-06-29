package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DammiProfilo
 */
@WebServlet("/DammiProfilo")
public class DammiProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(req.getSession().getAttribute("username") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("loginfailed.html");
			rd.forward(req, resp);
		}else {
			req.getSession().setAttribute("username", req.getSession().getAttribute("username"));
			
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, resp);
		}
	}

}

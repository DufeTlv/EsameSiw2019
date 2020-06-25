package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DammiHome
 */
@WebServlet("/DammiHome")
public class DammiHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		//System.out.println(req.getSession().getAttribute("username"));
		req.getSession().setAttribute("username", req.getSession().getAttribute("username"));
		
		RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
	}

}

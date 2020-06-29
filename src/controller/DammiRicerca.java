package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DammiRicerca
 */
@WebServlet("/DammiRicerca")
public class DammiRicerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("username") != null)
			request.getSession().setAttribute("username", request.getSession().getAttribute("username"));
		
		//getServletContext().setAttribute("username", getServletContext().getAttribute("username"));
		
		RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
		rd.forward(request, response);
		
	}

}

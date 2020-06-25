package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import persistence.DatabaseManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Home")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println(username);
		
		/*DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		AccountDao aDao = factory.getAccountDAO();*/
		Account a = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail(username);
		
		if(a != null && a.getPassword().equals(password)) {
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("id", a.getId());
			//getServletContext().setAttribute("username", username);
			//getServletContext().setAttribute("id", a.getId());
			
			RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
			rd.forward(req, resp);
			
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("loginfailed.html");
			rd.forward(req, resp);
		}
		
	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import persistence.DAOFactory;
import persistence.DatabaseManager;
import persistence.dao.AccountDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println(username);
		
		/*DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		AccountDao aDao = factory.getAccountDAO();*/
		Account a = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail(username);
		
		if (a == null) {
			RequestDispatcher rd = req.getRequestDispatcher("loginfailed.html");
			rd.forward(req, resp);
		}else {
			System.out.println(a.getCognome());
			if (a.getPassword().equals(password)) {
				req.getSession().setAttribute("username", username);
				req.getSession().setAttribute("id", a.getId());
				req.setAttribute("account", a);
				RequestDispatcher rd = req.getRequestDispatcher("logged.jsp");
				rd.forward(req, resp);
			}else {
				RequestDispatcher rd = req.getRequestDispatcher("loginfailed.html");
				rd.forward(req, resp);
			}
		}
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*String username = (String) req.getSession().getAttribute("username");
		String logout = null; //req.getParameter("logout");
		if (logout == null) {
			if (username != null) {
				RequestDispatcher rd = req.getRequestDispatcher("Logged.jsp");
				rd.forward(req, resp);
			}else {
				RequestDispatcher rd = req.getRequestDispatcher("Logged.jsp"); //leakyeye@luca.ps
				rd.forward(req, resp);
			}
		}else {
			if (logout.equals("true")) {
				req.getSession().setAttribute("username", null);
			}
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}*/
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}

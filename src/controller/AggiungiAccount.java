package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import persistence.AccountDaoJDBC;
import persistence.DatabaseManager;
import persistence.dao.AccountDao;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AggiungiAccount")
public class AggiungiAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			Account account = new Account();
			account.setCognome(req.getParameter("lastname"));
			account.setNome(req.getParameter("firstname"));
			
			/**Calendar cal = Calendar.getInstance();
			cal.set(1959, Calendar.MAY, 20); // // 1 marzo 1987
			Date date = cal.getTime();**/
			
			DateFormat format = new SimpleDateFormat("yyyy,MM,dd", Locale.ITALIAN);
			Date date = format.parse(req.getParameter("birthday"));
			
			System.out.println(date);
			
			account.setDataNascita(date);
			account.setSesso(req.getParameter("sex"));
			account.setIndirizzoEmail(req.getParameter("email"));
			account.setPassword(req.getParameter("password"));
			
			DatabaseManager.getInstance().getDaoFactory().getAccountDAO().save(account);
			
		}catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}		
		
		RequestDispatcher view = req.getRequestDispatcher("useradd.jsp");
		view.forward(req, resp);
	}

}

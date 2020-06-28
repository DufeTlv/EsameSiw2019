package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DatabaseManager;

/**
 * Servlet implementation class EliminaRicetta
 */
@WebServlet("/EliminaRicetta")
public class EliminaRicetta extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") != null) {
			Long rId = (Long) request.getSession().getAttribute("ricetta_id");
			Long aId = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			Long aIdR = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().getAccountId(rId);
			
			if(aIdR == aId) {
				DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().clearFromRicetta(rId);
				DatabaseManager.getInstance().getDaoFactory().getCommentoDAO().clearFromRicetta(rId);
				DatabaseManager.getInstance().getDaoFactory().getQuantitaDAO().clearFromRicetta(rId);
				DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().delete(rId);
			}
		}
	}

}

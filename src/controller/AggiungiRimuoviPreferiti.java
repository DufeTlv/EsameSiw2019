package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.Preferito;
import persistence.DatabaseManager;

/**
 * Servlet implementation class AggiungiRimuoviPreferiti
 */
@WebServlet("/AggiungiRimuoviPreferiti")
public class AggiungiRimuoviPreferiti extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonReceived = reader.readLine();

		try {
			JSONObject json = new JSONObject(jsonReceived);
			Long account_id = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			Long ricetta_id = Long.parseLong(json.getString("ricetta_id"));
			
			boolean p = (DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().findByPrimaryKeys(account_id, ricetta_id) != null);
			
			Preferito preferito = new Preferito();
			preferito.setAccount_id(account_id);
			preferito.setRicetta_id(ricetta_id);
			
			if(p) {
				DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().delete(preferito);
			}else{
				DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().save(preferito);
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("p", (p)? "true": "false");
			response.getWriter().println(jsonObject.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

}

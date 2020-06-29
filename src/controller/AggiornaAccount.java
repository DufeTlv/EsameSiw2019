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

import model.Account;
import persistence.DatabaseManager;

/**
 * Servlet implementation class AggiornaUtente
 */
@WebServlet("/AggiornaAccount")
public class AggiornaAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String jsonReceived = reader.readLine();
		
		try {
			
			JSONObject json = new JSONObject(jsonReceived);
			
			System.out.println(json.getString("account_id"));
			
			Account accBkp = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail(json.getString("account_id"));
			
			if(!json.getString("nome").isEmpty())
				accBkp.setNome(json.getString("nome"));
			if(!json.getString("cognome").isEmpty())
				accBkp.setCognome(json.getString("cognome"));
			if(!json.getString("sesso").isEmpty())
				accBkp.setSesso(json.getString("sesso"));
			
			if(!json.getString("oldpassword").isEmpty()) {
				if(json.getString("oldpassword").equals(accBkp.getPassword()) && json.getString("newpassword").equals(json.getString("confnewpassword"))) {
					accBkp.setPassword(json.getString("newpassword"));
				}else{
					throw new Exception("vecchia password errata");
				}
			}
			
			DatabaseManager.getInstance().getDaoFactory().getAccountDAO().update(accBkp);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

}

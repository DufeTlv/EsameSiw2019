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
			String action = json.getString("action");
			System.out.println(action + "add " + (action.equals("add")));
			
			Preferito preferito = new Preferito();
			preferito.setAccount_id(DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail(json.getString("account_id")).getId());
			preferito.setRicetta_id(Long.parseLong(json.getString("ricetta_id")));
			
			if(json.getString("action").equals("add")) {
				DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().save(preferito);
			}else if(json.getString("action").equals("remove")){
				System.out.println("else");
				DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().delete(preferito);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

}

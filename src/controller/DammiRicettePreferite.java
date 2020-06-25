package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Preferito;
import model.Ricetta;
import persistence.DatabaseManager;

/**
 * Servlet implementation class DammiRicettePreferite
 */
@WebServlet("/DammiRicettePreferite")
public class DammiRicettePreferite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonReceived = reader.readLine();
		//System.out.println(jsonReceived);
		
		try {
			JSONObject json = new JSONObject(jsonReceived);
			long account_id = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail(json.getString("account_id")).getId();
			List<Preferito> preferiti = DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().retrieveGradually(account_id, json.getInt("offset"));
			//Creating a JSONObject object
		    JSONObject jsonObject = new JSONObject();
		    //Creating a json array
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < preferiti.size(); i++) {
			   JSONObject record = new JSONObject();
			   //Inserting key-value pairs into the json object 
			   Ricetta ricetta = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().findByPrimaryKey(preferiti.get(i).getRicetta_id());
			   record.put("id", ricetta.getId());
			   record.put("titolo", ricetta.getTitolo());
			   record.put("difficolta", ricetta.getDifficolta());
			   record.put("tempo", ricetta.getTempo());
			   record.put("imageurl", ricetta.getImageUrl());
			   
			   if(DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().findByIdRicetta(ricetta.getId()) != null)
				   record.put("preferito", "1");
			   else
				   record.put("preferito", "0");
			  
			   array.put(record);
			}
			
			jsonObject.put("ricetta", array);
			
			response.getWriter().println(jsonObject.toString());
		
		} catch (JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}

}

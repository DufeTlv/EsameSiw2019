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
			
			Long account_id = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			
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
			   record.put("difficolta", (ricetta.getDifficolta() == 0)? "Facile" :(ricetta.getDifficolta() == 1)? "Media": "Difficile");
			   record.put("tempo", ricetta.getTempo());
			   record.put("imageurl", ricetta.getImageUrl());
			   
			   record.put("preferito", "1");
			  
			   array.put(record);
			}
			
			jsonObject.put("ricetta", array);
			//System.out.println(preferiti.size());
			response.getWriter().println(jsonObject.toString());
		
		} catch (JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}

}

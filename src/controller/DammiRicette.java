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

import model.Ricetta;
import persistence.DatabaseManager;

/**
 * Servlet implementation class DammiRicetta
 */
@WebServlet("/DammiRicette")
public class DammiRicette extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonReceived = reader.readLine();
		//System.out.println(jsonReceived);
		
		try {
			JSONObject json = new JSONObject(jsonReceived);
			
			Long account_id = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			
			List<Ricetta> ricette;
			if(json.getLong("action") == 0 )
				ricette = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().retrieveGradually(json.getLong("id"));
			else
				ricette = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().retrieveGraduallyByAccountId(account_id, json.getLong("id"));
			
			//Creating a JSONObject object
		    JSONObject jsonObject = new JSONObject();
		    //Creating a json array
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < ricette.size(); i++) {
			   JSONObject record = new JSONObject();
			   
			   record.put("id", ricette.get(i).getId());
			   record.put("titolo", ricette.get(i).getTitolo());
			   record.put("difficolta", (ricette.get(i).getDifficolta() == 0)? "Facile" :(ricette.get(i).getDifficolta() == 1)? "Media": "Difficile");
			   record.put("tempo", ricette.get(i).getTempo());
			   record.put("imageurl", ricette.get(i).getImageUrl());
			   
			   if(DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().findByPrimaryKeys(account_id, ricette.get(i).getId()) != null)
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
		//doGet(request, response);
	}

}

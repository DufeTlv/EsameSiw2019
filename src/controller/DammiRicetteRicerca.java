package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
 * Servlet implementation class DammiRicettaRicerca
 */
@WebServlet("/DammiRicetteRicerca")
public class DammiRicetteRicerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonReceived = reader.readLine();
		System.out.println(jsonReceived);
		
		try {
			JSONObject json = new JSONObject(jsonReceived);
			String s = json.getString("search");
			
			List<String> subs = new ArrayList<>();
			String subStr = "";
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) != ' ') {
					subStr += s.charAt(j);
				}
				if((s.charAt(j) == ' ' || j == s.length()-1) && subStr.length() != 0) {
					subs.add(subStr);
					subStr = "";
				}
			}			
			
			Long account_id = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			
			List<Ricetta> ricette = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().retrieveBySearch(subs);
			//Creating a JSONObject object
		    JSONObject jsonObject = new JSONObject();
		    //Creating a json array
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < ricette.size(); i++) {
			   JSONObject record = new JSONObject();
			   //Inserting key-value pairs into the json object 
			   
			   record.put("id", ricette.get(i).getId());
			   record.put("titolo", ricette.get(i).getTitolo());
			   record.put("difficolta", ( (ricette.get(i).getDifficolta() == 0)? "Facile" :(ricette.get(i).getDifficolta() == 1)? "Media": "Difficile") );
			   record.put("tempo", ricette.get(i).getTempo());
			   //record.put("procedimento", ricette.get(i).getProcedimento());
			   record.put("imageurl", ricette.get(i).getImageUrl());
			   
			   if(DatabaseManager.getInstance().getDaoFactory().getPreferitoDAO().findByPrimaryKeys(account_id, ricette.get(i).getId()) != null)
				   record.put("preferito", "1");
			   else
				   record.put("preferito", "0");
			  
			   array.put(record);
			}
			
			jsonObject.put("ricetta", array);
			
			//System.out.println(jsonObject.toString());
			
			response.getWriter().println(jsonObject.toString());			
			
		} catch (JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}

}

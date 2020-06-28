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

import model.Commento;
import persistence.DatabaseManager;

/**
 * Servlet implementation class DammiCommento
 */
@WebServlet("/DammiCommento")
public class DammiCommento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		//String jsonReceived = reader.readLine();
		//System.out.println(jsonReceived);
		
		try {
			Long aId = null;
			if(request.getSession().getAttribute("username") != null)
				aId = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			
			//JSONObject json = new JSONObject(jsonReceived);
			List<Commento> commenti = DatabaseManager.getInstance().getDaoFactory().getCommentoDAO().findAll((Long) request.getSession().getAttribute("ricetta_id"));
			//Creating a JSONObject object
		    JSONObject jsonObject = new JSONObject();
		    //Creating a json array
			JSONArray array = new JSONArray();
			for(int i = 0; i < commenti.size(); i++) {
				JSONObject record = new JSONObject();
				
				record.put("username", DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByPrimaryKey(commenti.get(i).getAccount_id()).getIndirizzoEmail());
				record.put("descrizione", commenti.get(i).getDescrizione());
				record.put("gif_url", commenti.get(i).getGifUrl());
				record.put("gif_url_still", commenti.get(i).getGifUrlStill());
								
				if(aId != null) {
					record.put("del", (commenti.get(i).getAccount_id() == aId)? "1": "0" );
					record.put("id", commenti.get(i).getId());
					//System.out.println(commenti.get(i).getId());
				}else
					record.put("del", "0");
				
				array.put(record);
			}
			
			jsonObject.put("commento", array);
			
			//System.out.println(jsonObject.toString());
			response.getWriter().println(jsonObject.toString());
			
		
		} catch (JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}

}

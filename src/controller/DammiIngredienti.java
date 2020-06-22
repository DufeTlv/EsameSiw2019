package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Ingrediente;
import model.UnitaDiMisura;
import persistence.DatabaseManager;

/**
 * Servlet implementation class DammiIngredienti
 */
@WebServlet("/DammiIngredienti")
public class DammiIngredienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Ingrediente> ingredienti = DatabaseManager.getInstance().getDaoFactory().getIngredienteDAO().findAll();
		List<UnitaDiMisura> udm = DatabaseManager.getInstance().getDaoFactory().getUnitaDiMisuraDAO().findAll();
		
		try {
			//Creating a JSONObject object
		    JSONObject jsonObject = new JSONObject();
		    //Creating a json array
			JSONArray array = new JSONArray();
			for(int i = 0; i < ingredienti.size(); i++) {
				JSONObject record = new JSONObject();
				
				record.put("id", ingredienti.get(i).getId());
				record.put("nome", ingredienti.get(i).getNome());
				//record.put("apifoodid", ingredienti.get(i).getApiFoodId());
				
				array.put(record);
			}
			
			jsonObject.put("ingrediente", array);
			
			array = new JSONArray();
			for(int i = 0; i < udm.size(); i++) {
				JSONObject record = new JSONObject();
				
				record.put("id", udm.get(i).getId());
				record.put("nome", udm.get(i).getNome());
				//record.put("apiuri", udm.get(i).getUri());
				
				array.put(record);
			}
			
			jsonObject.put("udm", array);
			
			System.out.println(jsonObject.toString());
			
			JSONObject json = new JSONObject(jsonObject.toString());
			//System.out.println("stampaprova (ingr):");
			//System.out.println(json.getJSONArray("ingrediente").getJSONObject(0).getString("nome"));
			
			response.getWriter().println(jsonObject.toString());
			
		} catch (JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}

}

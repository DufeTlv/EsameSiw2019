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
 * Servlet implementation class AggiungiCommento
 */
@WebServlet("/AggiungiCommento")
public class AggiungiCommento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("username") != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonReceived = reader.readLine();
			System.out.println(jsonReceived);
			
			try {
				JSONObject json = new JSONObject(jsonReceived);
				
				Commento commento = new Commento();
				
				commento.setAccount_id(DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail((String) request.getSession().getAttribute("username")).getId());
				commento.setRicetta_id((Long) request.getSession().getAttribute("ricetta_id"));
				commento.setDescrizione(json.getString("descrizione"));
				commento.setGifUrl(json.getString("gif_url"));
				commento.setGifUrlStill(json.getString("gif_url_still"));
				
				DatabaseManager.getInstance().getDaoFactory().getCommentoDAO().save(commento);
			
			} catch (JSONException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			}
		}
	}

}

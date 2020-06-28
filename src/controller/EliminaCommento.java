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

import model.Commento;
import persistence.DatabaseManager;

/**
 * Servlet implementation class EliminaCommento
 */
@WebServlet("/EliminaCommento")
public class EliminaCommento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if(request.getSession().getAttribute("username") != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonReceived = reader.readLine();
			System.out.println(jsonReceived);
			
			Long aId = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) request.getSession().getAttribute("username"));
			try {
				JSONObject json = new JSONObject(jsonReceived);
				
				//System.out.println(json.getLong("id"));
				//Long c_aId = DatabaseManager.getInstance().getDaoFactory().getCommentoDAO().findByPrimaryKey(json.getLong("id"))
				
				Commento commento = new Commento();
				commento.setAccount_id(aId);
				commento.setRicetta_id((long) request.getSession().getAttribute("username"));
				commento.setId(id);
				if(json.getLong("id") == aId)
					DatabaseManager.getInstance().getDaoFactory().getCommentoDAO().delete(commento);
			} catch (JSONException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			}
		}*/
	}

}

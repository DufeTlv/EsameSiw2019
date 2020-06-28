package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Quantita;
import model.Ricetta;
import persistence.DatabaseManager;
import persistence.IdBroker;

/**
 * Servlet implementation class AggiungiRicetta
 */
@WebServlet("/AggiungiModificaRicetta")
public class AggiungiModificaRicetta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("username") != null) {
			String jsonReceived = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				jsonReceived = jsonReceived + line + "\n";
				line = reader.readLine();
			}
			System.out.println(jsonReceived);
			
			if(jsonReceived.contains(System.getProperty("line.separator")))
				jsonReceived.replace(System.getProperty("line.separator"), "<br/>");
			
			//System.out.println(jsonReceived);		
			try {
				JSONObject json = new JSONObject(jsonReceived); 
				
				Ricetta ricetta = new Ricetta();
				ricetta.setTitolo(json.getString("titolo"));
				ricetta.setDifficolta(json.getInt("difficolta"));
				ricetta.setTempo(json.getString("tempopreparazione"));
				//System.out.println(json.getString("procedimento"));
				ricetta.setProcedimento(json.getString("procedimento"));
				//ricetta.setAccountId((long)req.getSession().getAttribute("id"));
				ricetta.setImageUrl(json.getString("image_url"));
				ricetta.setDeleteHash(json.getString("deletehash"));
				
				boolean b;
				Long aId = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().retrieveIdByEmail((String) req.getSession().getAttribute("username"));
				
				if(json.getString("comando").equals("0")) {
					
					ricetta.setAccountId(aId);
					ricetta.setId(DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().save(ricetta));
					b = (ricetta.getId() != (long) -1);
					System.out.println(b);
					
				}else{
					
					ricetta.setId((Long) req.getSession().getAttribute("ricetta_id"));
					Long aIdR = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().getAccountId(ricetta.getId());
					b = (aIdR == aId);
					if(b) {
						DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().update(ricetta);
						DatabaseManager.getInstance().getDaoFactory().getQuantitaDAO().clearFromRicetta(ricetta.getId());
					}
					
				}		
				
				if(b) {
					for(int i = 0; i < json.getJSONArray("ingr").length(); i++) {
						Quantita quantita = new Quantita();
						quantita.setIngrediente_id(json.getJSONArray("ingr").getJSONObject(i).getLong("ingrediente_id"));
						quantita.setValore(json.getJSONArray("ingr").getJSONObject(i).getInt("quantita"));
						quantita.setUnitaDiMisura_id(json.getJSONArray("ingr").getJSONObject(i).getInt("udm"));
						
						quantita.setRicetta_id(ricetta.getId());
						
						DatabaseManager.getInstance().getDaoFactory().getQuantitaDAO().save(quantita);
					}
					resp.getWriter().println("ricetta caricata con successo");
				}else if(json.getString("comando").equals("1"))
					resp.getWriter().println("non sei l'autore della ricetta");
				
				
			}catch (Exception e) {
				System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
				e.printStackTrace();
			}	
		}else {
			resp.getWriter().println("devi effettuare il login prima di poter procedere");
		}
		
		
		//RequestDispatcher view = req.getRequestDispatcher("home.jsp");
		//view.forward(req, resp);
	}

}

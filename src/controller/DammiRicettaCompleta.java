package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ingrediente;
import model.Quantita;
import model.Ricetta;
import model.UnitaDiMisura;
import persistence.DatabaseManager;

/**
 * Servlet implementation class DammiRicettaCompleta
 */
@WebServlet("/DammiRicettaCompleta")
public class DammiRicettaCompleta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idRicetta = Long.parseLong(request.getParameter("id_ricetta"));
		Ricetta ricetta = DatabaseManager.getInstance().getDaoFactory().getRicettaDAO().findByPrimaryKey(idRicetta);
		
		Long idAccount = DatabaseManager.getInstance().getDaoFactory().getAccountDAO().findByEmail((String) request.getSession().getAttribute("username")).getId();
		request.getSession().setAttribute("edit", (idAccount == ricetta.getAccountId()) );
		
		request.getSession().setAttribute("ricetta_id", ricetta.getId());
		request.getSession().setAttribute("titolo", ricetta.getTitolo());
		request.getSession().setAttribute("difficolta",ricetta.getDifficolta()); //(ricetta.getDifficolta() == 0)? "Facile" :(ricetta.getDifficolta() == 1)? "Media": "Difficile");
		request.getSession().setAttribute("tempo", ricetta.getTempo());
		request.getSession().setAttribute("procedimento", ricetta.getProcedimento());
		request.getSession().setAttribute("imgurl", ricetta.getImageUrl());
		
		List<Quantita> quantita = DatabaseManager.getInstance().getDaoFactory().getQuantitaDAO().findAllByRicettaId(idRicetta);
		//System.out.println(quantita.get(0).getValore());
		request.getSession().setAttribute("quantitalist", quantita);
		
		List<Ingrediente> ingredienti = new ArrayList<>();
		for(int i=0; i<quantita.size(); i++) {
			//System.out.println("quant: "+quantita.get(i).getIngrediente_id());
			
			//unitaDiMisura.add(DatabaseManager.getInstance().getDaoFactory().getUnitaDiMisuraDAO.findByPrimaryKey());
			ingredienti.add(DatabaseManager.getInstance().getDaoFactory().getIngredienteDAO().findByPrimaryKey(quantita.get(i).getIngrediente_id()));
			
			//ioSystem.out.println(ingredienti.get(ingredienti.size()-1).getNome());
		}
		request.getSession().setAttribute("ingrlist", ingredienti);
		
		List<UnitaDiMisura> unitaDiMisura = DatabaseManager.getInstance().getDaoFactory().getUnitaDiMisuraDAO().findAll();
		request.getSession().setAttribute("udmlist", unitaDiMisura);
		
		//System.out.println(idRicetta);
		
		RequestDispatcher rd = request.getRequestDispatcher("recipe.jsp");
		rd.forward(request, response);
		
	}

}

package persistence.dao;

import java.util.List;
import model.Commento;

public interface CommentoDao {

	public void save(Commento commento);					// Create
	public Commento findByPrimaryKey(Long codice);  		// Retrieve
	public List<Commento> findAll(Long codice);
	public void update(Commento commento);					// Update
	public void delete(Commento commento);					// Delete
	
	public List<Commento> retrieveGradually(Long id);		// Recupera 6 ricette dall'ultima recuperata
	public void clearFromRicetta(Long id);
}

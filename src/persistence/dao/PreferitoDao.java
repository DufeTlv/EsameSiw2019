package persistence.dao;

import java.util.List;
import model.Preferito;

public interface PreferitoDao {
	
	public void save(Preferito preferito);  							// Create
	public Preferito findByPrimaryKeys(Long aId, Long rId);  			// Retrieve
	public List<Preferito> retrieveGradually(Long codice, int offset);
	public List<Preferito> findAll();       
	public void update(Preferito preferito); 							// Update
	public void delete(Preferito preferito); 							// Delete
	
	public void clearFromRicetta(Long id);
	
}

package persistence.dao;

import java.util.List;
import model.Preferito;

public interface PreferitoDao {
	
	public void save(Preferito preferito);  				// Create
	public Preferito findByPrimaryKey(Long codice);  		// Retrieve
	public Preferito findByIdRicetta(Long codice);
	public List<Preferito> retrieveGradually(Long codice, int offset);
	public List<Preferito> findAll();       
	public void update(Preferito preferito); 				// Update
	public void delete(Preferito preferito); 				// Delete
	
}

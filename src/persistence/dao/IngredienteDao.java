package persistence.dao;

import java.util.List;
import model.Ingrediente;

public interface IngredienteDao {
	
	public void save(Ingrediente ingrediente);  			// Create
	public Ingrediente findByPrimaryKey(Long codice);  		// Retrieve
	public List<Ingrediente> findAll();       
	public void update(Ingrediente ingrediente); 			// Update
	public void delete(Ingrediente ingrediente); 			// Delete
	
	public List<Ingrediente> findAllByRicettaId(Long id);
	
}

package persistence.dao;

import java.util.List;

import model.UnitaDiMisura;

public interface UnitaDiMisuraDao {
	
	//public void save(UnitaDiMisura quantita);  			 		// Create
	//public UnitaDiMisura findByPrimaryKey(Long codice);   		// Retrieve
	public List<UnitaDiMisura> findAll();      
	//public void update(UnitaDiMisura quantita); 			 		// Update
	//public void delete(UnitaDiMisura quantita); 			 		// Delete

}

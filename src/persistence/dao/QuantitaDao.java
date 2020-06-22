package persistence.dao;

import java.util.List;

import model.Quantita;

public interface QuantitaDao {

	public void save(Quantita quantita);  			 		// Create
	public Quantita findByPrimaryKey(Long codice);   		// Retrieve
	public List<Quantita> findAll();       
	public void update(Quantita quantita); 			 		// Update
	public void delete(Quantita quantita); 			 		// Delete
	
	public List<Quantita> findAllByRicettaId(Long codice);	//
	public void clearFromRicetta(Long id);
}

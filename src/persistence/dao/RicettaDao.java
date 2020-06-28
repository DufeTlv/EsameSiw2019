package persistence.dao;

import java.util.List;

import model.Ricetta;

public interface RicettaDao {
	
	public Long save(Ricetta ricetta);  										// Create
	public Ricetta findByPrimaryKey(Long codice);   							// Retrieve
	public List<Ricetta> findAll();       
	public void update(Ricetta ricetta); 										//Update
	public void delete(Ricetta ricetta); 										//Delete
	
	public Long getAccountId(Long id);
	public Ricetta findByPrimaryKeyJoin(Long id); 								// RetrievePorzioni
	public Long getLastRicettaId(Long id);          							// Recupera l'ultima ricetta inserita
	public List<Ricetta> retrieveGradually(Long id);							// Recupera 6 ricette dall'ultima recuperata
	public List<Ricetta> retrieveBySearch(List<String> substrings); 			// Recupera tramite ricerca
	public List<Ricetta> retrieveGraduallyByAccountId(Long aId, Long rId);
}

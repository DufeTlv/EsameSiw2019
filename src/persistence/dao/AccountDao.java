package persistence.dao;

import java.util.List;
import model.Account;

public interface AccountDao {
	public void save(Account account);  							// Create
	public Account findByPrimaryKey(Long id);   					// Retrieve
	public Account findByEmail(String email);						// RetrieveByEmail
	public Long retrieveIdByEmail(String email);
	public List<Account> findAll();           						// RetrieveAll
	public void update(Account account); 							// Update
	public void delete(Account account); 							// Delete	
	public void updatePassword(Account account, String password);	// UpdatePassword
	public boolean emailPresent(String email); 
}

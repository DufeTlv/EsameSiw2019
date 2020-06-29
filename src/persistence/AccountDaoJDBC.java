package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Account;
import persistence.dao.AccountDao;

public class AccountDaoJDBC implements AccountDao {
	private DataSource dataSource;
	
	public AccountDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Account account) {
		Connection connection = this.dataSource.getConnection();
        try {
            Long id = IdBroker.getIdAccount(connection);
            account.setId(id);
            
            String insert = "insert into "
            		+ " account(id, cognome, nome, datanascita, sesso, indirizzoemail, password)"
                    + " values (?,?,?,?,?,?,?)";
                    
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong  (1, id);
            statement.setString(2, account.getCognome());
            statement.setString(3, account.getNome());
            
            //long dataN = account.getDataNascita().getTime();
            //statement.setDate(4, new java.sql.Date(dataN));
            
           // statement.setDate(4, new java.sql.Date(account.getDataNascita().getTime()));
            statement.setDate(4, null);
            
            statement.setString(5, account.getSesso());
            statement.setString(6, account.getIndirizzoEmail());
            statement.setString(7, account.getPassword());
            
            //long dataI = account.getDataNascita().getTime();
            //statement.setDate(8, new java.sql.Date(dataI));
            
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
	}

	@Override
	public Account findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		Account account = null;
		try {
			PreparedStatement statement;
			String query = "select * from account where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				account = new Account();
				account.setId(result.getLong("id"));				
				account.setNome(result.getString("nome"));
				account.setCognome(result.getString("cognome"));
				account.setSesso(result.getString("sesso"));
				account.setIndirizzoEmail(result.getString("indirizzoemail"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return account;
	}
	
	@Override
	public Account findByEmail(String email) {
		Connection connection = this.dataSource.getConnection();
		Account account = null;
		try {
			PreparedStatement statement;
			String query = "select * from account where indirizzoemail = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				account = new Account();
				account.setId(result.getLong("id"));				
				account.setNome(result.getString("nome"));
				account.setCognome(result.getString("cognome"));
				account.setSesso(result.getString("sesso"));
				account.setIndirizzoEmail(result.getString("indirizzoemail"));
				account.setPassword(result.getString("password"));
				//long dateN = result.getDate("datanascita").getTime();
				//long dateI = result.getDate("dataiscrizione").getTime();
				//account.setDataNascita(new java.util.Date(dateN));
				//account.setDataIscrizione(new java.util.Date(dateI));
			}
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return account;
	}
	
	@Override
	public Long retrieveIdByEmail(String email) {
		Connection connection = this.dataSource.getConnection();
		
		try {
			PreparedStatement statement;
			String query = "select id from account where indirizzoemail = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getLong("id");
			}
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
		return null;
	}

	@Override
	public List<Account> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Account> accountList = new LinkedList<>();
		try {
			Account account;
			PreparedStatement statement;
			String query = "select * from account";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				account = new Account();
				account.setId(result.getLong("id"));				
				account.setNome(result.getString("nome"));
				account.setCognome(result.getString("cognome"));
				
				account.setSesso(result.getString("sesso"));
				
				account.setIndirizzoEmail(result.getString("indirizzoemail"));
				// password
				long dataN = result.getDate("datanascita").getTime();
				//long dataI = result.getDate("dataiscrizione").getTime();
				account.setDataNascita(new java.util.Date(dataN));
				//account.setDataIscrizione(new java.util.Date(dataI));
				
				accountList.add(account);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return accountList;
	}

	@Override
	public void update(Account account) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update account set "
						  + " nome = ?, cognome = ?, sesso = ?,"
						  + " indirizzoemail = ?, password = ?" //, datanascita = ?
						  + " where id = ?";				
                    
            PreparedStatement statement = connection.prepareStatement(update);
            
            statement.setString(1, account.getNome());
            statement.setString(2, account.getCognome());
            statement.setString(3, account.getSesso());
            statement.setString(4, account.getIndirizzoEmail());
            statement.setString(5, account.getPassword());
            //statement.setDate(6, new java.sql.Date(account.getDataNascita().getTime()));
            
            statement.setLong  (6, account.getId());
            
            statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Account account) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM account WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, account.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void updatePassword(Account account, String password) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update account SET password = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, password);
			statement.setLong(2, account.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public boolean emailPresent(String email) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select indirizzoemail from account where indirizzoemail = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			return (result.next());
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
}

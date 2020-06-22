package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Preferito;
import persistence.dao.PreferitoDao;

public class PreferitoDaoJDBC implements PreferitoDao{
	private DataSource dataSource;
	
	public PreferitoDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Preferito preferito) {
		Connection connection = this.dataSource.getConnection();
		
		 try {
			 String insert = "insert into preferito(account_id, ricetta_id) values(?,?)";
			 
			 PreparedStatement statement = connection.prepareStatement(insert);
			 statement.setLong(1, preferito.getAccount_id());
			 statement.setLong(2, preferito.getRicetta_id());
			 
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
	public Preferito findByPrimaryKey(Long codice) {
		
		return null;
	}
	
	public Preferito findByIdRicetta(Long codice) {
		Connection connection = this.dataSource.getConnection();
		Preferito preferito = null;
		
		try {
			 String retrive = "select * from preferito where ricetta_id = ?";
			 
			 PreparedStatement statement = connection.prepareStatement(retrive);
			 statement.setLong(1, codice);
			 
			 ResultSet result = statement.executeQuery();
			 
			 if (result.next()) {
				 preferito = new Preferito();
				 preferito.setAccount_id(result.getLong("account_id"));
				 preferito.setRicetta_id(result.getLong("ricetta_id"));
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
		
		return preferito;
	}
	
	@Override
	public List<Preferito> retrieveGradually(Long codice, int offset){
		Connection connection = this.dataSource.getConnection();
        List<Preferito> preferList = new ArrayList<>();
        try {
        	Preferito preferito;
        	
	    	PreparedStatement statement;
	        String query = "select ricetta_id from preferito where account_id = ? order by account_id desc limit 6 offset ? rows";
	        statement = connection.prepareStatement(query);
            statement.setLong(1, codice);
            statement.setLong(2, offset);
			 
			ResultSet result = statement.executeQuery();
			 
			while (result.next()) {
				preferito = new Preferito();
				preferito.setRicetta_id(result.getLong("ricetta_id"));
				
				preferList.add(preferito);
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
		
		return preferList;			
	}

	@Override
	public List<Preferito> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Preferito preferito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Preferito preferito) {
		Connection connection = this.dataSource.getConnection();
		
		 try {
			 String delete = "delete from preferito where account_id = ? and ricetta_id = ?";
			 
			 PreparedStatement statement = connection.prepareStatement(delete);
			 statement.setLong(1, preferito.getAccount_id());
			 statement.setLong(2, preferito.getRicetta_id());
			 
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

}

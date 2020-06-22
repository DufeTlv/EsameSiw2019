package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commento;
import persistence.dao.CommentoDao;

public class CommentoDaoJDBC implements CommentoDao{
	private DataSource dataSource;
	
	public CommentoDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Commento commento) {
		Connection connection = this.dataSource.getConnection();
		
		try {
			Long id = IdBroker.getIdCommento(connection);
			//commento.setId(id);
			
			String insert = "insert into commento(account_id, ricetta_id, descrizione, id, gif_url, gif_url_still) values(?,?,?,?,?,?)";
			 
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, commento.getAccount_id());
			statement.setLong(2, commento.getRicetta_id());
			statement.setString(3, commento.getDescrizione());
			statement.setLong(4, id);
			statement.setString(5, commento.getGifUrl());
			statement.setString(6, commento.getGifUrlStill());
			 
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
	public Commento findByPrimaryKey(Long codice) {
		return null;
	}
	
	@Override
	public List<Commento> retrieveGradually(Long codice) {
		Connection connection = this.dataSource.getConnection();
		List<Commento> commentList = new ArrayList<>();
		try {
			Commento commento;
			
			String insert = "select account_id, descrizione, gif_url, gif_url_still from commento where ricetta_id = ? limit 6";	 
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, codice);
			 
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				 commento = new Commento();
				 commento.setAccount_id(result.getLong("account_id"));
				 //commento.setRicetta_id(result.getLong("ricetta_id"));
				 commento.setDescrizione(result.getString("descrizione"));
				 
				 commento.setGifUrl(result.getString("gif_url"));
				 commento.setGifUrlStill(result.getString("gif_url_still"));
				 
				 commentList.add(commento);
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
		return commentList;
	}

	@Override
	public List<Commento> findAll(Long codice) {
		Connection connection = this.dataSource.getConnection();
		List<Commento> commentList = new ArrayList<>();
		try {
			Commento commento;
			
			String insert = "select account_id, descrizione, gif_url, gif_url_still from commento where ricetta_id = ?";	 
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, codice);
			 
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				 commento = new Commento();
				 commento.setAccount_id(result.getLong("account_id"));
				 //commento.setRicetta_id(result.getLong("ricetta_id"));
				 commento.setDescrizione(result.getString("descrizione"));
				 
				 commento.setGifUrl(result.getString("gif_url"));
				 commento.setGifUrlStill(result.getString("gif_url_still"));
				 
				 commentList.add(commento);
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
		return commentList;
	}

	@Override
	public void update(Commento commento) {
		
	}

	@Override
	public void delete(Commento commento) {
		
	}

}

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ingrediente;
import model.Ricetta;
import persistence.dao.RicettaDao;

public class RicettaDaoJDBC implements RicettaDao{
	private DataSource dataSource;
	
	public RicettaDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
    public void save(Ricetta ricetta) {
        // TODO Auto-generated method stub
        
           Connection connection = this.dataSource.getConnection();
            try {
                Long id = IdBroker.getIdRicetta(connection);
                ricetta.setId(id);
                 
                String insert = "insert into ricetta(id, titolo, difficolta, tempo, procedimento, account_id, image_url, deletehash) values (?,?,?,?,?,?,?,?)";
                        
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setLong(1, ricetta.getId());
                statement.setString(2, ricetta.getTitolo());
                statement.setInt(3, ricetta.getDifficolta());
                statement.setString(4, ricetta.getTempo());
                statement.setString(5, ricetta.getProcedimento());
                statement.setLong(6, ricetta.getAccountId());
                statement.setString(7, ricetta.getImageUrl());
                statement.setString(8, ricetta.getDeleteHash());                
                
                statement.executeUpdate();
                //this.updatePorzioni(ricetta, connection);
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
    public Ricetta findByPrimaryKey(Long id) {
        Connection connection = this.dataSource.getConnection();
        Ricetta ricetta = null;
        try {
            PreparedStatement statement;
            String query = "select * from ricetta where id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                ricetta = new Ricetta();
                ricetta.setId(result.getLong("id"));                
                ricetta.setTitolo(result.getString("titolo"));
                ricetta.setDifficolta(result.getInt("difficolta"));  
                ricetta.setTempo(result.getString("tempo"));
                ricetta.setProcedimento(result.getString("procedimento"));
                ricetta.setAccountId(result.getLong("account_id"));
                ricetta.setImageUrl(result.getString("image_url"));
                ricetta.setDeleteHash(result.getString("deletehash"));
              
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
        return ricetta;
    }
    
    
    public Long getLastRicettaId(Long id) {
    	//select id from ricetta where account_id = '1' order by id desc limit 1
    	Connection connection = this.dataSource.getConnection();
        Long ricettaId = null;
        try {
            PreparedStatement statement;
            String query = "select id from ricetta as id where account_id = ? order by id desc limit 1";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                ricettaId = result.getLong("id");
              
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
        return ricettaId;
    }
    
    public List<Ricetta> retrieveGradually(Long id){
    	Connection connection = this.dataSource.getConnection();
        List<Ricetta> ricettaList = new ArrayList<>();
        try {
        	Ricetta ricetta;
            PreparedStatement statement;
            String query = "";
            if(id < 0) {
            	query = "select id, titolo, difficolta, tempo, image_url from ricetta order by id desc limit 6";
            	statement = connection.prepareStatement(query);
            }else {
            	query = "select id, titolo, difficolta, tempo, image_url from ricetta where id < ? order by id desc limit 6";
            	statement = connection.prepareStatement(query);
                statement.setLong(1, id);
            }
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                ricetta = new Ricetta();
                ricetta.setId(result.getLong("id"));                
                ricetta.setTitolo(result.getString("titolo"));
                ricetta.setDifficolta(result.getInt("difficolta"));  
                ricetta.setTempo(result.getString("tempo"));
                //ricetta.setProcedimento(result.getString("procedimento"));
                //ricetta.setAccountId(result.getLong("account_id"));
                ricetta.setImageUrl(result.getString("image_url"));
                //ricetta.setDeleteHash(result.getString("deletehash"));
                
                ricettaList.add(ricetta);
              
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
        return ricettaList;
    }
    
    public List<Ricetta> retrieveBySearch(List<String> substrings){
    	Connection connection = this.dataSource.getConnection();
        List<Ricetta> ricettaList = new ArrayList<>();
        try {
        	Ricetta ricetta;
            PreparedStatement statement;
            String query = "";
            
        	query = "select * from ricetta where";
        	for(int i = 0; i < substrings.size(); i++) {
        		query += " lower(titolo) like lower('%"+ substrings.get(i) +"%')";
        		
        		if(i < substrings.size()-1)
        			query += " and";
        	}
        	
        	System.out.println(query);
        	statement = connection.prepareStatement(query);            
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                ricetta = new Ricetta();
                ricetta.setId(result.getLong("id"));                
                ricetta.setTitolo(result.getString("titolo"));
                ricetta.setDifficolta(result.getInt("difficolta"));  
                ricetta.setTempo(result.getString("tempo"));
                //ricetta.setProcedimento(result.getString("procedimento"));
                //ricetta.setAccountId(result.getLong("account_id"));
                ricetta.setImageUrl(result.getString("image_url"));
                //ricetta.setDeleteHash(result.getString("deletehash"));
                
                ricettaList.add(ricetta);
              
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
        return ricettaList;
    }
    
    public List<Ricetta> findByAccountId(Long id) {
        Connection connection = this.dataSource.getConnection();
        List<Ricetta> ricettaList = new ArrayList<>();
        try {
        	Ricetta ricetta;
            PreparedStatement statement;
            String query = "select * from ricetta where account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                ricetta = new Ricetta();
                ricetta.setId(result.getLong("id"));                
                ricetta.setTitolo(result.getString("titolo"));
                ricetta.setDifficolta(result.getInt("difficolta"));  
                ricetta.setTempo(result.getString("tempo"));
                ricetta.setProcedimento(result.getString("procedimento"));
                ricetta.setAccountId(result.getLong("account_id"));
                ricetta.setImageUrl(result.getString("image_url"));
                ricetta.setDeleteHash(result.getString("deletehash"));
                
                ricettaList.add(ricetta);
              
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
        return ricettaList;
    }

    @Override
    public List<Ricetta> findAll() {
        Connection connection = this.dataSource.getConnection();
        List<Ricetta> ricettaList = new ArrayList<>();
        try {
            Ricetta ricetta;
            PreparedStatement statement;
            String query = "select * from ricetta";
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                ricetta = new Ricetta();
                ricetta.setId(result.getLong("id"));                
                ricetta.setTitolo(result.getString("titolo"));
                ricetta.setDifficolta(result.getInt("difficolta"));   
                ricetta.setTempo(result.getString("tempo"));
                
                ricettaList.add(ricetta);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }    finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
        return ricettaList;
    }

    @Override
    public void update(Ricetta ricetta) {
    
        Connection connection = this.dataSource.getConnection();
        try {
        	
            String update;
            
            if(ricetta.getImageUrl().isEmpty())
            	update = "update ricetta SET titolo = ?, difficolta = ?, tempo = ?, procedimento = ?, account_id = ? WHERE id = ?";
            else
            	update = "update ricetta SET titolo = ?, difficolta = ?, tempo = ?, procedimento = ?, account_id = ?, image_url = ?, deletehash = ? WHERE id = ?";
            	
            PreparedStatement statement = connection.prepareStatement(update);
            
            statement.setString(1, ricetta.getTitolo());
            statement.setInt(2, ricetta.getDifficolta());
            statement.setString(3, ricetta.getTempo());
            statement.setString(4, ricetta.getProcedimento());
            statement.setLong(5, ricetta.getAccountId());
            
            int index = 6;
            if(!ricetta.getImageUrl().isEmpty()) {
	            statement.setString(6, ricetta.getImageUrl());
	            statement.setString(7, ricetta.getDeleteHash()); 
	            index = 8;
            }

            statement.setLong(index, ricetta.getId());
            
            statement.executeUpdate();
            
            //this.updatePorzioni(ricetta, connection);
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
    public void delete(Ricetta ricetta) {
    	Connection connection = this.dataSource.getConnection();
        try {
            String delete = "delete from ricetta where id = ?";
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, ricetta.getId());
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
    public Ricetta findByPrimaryKeyJoin(Long id) {
        Connection connection = this.dataSource.getConnection();
        Ricetta ricetta = null;
        try {
            PreparedStatement statement;
            String query = "select r.id as r_id, p.quantita as p_quantita, p.id_ingrediente as p_id_ingrediente" 
                         + "from ricetta r, porzione p where id = ?";
            
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            boolean first = true;
            if (result.next()) {
                if(first){
                    //RicettaProxy?
                    first = false;
                }
                
                if(result.getLong("p_id_ingrediente") == 0){ //da modificare
                    //Porzione porzione = new Porzione();
                    //porzione.setQuantita(result.getInt("p_quantita"));
                    //porzione.setIdRicetta(result.getLong("p_id_ricetta"));
                    //porzione.setIdIngrediente(result.getLong("p_id_ingrediente"));
                   
                    IngredienteDaoJDBC ingredienteDao = new IngredienteDaoJDBC(dataSource);
                    //Ingrediente = ingredienteDao.findByPrimaryKey(result.getLong("p_id_ingrediente"));
                    //porzione.setIngrediente(ingredienteDao.findByPrimaryKey(result.getLong("p_id_ingrediente")));    
                    
                    //ricetta.addPorzione(porzione);
                    //ricetta.addIngrediente(Ingrediente);
                }
              
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
        return ricetta;
    }

}

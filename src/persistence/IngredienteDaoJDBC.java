package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Ingrediente;
import persistence.dao.IngredienteDao;

public class IngredienteDaoJDBC implements IngredienteDao {
	private DataSource dataSource;
	
	public IngredienteDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
    public void save(Ingrediente alimento) {
        
           Connection connection = this.dataSource.getConnection();
            try {
                Long id = IdBroker.getIdRicetta(connection);
                alimento.setId(id); // >:[
                 
                String insert = "insert into ingrediente(id, nome) values (?,?)";
                        
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setLong(1, id);
                statement.setString(2, alimento.getNome());
                // settare dataiscrizione
                
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
    public Ingrediente findByPrimaryKey(Long id) {
        Connection connection = this.dataSource.getConnection();
        Ingrediente ingrediente = null;
        try {
            PreparedStatement statement;
            String query = "select * from ingrediente where id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                ingrediente = new Ingrediente();
                ingrediente.setId(result.getLong("id"));                
                ingrediente.setNome(result.getString("nome"));
                ingrediente.setApiFoodId(result.getString("api_foodid"));
              
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
        return ingrediente;
    }

    @Override
    public List<Ingrediente> findAll() {
        
        Connection connection = this.dataSource.getConnection();
        List<Ingrediente> ingredienteList = new ArrayList<>();
        try {
            Ingrediente ingrediente;
            PreparedStatement statement;
            String query = "select * from ingrediente";
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
            	ingrediente = new Ingrediente();
            	ingrediente.setId(result.getLong("id"));                
            	ingrediente.setNome(result.getString("nome"));
            	ingrediente.setApiFoodId(result.getString("api_foodid"));
                
            	ingredienteList.add(ingrediente);
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
        return ingredienteList;
    }

    @Override
    public void update(Ingrediente alimento) {
        
        Connection connection = this.dataSource.getConnection();
        try {
            String update = "update ingrediente SET nome = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, alimento.getNome());
            statement.setLong(5, alimento.getId());
            
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
    public void delete(Ingrediente alimento) {
          Connection connection = this.dataSource.getConnection();
            try {
                String delete = "delete FROM ingrediente WHERE id = ? ";
                PreparedStatement statement = connection.prepareStatement(delete);
                statement.setLong(1, alimento.getId());
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
	public List<Ingrediente> findAllByRicettaId(Long id) {
		
		Connection connection = this.dataSource.getConnection();
        List<Ingrediente> ingredienteList = new ArrayList<>();
        try {
            Ingrediente ingrediente;
            PreparedStatement statement;
            String query = "select * from ingrediente where ricetta_id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
            	ingrediente = new Ingrediente();
            	ingrediente.setId(result.getLong("id"));                
            	ingrediente.setNome(result.getString("nome"));
                
            	ingredienteList.add(ingrediente);
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
        return ingredienteList;
	}

}

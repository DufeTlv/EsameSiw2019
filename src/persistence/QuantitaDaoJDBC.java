package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Quantita;
import persistence.dao.QuantitaDao;

public class QuantitaDaoJDBC implements QuantitaDao{
	private DataSource dataSource;
	
	public QuantitaDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Quantita quantita) {
		// TODO Auto-generated method stub
		
		 Connection connection = this.dataSource.getConnection();
         try {
              
             String insert = "insert into quantita (valore, unitadimisura_id, ricetta_id, ingrediente_id) values(?,?,?,?)";
                     
             PreparedStatement statement = connection.prepareStatement(insert);
             statement.setInt(1, quantita.getValore());
             statement.setInt(2, quantita.getUnitaDiMisura_id());
             statement.setLong(3, quantita.getRicetta_id());
             statement.setLong(4, quantita.getIngrediente_id());
             
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
	public Quantita findByPrimaryKey(Long codice) {
		// TODO Auto-generated method stub
		Connection connection = this.dataSource.getConnection();
		Quantita quantita = null;
        try {
             
            String insert = "select * from quantita where ricetta_id ='?'"; //and ingrediente_id = '?'";
            
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, codice);
            //statement.setLong(2, codiceB);
            
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
            	quantita = new Quantita();
            	quantita.setValore(result.getInt("valore"));
            	quantita.setUnitaDiMisura_id(result.getInt("unitadimisura_id"));
            	quantita.setRicetta_id(result.getLong("ricetta_id"));
            	quantita.setIngrediente_id(result.getLong("ingrediente_id"));
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
		return quantita;
	}
	
	@Override
	public List<Quantita> findAllByRicettaId(Long codice){
		Connection connection = this.dataSource.getConnection();
		List<Quantita> quantitaList = new ArrayList<>();
        try {
            Quantita quantita;
            String insert = "select * from quantita where ricetta_id = ?"; //and ingrediente_id = '?'";
            
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, codice);
            //statement.setLong(2, codiceB);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
            	quantita = new Quantita();
            	quantita.setValore(result.getInt("valore"));
            	quantita.setUnitaDiMisura_id(result.getInt("unitadimisura_id"));
            	quantita.setRicetta_id(result.getLong("ricetta_id"));
            	quantita.setIngrediente_id(result.getLong("ingrediente_id"));
            	
            	quantitaList.add(quantita);
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
		return quantitaList;
	}
	

	@Override
	public List<Quantita> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clearFromRicetta(Long id) {
		Connection connection = this.dataSource.getConnection();
		
		try {
			String delete = "delete from quantita where ricetta_id = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, id);
			
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
	public void update(Quantita quantita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Quantita quantita) {
		// TODO Auto-generated method stub
		
	}

}

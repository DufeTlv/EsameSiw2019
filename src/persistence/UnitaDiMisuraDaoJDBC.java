package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UnitaDiMisura;
import persistence.dao.UnitaDiMisuraDao;

public class UnitaDiMisuraDaoJDBC implements UnitaDiMisuraDao{
	private DataSource dataSource;
	
	public UnitaDiMisuraDaoJDBC(DataSource dS) {
		setDataSource(dS);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<UnitaDiMisura> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<UnitaDiMisura> udmList = new ArrayList<>();
		
		try {
			UnitaDiMisura udm;
			String insert = "select * from unitadimisura";
            
            PreparedStatement statement = connection.prepareStatement(insert);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
            	udm = new UnitaDiMisura();
            	udm.setId(result.getInt("id"));
            	udm.setNome(result.getString("nome"));
            	udm.setUri(result.getString("api_misureuri"));
            	
            	udmList.add(udm);
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
		return udmList;
	}

}

package persistence;

import persistence.dao.AccountDao;
import persistence.dao.CommentoDao;
import persistence.dao.IngredienteDao;
import persistence.dao.PreferitoDao;
import persistence.dao.QuantitaDao;
import persistence.dao.RicettaDao;
import persistence.dao.UnitaDiMisuraDao;

public class MySQLDAOFactory extends DAOFactory{
	
	private static  DataSource dataSource;
	
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/Segreteria2019","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	@Override
	public AccountDao getAccountDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredienteDao getIngredienteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RicettaDao getRicettaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuantitaDao getQuantitaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferitoDao getPreferitoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentoDao getCommentoDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UnitaDiMisuraDao getUnitaDiMisuraDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilDao getUtilDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}

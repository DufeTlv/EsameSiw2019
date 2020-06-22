
package persistence;

import persistence.dao.AccountDao;
import persistence.dao.CommentoDao;
import persistence.dao.IngredienteDao;
import persistence.dao.PreferitoDao;
import persistence.dao.QuantitaDao;
import persistence.dao.RicettaDao;
import persistence.dao.UnitaDiMisuraDao;

class PostgresDAOFactory extends DAOFactory {
	
	private static  DataSource dataSource;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			dataSource = new DataSource("jdbc:postgresql://rogue.db.elephantsql.com:5432/fpmmnogr",
						 				"fpmmnogr",
						 				"J_y4tRjBL6_I0PR783Z57gslrOS89WuZ");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load postgreSQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	@Override
	public AccountDao getAccountDAO() {
		// TODO Auto-generated method stub
		return new AccountDaoJDBC(dataSource);
	}

	@Override
	public IngredienteDao getIngredienteDAO() {
		// TODO Auto-generated method stub
		return new IngredienteDaoJDBC(dataSource);
	}
	
	@Override
	public PreferitoDao getPreferitoDAO() {
		// TODO Auto-generated method stub
		return new PreferitoDaoJDBC(dataSource);
	}

	@Override
	public RicettaDao getRicettaDAO() {
		// TODO Auto-generated method stub
		return new RicettaDaoJDBC(dataSource);
	}
	
	@Override
	public QuantitaDao getQuantitaDAO() {
		// TODO Auto-generated method stub
		return new QuantitaDaoJDBC(dataSource);
	}
	
	@Override
	public CommentoDao getCommentoDAO() {
		// TODO Auto-generated method stub
		return new CommentoDaoJDBC(dataSource);
	}
	
	@Override
	public UnitaDiMisuraDao getUnitaDiMisuraDAO() {
		// TODO Auto-generated method stub
		return new UnitaDiMisuraDaoJDBC(dataSource);
	}

	@Override
	public UtilDao getUtilDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}

package persistence;

import persistence.dao.AccountDao;
import persistence.dao.CommentoDao;
import persistence.dao.IngredienteDao;
import persistence.dao.PreferitoDao;
import persistence.dao.RicettaDao;
import persistence.dao.UnitaDiMisuraDao;
import persistence.dao.QuantitaDao;


public abstract class DAOFactory {
		
		public static final int MYSQL = 1;
		public static final int POSTGRESQL = 2;
		
		public static DAOFactory getDAOFactory(int whichFactory) {
			switch ( whichFactory ) {
			
			case MYSQL:
				return new MySQLDAOFactory();
			case POSTGRESQL:
				return new PostgresDAOFactory();
			default:
				return null;
			}
		}
		
		public abstract AccountDao getAccountDAO();

		public abstract IngredienteDao getIngredienteDAO();
		
		public abstract PreferitoDao getPreferitoDAO();
		
		public abstract RicettaDao getRicettaDAO();
		
		public abstract QuantitaDao getQuantitaDAO();
		
		public abstract CommentoDao getCommentoDAO();
		
		public abstract UnitaDiMisuraDao getUnitaDiMisuraDAO();

		public abstract persistence.UtilDao getUtilDAO();
		
}

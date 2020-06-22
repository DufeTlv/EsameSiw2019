package persistence;

public class DatabaseManager {
	private static DatabaseManager instance = null;
	private DAOFactory daoFactory;
	
	public static DatabaseManager getInstance(){
		if (instance == null){
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	private DatabaseManager() {
		daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
	}
	
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

}

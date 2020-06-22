package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DataSource {
	final private String dbURI;	  // = "jdbc:postgres://fpmmnogr:J_y4tRjBL6_I0PR783Z57gslrOS89WuZ@rogue.db.elephantsql.com:5432/fpmmnogr"
	final private String userName;// = "postgres"; = "fpmmnogr";
	final private String password;// = "postgres"; = "J_y4tRjBL6_I0PR783Z57gslrOS89WuZ";
	
	//final private String dbURI;   // = "jdbc:postgresql://localhost/test";
	//final private String userName;// = "dufe";
	//final private String password;// = "dufe";
	
	public DataSource(String dbURI, String userName, String password) {
		this.dbURI=dbURI;
		this.userName=userName;
		this.password=password;
	}

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
			
		    connection = DriverManager.getConnection(dbURI,userName, password);
		    
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdBroker {

	private static final String queryA = "SELECT nextval('sequenza_id_account') AS id";
	private static final String queryR = "SELECT nextval('sequenza_id_ricetta') AS id";
	private static final String queryC = "SELECT nextval('sequenza_id_commento') AS id";

	public static Long getIdAccount(Connection connection) {
		Long id = null;
		try {
			PreparedStatement statement = connection.prepareStatement(queryA);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return id;
	}
	
	public static Long getIdRicetta(Connection connection) {
		Long id = null;
		try {
			PreparedStatement statement = connection.prepareStatement(queryR);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return id;
	}
	
	public static Long getIdCommento(Connection connection) {
		Long id = null;
		try {
			PreparedStatement statement = connection.prepareStatement(queryC);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return id;
	}
	
}

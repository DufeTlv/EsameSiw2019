package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class UtilDao {
	private DataSource dataSource;
	
	public UtilDao(DataSource dS) {
		setDataSource(dS);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void dropDatabase(){
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS sequenza_id;"
					+ "drop table if exists account;"							
					+ "drop table if exists apportocalorico;"
					+ "drop table if exists ripartizionenutrizionale;"
					+ "drop table if exists calendario;"
					+ "drop table if exists giorno;"
					+ "drop table if exists pasto;"
					+ "drop table if exists porzione;"
					+ "drop table if exists ingrediente;"
					+ "drop table if exists catalogoingredienti;"
					+ "drop table if exists ricetta;"
					+ "drop table if exists catalogoricette;"
					;
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();
			System.out.println("Executed drop database");
			
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
		
	public void createDatabase(){
		Connection connection = dataSource.getConnection();
		
		try {
			
			String create = 
					  "create SEQUENCE sequenza_id;"
                    + "create table account 				 (\"id\" bigint primary key, nome varchar(255), cognome varchar(255), sesso int, indirizzoemail varchar(255), password varchar(255), datanascita date, dataiscrizione data);"
                    + "create table apportocalorico 		 (\"id_account\" bigint primary key REFERENCES account(\"id\"), caloriegiornaliere int);"
                    + "create table ripartizionenutrizionale (\"id_apportocalorico\" bigint primary key REFERENCES apportocalorico(\"id_account\"), carboidrati int, proteine int, grassi int);"
                    //+ "create table calendario 				 (\"id\" bigint primary key REFERENCES account(\"id\"));"
                    + "create table giorno 					 (\"id_account\" bigint primary key REFERENCES account(\"id\"), data date, giornosettimana int);"
                    + "create table pasto 					 (\"id_giorno\" bigint primary key REFERENCES giorno(\"id\"), tipopasto int, datapasto date);"
                    + "create table porzione 				 (\"id_A\" bigint primary key, id_B bigint primary key, quantita int);"
                    + "create table ingrediente 			 (\"id\" bigint primary key, nome varchar(255), calorie int, carboidrati int, proteine int, grassi int);"
                    //+ "create table catalogoingredienti 	 (\"id\" bigint primary key, nome varchar(255));"
                    + "create table ricetta 				 (\"id\" bigint primary key, titolo varchar(255), titolo varchar(255));"       
                    //+ "create table catalogoricette 		 (\"id\" bigint primary key, nome varchar(255));" 
                    ;
					
			//+ "create table giorno (\"id_account\" bigint primary key REFERENCES account(\"id\"), data date, giornosettimana int, PRIMARY KEY (item_code,item_name));"				
			PreparedStatement statement = connection.prepareStatement(create);
			
			statement.executeUpdate();
			System.out.println("Executed create database");
			
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
	
	public  void resetDatabase() {
		
	}

}

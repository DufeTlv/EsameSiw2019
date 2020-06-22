package persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import model.Account;
import persistence.dao.AccountDao;

public class Main {
	public static void main(String args[]) {				
		try {
			Class.forName("org.postgresql.Driver");
			DataSource dataSource=new DataSource("jdbc:postgresql://rogue.db.elephantsql.com:5432/fpmmnogr",
					  							 "fpmmnogr",
					  							 "J_y4tRjBL6_I0PR783Z57gslrOS89WuZ");
			
			AccountDao accountDao = new AccountDaoJDBC(dataSource);
			
			Account account = accountDao.findAll().get(0);
			
			System.out.println(account.getNome());
			System.out.println(account.getCognome());
			
			/*DateFormat format = new SimpleDateFormat("yyyy,MM,dd", Locale.ITALIAN);
			Date date = format.parse("1997,02,12");*/
			
			//System.out.println(date);
			
			/*Account account1 = new Account();
			account1.setCognome("brando");
			account1.setNome("diego");
			
			Calendar cal = Calendar.getInstance();
			cal.set(1987, Calendar.MARCH, 1); // // 1 marzo 1987
			Date date1 = cal.getTime();
			
			account1.setDataNascita(date1);
			account1.setSesso("M");
			account1.setIndirizzoEmail("konodioda@giogio.jojo");
			account1.setPassword("hoho");
			
			accountDao.save(account1);*/
			
		}catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}		
		
	}
}

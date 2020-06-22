package model;

public class Preferito {
	private long account_id;
	private long ricetta_id;
	
	public Preferito() {
		
	}
	
	public long getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	
	public long getRicetta_id() {
		return ricetta_id;
	}
	
	public void setRicetta_id(long ricetta_id) {
		this.ricetta_id = ricetta_id;
	}
	
}

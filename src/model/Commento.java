package model;

public class Commento {
	private String descrizione;
	private long account_id;
	private long ricetta_id;
	private long id;
	private String gifUrl;
	private String gifUrlStill;
	
	public Commento() {
		
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGifUrl() {
		return gifUrl;
	}

	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}

	public String getGifUrlStill() {
		return gifUrlStill;
	}

	public void setGifUrlStill(String gifUrlStill) {
		this.gifUrlStill = gifUrlStill;
	}
	
	
}

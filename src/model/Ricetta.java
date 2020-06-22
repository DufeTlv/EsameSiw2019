package model;

public class Ricetta {
	
	private long id;
	private String titolo;
	private int difficolta;
	private String tempo;
	private String procedimento;
	private long accountId;
	private String imageUrl;
	private String deleteHash;
	
	public Ricetta() {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}
	
	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public int getDifficolta() {
		return difficolta;
	}       	

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDeleteHash() {
		return deleteHash;
	}

	public void setDeleteHash(String deleteHash) {
		this.deleteHash = deleteHash;
	}

}

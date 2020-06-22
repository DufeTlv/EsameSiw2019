package model;

import java.util.Date;

public class Account {
	
	private Long   id;
	private String nome;
	private String cognome;
	private String sesso;
	private String indirizzoEmail;
	private String password;
	private Date   dataNascita;
	private Date   dataIscrizione;
	
	public Account(){}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	public String getSesso() {
		return sesso;
	}
	
	public void setIndirizzoEmail(String indirizzoEmail) {
		this.indirizzoEmail = indirizzoEmail;
	}
	
	public String getIndirizzoEmail() {
		return indirizzoEmail;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	
	public Date getDataIscrizione() {
		return dataIscrizione;
	}
	
	
}

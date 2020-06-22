package model;

public class Ingrediente {

	private long id;
	private String nome;
	private String apiFoodId;
	
	public Ingrediente(){
		
	}
	
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

	public String getApiFoodId() {
		return apiFoodId;
	}

	public void setApiFoodId(String api_foodid) {
		this.apiFoodId = api_foodid;
	}
}

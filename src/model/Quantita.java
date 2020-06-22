package model;

public class Quantita {
	
	private int valore;
	//public static enum unita_misura {g, l;};
	private int unitadimisura_id;
	private long ricetta_id;
	private long ingrediente_id;
	
	public Quantita() {
		
	}	
	
	public int getValore() {
		return valore;
	}
	
	public void setValore(int valore) {
		this.valore = valore;
	}

	public int getUnitaDiMisura_id() {
		return unitadimisura_id;
	}

	public void setUnitaDiMisura_id(int unitadimisura_id) {
		this.unitadimisura_id = unitadimisura_id;
	}

	public long getRicetta_id() {
		return ricetta_id;
	}

	public void setRicetta_id(long ricetta_id) {
		this.ricetta_id = ricetta_id;
	}

	public long getIngrediente_id() {
		return ingrediente_id;
	}

	public void setIngrediente_id(long ingrediente_id) {
		this.ingrediente_id = ingrediente_id;
	}
}

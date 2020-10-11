package com.leets.model;

/**
*
* @author estidlore
*/
public class Dane {
		
	public final static String
			ESTRATO = "estrato",
			ID = "id",
			SISBEN = "sisben";
	
	private int estrato;
	private int id;
	private int sisben;
	
	public Dane(int estrato, int id, int sisben) {
		this.estrato = estrato;
		this.id = id;
		this.sisben = sisben;
	}
	
	public int getEstrato() {
		return estrato;
	}
	
	public void setEstrato(int estrato) {
		this.estrato = estrato;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSisben() {
		return sisben;
	}
	
	public void setSisben(int sisben) {
		this.sisben = sisben;
	}

}

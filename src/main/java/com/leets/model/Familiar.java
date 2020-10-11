package com.leets.model;

/**
*
* @author estidlore
*/
public class Familiar {
	
	private final String
			ID = "id",
			PARENTESCO = "parentesco",
			TIPO_ID = "tipo_id";

	private int id;
	private String parentesco;
	private String tipoId;
	
	public Familiar() {
	}
	
	public Familiar(int id, String parentesco, String tipoId) {
		this.id = id;
		this.parentesco = parentesco;
		this.tipoId = tipoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	
}

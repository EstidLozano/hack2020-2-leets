package com.leets.model;

/**
*
* @author estidlore
*/
public class Registraduria {
	
	public final static String
			F_NACIMIENTO = "f_nacimiento",
			ID = "id",
			NOMBRE = "nombre";
	
	private String f_nacimiento;
	private int id;
	private String nombre;
	
	public Registraduria(String f_nacimiento, int id, String nombre) {
		this.f_nacimiento = f_nacimiento;
		this.id = id;
		this.nombre = nombre;
	}

	public String getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

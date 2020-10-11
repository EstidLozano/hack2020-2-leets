package com.leets.model;

/**
*
* @author estidlore
*/
public class Inscrito {
	
	public final static String
			DEPARTAMENTO = "departamento",
			DIRECCION = "direccion",
			ESTRATO = "estrato",
			F_NACIMIENTO = "f_nacimiento",
			FAMILIARES = "familiares",
			ID = "id",
			INGRESOS = "ingresos",
			MUNICIPIO = "municipio",
			NOMBRE = "nombre",
			SISBEN = "sisben",
			TELEFONO = "telefono",
			ZONA = "zona";
	
	public String departamento;
	public String direccion;
	public int estrato; // dane
	public String f_nacimiento; // registraduria
	public String[] familiares;
	public int id;
	public int ingresos;
	public String municipio;
	public String nombre; // registraduria
	public int sisben; // dane
	public int telefono;
	public String zona;
	
	public Inscrito() {
	}
	
	public Inscrito(String departamento, String direccion, String[] familiares,
			int id, int ingresos, String municipio, int telefono, String zona) {
		this.departamento = departamento;
		this.direccion = direccion;
		this.familiares = familiares;
		this.id = id;
		this.ingresos = ingresos;
		this.municipio = municipio;
		this.telefono = telefono;
		this.zona = zona;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstrato() {
		return estrato;
	}

	public void setEstrato(int estrato) {
		this.estrato = estrato;
	}

	public String getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public String[] getFamiliares() {
		return familiares;
	}

	public void setFamiliares(String[] familiares) {
		this.familiares = familiares;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIngresos() {
		return ingresos;
	}

	public void setIngresos(int ingresos) {
		this.ingresos = ingresos;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSisben() {
		return sisben;
	}

	public void setSisben(int sisben) {
		this.sisben = sisben;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}

package com.leets.model;

import java.util.List;

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
            INTEGRANTES = "familiares",
            ID = "id",
            INGRESOS = "ingresos",
            MUNICIPIO = "municipio",
            NOMBRE = "nombre",
            SISBEN = "sisben",
            TELEFONO = "telefono",
            ZONA = "zona";

    private String departamento;
    private String direccion;
    private int estrato; // dane
    private String f_nacimiento; // registraduria
    private List<Integer> familiares;
    private int id;
    private int ingresos;
    private String municipio;
    private String nombre; // registraduria
    private int sisben; // dane
    private int telefono;
    private String zona;

    public Inscrito() {
    }

    public Inscrito(String departamento, String direccion, List<Integer> familiares,
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

    public List<Integer> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Integer> familiares) {
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

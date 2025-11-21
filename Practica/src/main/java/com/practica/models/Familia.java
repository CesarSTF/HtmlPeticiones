package com.practica.models;

public class Familia {
    private Integer idFamilias;
    private Integer nroIntegrantes;
    private String nombreFamilia;
    private Generador generador;

    public Familia(){}

    public Familia(Integer idFamilias, String nombreFamilia, Integer nroIntegrantes, Generador generador){
        this.idFamilias = idFamilias;
        this.nombreFamilia = nombreFamilia;
        this.nroIntegrantes = nroIntegrantes;
        this.generador = generador;
    }

    public Integer getNroIntegrantes() {
        return this.nroIntegrantes;
    }

    public void setNroIntegrantes(Integer nroIntegrantes) {
        this.nroIntegrantes = nroIntegrantes;
    }

    public String getNombreFamilia() {
        return this.nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public Integer getIdFamilias() {
        return this.idFamilias;
    }

    public void setIdFamilias(Integer idFamilias) {
        this.idFamilias = idFamilias;
    }

    public Generador getGenerador() {
        return this.generador;
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    @Override
    public String toString() {
        return "Familia{" +
                "idFamilias=" + idFamilias +
                ", nombreFamilia='" + nombreFamilia + '\'' +
                ", nroIntegreantes=" + nroIntegrantes +
                ", generador=" + (generador != null ? generador.toString() : "null") +
                '}';
    }
}

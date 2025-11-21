package com.practica.models;

public class Generador {
    private Integer idGenerador;
    private String modelo;
    private float costo;
    private float consumoComustible;
    private float enegeriaGenerada;
    private Uso uso;

    public Generador(){}

    public Generador(Integer idGenerador, String modelo, float costo, float consumoComustible, float enegeriaGenerada, Uso uso){
        this.idGenerador = idGenerador;
        this.modelo = modelo;
        this.costo = costo;
        this.consumoComustible = consumoComustible;
        this.enegeriaGenerada = enegeriaGenerada;
        this.uso = uso;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Uso getUso() {
        return uso;
    }

    public void setUso(Uso uso) {
        this.uso = uso;
    }

    public Integer getIdGenerador() {
        return this.idGenerador;
    }

    public void setIdGenerador(Integer idGenerador) {
        this.idGenerador = idGenerador;
    }

    public float getCosto() {
        return this.costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getConsumoComustible() {
        return this.consumoComustible;
    }

    public void setConsumoComustible(float consumoComustible) {
        this.consumoComustible = consumoComustible;
    }

    public float getEnegeriaGenerada() {
        return this.enegeriaGenerada;
    }

    public void setEnegeriaGenerada(float enegeriaGenerada) {
        this.enegeriaGenerada = enegeriaGenerada;
    }

    @Override
    public String toString() {
    return "Generador{" +
            "idGenerador=" + idGenerador +
            ", modelo=" + modelo +
            ", costo=" + costo +
            ", consumoComustible=" + consumoComustible +
            ", enegeriaGenerada=" + enegeriaGenerada +
            ", uso=" + (uso != null ? uso.getName() : "No especificado") + 
            '}';
    }
}

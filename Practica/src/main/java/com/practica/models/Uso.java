package com.practica.models;

public enum Uso {
    DOMESTICO("Domestico"),
    COMERCIAL("Comercial");

    private String name;

    private Uso(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
        
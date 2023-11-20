package com.upc.curso.entidades;

public class Auto {
    private String nombre;
    private float precio;

    public Auto() {
    }

    public Auto(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }
}

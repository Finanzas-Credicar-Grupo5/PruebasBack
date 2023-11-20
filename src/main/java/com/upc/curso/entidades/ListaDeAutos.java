package com.upc.curso.entidades;

public class ListaDeAutos {
    private Auto[] autos;
    private int size;
    private int capacity;

    public ListaDeAutos() {
        capacity = 10;
        autos = new Auto[capacity];
    }

    public void AgregarAutos(String nombre, float precio) {
        if (size >= capacity) {
            AumentarCapacidad();
        }
        autos[size++] = new Auto(nombre, precio);
    }

    public void Imprimir() {
        for (int i = 0; i < size; ++i) {
            System.out.println(autos[i].getNombre() + " " + autos[i].getPrecio());
        }
    }

    public Auto BuscaAuto(int pos) {
        if (pos >= 0 && pos < size) {
            return autos[pos];
        }
        return new Auto("", 0);
    }

    private void AumentarCapacidad() {
        int newCapacity = capacity * 2;
        Auto[] newAutos = new Auto[newCapacity];

        System.arraycopy(autos, 0, newAutos, 0, size);

        autos = newAutos;
        capacity = newCapacity;
    }
}

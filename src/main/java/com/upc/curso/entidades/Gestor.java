package com.upc.curso.entidades;

import java.util.Scanner;

public class Gestor {
    private ListaDeAutos lista;

    public Gestor() {
        lista = new ListaDeAutos();
    }

    public void LlenarLista() {
        lista.AgregarAutos("Chevrolet", 10000);
        lista.AgregarAutos("Nissan", 20000);
        lista.AgregarAutos("Toyota", 35000);
        lista.AgregarAutos("EjemploExcel", 1800000);
    }

    public void ImprimirLista() {
        lista.Imprimir();
    }

    public float calcularElVanConFlujoContinuo(float prestamo, float cok, float Flujo, float tiempo) {
        return (float) (prestamo - Flujo * (1 - Math.pow(1 + cok, -tiempo)) / cok);
    }


    public float convetirTiempoTasaNominal(float tasa, String periodo) {
        tasa = tasa / 100;
        float divisor = 0.0f;
        switch (periodo) {
            case "Diaria":
                divisor = 360.0f;
                break;
            case "Quincenal":
                divisor = 24.0f;
                break;
            case "Mensual":
                divisor = 12.0f;
                break;
            case "Bimestral":
                divisor = 6.0f;
                break;
            case "Trimestral":
                divisor = 4.0f;
                break;
            case "Cuatrimestral":
                divisor = 3.0f;
                break;
            case "Semestral":
                divisor = 2.0f;
                break;
            case "Anual":
                divisor = 1.0f;
                break;
        }
        return tasa / divisor;
    }

    public float convertirTasaDeSeguroMensual(float tasa, String periodo) {
        tasa = tasa / 100;
        float multiplicando;
        switch (periodo) {
            case "Diaria":
                multiplicando = 1.0f / 30.0f;
                break;
            case "Quincenal":
                multiplicando = 1.0f / 2.0f;
                break;
            case "Mensual":
                multiplicando = 1.0f;
                break;
            case "Bimestral":
                multiplicando = 2.0f;
                break;
            case "Trimestral":
                multiplicando = 3.0f;
                break;
            case "Cuatrimestral":
                multiplicando = 4.0f;
                break;
            case "Semestral":
                multiplicando = 6.0f;
                break;
            case "Anual":
                multiplicando = 1.0f;
                break;
            default:
                multiplicando = 1.0f;
                break;
        }
        return tasa * multiplicando;
    }

    public float ConvertirAltiempoDeLaTasa(float tiempo, String periodo) {
        float multiplicando = 0.0f;
        switch (periodo) {
            case "Diaria":
                multiplicando = 360.0f;
                break;
            case "Quincenal":
                multiplicando = 24.0f;
                break;
            case "Mensual":
                multiplicando = 12.0f;
                break;
            case "Bimestral":
                multiplicando = 6.0f;
                break;
            case "Trimestral":
                multiplicando = 4.0f;
                break;
            case "Cuatrimestral":
                multiplicando = 3.0f;
                break;
            case "Semestral":
                multiplicando = 2.0f;
                break;
            case "Anual":
                multiplicando = 1.0f;
                break;
            default:
                multiplicando = 1.0f;
                break;
        }
        return tiempo * multiplicando;
    }

    public float CambioPeriodoTasaEfectiva(float tasa, String periodo) {
        tasa = tasa / 100;
        float TEA;
        float divisor = 0.0f;
        switch (periodo) {
            case "Diaria":
                divisor = 360.0f;
                break;
            case "Quincenal":
                divisor = 24.0f;
                break;
            case "Mensual":
                divisor = 12.0f;
                break;
            case "Bimestral":
                divisor = 6.0f;
                break;
            case "Trimestral":
                divisor = 4.0f;
                break;
            case "Cuatrimestral":
                divisor = 3.0f;
                break;
            case "Semestral":
                divisor = 2.0f;
                break;
            case "Anual":
                divisor = 1.0f;
                break;
            default:
                divisor = 1.0f;
                break;
        }
        return TEA = (float) (Math.pow(1 + tasa, 1.0 / divisor) - 1);
    }

    public float CambioTasaNominalAEfectiva(float tasa, String periodo) {
        float TEA;
        tasa = tasa / 100;
        TEA = (float) ((Math.pow((1 + tasa / 360), 360) - 1) * 100);
        return CambioPeriodoTasaEfectiva(TEA, periodo);
    }

    public float CalcularCuotaInicial(float porcentaje, float precio) {
        float cuota;
        porcentaje = porcentaje / 100;
        cuota = precio * (porcentaje);
        return cuota;
    }

    public float ObtenerTasaDeInteres(float tasa, String tipoTasa, String periodo) {
        float TEA = 0.0f;
        if (tipoTasa.equals("Nominal")) {
            TEA = CambioTasaNominalAEfectiva(tasa, periodo);
        } else if (tipoTasa.equals("Efectiva")) {
            TEA = CambioPeriodoTasaEfectiva(tasa, periodo);
        }
        return TEA;
    }

    public float CalcularCuotaTotal(float prestamo, float TE, float TSD, float tiempo) {
        float cuota;
        cuota = (float) ((prestamo * (TE + TSD)) / (1 - Math.pow(1 + (TE + TSD), 0.0 - tiempo)));
        return cuota;
    }
}

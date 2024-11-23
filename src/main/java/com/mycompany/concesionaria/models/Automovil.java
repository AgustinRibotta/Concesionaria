package com.mycompany.concesionaria.models;

public class Automovil {

    int id;
    String modelo;
    String marca;
    String motor;
    String patente;
    int cantidadPuertas;

    public Automovil() {
    }

    public Automovil(int id, String modelo, String marca, String motor, String patente, int cantidadPuertas) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.motor = motor;
        this.patente = patente;
        this.cantidadPuertas = cantidadPuertas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }

    
}
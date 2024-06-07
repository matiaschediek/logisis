package com.mchediek.logisis.model;

public class Conductor {
    private int idConductor;
    private String nombre;
    private String licencia;

    // Constructor
    public Conductor(int idConductor, String nombre, String licencia) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.licencia = licencia;
    }

    // Getters
    public int getIdConductor() {
        return idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLicencia() {
        return licencia;
    }
}

package com.mchediek.logisis.model;

public class Conductor {
    private int idConductor;
    private String nombre;
    private String licencia;

    public Conductor(int idConductor, String nombre, String licencia) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.licencia = licencia;
    }

    public String getNombre() {
        return nombre;
    }
}

package com.mchediek.logisis.model;

import java.util.Date;

public class Viaje {
    private int idViaje;
    private Cliente cliente;
    private Conductor conductor;
    private Date fechaInicio;
    private Date fechaFin;
    private String origen;
    private String destino;
    private String carga;

    // Constructor
    public Viaje(int idViaje, Cliente cliente, Conductor conductor, Date fechaInicio, Date fechaFin, String origen, String destino, String carga) {
        this.idViaje = idViaje;
        this.cliente = cliente;
        this.conductor = conductor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.origen = origen;
        this.destino = destino;
        this.carga = carga;
    }

    // Getters
    public int getIdViaje() {
        return idViaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getCarga() {
        return carga;
    }
}

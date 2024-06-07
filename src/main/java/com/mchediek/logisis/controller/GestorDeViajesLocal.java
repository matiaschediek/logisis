package com.mchediek.logisis.controller;

import com.mchediek.logisis.ColicionDeFechasConductorExcepcion;
import com.mchediek.logisis.model.Cliente;
import com.mchediek.logisis.model.Conductor;
import com.mchediek.logisis.model.Viaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class GestorDeViajesLocal implements GestorDeViajes {
    private List<Viaje> listaViajes;

    public GestorDeViajesLocal() {
        this.listaViajes = new ArrayList<>();
    }

    public void agregarViaje(Viaje viaje) throws ColicionDeFechasConductorExcepcion {
        validarColicionDeFechasConductor(viaje);
        listaViajes.add(viaje);
    }

    public void validarColicionDeFechasConductor(Viaje viaje) throws ColicionDeFechasConductorExcepcion {
        for (Viaje v : listaViajes) {
            if (v.getConductor().equals(viaje.getConductor())) {
                if (viaje.getFechaInicio().after(v.getFechaInicio()) && viaje.getFechaInicio().before(v.getFechaFin())) {
                    throw new ColicionDeFechasConductorExcepcion("El conductor ya tiene un viaje en esa fecha.");
                }
                if (viaje.getFechaFin().after(v.getFechaInicio()) && viaje.getFechaFin().before(v.getFechaFin())) {
                    throw new ColicionDeFechasConductorExcepcion("El conductor ya tiene un viaje en esa fecha.");
                }
            }
        }
    }

    public List<Viaje> listarViajes() {
        return listaViajes;
    }

}

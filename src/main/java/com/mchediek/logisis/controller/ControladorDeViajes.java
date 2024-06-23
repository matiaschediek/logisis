package com.mchediek.logisis.controller;

import com.mchediek.logisis.ColicionDeFechasConductorExcepcion;
import com.mchediek.logisis.model.Viaje;
import com.mchediek.logisis.model.ViajeDAO;
import com.mchediek.logisis.model.ViajeRepository;

import java.util.List;

public class ControladorDeViajes implements GestorDeViajes {

    public ViajeRepository viajeRepository ;
    public ControladorDeViajes()  {
        this.viajeRepository = new ViajeDAO();
    }
    public void agregarViaje(Viaje viaje) throws ColicionDeFechasConductorExcepcion {
        validarColicionDeFechasConductor(viaje);
        viajeRepository.agregar(viaje);
    }

    public void validarColicionDeFechasConductor(Viaje viaje) throws ColicionDeFechasConductorExcepcion {
        List<Viaje> listaViajes = viajeRepository.obtenerViajesPorConductor(viaje.getConductor());
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
        return viajeRepository.obtenerTodos();
    }

}

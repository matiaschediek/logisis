package com.mchediek.logisis.controller;

import com.mchediek.logisis.ColicionDeFechasConductorExcepcion;
import com.mchediek.logisis.model.Cliente;
import com.mchediek.logisis.model.Conductor;
import com.mchediek.logisis.model.Viaje;

import java.util.Date;
import java.util.List;

public interface GestorDeViajes {
    public void agregarViaje(Viaje viaje) throws ColicionDeFechasConductorExcepcion;

    public List<Viaje> listarViajes();
    public void validarColicionDeFechasConductor(Viaje viaje) throws ColicionDeFechasConductorExcepcion;
}

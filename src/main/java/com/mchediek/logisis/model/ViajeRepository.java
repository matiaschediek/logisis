package com.mchediek.logisis.model;

import java.sql.SQLException;
import java.util.List;

public interface ViajeRepository {
    void agregar(Viaje viaje) ;
    List<Viaje> obtenerTodos();
    List<Viaje> obtenerViajesPorConductor(Conductor conductor);
}


USE logistica;

SELECT 
    Viaje.idViaje,
    Cliente.nombre AS nombreCliente,
    Cliente.direccion,
    Cliente.telefono,
    Conductor.nombre AS nombreConductor,
    Conductor.licencia,
    Viaje.fechaInicio,
    Viaje.fechaFin,
    Viaje.origen,
    Viaje.destino,
    Viaje.carga
FROM 
    Viaje
JOIN 
    Cliente ON Viaje.idCliente = Cliente.idCliente
JOIN 
    Conductor ON Viaje.idConductor = Conductor.idConductor;

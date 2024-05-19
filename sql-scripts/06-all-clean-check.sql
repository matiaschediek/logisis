
USE logistica;

SELECT 
    idViaje,
    idCliente,
    idConductor,
    fechaInicio,
    fechaFin,
    origen,
    destino,
    carga
FROM 
    Viaje;

SELECT 
    idConductor,
    nombre,
    licencia
FROM 
    Conductor;

SELECT 
    idCliente,
    nombre,
    direccion,
    telefono
FROM 
    Cliente;

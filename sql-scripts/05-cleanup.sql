
USE logistica;

SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar datos de la tabla Conductor
DELETE FROM Conductor;

-- Eliminar datos de la tabla Cliente
DELETE FROM Cliente;

-- Eliminar datos de la tabla Viaje
DELETE FROM Viaje;


SET FOREIGN_KEY_CHECKS = 1;
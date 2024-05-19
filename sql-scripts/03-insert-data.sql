
USE logistica;

-- Insertar datos de prueba en la tabla Cliente
INSERT INTO Cliente (nombre, direccion, telefono) VALUES 
('Cliente A', 'Direccion A', '1234567890'),
('Cliente B', 'Direccion B', '0987654321');

-- Insertar datos de prueba en la tabla Conductor
INSERT INTO Conductor (nombre, licencia) VALUES 
('Conductor A', 'Licencia A'),
('Conductor B', 'Licencia B');

-- Insertar datos de prueba en la tabla Viaje
INSERT INTO Viaje (idCliente, idConductor, fechaInicio, fechaFin, origen, destino, carga) VALUES 
(1, 1, '2024-06-01', '2024-06-05', 'Ciudad A', 'Ciudad B', 'Carga A'),
(2, 2, '2024-06-10', '2024-06-15', 'Ciudad C', 'Ciudad D', 'Carga B');


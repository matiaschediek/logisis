
USE logistica;


CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE Conductor (
    idConductor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    licencia VARCHAR(50) NOT NULL
);

CREATE TABLE Viaje (
    idViaje INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    idConductor INT,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    origen VARCHAR(255) NOT NULL,
    destino VARCHAR(255) NOT NULL,
    carga VARCHAR(255) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idConductor) REFERENCES Conductor(idConductor)
);


-- Creación de índices
CREATE INDEX idx_cliente_nombre ON Cliente (nombre);
CREATE INDEX idx_conductor_nombre ON Conductor (nombre);
CREATE INDEX idx_viaje_fechaInicio ON Viaje (fechaInicio);
CREATE INDEX idx_viaje_fechaFin ON Viaje (fechaFin);
CREATE INDEX idx_viaje_origen ON Viaje (origen);
CREATE INDEX idx_viaje_destino ON Viaje (destino);

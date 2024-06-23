package com.mchediek.logisis.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViajeDAO implements ViajeRepository {
    private static final String DB_URL = "jdbc:mysql://db:3306/logistica";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootpassword";

    @Override
    public void agregar(Viaje viaje) {

        try (Connection conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Integer clientID= obtenerOCrearCliente(viaje.getCliente(), conexion);
            Integer conductorID= obtenerOCrearConductor(viaje.getConductor(), conexion);
            String consultaSQL = "INSERT INTO Viaje (idCliente, idConductor, fechaInicio, fechaFin, origen, destino, carga) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {
                sentencia.setInt(1,clientID );
                sentencia.setInt(2, conductorID);
                sentencia.setDate(3, new Date(viaje.getFechaInicio().getTime()));
                sentencia.setDate(4, new Date(viaje.getFechaFin().getTime()));
                sentencia.setString(5, viaje.getOrigen());
                sentencia.setString(6, viaje.getDestino());
                sentencia.setString(7, viaje.getCarga());
                sentencia.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al insertar el viaje: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error de conexi�n a la base de datos: " + e.getMessage());
        }
    }

    private int obtenerOCrearConductor(Conductor conductor, Connection conexion) {
        String consultaSQL = "SELECT idConductor FROM Conductor WHERE nombre = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {
            sentencia.setString(1, conductor.getNombre());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return resultado.getInt("idConductor");
            } else {
                consultaSQL = "INSERT INTO Conductor (nombre, licencia) VALUES (?, ?)";
                try (PreparedStatement sentenciaInsert = conexion.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {
                    sentenciaInsert.setString(1, conductor.getNombre());
                    sentenciaInsert.setString(2, conductor.getLicencia());
                    sentenciaInsert.executeUpdate();
                    ResultSet resultadoInsert = sentenciaInsert.getGeneratedKeys();
                    if (resultadoInsert.next()) {
                        return resultadoInsert.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar o crear al conductor: " + e.getMessage());
        }
        return -1;
    }

    private int obtenerOCrearCliente(Cliente cliente, Connection conexion) {
        String consultaSQL = "SELECT idCliente FROM Cliente WHERE nombre = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {
            sentencia.setString(1, cliente.getNombre());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return resultado.getInt("idCliente");
            } else {
                consultaSQL = "INSERT INTO Cliente (nombre, direccion, telefono) VALUES (?, ?, ?)";
                try (PreparedStatement sentenciaInsert = conexion.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {
                    sentenciaInsert.setString(1, cliente.getNombre());
                    sentenciaInsert.setString(2, cliente.getDireccion());
                    sentenciaInsert.setString(3, cliente.getTelefono());
                    sentenciaInsert.executeUpdate();
                    ResultSet resultadoInsert = sentenciaInsert.getGeneratedKeys();
                    if (resultadoInsert.next()) {
                        return resultadoInsert.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar o crear al cliente: " + e.getMessage());
        }
        return -1;
    }


    @Override
    public List<Viaje> obtenerTodos() {
        List<Viaje> viajes = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            try {
                statement = conexion.prepareStatement("SELECT \n" +
                        "    Viaje.idViaje,\n" +
                        "    Cliente.nombre AS nombreCliente,\n" +
                        "    Cliente.direccion,\n" +
                        "    Cliente.telefono,\n" +
                        "    Conductor.nombre AS nombreConductor,\n" +
                        "    Conductor.licencia,\n" +
                        "   Conductor.idConductor,\n" +
                        "   Cliente.idCliente,\n" +
                        "    Viaje.fechaInicio,\n" +
                        "    Viaje.fechaFin,\n" +
                        "    Viaje.origen,\n" +
                        "    Viaje.destino,\n" +
                        "    Viaje.carga\n" +
                        "FROM \n" +
                        "    Viaje\n" +
                        "JOIN \n" +
                        "    Cliente ON Viaje.idCliente = Cliente.idCliente\n" +
                        "JOIN \n" +
                        "    Conductor ON Viaje.idConductor = Conductor.idConductor;");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int idViaje = resultSet.getInt("idViaje");
                    int idCliente = resultSet.getInt("idCliente");
                    int idConductor = resultSet.getInt("idConductor");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    String origen = resultSet.getString("origen");
                    String destino = resultSet.getString("destino");
                    String carga = resultSet.getString("carga");

                    Cliente cliente = new Cliente(idCliente, resultSet.getString("nombreCliente"), resultSet.getString("direccion"), resultSet.getString("telefono"));
                    Conductor conductor = new Conductor(idConductor, resultSet.getString("nombreConductor"), resultSet.getString("licencia"));

                    viajes.add(new Viaje(idViaje, cliente, conductor, fechaInicio, fechaFin, origen, destino, carga));
                }

            } catch (SQLException e) {
                System.err.println("Error al consultar l1os viajes: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error de conexi�n a la base de datos: " + e.getMessage());
        }
        return viajes;
    }

    @Override
    public List<Viaje> obtenerViajesPorConductor(Conductor conductor) {
        List<Viaje> viajes = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            try {
                statement = conexion.prepareStatement("SELECT \n" +
                        "    Viaje.idViaje,\n" +
                        "    Cliente.nombre AS nombreCliente,\n" +
                        "    Cliente.direccion,\n" +
                        "    Cliente.telefono,\n" +
                        "    Conductor.nombre AS nombreConductor,\n" +
                        "    Conductor.licencia,\n" +
                        "   Conductor.idConductor,\n" +
                        "   Cliente.idCliente,\n" +
                        "    Viaje.fechaInicio,\n" +
                        "    Viaje.fechaFin,\n" +
                        "    Viaje.origen,\n" +
                        "    Viaje.destino,\n" +
                        "    Viaje.carga\n" +
                        "FROM \n" +
                        "    Viaje\n" +
                        "JOIN \n" +
                        "    Cliente ON Viaje.idCliente = Cliente.idCliente\n" +
                        "JOIN \n" +
                        "    Conductor ON Viaje.idConductor = Conductor.idConductor\n" +
                        "WHERE \n" +
                        "    Conductor.nombre = ?;");
                statement.setString(1, conductor.getNombre());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int idViaje = resultSet.getInt("idViaje");
                    int idCliente = resultSet.getInt("idCliente");
                    Date fechaInicio = resultSet.getDate("fechaInicio");
                    Date fechaFin = resultSet.getDate("fechaFin");
                    String origen = resultSet.getString("origen");
                    String destino = resultSet.getString("destino");
                    String carga = resultSet.getString("carga");

                    Cliente cliente = new Cliente(idCliente, resultSet.getString("nombreCliente"), resultSet.getString("direccion"), resultSet.getString("telefono"));


                    viajes.add(new Viaje(idViaje, cliente, conductor, fechaInicio, fechaFin, origen, destino, carga));
                }

            } catch (SQLException e) {
                System.err.println("Error al consultar los viajes: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error de conexi�n a la base de datos: " + e.getMessage());
        }
        return viajes;
    }


}

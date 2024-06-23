package com.mchediek.logisis;

import com.mchediek.logisis.controller.ControladorDeViajes;
import com.mchediek.logisis.controller.GestorDeViajes;
import com.mchediek.logisis.model.Cliente;
import com.mchediek.logisis.model.Conductor;
import com.mchediek.logisis.model.Viaje;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final GestorDeViajes gestorDeViajes = new ControladorDeViajes();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) throws ColicionDeFechasConductorExcepcion {

        int opcion;
        do {
            System.out.println(ANSI_GREEN + "###################################");
            System.out.println("1- Agregar viaje");
            System.out.println("2- Listar viajes");
            System.out.println("3- Finalizar");
            System.out.println("###################################" + ANSI_RESET);
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            switch (opcion) {
                case 1:
                    agregarViaje();
                    break;
                case 2:
                    listarViajes();
                    break;
                case 3:
                    System.out.println("Saliendo del menú.");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void agregarViaje() throws ColicionDeFechasConductorExcepcion {

        Cliente cliente = CrearCliente();

        Conductor conductor = crearConductor();

        Viaje viaje = crearViaje(cliente, conductor);

        try {
            gestorDeViajes.agregarViaje(viaje);
            System.out.println("Viaje agregado exitosamente.");
            System.out.println();
        } catch (ColicionDeFechasConductorExcepcion e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
        }

    }

    private static Viaje crearViaje(Cliente cliente, Conductor conductor) {
        Date fechaInicio = ObtenerUnaFechaValida("Ingrese la fecha de inicio del viaje (yyyy-mm-dd): ");
        Date fechaFin = ObtenerUnaFechaValida("Ingrese la fecha de fin del viaje (yyyy-mm-dd): ");
        while (fechaInicio.after(fechaFin)) {
            System.out.println(ANSI_RED + "La fecha de inicio no puede ser posterior a la fecha de fin." + ANSI_RESET);
            fechaInicio = ObtenerUnaFechaValida("Ingrese la fecha de inicio del viaje (yyyy-mm-dd): ");
            fechaFin = ObtenerUnaFechaValida("Ingrese la fecha de fin del viaje (yyyy-mm-dd): ");
        }
        String origen = ObtenerUnStringNoVacio("Ingrese el origen del viaje: ");
        String destino = ObtenerUnStringNoVacio("Ingrese el destino del viaje: ");
        String carga = ObtenerUnStringNoVacio("Ingrese la carga del viaje: ");

        return new Viaje(gestorDeViajes.listarViajes().size() + 1, cliente, conductor, fechaInicio, fechaFin, origen, destino, carga);
    }

    private static Conductor crearConductor() {
        String nombreConductor = ObtenerUnStringNoVacio("Ingrese el nombre del conductor: ");
        String licenciaConductor = ObtenerUnStringNoVacio("Ingrese la licencia del conductor: ");
        return new Conductor( nombreConductor, licenciaConductor);
    }

    private static Cliente CrearCliente() {
        String nombreCliente = ObtenerUnStringNoVacio("Ingrese el nombre del cliente: ");
        String direccionCliente = ObtenerUnStringNoVacio("Ingrese la dirección del cliente: ");
        String telefonoCliente = ObtenerUnStringNoVacio("Ingrese el teléfono del cliente: ");
        return new Cliente( nombreCliente, direccionCliente, telefonoCliente);
    }

    private static void listarViajes() {
        List<Viaje> viajes = gestorDeViajes.listarViajes();

        if (viajes.isEmpty()) {
            System.out.println(ANSI_PURPLE + "No hay viajes registrados." + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_PURPLE + "-----------------------------------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "Viajes:" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "-----------------------------------" + ANSI_RESET);

        int i = 1;
        for (Viaje viaje : viajes) {
            MostrarViaje(viaje, i % 2 == 0 ? ANSI_BLUE : ANSI_CYAN);
            i++;
        }
        System.out.println();
    }

    private static void MostrarViaje(Viaje viaje, String color) {
        String viajeStr = String.format("ID: %d\nCliente: %s\nConductor: %s\nFecha de inicio: %s\nFecha de fin: %s\nOrigen: %s\nDestino: %s\nCarga: %s",
                viaje.getIdViaje(),
                viaje.getCliente().getNombre(),
                viaje.getConductor().getNombre(),
                formatter.format(viaje.getFechaInicio()),
                formatter.format(viaje.getFechaFin()),
                viaje.getOrigen(),
                viaje.getDestino(),
                viaje.getCarga());
        System.out.println(color + viajeStr + ANSI_RESET);
    }

    private static String ObtenerUnStringNoVacio(String mensaje) {

        return ObtenerUnStringValido(mensaje, "Deber ingresar al menos dos caracteres.", "[\\S\\s]+[\\S]+");
    }

    private static Date ObtenerUnaFechaValida(String mensaje) {

        String fechaStr = ObtenerUnStringValido(mensaje, "Formato invalido", "\\d{4}-\\d{2}-\\d{2}");

        try {
            return formatter.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println(ANSI_RED + "Fecha inválida." + ANSI_RESET);
            return ObtenerUnaFechaValida(mensaje);
        }
    }

    private static String ObtenerUnStringValido(String mensaje, String mensageDeError, String regex) {
        System.out.print(mensaje);
        String input = scanner.nextLine();
        while (!input.matches(regex)) {
            System.out.println(ANSI_RED + mensageDeError + ANSI_RESET);
            System.out.print(mensaje);
            input = scanner.nextLine();
        }
        return input;
    }


}

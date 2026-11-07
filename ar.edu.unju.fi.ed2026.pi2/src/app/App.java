package app;

import java.util.Scanner;

import Helper.Helper;
import model.Jugador;
import operations.Juego;
import operations.Inscripcion;
import operations.Ranking;

public class App {

    private static final int JUGADORES_POR_PARTIDA = 4;

    public static void main(String[] args) {
        Inscripcion inscripcion = new Inscripcion();
        Ranking ranking = new Ranking();
        Juego juego = new Juego(inscripcion.getJugadoresDisponibles(), ranking);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                String opcion = scanner.nextLine().trim();
                switch (opcion) {
                case "1":
                        // Registra participantes dinámicamente guardando DNI, Nombre, Apellido y Edad
                        Jugador jugadorInscripto = inscripcion.inscribirParticipante(scanner);
                        if (jugadorInscripto != null) {
                            ranking.actualizar(jugadorInscripto, jugadorInscripto.getPuntaje());
                        }
                        break;

                    case "2":
                        // Validar que haya al menos 4 jugadores inscriptos para poder jugar
                        if (hayJugadoresSuficientes(inscripcion)) {
                            juego.iniciarPartidaAleatoria();
                            ranking.mostrarRanking();
                        }
                        break;

                    case "3":
                        // El operador elige uno por uno los 4 jugadores, escribiendo su id
                        if (hayJugadoresSuficientes(inscripcion)) {
                            Jugador[] elegidos = elegirJugadores(scanner, inscripcion);
                            if (elegidos != null) {
                                juego.iniciarPartida(elegidos);
                                ranking.mostrarRanking();
                            }
                        }
                        break;

                    case "4":
                        ranking.mostrarRanking();
                        break;

                    case "0":
                        System.out.println("Gracias por participar! Fin del programa.");
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción inválida. Elija 1, 2, 3, 4 o 0.");
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=================================");
        System.out.println("   PROYECTO INTEGRADOR II - JUEGO");
        System.out.println("=================================");
        System.out.println("1. Inscribir nuevo participante");
        System.out.println("2. Iniciar partida aleatoria (4 jugadores)");
        System.out.println("3. Iniciar partida eligiendo los 4 jugadores");
        System.out.println("4. Mostrar ranking de la jornada");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private static boolean hayJugadoresSuficientes(Inscripcion inscripcion) {
        int inscriptos = inscripcion.getJugadoresDisponibles().size();
        if (inscriptos < JUGADORES_POR_PARTIDA) {
            System.out.println("Error: Se necesitan al menos " + JUGADORES_POR_PARTIDA
                    + " jugadores inscriptos para iniciar una partida.");
            System.out.println("Actualmente hay " + inscriptos + " jugador(es) inscripto(s).");
            return false;
        }
        return true;
    }

    private static void mostrarInscriptos(Inscripcion inscripcion) {
        System.out.println("Jugadores inscriptos:");
        for (Jugador jugador : inscripcion.getJugadoresDisponibles()) {
            System.out.println("  id " + jugador.getId() + " - " + jugador.getNombre() + " "
                    + jugador.getApellido() + " (DNI " + jugador.getDni() + ") - Puntaje: "
                    + jugador.getPuntaje());
        }
    }

    /* Devuelve los 4 jugadores elegidos por id, o null si el usuario cancela. */
    private static Jugador[] elegirJugadores(Scanner scanner, Inscripcion inscripcion) {
        Jugador[] elegidos = new Jugador[JUGADORES_POR_PARTIDA];
        int cantidad = 0;

        System.out.println("\n=== SELECCIÓN DE JUGADORES ===");
        mostrarInscriptos(inscripcion);

        while (cantidad < JUGADORES_POR_PARTIDA) {
            int id = Helper.nextInteger(scanner,
                    "Ingrese el id del jugador " + (cantidad + 1) + " de " + JUGADORES_POR_PARTIDA
                            + " (0 para cancelar): ",
                    "Error: Debe ingresar un número entero.");

            if (id == 0) {
                System.out.println("Selección cancelada.");
                return null;
            }

            Jugador jugador = inscripcion.buscarPorNumeroJugador(id);
            if (jugador == null) {
                System.out.println("Error: No existe un jugador inscripto con el id " + id + ".");
                continue;
            }
            if (yaElegido(elegidos, cantidad, id)) {
                System.out.println("Error: El jugador con id " + id + " ya fue elegido. Elija otro.");
                continue;
            }

            elegidos[cantidad++] = jugador;
            System.out.println("Elegido: " + jugador.getNombre() + " " + jugador.getApellido()
                    + " (id " + jugador.getId() + ")");
        }
        return elegidos;
    }

    private static boolean yaElegido(Jugador[] elegidos, int cantidad, int id) {
        for (int i = 0; i < cantidad; i++) {
            if (elegidos[i].getId() == id) {
                return true;
            }
        }
        return false;
    }
}
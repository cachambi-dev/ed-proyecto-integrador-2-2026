package app;

import java.util.Scanner;

import model.Jugador;
import operations.Juego;
import operations.Inscripcion;
import operations.Ranking;

public class App {

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
                        if (inscripcion.getJugadoresDisponibles().size() < 4) {
                            System.out.println("Error: Se necesitan al menos 4 jugadores inscriptos para iniciar una partida.");
                            System.out.println("Actualmente hay " + inscripcion.getJugadoresDisponibles().size() + " jugador(es) inscripto(s).");
                        } else {
                            juego.iniciarPartidaAleatoria();
                            ranking.mostrarRanking();
                        }
                        break;

                    case "3":
                        ranking.mostrarRanking();
                        break;

                    case "0":
                        System.out.println("¡Gracias por participar!");
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción inválida. Elija 1, 2, 3 o 0.");
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
        System.out.println("3. Mostrar ranking de la jornada");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }
}

package app;

import java.util.Scanner;

import model.Jugador;
import operations.Juego;
import operations.Ranking;
import structures.SimpleLinkedList;

public class App {

    public static void main(String[] args) {
        SimpleLinkedList<Jugador> jugadores = crearJugadoresDePrueba();
        Ranking ranking = new Ranking();
        Juego juego = new Juego(jugadores, ranking);

        System.out.println("Modo de prueba: hay cuatro jugadores cargados en memoria.");
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n1. Iniciar partida aleatoria");
                System.out.println("2. Mostrar ranking");
                System.out.println("0. Salir");
                System.out.print("Opción: ");

                String opcion = scanner.nextLine().trim();
                switch (opcion) {
                    case "1":
                        juego.iniciarPartidaAleatoria();
                        ranking.mostrarRanking();
                        break;
                    case "2":
                        ranking.mostrarRanking();
                        break;
                    case "0":
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Elegí 1, 2 o 0.");
                }
            }
        }
    }

    private static SimpleLinkedList<Jugador> crearJugadoresDePrueba() {
        SimpleLinkedList<Jugador> jugadores = new SimpleLinkedList<>();
        jugadores.addLast(new Jugador("Ana", "Pérez"));
        jugadores.addLast(new Jugador("Luis", "Gómez"));
        jugadores.addLast(new Jugador("Mara", "López"));
        jugadores.addLast(new Jugador("Tomás", "Díaz"));
        return jugadores;
    }
}

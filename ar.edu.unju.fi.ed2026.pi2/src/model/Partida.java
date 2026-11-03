package model;

import java.util.Objects;

import structures.Queue;
import structures.QueueLinkedList;
import structures.StackLinkedList;

public class Partida {

    private static final int CANTIDAD_JUGADORES = 4;
    private static final int CANTIDAD_RONDAS = 3;
    private static final int CARTAS_POR_JUGADOR = 3;

    private final StackLinkedList<Naipe> mazo;
    private final int numeroPartida;
    private final Jugador[] jugadores;

    public Partida(int numeroPartida, StackLinkedList<Naipe> mazo, Jugador[] jugadores) {
        if (numeroPartida < 1) {
            throw new IllegalArgumentException("El número de partida debe ser positivo.");
        }
        this.numeroPartida = numeroPartida;
        this.mazo = Objects.requireNonNull(mazo, "El mazo no puede ser null");
        this.jugadores = validarJugadores(jugadores);
    }

    public void partidaNueva() {
        if (mazo.size() < CANTIDAD_JUGADORES * CARTAS_POR_JUGADOR * CANTIDAD_RONDAS) {
            throw new IllegalStateException("El mazo no tiene cartas suficientes para completar la partida.");
        }

        for (Jugador jugador : jugadores) {
            jugador.setManoActual(new Queue<>(CARTAS_POR_JUGADOR));
            jugador.setNaipesGanados(new QueueLinkedList<>());
        }

        System.out.println("Iniciando partida N° " + numeroPartida);
        for (int numeroRonda = 1; numeroRonda <= CANTIDAD_RONDAS; numeroRonda++) {
            Ronda ronda = new Ronda(numeroPartida, numeroRonda, jugadores, mazo);
            ronda.jugar();
        }

        for (Jugador jugador : jugadores) {
            jugador.calcularPuntaje();
            System.out.println(jugador.getNombre() + " " + jugador.getApellido()
                    + " terminó la partida con " + jugador.getPuntaje() + " puntos.");
        }
    }

    public int getNumeroPartida() {
        return numeroPartida;
    }

    public Jugador[] getJugadores() {
        return jugadores.clone();
    }

    private Jugador[] validarJugadores(Jugador[] participantes) {
        Objects.requireNonNull(participantes, "Los jugadores no pueden ser null");
        if (participantes.length != CANTIDAD_JUGADORES) {
            throw new IllegalArgumentException("La partida requiere exactamente cuatro jugadores.");
        }

        Jugador[] copia = participantes.clone();
        for (int i = 0; i < copia.length; i++) {
            Objects.requireNonNull(copia[i], "El jugador no puede ser null");
            for (int j = 0; j < i; j++) {
                if (copia[i].getId() == copia[j].getId()) {
                    throw new IllegalArgumentException("Los jugadores de una partida deben ser distintos.");
                }
            }
        }
        return copia;
    }
}

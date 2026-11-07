package operations;

import java.util.Objects;
import java.util.Random;

import model.Jugador;
import model.Partida;
import structures.SimpleLinkedList;

/** Coordina el inicio de partidas y la actualización del ranking. */
public class Juego {

    private static final int CANTIDAD_JUGADORES = 4;

    private final SimpleLinkedList<Jugador> jugadoresDisponibles;
    private final Ranking ranking;
    private final Random random;
    private static int numeroPartida; //static because the variable must be unitary, should >1 Games be instanced
    private boolean partidaEnCurso;

    /**
     * Crea un juego con la lista de inscriptos y el ranking que administrará.
     *
     * @param jugadoresDisponibles jugadores que pueden participar
     * @param ranking ranking que acumula el mejor puntaje de cada jugador
     * @throws NullPointerException si alguno de los argumentos es {@code null}
     */
    public Juego(SimpleLinkedList<Jugador> jugadoresDisponibles, Ranking ranking) {
        this.jugadoresDisponibles = Objects.requireNonNull(jugadoresDisponibles,
                "La lista de jugadores no puede ser null");
        this.ranking = Objects.requireNonNull(ranking, "El ranking no puede ser null");
        this.random = new Random();
    }
    
    /**
     * Inicia una partida con los cuatro jugadores seleccionados.
     *
     * @param seleccionados jugadores que participarán en la partida
     * @return partida ejecutada
     * @throws NullPointerException si la selección o uno de sus jugadores es {@code null}
     * @throws IllegalArgumentException si la selección no contiene cuatro jugadores
     *         inscriptos y distintos
     * @throws IllegalStateException si ya hay una partida en curso
     */
    public Partida iniciarPartida(Jugador[] seleccionados) {
        if (partidaEnCurso) {
            throw new IllegalStateException("Ya hay una partida en curso.");
        }

        Jugador[] participantes = validarSeleccion(seleccionados);
        Partida partida = new Partida(++numeroPartida, participantes);

        partidaEnCurso = true;
        try {
            partida.jugar();
            actualizarRanking(participantes);
        } finally {
            partidaEnCurso = false;
        }

        return partida;
    }

    /**
     * Selecciona cuatro jugadores inscriptos al azar e inicia una partida.
     *
     * @return partida ejecutada
     * @throws IllegalStateException si hay menos de cuatro jugadores disponibles
     *         o ya existe una partida en curso
     */
    public Partida iniciarPartidaAleatoria() {
        return iniciarPartida(seleccionarAleatoriamente());
    }

    private Jugador[] validarSeleccion(Jugador[] seleccionados) {
        Objects.requireNonNull(seleccionados, "La selección no puede ser null");
        if (seleccionados.length != CANTIDAD_JUGADORES) {
            throw new IllegalArgumentException("La partida requiere exactamente cuatro jugadores.");
        }

        Jugador[] participantes = new Jugador[CANTIDAD_JUGADORES];
        for (int i = 0; i < seleccionados.length; i++) {
            Jugador solicitado = Objects.requireNonNull(seleccionados[i], "El jugador no puede ser null");
            for (int j = 0; j < i; j++) {
                if (seleccionados[j].getId() == solicitado.getId()) {
                    throw new IllegalArgumentException("No se puede repetir un jugador en la partida.");
                }
            }

            participantes[i] = buscarJugadorRegistrado(solicitado.getId());
            if (participantes[i] == null) {
                throw new IllegalArgumentException("Todos los participantes deben estar inscriptos.");
            }
        }
        return participantes;
    }

    private Jugador[] seleccionarAleatoriamente() {
        Jugador[] unicos = new Jugador[jugadoresDisponibles.size()];
        int cantidad = 0;
        for (Jugador jugador : jugadoresDisponibles) {
            if (jugador != null && buscarEnArreglo(unicos, cantidad, jugador.getId()) == null) {
                unicos[cantidad++] = jugador;
            }
        }

        if (cantidad < CANTIDAD_JUGADORES) {
            throw new IllegalStateException("Se necesitan cuatro jugadores inscriptos para iniciar.");
        }

        for (int i = cantidad - 1; i > 0; i--) {
            int indice = random.nextInt(i + 1);
            Jugador temporal = unicos[i];
            unicos[i] = unicos[indice];
            unicos[indice] = temporal;
        }

        Jugador[] seleccionados = new Jugador[CANTIDAD_JUGADORES];
        System.arraycopy(unicos, 0, seleccionados, 0, CANTIDAD_JUGADORES);
        return seleccionados;
    }

    private Jugador buscarJugadorRegistrado(int id) {
        for (Jugador jugador : jugadoresDisponibles) {
            if (jugador != null && jugador.getId() == id) {
                return jugador;
            }
        }
        return null;
    }

    private Jugador buscarEnArreglo(Jugador[] jugadores, int cantidad, int id) {
        for (int i = 0; i < cantidad; i++) {
            if (jugadores[i].getId() == id) {
                return jugadores[i];
            }
        }
        return null;
    }

    private void actualizarRanking(Jugador[] participantes) {
        for (Jugador jugador : participantes) {
            ranking.actualizar(jugador, jugador.getPuntaje());
            jugador.setPuntaje(ranking.consultarPuntaje(jugador));
        }
    }
}

package operations;

import java.util.Objects;

import model.Jugador;
import structures.BinarySearchTree;
import structures.SimpleLinkedList;

/**
 * Mantiene el mejor puntaje de cada jugador y permite mostrar el ranking
 * ordenado de mayor a menor usando el árbol binario de búsqueda propio.
 * Cada jugador se identifica por su id único, no por la instancia del objeto.
 */
public class Ranking {

    private final BinarySearchTree<EntradaRanking> ranking;
    private final SimpleLinkedList<EntradaRanking> entradas;
    private long siguienteOrden;

    /** Crea un ranking vacío. */
    public Ranking() {
        this.ranking = new BinarySearchTree<>();
        this.entradas = new SimpleLinkedList<>();
        this.siguienteOrden = 0;
    }

    /**
     * Agrega un jugador o conserva y actualiza su mejor puntaje.
     * Los resultados menores o iguales al mejor puntaje registrado se ignoran.
     *
     * @param jugador jugador que se incorpora o actualiza
     * @param puntajeDeJugador puntaje obtenido en la partida
     * @throws NullPointerException si {@code jugador} es {@code null}
     * @throws IllegalArgumentException si el puntaje es negativo
     */
    public void actualizar(Jugador jugador, int puntajeDeJugador) {
        Objects.requireNonNull(jugador, "El jugador no puede ser null");
        if (puntajeDeJugador < 0) {
            throw new IllegalArgumentException("El puntaje no puede ser negativo");
        }

        EntradaRanking entrada = buscarEntrada(jugador.getId());
        if (entrada == null) {
            entrada = new EntradaRanking(jugador, puntajeDeJugador, siguienteOrden++);
            entradas.addLast(entrada);
            ranking.add(entrada);
            return;
        }

        // Mantiene los datos más recientes del jugador asociado a ese id.
        entrada.setJugador(jugador);

        if (puntajeDeJugador <= entrada.getPuntaje()) {
            return;
        }

        // Se quita antes de cambiar la clave para no dejar desordenado el ABB.
        ranking.remove(entrada);
        entrada.setPuntaje(puntajeDeJugador);
        ranking.add(entrada);
    }

    /**
     * Devuelve el mejor puntaje registrado para un jugador.
     *
     * @param jugador jugador cuyo puntaje se desea consultar
     * @return mejor puntaje registrado, o {@code null} si todavía no está en el ranking
     * @throws NullPointerException si {@code jugador} es {@code null}
     */
    public Integer consultarPuntaje(Jugador jugador) {
        Objects.requireNonNull(jugador, "El jugador no puede ser null");
        EntradaRanking entrada = buscarEntrada(jugador.getId());
        return entrada == null ? null : entrada.getPuntaje();
    }

    /** Muestra por consola los jugadores ordenados de mayor a menor puntaje. */
    public void mostrarRanking() {
        if (entradas.size() == 0) {
            System.out.println("El ranking todavía no tiene jugadores.");
            return;
        }

        System.out.println("=== RANKING ===");
        ranking.DescendingOrder();
        System.out.println();
    }

    /**
     * Devuelve la cantidad de jugadores incluidos en el ranking.
     *
     * @return cantidad de jugadores registrados
     */
    public int cantidadJugadores() {
        return entradas.size();
    }

    private EntradaRanking buscarEntrada(int idJugador) {
        for (EntradaRanking entrada : entradas) {
            if (entrada.getJugador().getId() == idJugador) {
                return entrada;
            }
        }
        return null;
    }
    // Una ficha interna que nos permite mantener el puntaje de cada jugador y el orden en que se incorporó al ranking.
    private static final class EntradaRanking implements Comparable<EntradaRanking> {
        private Jugador jugador;
        private final long orden;
        private int puntaje;

        private EntradaRanking(Jugador jugador, int puntaje, long orden) {
            this.jugador = jugador;
            this.puntaje = puntaje;
            this.orden = orden;
        }

        private Jugador getJugador() {
            return jugador;
        }

        private void setJugador(Jugador jugador) {
            this.jugador = jugador;
        }

        private int getPuntaje() {
            return puntaje;
        }

        private void setPuntaje(int puntaje) {
            this.puntaje = puntaje;
        }

        @Override
        public int compareTo(EntradaRanking otra) {
            int comparacionPuntaje = Integer.compare(this.puntaje, otra.puntaje);
            if (comparacionPuntaje != 0) {
                return comparacionPuntaje;
            }

            return Long.compare(otra.orden, this.orden);
        }

        @Override
        public String toString() {
            return System.lineSeparator() + jugador.getNombre() + " " + jugador.getApellido()
                    + " (id " + jugador.getId() + ") - Puntaje: " + puntaje;
        }
    }

}

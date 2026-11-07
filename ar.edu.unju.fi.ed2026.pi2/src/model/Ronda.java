package model;

import java.util.Arrays;
import java.util.Objects;

import structures.Queue;
import structures.SimpleLinkedList;
import structures.StackLinkedList;

/** Representa una ronda y resuelve el reparto, puntaje y ganador. */
public class Ronda {
	
    /** Cantidad de cartas que recibe cada jugador en una ronda. */
    public static final int CARTAS_POR_RONDA = 3;
	
    private int numeroPartida;
    private int numeroRonda;
    private Jugador[] participantes;
    private int[] puntajes;
    private SimpleLinkedList<Jugador> ganadores;
    private StackLinkedList<Naipe> mazo;

    /**
     * Crea una ronda con sus participantes y el mazo disponible.
     *
     * @param numero número de la partida
     * @param numeroRonda número de la ronda dentro de la partida
     * @param participantes jugadores que participan en la ronda
     * @param mazo mazo del que se repartirán las cartas
     * @throws NullPointerException si los participantes o el mazo son {@code null}
     */
    public Ronda(int numero, int numeroRonda, Jugador[] participantes, StackLinkedList<Naipe> mazo) {
        this.numeroPartida = numero;
        this.numeroRonda = numeroRonda;
        this.participantes = Objects.requireNonNull(participantes, "Los participantes no pueden ser null");
        this.mazo = Objects.requireNonNull(mazo, "El mazo no puede ser null");
        this.puntajes = new int[participantes.length];
        this.ganadores = new SimpleLinkedList<>();
    }

    /**
     * Devuelve el número de la partida a la que pertenece la ronda.
     *
     * @return número de partida
     */
    public int getNumero() {
        return numeroPartida;
    }

    /**
     * Modifica el número de partida asociado.
     *
     * @param numero nuevo número de partida
     */
    public void setNumero(int numero) {
        this.numeroPartida = numero;
    }

    /**
     * Devuelve el número de la ronda.
     *
     * @return número de ronda
     */
    public int getNumeroRonda() {
        return numeroRonda;
    }

    /**
     * Modifica el número de la ronda.
     *
     * @param numeroRonda nuevo número de ronda
     */
    public void setNumeroRonda(int numeroRonda) {
        this.numeroRonda = numeroRonda;
    }

    /**
     * Devuelve una copia de los participantes.
     *
     * @return copia del arreglo de participantes
     */
    public Jugador[] getParticipantes() {
        return participantes.clone();
    }

    /**
     * Reemplaza los participantes y reinicia sus puntajes de ronda.
     *
     * @param participantes nuevos participantes
     * @throws NullPointerException si {@code participantes} es {@code null}
     */
    public void setParticipantes(Jugador[] participantes) {
        this.participantes = Objects.requireNonNull(participantes, "Los participantes no pueden ser null");
        this.puntajes = new int[participantes.length];
    }

    /**
     * Devuelve la lista de ganadores de la ronda.
     *
     * @return lista de ganadores
     */
    public SimpleLinkedList<Jugador> getGanadores() {
        return ganadores;
    }

    /**
     * Reemplaza la lista de ganadores de la ronda.
     *
     * @param ganadores nueva lista de ganadores
     * @throws NullPointerException si {@code ganadores} es {@code null}
     */
    public void setGanadores(SimpleLinkedList<Jugador> ganadores) {
        this.ganadores = Objects.requireNonNull(ganadores, "La lista de ganadores no puede ser null");
    }

    /**
     * Devuelve una copia de los puntajes calculados para los participantes.
     *
     * @return copia de los puntajes
     */
    public int[] getPuntajes() {
        return puntajes.clone();
    }

    /**
     * Indica si la carta recibida es un as.
     *
     * @param n carta que se desea evaluar
     * @return {@code true} si la carta es un as
     */
    public boolean esAs(Naipe n) {
        return n != null && n.getValor() == 1;
    }

    /**
     * Indica si la carta recibida es un comodín.
     *
     * @param n carta que se desea evaluar
     * @return {@code true} si la carta es un comodín
     */
    public boolean esComodin(Naipe n) {
        return n != null && "Comodin".equalsIgnoreCase(n.getPalo());
    }

    /**
     * Indica si el jugador tiene al menos un as en su mano actual.
     *
     * @param j jugador que se desea evaluar
     * @return {@code true} si la mano contiene un as
     */
    public boolean tieneAs(Jugador j) {
        for (Naipe n : j.getManoActual()) {
            if (esAs(n)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si el jugador tiene al menos un comodín en su mano actual.
     *
     * @param j jugador que se desea evaluar
     * @return {@code true} si la mano contiene un comodín
     */
    public boolean tieneComodin(Jugador j) {
        for (Naipe n : j.getManoActual()) {
            if (esComodin(n)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula la suma de la mano y duplica el resultado si contiene un as.
     *
     * @param j jugador cuyo puntaje se calcula
     * @return puntaje calculado
     */
    public int calcularPuntajeAs(Jugador j) {
        int puntaje = 0;
        for (Naipe n : j.getManoActual()) {
            if (n != null) {
                puntaje += n.getValor();
            }
        }
        return tieneAs(j) ? puntaje * 2 : puntaje;
    }

    /**
     * Reparte cartas, resuelve el ganador y conserva las cartas obtenidas.
     *
     * @throws IllegalStateException si el mazo no tiene cartas suficientes
     */
    public void jugarRonda() {
        repartirCartas();
        resolverGanador();
        conservarCartas();
        mostrarResultado();
    }

    private void repartirCartas() {
        int cartasNecesarias = participantes.length * CARTAS_POR_RONDA;
        if (mazo.size() < cartasNecesarias) {
            throw new IllegalStateException("No hay cartas suficientes para repartir la ronda.");
        }

        for (Jugador jugador : participantes) {
            jugador.setManoActual(new Queue<>(CARTAS_POR_RONDA));
        }

        for (Jugador jugador : participantes) {
            for (int i = 0; i < CARTAS_POR_RONDA; i++) {
                Naipe carta = mazo.pop();
                carta.setDisponible(false);
                jugador.recibirNaipe(carta);
            }
        }
    }
    
    private void resolverGanador() {
        int cantComodin = 0;
        for (int i = 0; i < participantes.length; i++) {
            puntajes[i] = calcularPuntajeAs(participantes[i]);
            if (tieneComodin(participantes[i])) {
                cantComodin++;
            }
        }

        ganadores = new SimpleLinkedList<>();
        if (cantComodin == 1) {
            for (int i = 0; i < participantes.length; i++) {
                if (tieneComodin(participantes[i])) {
                    ganadores.addLast(participantes[i]);
                    return;
                }
            }
        }

        int maximo = -1;
        for (int i = 0; i < participantes.length; i++) {
            if (cantComodin > 1 && !tieneComodin(participantes[i])) {
                continue;
            }
            if (puntajes[i] > maximo) {
                maximo = puntajes[i];
            }
        }

        for (int i = 0; i < participantes.length; i++) {
            if (cantComodin > 1 && !tieneComodin(participantes[i])) {
                continue;
            }
            if (puntajes[i] == maximo) {
                ganadores.addLast(participantes[i]);
            }
        }
    }

    private void conservarCartas() {
        boolean hayEmpate = ganadores.size() > 1;
        Jugador ganadorUnico = hayEmpate ? null : ganadores.getFirst();

        for (Jugador jugador : participantes) {
            for (Naipe carta : jugador.getManoActual()) {
                if (carta != null) {
                    Jugador destino = hayEmpate ? jugador : ganadorUnico;
                    destino.guardarNaipeGanados(carta);
                }
            }
        }
    }

    private void mostrarResultado() {
        System.out.println("Ronda " + numeroRonda + " de la partida " + numeroPartida + ":");
        for (int i = 0; i < participantes.length; i++) {
            System.out.println(participantes[i].getNombre() + " " + participantes[i].getApellido()
                    + " obtuvo " + puntajes[i] + " puntos.");
        }
        System.out.print(ganadores.size() == 1 ? "Ganó: " : "Empate entre: ");
        for (Jugador ganador : ganadores) {
            System.out.print(ganador.getNombre() + " " + ganador.getApellido()
                    + " (id " + ganador.getId() + ") ");
        }
        System.out.println();
    }

    /**
     * Devuelve una representación textual del estado de la ronda.
     *
     * @return representación textual de la ronda
     */
    @Override
    public String toString() {
        return "Ronda [numeroPartida=" + numeroPartida + ", numeroRonda=" + numeroRonda + ", participantes="
                + Arrays.toString(participantes) + ", ganadores=" + ganadores + "]";
    }
}

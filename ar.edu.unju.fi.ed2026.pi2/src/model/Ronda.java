package model;

import java.util.Arrays;
import java.util.Objects;

import structures.Queue;
import structures.SimpleLinkedList;
import structures.StackLinkedList;

public class Ronda {
	
	public static final int CARTAS_POR_RONDA = 3; 
	
    private int numeroPartida;
    private int numeroRonda;
    private Jugador[] participantes;
    private int[] puntajes;
    private SimpleLinkedList<Jugador> ganadores;
    private StackLinkedList<Naipe> mazo;

    public Ronda(int numero, int numeroRonda, Jugador[] participantes, StackLinkedList<Naipe> mazo) {
        this.numeroPartida = numero;
        this.numeroRonda = numeroRonda;
        this.participantes = Objects.requireNonNull(participantes, "Los participantes no pueden ser null");
        this.mazo = Objects.requireNonNull(mazo, "El mazo no puede ser null");
        this.puntajes = new int[participantes.length];
        this.ganadores = new SimpleLinkedList<>();
    }

    public int getNumero() {
        return numeroPartida;
    }

    public void setNumero(int numero) {
        this.numeroPartida = numero;
    }

    public int getNumeroRonda() {
        return numeroRonda;
    }

    public void setNumeroRonda(int numeroRonda) {
        this.numeroRonda = numeroRonda;
    }

    public Jugador[] getParticipantes() {
        return participantes.clone();
    }

    public void setParticipantes(Jugador[] participantes) {
        this.participantes = Objects.requireNonNull(participantes, "Los participantes no pueden ser null");
        this.puntajes = new int[participantes.length];
    }

    public SimpleLinkedList<Jugador> getGanadores() {
        return ganadores;
    }

    public void setGanadores(SimpleLinkedList<Jugador> ganadores) {
        this.ganadores = Objects.requireNonNull(ganadores, "La lista de ganadores no puede ser null");
    }

    public int[] getPuntajes() {
        return puntajes.clone();
    }

    public boolean esAs(Naipe n) {
        return n != null && n.getValor() == 1;
    }

    public boolean esComodin(Naipe n) {
        return n != null && "Comodin".equalsIgnoreCase(n.getPalo());
    }

    public boolean tieneAs(Jugador j) {
        for (Naipe n : j.getManoActual()) {
            if (esAs(n)) {
                return true;
            }
        }
        return false;
    }

    public boolean tieneComodin(Jugador j) {
        for (Naipe n : j.getManoActual()) {
            if (esComodin(n)) {
                return true;
            }
        }
        return false;
    }

    public int calcularPuntajeAs(Jugador j) {
        int puntaje = 0;
        for (Naipe n : j.getManoActual()) {
            if (n != null) {
                puntaje += n.getValor();
            }
        }
        return tieneAs(j) ? puntaje * 2 : puntaje;
    }

    public void jugarRonda() {
        repartirCartas();
        resolverGanador();
        conservarCartas();
        mostrarResultado();
    }

    private void repartirCartas() {
        int cartasNecesarias = participantes.length * 3;
        if (mazo.size() < cartasNecesarias) {
            throw new IllegalStateException("No hay cartas suficientes para repartir la ronda.");
        }

        for (Jugador jugador : participantes) {
            jugador.setManoActual(new Queue<>(3));
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

    @Override
    public String toString() {
        return "Ronda [numeroPartida=" + numeroPartida + ", numeroRonda=" + numeroRonda + ", participantes="
                + Arrays.toString(participantes) + ", ganadores=" + ganadores + "]";
    }
}

package model;

import java.util.Objects;
import java.util.Random;

import structures.QueueLinkedList;
import structures.StackLinkedList;
import structures.SimpleLinkedList;

/** Coordina una partida completa, sus rondas, puntajes y ganadores. */
public class Partida {
	
	//constants
    private static final int CANTIDAD_JUGADORES = 4;
    private static final int CANTIDAD_RONDAS = 3;
    private static final int CANTIDAD_CARTAS = 54;
    
    //attributes
    private final StackLinkedList<Naipe> mazo;
    private final int numeroPartida;
    private final Jugador[] jugadores;
    private SimpleLinkedList<Jugador> ganadores = new SimpleLinkedList<>();
    private boolean jugada;
    
    //methods
    /**
     * Crea una partida usando un mazo proporcionado por el llamador.
     *
     * @param numeroPartida identificador positivo de la partida
     * @param mazo mazo con cartas suficientes para las rondas
     * @param jugadores exactamente cuatro jugadores distintos
     * @throws NullPointerException si el mazo o los jugadores son {@code null}
     * @throws IllegalArgumentException si el número, el mazo o los jugadores no son válidos
     */
    public Partida(int numeroPartida, StackLinkedList<Naipe> mazo, Jugador[] jugadores) {
        if (numeroPartida < 1) {
            throw new IllegalArgumentException("El número de partida debe ser positivo.");
        }
        this.numeroPartida = numeroPartida;
        this.mazo = validarMazo(mazo);
        this.jugadores = validarJugadores(jugadores);
    }
    
    
    /**
     * Crea una partida con un mazo completo mezclado aleatoriamente.
     *
     * @param numeroPartida identificador positivo de la partida
     * @param jugadores exactamente cuatro jugadores distintos
     * @throws NullPointerException si {@code jugadores} es {@code null}
     * @throws IllegalArgumentException si el número o los jugadores no son válidos
     */
    public Partida(int numeroPartida, Jugador[] jugadores) {
        if (numeroPartida < 1) {
            throw new IllegalArgumentException("El número de partida debe ser positivo.");
        }
        this.numeroPartida = numeroPartida;
        this.jugadores = validarJugadores(jugadores);
        this.mazo = crearMazoMezclado();
    }
    
    
    /**
     * Ejecuta las rondas, calcula los puntajes y determina los ganadores.
     *
     * @throws IllegalStateException si la partida ya fue ejecutada
     */
    public void jugar() {
    	if (jugada) {
    		throw new IllegalStateException("La partida ya fue jugada.");
    	}
    	jugada = true;
    	
    	System.out.println("Iniciando partida N° " + numeroPartida);
    	prepararJugadores();
    	jugarRondas();
    	calcularPuntajes();
    	ganadores = determinarGanadores();
    	mostrarGanadores();
    }
    
    
    private void prepararJugadores() {
        for (Jugador jugador : jugadores) {
            jugador.setNaipesGanados(new QueueLinkedList<>());
        }
    }
    
    private void jugarRondas() {
        for (int numeroRonda = 1; numeroRonda <= CANTIDAD_RONDAS; numeroRonda++) {
            Ronda ronda = new Ronda(numeroPartida, numeroRonda, jugadores, mazo);
            ronda.jugarRonda();
        }
    }
 
    private void calcularPuntajes() {
        for (Jugador jugador : jugadores) {
            jugador.calcularPuntaje();
            System.out.println(jugador.getNombre() + " " + jugador.getApellido()
                    + " terminó la partida con " + jugador.getPuntaje() + " puntos.");
        }
    }
 
    private SimpleLinkedList<Jugador> determinarGanadores() {
        int maximo = -1;
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntaje() > maximo) {
                maximo = jugador.getPuntaje();
            }
        } 
        SimpleLinkedList<Jugador> resultado = new SimpleLinkedList<>();
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntaje() == maximo) {
                resultado.addLast(jugador);
            }
        }
        return resultado;
    }
 
    
    private void mostrarGanadores() {
        System.out.print(ganadores.size() == 1 ? "Ganó la partida: " : "Empate entre: ");
        for (Jugador ganador : ganadores) {
            System.out.print(ganador.getNombre() + " " + ganador.getApellido() + " (id " 
            		+ ganador.getId() + ") con un puntaje de " + ganador.getPuntaje() + ". ");
        }
        System.out.println();
    }
   
    
    private StackLinkedList<Naipe> crearMazoMezclado() {
    	Random random = new Random();
        String[] palos = { "trebol", "corazon", "diamante", "pica" };
        Naipe[] cartas = new Naipe[CANTIDAD_CARTAS];
        int indice = 0;

        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                cartas[indice++] = new Naipe(palo, valor);
            }
        }
        cartas[indice++] = new Naipe("Comodin", 100);
        cartas[indice] = new Naipe("Comodin", 100);

        for (int i = cartas.length - 1; i > 0; i--) {
            int indiceAleatorio = random.nextInt(i + 1);
            Naipe temporal = cartas[i];
            cartas[i] = cartas[indiceAleatorio];
            cartas[indiceAleatorio] = temporal;
        }
        StackLinkedList<Naipe> mazo = new StackLinkedList<>();
        for (Naipe carta : cartas) {
            mazo.push(carta);
        }
        return mazo;
    }

    
    /**
     * Devuelve el número identificador de la partida.
     *
     * @return identificador de la partida
     */
    public int getNumeroPartida() {
        return numeroPartida;
    }

    /**
     * Devuelve una copia del arreglo de jugadores de la partida.
     *
     * @return copia del arreglo de jugadores
     */
    public Jugador[] getJugadores() {
        return jugadores.clone();
    }
    
    /**
     * Devuelve la lista de jugadores ganadores, posiblemente con empate.
     *
     * @return lista de ganadores
     */
    public SimpleLinkedList<Jugador> getGanadores() {
    	return ganadores;
    }

    
    private StackLinkedList<Naipe> validarMazo(StackLinkedList<Naipe> mazo) {
        Objects.requireNonNull(mazo, "El mazo no puede ser null");
        if (mazo.size() < CANTIDAD_JUGADORES * Ronda.CARTAS_POR_RONDA * CANTIDAD_RONDAS) {
            throw new IllegalArgumentException("El mazo no tiene cartas suficientes para completar la partida.");
        }
        return mazo;
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

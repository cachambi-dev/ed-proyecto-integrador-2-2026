package model;

import structures.Queue;
import structures.QueueLinkedList;

/** Representa a un participante y las cartas que recibe o gana durante el juego. */
public class Jugador {
    private static int siguienteId = 1;
    private final int id;
    private String dni;
    private int edad;
    private String nombre;
    private String apellido;
    private int puntaje;
    private Queue<Naipe> manoActual;
    private QueueLinkedList<Naipe> naipesGanados;

    /**
     * Crea un jugador con un identificador autoincremental y una mano vacía.
     *
     * @param nombre   nombre del jugador
     * @param apellido apellido del jugador
    * @param dni      documento nacional de identidad del jugador
    * @param edad     edad del jugador
     */
    public Jugador(String nombre, String apellido, String dni, int edad) {
        this.id = siguienteId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.puntaje = 0;
        this.manoActual = new Queue<>(Ronda.CARTAS_POR_RONDA);
        this.naipesGanados = new QueueLinkedList<>();
    }

    /**
     * Obtiene el identificador único del jugador.
     *
     * @return identificador del jugador
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el DNI del jugador.
     *
     * @return dni del jugador
     */
    public String getDni() {
    	return dni;
    }
    
    
    /**
     * Obtiene la edad del jugador.
     *
     * @return edad del jugador
     */
    public int getEdad() {
    	return edad;
    }
    
    /**
     * Obtiene el nombre del jugador.
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido del jugador.
     *
     * @return apellido del jugador
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el puntaje acumulado del jugador.
     *
     * @return puntaje acumulado
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene las cartas que el jugador tiene en su mano actual.
     *
     * @return cola de cartas de la mano actual
     */
    public Queue<Naipe> getManoActual() {
        return manoActual;
    }

    /**
     * Obtiene la cola de cartas ganadas por el jugador.
     *
     * @return cola de cartas ganadas
     */
    public QueueLinkedList<Naipe> getNaipesGanados() {
        return naipesGanados;
    }

    /**
     * Modifica el puntaje del jugador.
     *
     * @param puntaje nuevo puntaje del jugador
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Reemplaza la cola de cartas ganadas del jugador.
     *
     * @param naipesGanados nueva cola de cartas ganadas
     */
    public void setNaipesGanados(QueueLinkedList<Naipe> naipesGanados) {
        this.naipesGanados = naipesGanados;
    }

    /**
     * Reemplaza la cola que representa la mano actual del jugador.
     *
     * @param manoActual nueva cola de cartas de la mano
     */
    public void setManoActual(Queue<Naipe> manoActual) {
        this.manoActual = manoActual;
    }

    /**
     * Agrega una carta al final de la mano actual si todavía tiene menos de
     * tres cartas.
     *
     * @param naipe carta que recibe el jugador
     */
    public void recibirNaipe(Naipe naipe) {

        if (manoActual.size() < Ronda.CARTAS_POR_RONDA) {
            manoActual.add(naipe);
        }
    }

    /**
     * Agrega una carta al final de la cola de cartas ganadas.
     *
     * @param naipe carta ganada por el jugador
     */
    public void guardarNaipeGanados(Naipe naipe) {
        naipesGanados.add(naipe);
    }

    /**
     * Calcula el puntaje sumando el valor de todas las cartas ganadas.
     */
    public void calcularPuntaje() {
        int total = 0;
        for (Naipe naipe : naipesGanados) {
            total += naipe.getValor();
        }
        this.puntaje = total;
    }

    /**
     * Devuelve una representación textual del jugador y su estado actual.
     *
     * @return representación textual del jugador
     */
    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", puntaje=" + puntaje
                + ", manoActual=" + manoActual + ", naipesGanados=" + naipesGanados + "]";
    }

}

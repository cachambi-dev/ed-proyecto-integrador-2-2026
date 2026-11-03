package model;

import structures.SimpleLinkedList;

public class Jugador {
    private static int siguienteId = 1;
    private final int id;
    private String nombre;
    private String apellido;
    private int puntaje;
    private Naipe[] manoActual;
    private SimpleLinkedList<Naipe> naipesGanados;

    /**
     * Crea un jugador con un identificador autoincremental y una mano vacía.
     *
     * @param nombre   nombre del jugador
     * @param apellido apellido del jugador
     */
    public Jugador(String nombre, String apellido) {
        this.id = siguienteId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntaje = 0;
        this.manoActual = new Naipe[3];
        this.naipesGanados = new SimpleLinkedList<>();
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
     * @return arreglo de cartas de la mano actual
     */
    public Naipe[] getManoActual() {
        return manoActual;
    }

    /**
     * Obtiene la lista de cartas ganadas por el jugador.
     *
     * @return lista de cartas ganadas
     */
    public SimpleLinkedList<Naipe> getNaipesGanados() {
        return naipesGanados;
    }

    /**
     * Modifica el nombre del jugador.
     *
     * @param nombre nuevo nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el apellido del jugador.
     *
     * @param apellido nuevo apellido del jugador
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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
     * Reemplaza la lista de cartas ganadas del jugador.
     *
     * @param naipesGanados nueva lista de cartas ganadas
     */
    public void setNaipesGanados(SimpleLinkedList<Naipe> naipesGanados) {
        this.naipesGanados = naipesGanados;
    }

    /**
     * Reemplaza la mano actual del jugador.
     *
     * @param manoActual nuevo arreglo de cartas de la mano
     */
    public void setManoActual(Naipe[] manoActual) {
        this.manoActual = manoActual;
    }

    /**
     * Agrega una carta en el primer espacio libre de la mano actual.
     * Si la mano está completa, la carta no se agrega.
     *
     * @param naipe carta que recibe el jugador
     */
    public void recibirNaipe(Naipe naipe) {
        for (int i = 0; i < manoActual.length; i++) {
            if (manoActual[i] == null) {
                manoActual[i] = naipe;
                break;
            }
        }

    }

    /**
     * Guarda una carta en la lista de cartas ganadas.
     *
     * @param naipe carta ganada por el jugador
     */
    public void guardarNaipeGanados(Naipe naipe) {
        naipesGanados.addLast(naipe);
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

    @Override
    /**
     * Devuelve una representación textual del jugador y su estado actual.
     *
     * @return representación textual del jugador
     */
    public String toString() {
        return "Jugador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", puntaje=" + puntaje
                + ", manoActual=" + java.util.Arrays.toString(manoActual) + ", naipesGanados=" + naipesGanados + "]";
    }

}

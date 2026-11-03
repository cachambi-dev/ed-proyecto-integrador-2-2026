package model;

import structures.SimpleLinkedList;

public class Jugador {
    private int id;
    private String nombre;
    private String apellido;
    private int puntaje;
    private Naipe[] manoActual;
    private SimpleLinkedList<Naipe> naipesGanados;

    public Jugador(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntaje = 0;
        this.manoActual = new Naipe[3];
        this.naipesGanados = new SimpleLinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Naipe[] getManoActual() {
        return manoActual;
    }

    public SimpleLinkedList<Naipe> getNaipesGanados() {
        return naipesGanados;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setNaipesGanados(SimpleLinkedList<Naipe> naipesGanados) {
        this.naipesGanados = naipesGanados;
    }

    public void setManoActual(Naipe[] manoActual) {
        this.manoActual = manoActual;
    }

    public void recibirNaipe(Naipe naipe) {
        for (int i = 0; i < manoActual.length; i++) {
            if (manoActual[i] == null) {
                manoActual[i] = naipe;
                break;
            }
        }

    }

    public void guardarNaipeGanados(Naipe naipe) {
        naipesGanados.addLast(naipe);
    }

    public void calcularPuntaje() {
        int total = 0;
        for (Naipe naipe : naipesGanados) {
            total += naipe.getValor();
        }
        this.puntaje = total;
    }

    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", puntaje=" + puntaje
                + ", manoActual=" + java.util.Arrays.toString(manoActual) + ", naipesGanados=" + naipesGanados + "]";
    }
}

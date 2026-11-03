package model;

import java.util.Arrays;
import java.util.Queue;


import structures.SimpleLinkedList;

public class Ronda {
    private int numero;
    private int numeroRonda;
    private Jugador[] participantes;
    private int[] puntajes;
    private SimpleLinkedList<Jugador> ganadores;


    public Ronda(int numero, int numeroRonda, Jugador[] participantes, Queue<Naipe> cartaRepartidas,
            SimpleLinkedList<Jugador> ganadores) {
        this.numero = numero;
        this.numeroRonda = numeroRonda;
        this.participantes = participantes;
        this.ganadores = ganadores;
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public int getNumeroRonda() {
        return numeroRonda;
    }


    public void setNumeroRonda(int numeroRonda) {
        this.numeroRonda = numeroRonda;
    }


    public Jugador[] getParticipantes() {
        return participantes;
    }


    public void setParticipantes(Jugador[] participantes) {
        this.participantes = participantes;
    }


    public SimpleLinkedList<Jugador> getGanadores() {
        return ganadores;
    }


    public void setGanadores(SimpleLinkedList<Jugador> ganadores) {
        this.ganadores = ganadores;
    }

     public boolean esAs(Naipe n) {
        return n != null && n.getValor() == 1;
    }
 
    public boolean esComodin(Naipe n) {
        return n != null && "Comodin".equalsIgnoreCase(n.getPalo());
    }
 
    public boolean tieneAs(Jugador j) {
        for (Naipe n : j.getManoActual()) if (esAs(n)) return true;
        return false;
    }
 
    public boolean tieneComodin(Jugador j) {
        for (Naipe n : j.getManoActual()) if (esComodin(n)) return true;
        return false;
    }

    public int calcularPuntajeAs(Jugador j) {
        int puntaje = 0;
        for (Naipe n : j.getManoActual()) {
            if (n != null) puntaje += n.getValor();
        }
        if (tieneAs(j)) puntaje *= 2;
        return puntaje;
    }

    public void resolverGanador() {
        // calcular y guardar puntajes de ronda
        int cantComodin = 0;
        for (int i = 0; i < participantes.length; i++) {
            puntajes[i] = calcularPuntajeAs(participantes[i]);
            if (tieneComodin(participantes[i])) {
                cantComodin++;
            };
        }
 
        // 2. gana directo
        if (cantComodin == 1) {
            for (Jugador j : participantes) {
                if (tieneComodin(j)) { 
                    ganadores.addLast(j); return; 
                }
            }
        }
 
        // 3 y 4. "candidatos": con comodin si hay varios, todos si no hay ninguno
        boolean soloComodin = cantComodin > 1;
        int max = -1;
        for (int i = 0; i < participantes.length; i++) {
            if (soloComodin && !tieneComodin(participantes[i])) {
                continue; 
            }
            if (puntajes[i] > max)  {
                max = puntajes[i];
            }
        }
 
        // 5. todos los que igualan el maximo ganan (empate incluido)
        for (int i = 0; i < participantes.length; i++) {
            if (soloComodin && !tieneComodin(participantes[i])) {
                continue;
            }
            if (puntajes[i] == max) {
                ganadores.addLast(participantes[i]);
            }
        }
    }

    @Override
    public String toString() {
        return "Ronda [numero=" + numero + ", numeroRonda=" + numeroRonda + ", participantes="
                + Arrays.toString(participantes) + ", ganadores=" + ganadores
                + "]";
    }


}

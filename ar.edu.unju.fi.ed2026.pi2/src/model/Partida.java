package model;

public class Partida {
	
	private Naipe[] mazo;
	private int numeroPartida;
	private Jugador[] jugadores;
	
	
	public Partida(int numeroPartida, Naipe[] mazo, Jugador[] jugadores) {
		this.numeroPartida = numeroPartida;
        this.jugadores = jugadores;
        this.mazo = mazo;
	}
	
	
	public void partidaNueva() {
        System.out.println("Iniciando Partida N° " + numeroPartida);
        
        for (int i = 1; i <= 3; i++) {
            System.out.println("===Iniciando Ronda " + i + " ===");
            
            Ronda rondaActual = new Ronda(i, jugadores, mazo);
        }
	}
}

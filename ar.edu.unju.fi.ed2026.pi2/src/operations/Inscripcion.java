package operations;

import model.Jugador;
import structures.SimpleLinkedList;
import Helper.Helper;
import java.util.Scanner;

public class Inscripcion {
    private SimpleLinkedList <Jugador> jugadoresDisponibles;
    private int contadorNumeroJugador;

    public Inscripcion() {
        this.jugadoresDisponibles = new SimpleLinkedList<>();
        this.contadorNumeroJugador = 1; // Generador correlativo de ID de jugador
    }

    public SimpleLinkedList <Jugador> getJugadoresDisponibles() {
        return jugadoresDisponibles;
    }

    /*Registra a un estudiante mediante la consola */
    public void inscribirParticipante(Scanner scanner) {
        System.out.println("\n=== INSCRIPCIÓN DE PARTICIPANTE ===");

        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine().trim();

        // 1. Verificar si el DNI ya está registrado
        if (buscarPorDni(dni) != null) {
            System.out.println("Error: Ya existe un participante inscripto con el DNI " + dni);
            return;
        }

        String nombre = Helper.leerCadena(scanner,"Ingrese Nombre: ");

        String apellido = Helper.leerCadena(scanner,"Ingrese Apellido: ");

        int edad = Helper.leerEnteroPositivo(scanner,"Ingrese Edad: ");

        // 2. Asignar ID único y crear el jugador (puntaje inicial en 0)
        int id = contadorNumeroJugador++;
        Jugador nuevoJugador = new Jugador( nombre, apellido, dni,edad);

        // 3. Guardar en la estructura de disponibles
        jugadoresDisponibles.addLast(nuevoJugador);

        System.out.println(" ¡Jugador inscripto con éxito! Se le asignó el Número de Jugador:  " + id);
    }

    /* Valida si un DNI ya existe en la lista de inscriptos*/
    public Jugador buscarPorDni(String dni) {
        for (int i = 0; i < jugadoresDisponibles.size(); i++) {
            Jugador j = (Jugador) jugadoresDisponibles.get(i);
            if (j != null && j.getDni() != null && j.getDni().equalsIgnoreCase(dni)) {
                return j;
            }
        }
        return null;
    }

    /* Busca un participante por su número único de jugador*/
    public Jugador buscarPorNumeroJugador(int id) {
        for (int i = 0; i < jugadoresDisponibles.size(); i++) {
            Jugador j = jugadoresDisponibles.get(i);
            if (j != null && j.getId() == id) {
                return j;
            }
        }
        return null;
    }
}
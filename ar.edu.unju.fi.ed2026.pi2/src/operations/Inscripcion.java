package operacions;

import model.Jugador;
import structures.SimpleLinkedList;
import Helper.Helper;
import java.util.Scanner;

public class Inscripcion {
    private SimpleLinkedList jugadoresDisponibles;
    private int contadorNumeroJugador;

    public Inscripcion() {
        this.jugadoresDisponibles = new SimpleLinkedList();
        this.contadorNumeroJugador = 1; // Generador correlativo de ID de jugador
    }

    public SimpleLinkedList getJugadoresDisponibles() {
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

        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextString().trim();

        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextString().trim();

       
        int edad = Helper.leerEnteroPositivo(scanner,"Ingrese Edad: ");

        // 2. Asignar ID único y crear el jugador (puntaje inicial en 0)
        int numeroJugador = contadorNumeroJugador++;
        Jugador nuevoJugador = new Jugador(dni, nombre, apellido, edad, numeroJugador);

        // 3. Guardar en la estructura de disponibles
        jugadoresDisponibles.agregar(nuevoJugador);

        System.out.println(" ¡Jugador inscripto con éxito! Se le asignó el Número de Jugador:  " + numeroJugador);
    }

    /* Valida si un DNI ya existe en la lista de inscriptos*/
    public Jugador buscarPorDni(String dni) {
        for (int i = 0; i < jugadoresDisponibles.tamanio(); i++) {
            Jugador j = (Jugador) jugadoresDisponibles.obtener(i);
            if (j.getDni().equalsIgnoreCase(dni)) {
                return j;
            }
        }
        return null;
    }
}
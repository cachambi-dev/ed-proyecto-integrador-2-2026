package operations;

import model.Jugador;
import structures.SimpleLinkedList;
import Helper.Helper;
import java.util.Scanner;

/** Gestiona el registro y la búsqueda de participantes disponibles. */
public class Inscripcion {
    private final SimpleLinkedList<Jugador> jugadoresDisponibles;

    /** Crea una inscripción sin participantes registrados. */
    public Inscripcion() {
        this.jugadoresDisponibles = new SimpleLinkedList<>();
    }

    /**
     * Devuelve la lista de jugadores inscriptos disponibles.
     *
     * @return lista mutable de jugadores disponibles
     */
    public SimpleLinkedList<Jugador> getJugadoresDisponibles() {
        return jugadoresDisponibles;
    }

    /**
     * Lee los datos de un participante y lo agrega si su DNI no está registrado.
     *
     * @param scanner fuente de datos utilizada para solicitar la inscripción
     * @return jugador creado, o {@code null} si el DNI ya estaba registrado
     * @throws NullPointerException si {@code scanner} es {@code null}
     */
    public Jugador inscribirParticipante(Scanner scanner) {
        System.out.println("\n=== INSCRIPCIÓN DE PARTICIPANTE ===");

        String dni;
        while (true) {
            dni = Helper.leerCadena(scanner, "Ingrese DNI: ").trim();
            if (!dni.matches("\\d+")) {
                System.out.println("Error: El DNI debe contener solo números.");
                continue;
            }
            if (buscarPorDni(dni) != null) {
                System.out.println("Error: Ya existe un participante inscripto con el DNI " + dni);
                return null;
            }
            break;
        }

        String nombre = Helper.leerCadena(scanner, "Ingrese Nombre: ").trim();
        String apellido = Helper.leerCadena(scanner, "Ingrese Apellido: ").trim();

        int edad = Helper.leerEnteroPositivo(scanner,"Ingrese Edad: ");
        scanner.nextLine(); // Consume el salto de línea que deja nextInt().

        // Jugador asigna el ID único y comienza con puntaje cero.
        Jugador nuevoJugador = new Jugador(nombre, apellido, dni, edad);

        // Guardar en la estructura de disponibles
        jugadoresDisponibles.addLast(nuevoJugador);

        System.out.println("¡Jugador inscripto con éxito! Se le asignó el Número de Jugador: "
                + nuevoJugador.getId());
        return nuevoJugador;
    }

    /**
     * Busca un participante por su DNI, ignorando diferencias de mayúsculas.
     *
     * @param dni DNI que se desea buscar
     * @return jugador encontrado, o {@code null} si no existe
     */
    public Jugador buscarPorDni(String dni) {
        for (int i = 0; i < jugadoresDisponibles.size(); i++) {
            Jugador j = (Jugador) jugadoresDisponibles.get(i);
            if (j != null && j.getDni() != null && j.getDni().equalsIgnoreCase(dni)) {
                return j;
            }
        }
        return null;
    }

    /**
     * Busca un participante por su identificador único.
     *
     * @param id identificador del jugador
     * @return jugador encontrado, o {@code null} si no existe
     */
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

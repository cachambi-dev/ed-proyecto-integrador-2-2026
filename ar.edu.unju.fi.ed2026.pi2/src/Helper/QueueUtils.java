package Helper;

import structures.Queue;

public class QueueUtils {

    /**
     * Metodo que muestra los elementos de una cola
     * @param queue Cola a mostrar
     */
    public static <Element> void displayQueue(Queue<Element> queue) {
        if (queue.isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }

        System.out.print("Elementos de la cola: ");
        for (Element element : queue) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

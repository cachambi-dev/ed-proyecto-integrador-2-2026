package structures;

/** Implementa una pila LIFO respaldada por una lista enlazada. */
public class StackLinkedList<ELEMENT> {
    private final SimpleLinkedList<ELEMENT> list;

    /** Crea una pila vacía. */
    public StackLinkedList() {
        this.list = new SimpleLinkedList<>();
    }

    /**
     * Apila un elemento y lo devuelve.
     *
     * @param element elemento que se apilará
     * @return elemento apilado
     */
    public ELEMENT push(ELEMENT element) {
        list.addFirst(element);
        return element;
    }

    /**
     * Quita y devuelve el elemento superior.
     *
     * @return elemento retirado
     * @throws RuntimeException si la pila está vacía
     */
    public ELEMENT pop() {
        return list.removeFirst();
    }

    /**
     * Devuelve el elemento superior sin quitarlo.
     *
     * @return elemento superior
     * @throws IllegalStateException si la pila está vacía
     */
    public ELEMENT peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía...");
        }
        return list.getFirst();
    }

    /**
     * Indica si la pila está vacía.
     *
     * @return {@code true} si no contiene elementos
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Devuelve la cantidad de elementos de la pila.
     *
     * @return cantidad de elementos
     */
    public int size() {
        return list.size();
    }

    /**
     * Devuelve la pila en formato textual.
     *
     * @return representación textual de la pila
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
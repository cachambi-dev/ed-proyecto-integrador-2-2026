package structures;

import java.util.Iterator;

/** Implementa una cola FIFO respaldada por una lista enlazada. */
public class QueueLinkedList<ELEMENT> implements Iterable<ELEMENT> {
    private final SimpleLinkedList<ELEMENT> list;

    /** Crea una cola vacía. */
    public QueueLinkedList() {
        this.list = new SimpleLinkedList<>();
    }

    @SafeVarargs
    /**
     * Crea una cola y agrega los elementos en el orden recibido.
     *
     * @param elements elementos iniciales de la cola
     */
    public QueueLinkedList(ELEMENT... elements) {
        this();
        for (ELEMENT element : elements) {
            add(element);
        }
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param element elemento que se agregará
     * @return siempre {@code true}
     */
    public boolean add(ELEMENT element) {
        list.addLast(element);
        return true;
    }

    /**
     * Agrega un elemento y devuelve siempre {@code true}.
     *
     * @param element elemento que se agregará
     * @return siempre {@code true}
     */
    public boolean offer(ELEMENT element) {
        return add(element);
    }

    /**
     * Devuelve el primer elemento sin quitarlo.
     *
     * @return primer elemento
     * @throws IllegalStateException si la cola está vacía
     */
    public ELEMENT element() {
        return list.size() == 0 ? throwEmpty() : list.getFirst();
    }

    /**
     * Devuelve el primer elemento sin quitarlo.
     *
     * @return primer elemento, o {@code null} si está vacía
     */
    public ELEMENT peek() {
        return list.size() == 0 ? null : list.getFirst();
    }

    /**
     * Quita y devuelve el primer elemento.
     *
     * @return elemento retirado
     * @throws RuntimeException si la cola está vacía
     */
    public ELEMENT remove() {
        return list.removeFirst();
    }

    /**
     * Quita y devuelve el primer elemento.
     *
     * @return elemento retirado, o {@code null} si está vacía
     */
    public ELEMENT poll() {
        return list.size() == 0 ? null : list.removeFirst();
    }

    /**
     * Indica si la cola está vacía.
     *
     * @return {@code true} si no contiene elementos
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Devuelve la cantidad de elementos.
     *
     * @return cantidad de elementos
     */
    public int size() {
        return list.size();
    }

    @SuppressWarnings("unchecked")
    /**
     * Copia los elementos de la cola en un arreglo.
     *
     * @return arreglo con los elementos en orden FIFO
     */
    public ELEMENT[] toArray() {
        Object[] result = new Object[size()];
        int index = 0;
        for (ELEMENT element : list) {
            result[index++] = element;
        }
        return (ELEMENT[]) result;
    }

    private ELEMENT throwEmpty() {
        throw new IllegalStateException("La cola está vacía...");
    }

    /**
     * Devuelve un iterador FIFO sobre la cola.
     *
     * @return iterador de la cola
     */
    @Override
    public Iterator<ELEMENT> iterator() {
        return list.iterator();
    }

    /**
     * Devuelve la cola en formato textual.
     *
     * @return representación textual de la cola
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
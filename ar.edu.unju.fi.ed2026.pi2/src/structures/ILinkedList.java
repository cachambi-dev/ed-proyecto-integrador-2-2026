package structures;

/** Define las operaciones básicas de una lista enlazada. */
public interface ILinkedList<ELEMENT> extends Iterable<ELEMENT>  {
    
    /**
     * Devuelve la cantidad de elementos de la lista.
     *
     * @return cantidad de elementos
     */
    public int size();
    
    /**
     * Inserta un elemento al comienzo de la lista.
     *
     * @param item elemento que se insertará
     */
    public void addFirst(ELEMENT item);

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param item elemento que se agregará
     */
    public void addLast(ELEMENT item);

    /**
     * Elimina y devuelve el primer elemento de la lista.
     *
     * @return elemento eliminado
     * @throws RuntimeException si la lista está vacía
     */
    public ELEMENT removeFirst();
    

}

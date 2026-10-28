package structures;

//
// Created by Julio Tentor <jtentor@fi.unju.edu.ar>
//

import java.util.Iterator;

public class SimpleLinkedList<ELEMENT> implements ILinkedList<ELEMENT> {

    //region Node Class

    private class Node<T> {
        public T item;
        public Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return this.item.toString();
        }
    }
    //endregion

    //region Attributes

    protected Node<ELEMENT> head;
    protected int count;
    protected Node<ELEMENT> tail;
    //endregion

    //region Constructors

    public SimpleLinkedList() {
        this.head = null;
        this.count = 0;
        this.tail = null;
    }
    //endregion

    //region Linked List Methods

    public int size() {
        return this.count;
    }

    public ELEMENT getFirst() {
        if (this.count == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        return this.head.item;
    }

    public ELEMENT get(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Posicion invalida");
        }
        Node<ELEMENT> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public void addFirstRookieVersion(ELEMENT item) {
        if (this.count == 0) {
            this.head = this.tail = new Node<ELEMENT>(item, null);
            ++this.count;
        } else {
            Node<ELEMENT> temp = new Node<ELEMENT>(item, null);
            temp.next = this.head;
            this.head = temp;
            ++this.count;
        }
    }

    public void addFirst(ELEMENT item) {
        Node<ELEMENT> temp = new Node<ELEMENT>(item, this.head);
        if (this.count == 0) {
            this.tail = temp;
        }
        this.head = temp;
        ++this.count;
    }

    public void add(int index, ELEMENT item){
        if (index < 0 || index > this.count){
            throw new IndexOutOfBoundsException("Posicion invalida");
        }

        if (index == 0){
            addFirst(item);
            return;
        }

        if(index == this.count){
            addLast(item);
            return;
        }

        Node<ELEMENT> previous = this.head;
        for (int i = 0; i < index -1; i++){
            previous = previous.next;
        }

        Node<ELEMENT> newNode = new Node<ELEMENT>(item, previous.next);
        previous.next = newNode;
         this.count++;
    }

    public void addLastRookieVersion(ELEMENT item) {
        if (this.count == 0) {
            this.head = this.tail = new Node<ELEMENT>(item, null);
            ++this.count;
        } else {
            Node<ELEMENT> temp = new Node<ELEMENT>(item, null);
            this.tail.next = temp;
            this.tail = temp;
            ++this.count;
        }
    }

    public void addLast(ELEMENT item) {
        Node<ELEMENT> temp = new Node<ELEMENT>(item, null);
        if (this.count == 0) {
            this.head = temp;
        } else {
            this.tail.next = temp;
        }
        this.tail = temp;
        ++this.count;
    }

    public ELEMENT removeFirst() {
        if (this.count == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        ELEMENT item = this.head.item;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        --this.count;
        return item;
    }

    public ELEMENT removeLast() {
        if (this.count == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        ELEMENT item = this.tail.item;
        if (this.head.next == null) {
            this.head = this.tail = null;
        } else {
            Node<ELEMENT> skip = this.head;
            while (skip.next.next != null) {
                skip = skip.next;
            }
            this.tail = skip;
            this.tail.next = null;
        }
        --this.count;
        return item;
    }

    public ELEMENT remove(int index){
        if (index < 0 || index >= this.count){
            throw new IndexOutOfBoundsException("Posicion Invalida");
        }
        
        if(index == 0){
            return removeFirst();
        }

        if (index == this.count - 1 ){
            return  removeLast();
        }

        Node<ELEMENT> previous = this.head;
        for(int i=0; i < index-1; i++){
            previous = previous.next;
        }
        Node<ELEMENT> nodeRemove = previous.next;
        previous.next = nodeRemove.next;
        this.count--;
        return nodeRemove.item;

    }

    public void eliminarPares() {
        while (this.head != null && ((Integer) this.head.item) % 2 == 0) {
            removeFirst();
        }

        Node<ELEMENT> previous = this.head;
        while (previous != null && previous.next != null) {
            if (((Integer) previous.next.item) % 2 == 0) {
                if (previous.next == this.tail) {
                    this.tail = previous;
                }
                previous.next = previous.next.next;
                this.count--;
            } else {
                previous = previous.next;
            }
        }
    }
    //endregion

    //region Object Methods

    @Override
    public String toString() {

        if (this.size() <= 0) {
            return "";
        }

        // from https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/StringBuilder.html
        StringBuilder sb = new StringBuilder();

        sb.append("[" + this.head.item.toString());
        for (Node<ELEMENT> skip = this.head.next; skip != null; skip = skip.next) {
            sb.append(", " + skip.item.toString());
        }
        sb.append("]");

        return sb.toString();
    }


    //endregion

    //region Iterable Methods
    @Override
    public Iterator<ELEMENT> iterator() {
        return new SimpleLinkedListIterator(this.head);
    }

    private class SimpleLinkedListIterator implements Iterator<ELEMENT> {
        private Node<ELEMENT> current;

        public SimpleLinkedListIterator(Node<ELEMENT> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public ELEMENT next() {
            if (!this.hasNext()) {
                throw new RuntimeException("La lista está vacía...");
            }
            ELEMENT item = this.current.item;
            this.current = this.current.next;
            return item;
        }

    }

    //endregion

}

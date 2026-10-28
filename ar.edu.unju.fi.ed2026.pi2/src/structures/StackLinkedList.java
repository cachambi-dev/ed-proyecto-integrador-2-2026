package structures;

public class StackLinkedList<ELEMENT> {
    private final SimpleLinkedList<ELEMENT> list;

    public StackLinkedList() {
        this.list = new SimpleLinkedList<>();
    }

    public ELEMENT push(ELEMENT element) {
        list.addFirst(element);
        return element;
    }

    public ELEMENT pop() {
        return list.removeFirst();
    }

    public ELEMENT peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía...");
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
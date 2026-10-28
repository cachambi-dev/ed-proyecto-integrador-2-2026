package structures;

import java.util.Iterator;

public class QueueLinkedList<ELEMENT> implements Iterable<ELEMENT> {
    private final SimpleLinkedList<ELEMENT> list;

    public QueueLinkedList() {
        this.list = new SimpleLinkedList<>();
    }

    @SafeVarargs
    public QueueLinkedList(ELEMENT... elements) {
        this();
        for (ELEMENT element : elements) {
            add(element);
        }
    }

    public boolean add(ELEMENT element) {
        list.addLast(element);
        return true;
    }

    public boolean offer(ELEMENT element) {
        return add(element);
    }

    public ELEMENT element() {
        return list.size() == 0 ? throwEmpty() : list.getFirst();
    }

    public ELEMENT peek() {
        return list.size() == 0 ? null : list.getFirst();
    }

    public ELEMENT remove() {
        return list.removeFirst();
    }

    public ELEMENT poll() {
        return list.size() == 0 ? null : list.removeFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    @SuppressWarnings("unchecked")
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

    @Override
    public Iterator<ELEMENT> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
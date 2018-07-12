import java.util.NoSuchElementException;

public class LinkedList<T> implements _Deque<T>, _List<T> {
    private static final int NOT_FOUND = -1;

    private Node<T> first, last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size == 0) {
            createFirstNode(item);
        } else {
            Node node = new Node();
            node.setItem(item);
            node.setNext(first);
            first.setPrev(node);
            first = node;
            size++;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == 0) {
            createFirstNode(item);
        } else {
            Node node = new Node();
            node.setItem(item);
            node.setPrev(last);
            last.setNext(node);
            last = node;
            size++;
        }
    }

    @Override
    public T getFirst() {
        return first.getItem();
    }

    @Override
    public T getLast() {
        return last.getItem();
    }

    @Override
    public T pollFirst() {
        T item;
        if (size > 0) {
            item = first.getItem();
            removeNode(first);
        } else {
            item = null;
        }
        return item;
    }

    @Override
    public T pollLast() {
        T item;
        if (size > 0) {
            item = last.getItem();
            removeNode(last);
        } else {
            item = null;
        }
        return item;
    }

    @Override
    public T removeFirst() {
        T item = null;
        if (size > 0) {
            item = first.getItem();
        }
        removeNode(first);
        return item;
    }

    @Override
    public T removeLast() {
        T item = null;
        if (size > 0) {
            item = first.getItem();
        }
        removeNode(last);
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            System.out.println("Not empty");
            return false;
        }
        System.out.println("Empty");
        return true;
    }

    @Override
    public boolean contains(T item) {
        if (getNode(item) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(T item) {
        if (getNode(item) == null) {
            System.out.println("No element to be deleted");
            return true;
        }
        else {removeNode(getNode(item));}
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public boolean add(T item) {
        addLast(item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        checkForRange(index);
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size - 1) {
            addLast(item);
            return;
        }
        Node<T> foundNode = getNode(index);
        Node newNode = new Node();
        newNode.setItem(item);
        newNode.setPrev(foundNode.getPrev());
        newNode.setNext(foundNode);
        foundNode.setPrev(newNode);
        size++;
    }

    @Override
    public void set(int index, T item) {
        checkForRange(index);
        Node<T> foundNode = getNode(index);
        foundNode.setItem(item);
    }

    @Override
    public T get(int index) {
        checkForRange(index);
        return getNode(index).getItem();
    }

    @Override
    public int indexOf(T item) {
        int found = NOT_FOUND;
        if (first.getItem().equals(item)) {
            return 0;
        }
        if (size > 0) {
            Node<T> node = first;
            int i = 0;
            while (node.getNext() != null) {
                i++;
                node = node.getNext();
                if (item.equals(node.getItem())) {
                    found = i;
                    break;
                }
            }
        }
        return found;
    }

    @Override
    public int lastIndexOf(T item) {
        int found = NOT_FOUND;
        if (last.getItem().equals(item)) {
            return size - 1;
        }
        if (size > 0) {
            Node<T> node = last;
            int i = size - 1;
            while (node.getPrev() != null) {
                i--;
                node = node.getPrev();
                if (item.equals(node.getItem())) {
                    found = i;
                    break;
                }
            }
        }
        return found;
    }

    @Override
    public void remove(int index) {
        checkForRange(index);
        removeNode(getNode(index));
    }

    @Override
    public LinkedList<T> subList(int from, int to) {
        checkForRange(from, to);
        LinkedList<T> list = new LinkedList();
        Node<T> startNode = getNode(from);
        for (int i = 0; i < to - from; i++) {
            list.add(startNode.getItem());
            startNode = startNode.getNext();
        }
        return list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (size > 0) {
            Node node = first;
            sb.append(node.getItem() + " ");
            while (node.getNext() != null) {
                node = node.getNext();
                sb.append(node.getItem() + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private void removeNode(Node<T> node) {
        if (size <= 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return;
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            last = node.getPrev();
            node.getPrev().setNext(null);
        }

        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            first = node.getNext();
            node.getNext().setPrev(null);
        }
        size--;
    }

    private Node<T> getNode(T item) {
        Node<T> found = null;
        if (first.getItem().equals(item)) {
            return first;
        }
        if (size > 0) {
            Node<T> node = first;
            while (node.getNext() != null) {
                node = node.getNext();
                if (item.equals(node.getItem())) {
                    found = node;
                    break;
                }
            }
        }
        return found;
    }

    private Node<T> getNode(int index) {
        checkForRange(index);
        if (index == 0) {
            return first;
        }
        if (index == size - 1) {
            return last;
        }
        if (index > (size / 2)) {
            Node<T> node = last;
            for (int i = size - 1; i >= index; i--) {
                node = node.getPrev();
            }
            return node;
        } else {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
        }
    }

    //util

    private void createFirstNode(T item) {
        Node node = new Node();
        node.setItem(item);
        first = node;
        last = node;
        size++;
    }

    private void checkForRange(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkForRange(int from, int to) {
        if ((from < 0) || (from > to) || (to > size)) {
            throw new IndexOutOfBoundsException();
        }
    }
}
public class ArrayList<T> implements _List<T> {
    private static final int NOT_FOUND = -1;
    private int size;
    private T[] array;

    public ArrayList() {
        initialize(10);
    }

    public ArrayList(int capacity) {
        initialize(capacity);
    }

    private void initialize(int capacity) {
        size = 0;
        array = (T[]) new Object[capacity];
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != (size - 1)) {
                sb.append(array[i] + " ");
            } else {
                sb.append(array[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void add(int index, T item) {
        checkForRange(index);
        extendArrayIfNeeded();
        moveItemsRight(index);
        array[index] = item;
        size++;
    }

    @Override
    public void set(int index, T item) {
        checkForRange(index);
        array[index] = item;
    }

    @Override
    public Object get(int index) {
        checkForRange(index);
        return array[index];
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int lastIndexOf(T item) {
        if (item == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (item.equals(array[i])) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    @Override
    public void remove(int index) {
        checkForRange(index);
        moveItemsLeft(index);
        array[--size] = null;
    }

    @Override
    public _List subList(int from, int to) {
        checkForRange(from, to);
        _List list = new ArrayList(to - from);
        for (int i = from; i < to; i++) {
            list.add(array[i]);
        }
        return list;
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
        if (indexOf(item) != NOT_FOUND) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(T item) {
        extendArrayIfNeeded();
        array[size++] = item;
        return true;
    }

    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }


    //utility shite

    @Override
    public void clear() {
        initialize(10);
    }

    private void moveItemsRight(int index) {
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
    }

    private void moveItemsLeft(int index) {
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
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

    private void extendArrayIfNeeded() {
        if (array.length == size) {
            resize(array.length + array.length / 2 + 1);
        }
    }
}
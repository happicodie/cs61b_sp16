public class ArrayDeque<T> implements Deque <T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int INITIALSIZE = 8;
    private final int RFACTOR = 2;
    private final double UFACTOR = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[INITIALSIZE];
        size = 0;
        nextFirst = items.length/2 - 1;
        nextLast = items.length/2;
    }

    // Resize the underlying array to the target capacity
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int newNextFirst = a.length / 2 - 1;
        int newNextLast = a.length / 2;
        int i = 0;
        while (i < size) {
            a[newNextLast] = get(i);
            if (newNextLast == a.length - 1) {
                newNextLast = 0;
            } else {
                newNextLast += 1;
            }
            i += 1;
        }
        nextFirst = newNextFirst;
        nextLast = newNextLast;
        items = a;
    }

    // Adds an item of type T to the front of the deque
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    // Adds an item of type T to the bakc of the deque
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = item;
        size += 1;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    // Returns true if deque is empty, false otherwise
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque
    @Override
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last
    @Override
    public void printDeque() {
        int i = 0;
        while (i < size) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T first = get(0);
        size -= 1;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        items[nextFirst] = null;
        if (items.length >= 16 && (double) size/ items.length < UFACTOR) {
            resize(items.length/RFACTOR);
        }
        return first;
    }

    // Removes and returns the item at the back of the deque
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T last = get(size - 1);
        size -= 1;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        items[nextLast] = null;
        if (items.length >= 16 && (double) size/ items.length < UFACTOR) {
            resize(items.length/RFACTOR);
        }
        return last;
    }

    // Gets the item at the given index
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            return items[index];
        } else if (nextLast - 1 > nextFirst + 1 || nextLast == 0) {
            return items[nextFirst + 1 + index];
        } else {
            if (nextFirst + 1 + index < items.length) {
                return items[nextFirst + 1 + index];
            } else {
                return items[nextFirst + index - (items.length - 1)];
            }
        }
    }
}

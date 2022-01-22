public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int INITIALSIZE = 8;
    private final int RFACTOR = 2;
    private final double UFACTOR = 0.25;

    public ArrayDeque() {
        items = (Item[]) new Object[INITIALSIZE];
        size = 0;
        nextFirst = items.length/2 - 1;
        nextLast = items.length/2;
    }

    // Resize the underlying array to the target capacity
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        nextFirst = a.length / 2 - 1;
        nextLast = a.length / 2;
        int i = 0;
        while (i < size) {
            a[nextFirst] = get(i);
            nextFirst += 1;
            i += 1;
        }
        items = a;
    }

    // Adds an item of type Item to the front of the deque
    public void addFirst(Item item) {
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

    // Adds an item of type Item to the bakc of the deque
    public void addLast(Item item) {
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
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last
    public void printDeque() {
        int i = 0;
        while(i < size) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item first = get(0);
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
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item last = get(size - 1);
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
    public Item get(int index) {
        if (index > size - 1) {
            return null;
        }
        if (nextLast > nextFirst) {
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

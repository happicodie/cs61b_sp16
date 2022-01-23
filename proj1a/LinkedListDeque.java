public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    // The first item (if it exists) is at sentinel.next.item
    private final Node sentinel;
    private int size;

    // Creates an empty linked list deque
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // Adds an item of type T to the front of the deque
    public void addFirst(T item) {
        Node first = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    // Adds an item of type T to the back of the deque
    public void addLast(T item) {
        Node last = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of items in the deque
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space
    public void printDeque() {
        Node ptr = sentinel.next;
        int i = size;
        while (i > 0) {
            System.out.print(ptr.item + " ");
            i -= 1;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque. If no such item exists, return null
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, return null
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size -= 1;
        return last.item;
    }

    // Gets the item at the given index
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node ptr = sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.item;
    }

    // Recursive get method
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    // Recursive get method helper
    private T getRecursive(Node ptr, int index) {
        if (index == 0) {
            return ptr.item;
        }
        return getRecursive(ptr.next, index - 1);
    }
}

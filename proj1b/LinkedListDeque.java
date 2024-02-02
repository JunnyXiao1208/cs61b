/**
 * @Author : junnny
 * @Date : 2024/2/1 16:47
 * @Description : Implement the LinkedListDeque structure
 */
public class LinkedListDeque<T> implements Deque<T>{
    // Inner class Node
    public class Node {
        private T item; // The item stored in this node
        private Node previous; // Reference to the previous node
        private Node next; // Reference to the next node

        public Node(T item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node sentinel; // Sentinel node
    private int size; // Size of the deque

    // Constructor
    public LinkedListDeque() {
        initializeSentinel();
    }

    // Copy constructor
    public LinkedListDeque(LinkedListDeque<T> other) {
        initializeSentinel();
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    // Initialize the sentinel node
    private void initializeSentinel() {
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    // Adds an item to the front of the deque
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.previous = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    // Adds an item to the back of the deque
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.previous, sentinel);
        sentinel.previous.next = newNode;
        sentinel.previous = newNode;
        size++;
    }

    @Override
    // Checks if the deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    // Returns the number of items in the deque
    public int size() {
        return size;
    }

    @Override
    // Prints the items in the deque from first to last
    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(); // New line after printing
    }

    @Override
    // Removes and returns the item at the front of the deque
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size--;
        return result;
    }

    @Override
    // Removes and returns the item at the back of the deque
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return result;
    }

    @Override
    // Gets the item at the given index
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node ptr = sentinel.next;
        while (index-- > 0) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    // Public method to get item recursively
    public T getRecursive(int index) {
        return getRecursiveHelp(sentinel.next, index);
    }

    // Helper method for recursive get
    private T getRecursiveHelp(Node node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelp(node.next, index - 1);
        }
    }
}

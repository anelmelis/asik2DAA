public class LinkedList {
    private Node head;
    private int size;
    private long comparisons;
    private long accesses;

    private static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public LinkedList() {
        head = null;
        size = 0;
    }
    public void add(int x) {
        Node newNode = new Node(x);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public int getSize() {
        return size;
    }
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public boolean contains(int x) {
        Node current = head;

        while (current != null) {
            comparisons++;
            if (current.data == x) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        Node newNode = new Node(x);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                accesses++;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int removed;
        if (index == 0) {
            removed = head.data;
            head = head.next;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                accesses++;
            }
            removed = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removed;
    }
    public long getComparisons() {
        return comparisons;
    }
    public void resetComparisons() {
        comparisons = 0;
    }
    public long getAccesses() {
        return accesses;
    }
    public void resetAccesses() {
        accesses = 0;
    }

}

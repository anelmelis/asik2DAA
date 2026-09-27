public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap() {
        heap = new int[10];
        size = 0;
    }
    public void insert(int x) {
        if (size == heap.length) {
            int[] newHeap = new int[heap.length * 2];
            for (int i = 0; i < size; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
        heap[size] = x;
        size++;
        int current = size - 1;
        while (current > 0) {
            int parent = (current - 1) / 2;

            if (heap[parent] <= heap[current]) {
                break;
            }
            int temp = heap[parent];
            heap[parent] = heap[current];
            heap[current] = temp;
            current = parent;
        }
    }
    public int getSize() {
        return size;
    }
    public boolean isValidHeap() {
        for (int i = 0; i < size; i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < size && heap[i] > heap[left]) {
                return false;
            }

            if (right < size && heap[i] > heap[right]) {
                return false;
            }
        }

        return true;
    }
    public int peekMin() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        return heap[0];
    }
    public int extractMin() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        int min = heap[0];

        heap[0] = heap[size - 1];
        size--;

        int current = 0;

        while (true) {
            int left = current * 2 + 1;
            int right = current * 2 + 2;
            int smallest = current;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == current) {
                break;
            }

            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;

            current = smallest;
        }

        return min;
    }

}

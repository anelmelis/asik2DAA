public class DynamicArr {
    private int[] data;
    private int size;
    private long movements;
    private long comparisons;
    private long accesses;

    public DynamicArr() {
        data = new int[10];
        size = 0;
    }

    public void add(int x) {
        if (size == data.length) {
            int[] newData = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        data[size] = x;
        size++;
    }
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        accesses++;
        return data[index];
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (data[i] == x) {
                return true;
            }
        }
        return false;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == data.length) {
            int[] newData = new int[data.length * 2];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            movements++;
        }

        data[index] = x;
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            movements++;
        }

        size--;

        return removed;
    }

    public int getSize() {
        return size;
    }
    public long getComparisons() {
        return comparisons;
    }
    public void resetComparisons() {
        comparisons = 0;
    }
    public long getMovements() {
        return movements;
    }
    public void resetMovements() {
        movements = 0;
    }
    public long getAccesses() {
        return accesses;
    }
    public void resetAccesses() {
        accesses = 0;
    }
}

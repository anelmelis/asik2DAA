public class Tests {
    public static void main(String[] args) {
        testDynamicArray();
        testLinkedList();
        testMinHeap();
        System.out.println("All tests passed!");
    }

    public static void testDynamicArray() {
        DynamicArr arr = new DynamicArr();

        arr.add(10);
        arr.add(20);
        arr.add(30);

        assert arr.getSize() == 3;
        assert arr.get(0) == 10;
        assert arr.get(1) == 20;
        assert arr.get(2) == 30;

        assert arr.contains(20);
        assert !arr.contains(50);

        arr.add(1, 99);

        assert arr.get(0) == 10;
        assert arr.get(1) == 99;
        assert arr.get(2) == 20;
        assert arr.get(3) == 30;

        int removed = arr.remove(1);

        assert removed == 99;
        assert arr.getSize() == 3;

        for (int i = 0; i < 20; i++) {
            arr.add(i);
        }

        assert arr.getSize() == 23;

        System.out.println("DynamicArr: passed");
    }

    public static void testLinkedList() {
        LinkedList list = new LinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assert list.getSize() == 3;
        assert list.get(0) == 10;
        assert list.get(1) == 20;
        assert list.get(2) == 30;

        assert list.contains(20);
        assert !list.contains(50);

        list.add(1, 99);

        assert list.get(0) == 10;
        assert list.get(1) == 99;
        assert list.get(2) == 20;
        assert list.get(3) == 30;

        int removed = list.remove(1);

        assert removed == 99;
        assert list.getSize() == 3;

        System.out.println("LinkedList: passed");
    }

    public static void testMinHeap() {
        MinHeap heap = new MinHeap();

        heap.insert(10);
        heap.insert(5);
        heap.insert(2);
        heap.insert(8);
        heap.insert(1);

        assert heap.getSize() == 5;
        assert heap.peekMin() == 1;
        assert heap.isValidHeap();

        int first = heap.extractMin();
        int second = heap.extractMin();

        assert first == 1;
        assert second == 2;
        assert heap.isValidHeap();

        heap.insert(2);
        heap.insert(2);
        heap.insert(15);

        assert heap.isValidHeap();

        int previous = Integer.MIN_VALUE;

        while (heap.getSize() > 0) {
            int current = heap.extractMin();

            assert current >= previous;
            previous = current;

            assert heap.isValidHeap();
        }

        System.out.println("MinHeap: passed");
    }
}

import java.util.Random;

public class Benchmark {

    static final int[] SIZES = {100, 1000, 10000, 100000};
    static final int RUNS = 5;

    public static void main(String[] args) {
        System.out.println("Benchmark started");

        randomAccessBenchmark();
        searchBenchmark();
        insertionRemovalBenchmark();
        heapBenchmark();
    }

    public static void randomAccessBenchmark() {

        Random random = new Random(42);

        for (int n : SIZES) {

            long arrayTotal = 0;
            long listTotal = 0;

            long arrayAccessTotal = 0;
            long listAccessTotal = 0;


            for (int run = 0; run < RUNS; run++) {

                DynamicArr array = new DynamicArr();
                LinkedList list = new LinkedList();


                for (int i = 0; i < n; i++) {

                    int value = random.nextInt();

                    array.add(value);
                    list.add(value);
                }

                int[] indices = new int[10000];

                for (int i = 0; i < indices.length; i++) {
                    indices[i] = random.nextInt(n);
                }



                array.resetAccesses();

                long start = System.nanoTime();

                for (int index : indices) {
                    array.get(index);
                }

                long end = System.nanoTime();


                arrayTotal += end - start;
                arrayAccessTotal += array.getAccesses();




                list.resetAccesses();

                start = System.nanoTime();

                for (int index : indices) {
                    list.get(index);
                }

                end = System.nanoTime();


                listTotal += end - start;
                listAccessTotal += list.getAccesses();

            }



            double arrayAverage =
                    arrayTotal / (double) RUNS;

            double listAverage =
                    listTotal / (double) RUNS;


            double arrayAccessAverage =
                    arrayAccessTotal / (double) RUNS;

            double listAccessAverage =
                    listAccessTotal / (double) RUNS;


            System.out.println("n = " + n);

            System.out.println(
                    "Dynamic Array: "
                            + arrayAverage
                            + " ns, accesses = "
                            + arrayAccessAverage
            );


            System.out.println(
                    "Linked List: "
                            + listAverage
                            + " ns, accesses = "
                            + listAccessAverage
            );


            System.out.println("-------------------------");
        }
    }


    public static void searchBenchmark() {
        Random random = new Random(42);

        for (int n : SIZES) {
            long arrayTotal = 0;
            long listTotal = 0;

            long arrayComparisons = 0;
            long listComparisons = 0;

            for (int run = 0; run < RUNS; run++) {

                DynamicArr array = new DynamicArr();
                LinkedList list = new LinkedList();

                for (int i = 0; i < n; i++) {
                    int value = random.nextInt();
                    array.add(value);
                    list.add(value);
                }

                int[] searchValues = new int[1000];

                for (int i = 0; i < searchValues.length; i++) {
                    searchValues[i] = random.nextInt();
                }

                array.resetComparisons();
                list.resetComparisons();

                long start = System.nanoTime();

                for (int value : searchValues) {
                    array.contains(value);
                }

                long end = System.nanoTime();

                arrayTotal += end - start;
                arrayComparisons += array.getComparisons();

                start = System.nanoTime();

                for (int value : searchValues) {
                    list.contains(value);
                }

                end = System.nanoTime();

                listTotal += end - start;
                listComparisons += list.getComparisons();
            }

            double arrayAverage = arrayTotal / (double) RUNS;
            double listAverage = listTotal / (double) RUNS;

            double arrayAverageComparisons =
                    arrayComparisons / (double) RUNS;

            double listAverageComparisons =
                    listComparisons / (double) RUNS;

            System.out.println("n = " + n);
            System.out.println("Dynamic Array: "
                    + arrayAverage + " ns, comparisons = "
                    + arrayAverageComparisons);

            System.out.println("Linked List: "
                    + listAverage + " ns, comparisons = "
                    + listAverageComparisons);
        }
    }

    public static void insertionRemovalBenchmark() {
        Random random = new Random(42);

        for (int n : SIZES) {
            System.out.println("n = " + n);

            for (int positionType = 0; positionType < 2; positionType++) {
                long arrayInsertTotal = 0;
                long arrayRemoveTotal = 0;

                long listInsertTotal = 0;
                long listRemoveTotal = 0;

                long arrayInsertMovements = 0;
                long arrayRemoveMovements = 0;

                long listInsertAccesses = 0;
                long listRemoveAccesses = 0;

                for (int run = 0; run < RUNS; run++) {

                    int[] values = new int[n];

                    for (int i = 0; i < n; i++) {
                        values[i] = random.nextInt();
                    }

                    DynamicArr array = new DynamicArr();
                    LinkedList list = new LinkedList();

                    for (int value : values) {
                        array.add(value);
                        list.add(value);
                    }

                    int index;

                    if (positionType == 0) {
                        index = 0;
                    } else {
                        index = n / 2;
                    }

                    array.resetMovements();
                    long start = System.nanoTime();

                    for (int i = 0; i < 1000; i++) {
                        array.add(index, i);
                    }

                    long end = System.nanoTime();

                    arrayInsertTotal += end - start;
                    arrayInsertMovements += array.getMovements();

                    array = new DynamicArr();

                    for (int value : values) {
                        array.add(value);
                    }

                    array.resetMovements();
                    start = System.nanoTime();

                    int removalCount = Math.min(1000, array.getSize());
                    for (int i = 0; i < removalCount; i++) {
                        int currentIndex;
                        if (positionType == 0) {
                            currentIndex = 0;
                        } else {
                            currentIndex = array.getSize() / 2;
                        }
                        array.remove(currentIndex);
                    }
                    end = System.nanoTime();
                    arrayRemoveTotal += end - start;
                    arrayRemoveMovements += array.getMovements();


                    list = new LinkedList();

                    for (int value : values) {
                        list.add(value);
                    }

                    list.resetAccesses();
                    start = System.nanoTime();

                    for (int i = 0; i < 1000; i++) {
                        list.add(index, i);
                    }

                    end = System.nanoTime();

                    listInsertTotal += end - start;
                    listInsertAccesses += list.getAccesses();


                    list = new LinkedList();

                    for (int value : values) {
                        list.add(value);
                    }

                    list.resetAccesses();
                    start = System.nanoTime();
                    int removalCountList = Math.min(1000, list.getSize());

                    for (int i = 0; i < removalCountList; i++) {
                        int currentIndex;

                        if (positionType == 0) {
                            currentIndex = 0;
                        } else {
                            currentIndex = list.getSize() / 2;
                        }

                        list.remove(currentIndex);
                    }
                    end = System.nanoTime();

                    listRemoveTotal += end - start;
                    listRemoveAccesses += list.getAccesses();
                }

                double arrayInsertAverage =
                        arrayInsertTotal / (double) RUNS;

                double arrayRemoveAverage =
                        arrayRemoveTotal / (double) RUNS;

                double listInsertAverage =
                        listInsertTotal / (double) RUNS;

                double listRemoveAverage =
                        listRemoveTotal / (double) RUNS;

                double avgArrayInsertMovements =
                        arrayInsertMovements / (double) RUNS;

                double avgArrayRemoveMovements =
                        arrayRemoveMovements / (double) RUNS;

                double avgListInsertAccesses =
                        listInsertAccesses / (double) RUNS;

                double avgListRemoveAccesses =
                        listRemoveAccesses / (double) RUNS;

                String position;

                if (positionType == 0) {
                    position = "beginning";
                } else {
                    position = "middle";
                }

                System.out.println("Position: " + position);

                System.out.println(
                        "Dynamic Array insert: "
                                + arrayInsertAverage
                                + " ns, movements = "
                                + avgArrayInsertMovements);

                System.out.println(
                        "Dynamic Array remove: "
                                + arrayRemoveAverage
                                + " ns, movements = "
                                + avgArrayRemoveMovements);

                System.out.println(
                        "Linked List insert: "
                                + listInsertAverage
                                + " ns, accesses = "
                                + avgListInsertAccesses);

                System.out.println(
                        "Linked List remove: "
                                + listRemoveAverage
                                + " ns, accesses = "
                                + avgListRemoveAccesses);
            }
        }
    }

    public static void heapBenchmark() {
        Random random = new Random(42);

        for (int n : SIZES) {
            long insertTotal = 0;
            long extractTotal = 0;
            long totalComparisons = 0;

            for (int run = 0; run < RUNS; run++) {

                int[] values = new int[n];

                for (int i = 0; i < n; i++) {
                    values[i] = random.nextInt();
                }

                MinHeap heap = new MinHeap();

                long start = System.nanoTime();

                for (int value : values) {
                    heap.insert(value);
                }

                long end = System.nanoTime();

                insertTotal += end - start;

                heap.resetComparisons();

                start = System.nanoTime();

                int previous = Integer.MIN_VALUE;
                boolean sorted = true;

                while (heap.getSize() > 0) {
                    int current = heap.extractMin();

                    if (current < previous) {
                        sorted = false;
                    }

                    previous = current;
                }

                end = System.nanoTime();

                extractTotal += end - start;
                totalComparisons += heap.getComparisons();

                if (!sorted) {
                    System.out.println("ERROR: Heap output is not sorted!");
                }
            }

            double insertAverage =
                    insertTotal / (double) RUNS;

            double extractAverage =
                    extractTotal / (double) RUNS;

            double comparisonAverage =
                    totalComparisons / (double) RUNS;

            System.out.println("n = " + n);
            System.out.println(
                    "Insert: " + insertAverage + " ns");

            System.out.println(
                    "ExtractMin: " + extractAverage + " ns");

            System.out.println(
                    "Comparisons: " + comparisonAverage);
        }
    }

}
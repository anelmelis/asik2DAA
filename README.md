# Assignment 2 — Algorithmic Analysis, Correctness and Performance Trade-offs

## 1. Overview

This project focuses on the implementation, analysis, and experimental evaluation of three fundamental data structures: Dynamic Array, Linked List, and Min Heap.

The goal of this assignment is to compare theoretical algorithm complexity with practical performance measurements. All data structures were implemented from scratch in Java.

The implemented structures were tested using different workloads:
- random access;
- searching;
- insertion and removal;
- priority processing using Min Heap.

Execution time, element accesses, movements, and comparisons were measured to analyze the practical behavior of each data structure.

The results demonstrate that different workloads require different data structures. Dynamic Array provides efficient random access, Linked List can be useful for certain modification operations, and Min Heap is suitable for priority-based processing.


---

# 2. Complexity Analysis

## 2.1 Dynamic Array

| Operation | Best Case Ω | Average Case Θ | Worst Case O |
|---|---|---|---|
| add(x) | Ω(1) | Θ(1) | O(n) |
| add(index,x) | Ω(1) | Θ(n) | O(n) |
| remove(index) | Ω(1) | Θ(n) | O(n) |
| get(index) | Ω(1) | Θ(1) | O(1) |
| contains(x) | Ω(1) | Θ(n) | O(n) |

Auxiliary Space: O(n)

Dynamic Array provides constant-time indexed access because elements are stored continuously in memory. However, insertion and removal may require shifting elements.


## 2.2 Linked List

| Operation | Best Case Ω | Average Case Θ | Worst Case O |
|---|---|---|---|
| add(x) | Ω(1) | Θ(n) | O(n) |
| add(index,x) | Ω(1) | Θ(n) | O(n) |
| remove(index) | Ω(1) | Θ(n) | O(n) |
| get(index) | Ω(1) | Θ(n) | O(n) |
| contains(x) | Ω(1) | Θ(n) | O(n) |

Auxiliary Space: O(n)

Linked List operations require traversal because elements are stored in separate nodes connected by references.


## 2.3 Min Heap

| Operation | Best Case Ω | Average Case Θ | Worst Case O |
|---|---|---|---|
| insert(x) | Ω(1) | Θ(log n) | O(log n) |
| peekMin() | Ω(1) | Θ(1) | O(1) |
| extractMin() | Ω(log n) | Θ(log n) | O(log n) |

Auxiliary Space: O(n)


---

# 3. Correctness and Loop Invariants

## 3.1 Dynamic Array add(index,x)

### Loop Invariant

During the shifting loop, all elements after the insertion position that have already been processed are moved one position to the right while preserving their original order.

### Initialization

Before the first iteration, no elements have been shifted, therefore the invariant is true.

### Maintenance

Each iteration copies `data[i-1]` into `data[i]`, correctly shifting one element to the right.

### Termination

When the loop stops, the position `index` becomes empty and the new element can be inserted.

### Correctness

The invariant guarantees that all elements are shifted correctly, therefore the insertion operation produces a valid dynamic array.


## 3.2 Min Heap extractMin()

### Loop Invariant

Before each iteration of heapify-down, all nodes except possibly the current node satisfy the min-heap property.

### Initialization

After replacing the root with the last element, only the root may violate the heap property.

### Maintenance

The algorithm swaps the current node with the smallest child, restoring the heap property at the current position.

### Termination

When no child is smaller than the current node, the heap property is restored.

### Correctness

The invariant guarantees that after termination the entire structure satisfies the min-heap property.


---

# 4. Experimental Setup
## 4.1 Input Sizes

The experiments were performed using the following input sizes:

n = {100, 1000, 10000, 100000}

Each workload was tested using the same values of n to observe how performance changes with increasing input size.


## 4.2 Benchmark Configuration

Each experiment was executed 5 times.

The average execution time was calculated from all runs.

Execution time was measured using:

System.nanoTime()

Input generation and output printing were excluded from the measured section.

Random data was generated using a fixed seed:

Random(42)

This ensures reproducibility of the experiments.


## 4.3 Workloads

### Workload 1 — Random Access

Structures:
- Dynamic Array
- Linked List

Procedure:
1. Create a structure containing n random integers.
2. Generate 10,000 random indices.
3. Perform get(index) operations.
4. Measure execution time.
5. Record element accesses.


### Workload 2 — Search

Structures:
- Dynamic Array
- Linked List

Procedure:
1. Create a structure containing n random integers.
2. Generate 1,000 search values.
3. Perform contains(value) operations.
4. Measure execution time.
5. Count comparisons.


### Workload 3 — Insertion and Removal

Structures:
- Dynamic Array
- Linked List

Operations:
- insertion at index 0;
- removal at index 0;
- insertion at index n/2;
- removal at index n/2.

Recorded metrics:
- execution time;
- element movements;
- element accesses.


### Workload 4 — Priority Processing

Structure:
- Min Heap

Procedure:
1. Insert n random integers.
2. Measure insertion time.
3. Extract minimum element n times.
4. Measure extraction time.
5. Count comparisons.
6. Verify sorted extraction order.



# 5. Experimental Results

## 5.1 Random Access Results
| n | Dynamic Array Time(ns) | Dynamic Array Accesses | Linked List Time(ns) | Linked List Accesses |
|---|---:|---:|---:|---:|
|100|174020|10000|708000|506978.6|
|1000|25700|10000|5672040|5021577.4|
|10000|15520|10000|59035360|49916574.6|
|100000|116380|10000|577887200|499953032.8|


Results are stored in: results/tables/random_access_results.csv

The corresponding plots are located in: results/plots/

The experiment shows that Dynamic Array provides almost constant access time because elements can be accessed directly by index.

Linked List requires traversal through nodes, therefore the number of accesses increases linearly with input size.


## 5.2 Search Results

Results are stored in: results/tables/search_results.csv

Both Dynamic Array and Linked List perform linear search, therefore the number of comparisons increases proportionally with n.

Dynamic Array generally achieves better execution time because elements are stored continuously in memory.


## 5.3 Insertion and Removal Results

Results are stored in: results/tables/insertion_removal_results.csv


Dynamic Array requires shifting elements during insertion and removal operations, which increases the number of movements.

Linked List avoids shifting but requires traversal to reach the required position.


## 5.4 Min Heap Results

| n | Insert Time(ns) | ExtractMin Time(ns) | Comparisons |
|---|---:|---:|---:|
|100|16720|52740|853.4|
|1000|40680|100980|14980.8|
|10000|338960|802760|216668.8|
|100000|1447780|6980120|2831909.4|

Results are stored in: results/tables/heap_results.csv


The experiments demonstrate that Min Heap operations grow according to their theoretical complexity:

- insert: O(log n)
- extractMin: O(log n)
- peekMin: O(1)



# 6. Discussion

Increasing input size makes the performance differences between data structures more visible.

Dynamic Array performs best for random access because it supports direct indexing. Linked List becomes slower for indexed access because each node must be visited sequentially.

Search operations have linear complexity for both structures. However, Dynamic Array shows better practical performance because of improved memory locality.

Insertion and removal results depend on the position of modification. Dynamic Array spends time moving elements, while Linked List spends time traversing nodes.

Although two algorithms may have the same Big-O complexity, their practical running times can differ because of constant factors and implementation details.

Dynamic Array is suitable when fast access is required. Linked List can be useful when frequent modifications are performed at known positions. Min Heap is appropriate for priority-based applications because it efficiently maintains the smallest element.



# 7. Design Recommendations

| Workload | Recommended Structure | Reason |
|---|---|---|
|Random Access|Dynamic Array|O(1) indexing|
|Frequent insertion at known position|Linked List|No shifting required|
|Priority Processing|Min Heap|Efficient minimum extraction|


# 8. Conclusion

This project demonstrated the implementation and experimental analysis of Dynamic Array, Linked List, and Min Heap data structures.

The experimental results supported the theoretical complexity analysis. Dynamic Array provided efficient random access, Linked List showed the cost of sequential traversal, and Min Heap demonstrated efficient priority processing.

The choice of data structure depends on the workload. Understanding the practical trade-offs between different structures is important for designing efficient algorithms.







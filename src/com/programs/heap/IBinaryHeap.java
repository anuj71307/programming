package com.programs.heap;

public interface IBinaryHeap {

    int getLeft(int index);
    int getRight(int index);
    int getParent(int index);
    int getMin() throws Exception;
    int getMax() throws Exception;
    void insert(int value);
    void delete(int value);
    void heapify();
    int extractMin();
}

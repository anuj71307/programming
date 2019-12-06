package com.programs.heap;

public class MinHeap extends BinaryHeap {

    public MinHeap(int capacity) {
        super(capacity);
    }

    @Override
    public int getMin() {
        if(arr==null || arr.length==0) return -1;
        return arr[0];
    }

    @Override
    public void insert(int value) {

    }

    @Override
    public void delete(int value) {

    }

    @Override
    public int[] sortArray() {
        return new int[0];
    }

    @Override
    public void heapify(int index, int length) {

    }

    @Override
    public int extractMin() {
        return 0;
    }
}

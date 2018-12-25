package com.programs.heap;

public class MinHeap implements IBinaryHeap {
    private int [] arr;
    private int capacity;
    private int size;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
    }

    @Override
    public int getLeft(int index) {
        return 2*index+1;
    }

    @Override
    public int getRight(int index) {
        return 2*index+2;
    }

    @Override
    public int getParent(int index) {
        return (index-1)/2;
    }

    @Override
    public int getMin() {
        if(arr==null || arr.length==0) return -1;
        return arr[0];
    }

    @Override
    public int getMax() throws Exception {
        throw new Exception("Get Max is not supported for MinHeap");
    }

    @Override
    public void insert(int value) {

    }

    @Override
    public void delete(int value) {

    }

    @Override
    public void heapify() {

    }

    @Override
    public int extractMin() {
        return 0;
    }
}

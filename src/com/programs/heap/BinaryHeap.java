package com.programs.heap;

public abstract  class BinaryHeap {

    int arr[];
    public int size;
    int capacitiy;

    BinaryHeap(int capacitiy){
        this.capacitiy = capacitiy;
        arr = new int[capacitiy];
    }

    BinaryHeap(int arr[]){
        this.arr = arr;
        capacitiy = arr.length;
        size = capacitiy;

        for(int i = arr.length/2;i>=0;i--){
            heapify(i, capacitiy);
        }
    }

    void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int getLeft(int index){
        return 2*index+1;
    }
    int getRight(int index){
        return 2*index+2;
    }
    int getParent(int index){
        return (index-1)/2;
    }
    int getMin() {
        throw new RuntimeException("Not valid op");
    }
    int getMax() {
        throw new RuntimeException("Not valid op");
    }
    public abstract void insert(int value);
    public abstract void delete(int value);
    public abstract int[] sortArray();
    public abstract void heapify(int index, int length);
    int extractMin(){
       throw new RuntimeException("Not valid op");
    }
    int extractMax(){

        throw new RuntimeException("Not valid op");
    }
}

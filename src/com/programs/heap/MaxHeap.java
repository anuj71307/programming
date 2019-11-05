package com.programs.heap;

public class MaxHeap extends BinaryHeap {

    public MaxHeap(int capacity){
        super(capacity);
    }

    public MaxHeap(int arr[]){
        super(arr);
    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public void insert(int value) {

        if(size==capacitiy){
            throw new RuntimeException("Array full");
        }
        if(size==-1){
            size=0;
        }
        arr[size] = value;
        size++;

        int index = size-1;
        while(index>=0 && getParent(index) >=0 && arr[index]>arr[getParent(index)]){
            swap(index, getParent(index));
            index = getParent(index);
        }
    }

    @Override
    public void delete(int value) {


    }

    @Override
    public void heapify() {


    }

    @Override
    public int[] sortArray() {
        int len = size-1;
        while(len>=0){
            swap(0, len);
            heapify(0, len);
            len--;
        }

        return arr;
    }

    @Override
    public void heapify(int index, int length) {

        int left = getLeft(index);
        int right = getRight(index);
        int max = index;
        if(left<length && arr[max]<arr[left]){
            max = left;
        }
        if(right<length && arr[max]<arr[right]){
            max = right;
        }
        if(max!=index){
            swap(index, max);
            heapify(max, length);
        }
    }

    @Override
    public int extractMin() {
        return 0;
    }
}

package com.programs.array;

public class CircularQueue<T> {

    Object[] arr;
    int front;
    int rear;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public CircularQueue(int k) {
        arr = new Object[k];
        front=-1;
        rear = -1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(T value) {
        if(isFull()) return false;
        if(front==-1){
            arr[++front] = value;
            rear = front;
        }
        else if(rear==arr.length-1 && front!=0){
            rear = 0;
            arr[rear] = value;
        }
        else{
            arr[++rear] = value;
        }
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        if(front==rear){
            front=-1;
            rear=-1;
        }
        else if(front==arr.length-1){
            front=0;
        }
        else{
            front++;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public T Front() {
        if(isEmpty()) return null;
        return (T)arr[front];
    }

    /** Get the last item from the queue. */
    public T Rear() {
        if(isEmpty()) return null;
        return (T)arr[rear];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return front ==-1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if(rear==arr.length-1 && front==0) return true;
        if(rear == (front-1)%(arr.length-1) && front-1!=-1) return true;
        return false;
    }
}


package com.programs.linkedlist;

public class LinkedList<T> {

    private T data;
    private LinkedList<T> next;
    private LinkedList<T> prev;

    public LinkedList(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedList<T> getNext() {
        return next;
    }

    public void setNext(LinkedList<T> next) {
        this.next = next;
    }

    public LinkedList<T> getPrev() {
        return prev;
    }

    public void setPrev(LinkedList<T> prev) {
        this.prev = prev;
    }



}

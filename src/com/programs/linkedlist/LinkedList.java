package com.programs.linkedlist;

public class LinkedList<T> {

    public T data;
    public LinkedList<T> next;
    public LinkedList<T> prev;

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


    /**
     * add node to last of linked list
     * //TODO Change to remove time complexity of 0(n)
     * @param data
     */
    public void add(T data ){

        LinkedList<T> temp = this;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = new LinkedList<>(data);
        temp.next.prev = temp;
    }
}

package com.programs.queue;

import com.programs.linkedlist.LinkedList;

public class LLQueue<T> {
    private LinkedList<T> head;
    private LinkedList<T> rearNode;
//1 2 3
    public LLQueue() {
        head = null;
    }

    public void enQueue(T data) {
        LinkedList<T> node = new LinkedList<>(data);
        if (head == null) {
            head = node;
            rearNode = node;
        } else {
            rearNode.setNext(node);
            rearNode= node;
        }
    }

    public T deQueue() {
        if (isEmpty()) {
            return null;
        }
        T data = head.getData();
        head = head.getNext();
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

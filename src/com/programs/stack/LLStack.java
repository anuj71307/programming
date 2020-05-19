package com.programs.stack;

import com.programs.linkedlist.LinkedList;
import java.util.EmptyStackException;

/**
 * Stack Implementation using Stack
 * @param <T>
 * @author anujjha
 */
public class LLStack<T> implements IStack<T> {
    private LinkedList<T> head;
    private int size = 0;

    @Override
    public void push(T data) {
        LinkedList<T> node = new LinkedList<>(data);
        if (head != null) {
            node.setNext(head);
        }
        head = node;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T data = head.getData();
            head = head.getNext();
            size--;
            return data;
        }
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return head.getData();
        }

    }

    @Override
    public boolean isStackFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }
}

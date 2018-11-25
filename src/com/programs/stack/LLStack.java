package com.programs.stack;

import com.programs.linkedlist.LinkedList;
import java.util.EmptyStackException;

/**
 * Stack Implementation using Stack
 * @param <T>
 */
public class LLStack<T> implements IStack<T> {
    private LinkedList<T> head;

    @Override
    public void push(T data) {
        LinkedList<T> node = new LinkedList<>(data);
        if (head == null) {
            head = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    @Override
    public T pop() {
        if (isStackEmpty()) {
            throw new EmptyStackException();
        } else {
            T data = head.getData();
            head = head.getNext();
            return data;
        }
    }

    @Override
    public T top() {
        if (isStackEmpty()) {
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
    public boolean isStackEmpty() {
        return head == null;
    }
}

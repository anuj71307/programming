package com.programs.stack;

/**
 * @author anujjha
 * @param <T>
 */
public interface IStack<T> {

    void push(T data);

    T pop();

    T peek();

    boolean isStackFull();

    boolean isEmpty();
}

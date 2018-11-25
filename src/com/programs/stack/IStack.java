package com.programs.stack;

public interface IStack<T> {

    void push(T data);

    T pop();

    T top();

    boolean isStackFull();

    boolean isStackEmpty();
}

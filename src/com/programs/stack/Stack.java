package com.programs.stack;

import java.util.EmptyStackException;

/**
 * Stack Implementation using array
 *
 * @param <T>
 * @author anujjha
 */
public class Stack<T> implements IStack<T> {
    private int top = -1;
    private int mCapacity;
    private Object[] mStackArr;

    public Stack(int capacity) {
        this.mCapacity = capacity;
        mStackArr = new Object[capacity];
    }
    public Stack() {
        this.mCapacity = 100;
        mStackArr = new Object[100];
    }

    /**
     * add an elelmnt to stack
     *
     * @param data
     */
    public void push(T data) {
        if (isStackFull()) {
            System.out.println("Stack Oveflow, Push failed, Stack Full");
            throw new StackOverflowError();
        }
        mStackArr[++top] = data;
    }

    /**
     * delete an element and return that element
     *
     * @return
     */
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow, Push failed, Stack Full");
            throw new EmptyStackException();
        } else {
            T data = (T) mStackArr[top];
            top--;
            return data;
        }
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isStackFull() {
        return top == (mCapacity - 1);
    }

    public void deleteStack() {
        top = -1;
    }

    public T top() {
        if (top == -1) {
            System.out.println("top - No record");
            throw new EmptyStackException();
        } else {
            return (T) mStackArr[top];
        }
    }

    public int getMinimum() {
        int min = 0;

        return min;
    }


}

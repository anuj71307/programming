package com.programs.stack;

import java.util.EmptyStackException;

/**
 * https://leetcode.com/problems/min-stack/
 * @param <T>
 */
public class MinStack<T extends Comparable> implements IStack<T> {

    Stack<T> stack;
    Stack<T> minStack;

    public MinStack() {
        stack = new Stack(10);
        minStack = new Stack<>(10);
    }

    public MinStack(int size) {
        stack = new Stack(size);
        minStack = new Stack<>(size);
    }

    @Override
    public void push(T data) {
        if (stack.isStackFull()) {
            throw new StackOverflowError();
        }
        stack.push(data);

        if(minStack.isEmpty() || data.compareTo(minStack.top())<=0){
            minStack.push(data);
        }
    }

    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        T d =  stack.pop();
        if(d.compareTo(minStack.top())==0){
            minStack.pop();
        }
        return d;
    }

    @Override
    public T top() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.top();
    }


    @Override
    public boolean isStackFull() {
        return stack.isStackFull();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public T getMin() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return minStack.top();
    }
}

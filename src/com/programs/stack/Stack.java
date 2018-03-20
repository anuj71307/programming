package com.programs.stack;

import java.util.EmptyStackException;

/**
 * Stack Implementation
 * @author anujjha
 * @param <T>
 */
public class Stack<T> {
	private static final String TAG = Stack.class.getSimpleName();
    private int top = -1;
    private int mCapacity;
    private Object[] mStackArr;

    public Stack(int capacity)
    {
        this.mCapacity = capacity;
        mStackArr = new Object[capacity];
    }

    /**
     * add an elelmnt to stack
     * @param data
     */
    public void push(T data)
    {
        if (isStackFull()) {
            System.out.println("Stack Oveflow, Push failed, Stack Full");
            return;
        }
        mStackArr[++top] = data;
    }

    /**
     * delete an element and return that element
     * @return
     */
    public T pop()
    {
        if (isStackEmpty()) {
            System.out.println("Stack Underflow, Push failed, Stack Full");
            throw new EmptyStackException();
        }
        else {
            T data = (T)mStackArr[top];
            top--;
            return data;
        }
    }

    public boolean isStackEmpty()
    {
        return (top == -1);
    }

    public boolean isStackFull()
    {
        return top == (mCapacity - 1);
    }

    public void deleteStack()
    {
        top = -1;
    }

    public T top()
    {
        if (top == -1) {
            System.out.println("top - No record");
            throw new EmptyStackException();
        }
        else {
            return (T)mStackArr[top];
        }
    }

    public int getMinimum(){
        int min = 0;

        return min;
    }


}

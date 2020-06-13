package com.programs.queue;

import com.programs.stack.Stack;

/**
 * Queue Using two stack write heavy
 *
 * @param <T>
 */
public class QueueUsingStack<T> {
    Stack<T> write;
    Stack<T> read;

    public QueueUsingStack() {
        write = new Stack<>();
        read = new Stack<>();
    }

    void push(T data) {
        while (!read.isEmpty()) {
            write.push(read.pop());
        }
        write.push(data);
        while (!write.isEmpty()) {
            read.push(write.pop());
        }
    }

    T poll() {
        return read.pop();
    }

    public static void main(String[] arg) {
        QueueUsingOneStack<Integer> q = new QueueUsingOneStack<>();
        q.push(2);
        q.push(3);
        q.push(11);
        System.out.println(q.poll());
        System.out.println(q.poll());
        q.push(4);
        q.push(8);
        q.push(9);
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());


    }
}


/**
 * Queue using one stack , read heavy
 *
 * @param <T>
 */
class QueueUsingOneStack<T> {
    Stack<T> stack = new Stack<>();

    void push(T data) {
        stack.push(data);
    }

    T poll() {
        if (stack.size() <= 1) return stack.pop();
        T temp = stack.pop();
        T value = poll();
        stack.push(temp);
        return value;
    }
}

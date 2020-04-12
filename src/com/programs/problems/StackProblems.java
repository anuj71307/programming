package com.programs.problems;

import com.programs.stack.MinStack;

import java.util.Stack;

public class StackProblems {

    public static void main(String[] args){
        MinStack<Integer> minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
        System.out.println(minStack.pop());
        System.out.println(minStack.getMin());

    }

    /**
     * sort an stack without using any OTHER data structure or array
     * @param stack
     */
    private static void sortStack(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        while(!stack.isEmpty()){
            int t = stack.pop();
            while(!temp.isEmpty() && temp.peek()>t){
                stack.push(temp.pop());
            }

            temp.push(t);
        }


        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
    }
}

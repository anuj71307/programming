package com.programs.problems;

import java.util.Stack;

public class StackProblems {

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(6);
        stack.push(4);
        stack.push(9);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(11);
        stack.push(8);

        sortStack(stack);

        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }

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

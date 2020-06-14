package com.programs.problems;

import com.programs.stack.MinStack;

import java.util.Stack;

public class StackProblems {

    public static void main(String[] args) throws Exception{
        StackProblems sp = new StackProblems();
        Stack<Integer> st = new Stack<>();
        st.push(0);
        st.push(1);
        st.push(2);
        sp.reverse(st);
        // 1 2 3 4 
        while(!st.isEmpty()){
            System.out.print(st.pop()+" ");
        }

    }

    /**
     * Reverse stack using recursion
     * @param st
     */
    void reverse(Stack<Integer> st) {
        if (st.isEmpty()) return;
        int k = st.pop();
        reverse(st);
        insert(st, k);
    }

    void insert(Stack<Integer> st, int item){
        if(st.isEmpty()) {
            st.push(item);
            return;
        }
        int p = st.pop();
        insert(st, item);
        st.push(p);
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

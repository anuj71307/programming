package com.programs.problems;

import com.programs.queue.LLQueue;

public class QueueProblems {

    public static void main (String args[]){
        LLQueue<Integer> queue = new LLQueue<>();
        System.out.println(queue.isEmpty());
        queue.enQueue(5);
        queue.enQueue(2);
        queue.enQueue(1);


        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}

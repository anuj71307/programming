package com.programs.problems;

import com.programs.queue.LLQueue;

public class QueueProblems {

    public static void main (String args[]){
        LLQueue<Integer> queue = new LLQueue<>();
        queue.enQueue(5);
        queue.enQueue(2);
        queue.enQueue(1);

        queue = reverseQueue(queue);

        while (!queue.isEmpty()){
            System.out.print(queue.deQueue()+" ");
        }
    }

    /**
     * Reverse a queue using recursion
     *
     * @param queue to reverse
     * @return reverse queue
     */
    public static LLQueue<Integer> reverseQueue(LLQueue<Integer> queue) {

        if (queue.isEmpty()) {
            return queue;
        }
        Integer element = queue.deQueue();
        reverseQueue(queue);

        queue.enQueue(element);
        return queue;
    }
}

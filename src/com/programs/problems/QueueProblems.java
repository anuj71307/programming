package com.programs.problems;

import com.programs.queue.LLQueue;

import java.util.PriorityQueue;

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
     * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
     * https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/#:~:text=Total%20cost%20for%20connecting%20all%20ropes%20is%205,%28we%20get%20two%20strings%20of%2013%20and%202%29.
     * This problem can approach by simply taking two minimum element and add them together and then add it back to out array/list
     * We can keep a min heap to find minimum element add add the caluclated sum back to it.
     * Time complexity would be to create Priority Queue or Min heap would be O(N)
     * and then calculating sum would be O(NLogN)
     * @param arr
     * @return
     */
    public long minCostToConnect(int[] arr){
        if(arr==null || arr.length==0) return 0;
        long cost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num: arr){
            pq.add(num);
        }
        while(pq.size()>1){
            int sum = pq.poll()+pq.poll();
            cost+=sum;
            pq.add(sum);
        }
        return cost;
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

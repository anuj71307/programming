package com.programs.random;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * class which will call appropriate method and classes
 * for different problems
 * @author anujjha
 *
 */
public class Solution {


    /**
     * Reverse a queue using recursion
     * @param queue to reverse
     * @return reverse queue
     */
	public static Queue<Integer> reverseQueue(Queue<Integer> queue){

		if(queue.isEmpty()){
			return queue;
		}
		Integer element  = queue.poll();
		reverseQueue(queue);

		queue.add(element);
		return queue;
	}

	public static void main(String[] args) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(12);
		queue.add(13);
		queue.add(14);
		queue.add(15);

		queue = reverseQueue(queue);

		while(!queue.isEmpty()){
			System.out.println(queue.poll());
		}

	}

}

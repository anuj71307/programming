package com.programs.random;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * class which will call appropriate method and classes
 * for different problems
 *
 * @author anujjha
 */
public class Solution {


    /**
     * Reverse a queue using recursion
     *
     * @param queue to reverse
     * @return reverse queue
     */
    public static Queue<Integer> reverseQueue(Queue<Integer> queue) {

        if (queue.isEmpty()) {
            return queue;
        }
        Integer element = queue.poll();
        reverseQueue(queue);

        queue.add(element);
        return queue;
    }

    static int migratoryBirds(int n, int[] ar) {
        // Complete this function
        int type = 0;
        int arr[] = new int[5];
        for (int k : ar) {
            arr[--k]++;
        }
        for (int i = 0; i < 5; i++) {
            if (arr[i] > type) {
                type = i + 1;
            }
        }
        return type;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int ar_i = 0; ar_i < n; ar_i++) {
            ar[ar_i] = in.nextInt();
        }
        int result = migratoryBirds(n, ar);
        System.out.println(result);
    }

}

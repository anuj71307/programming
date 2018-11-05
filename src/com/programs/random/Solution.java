package com.programs.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;

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
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            int n = Integer.parseInt(line.trim());
            for (int i = 0; i < n; i++) {
                String lines = br.readLine();
                String[] strs = lines.trim().split("\\s+");
                int total = Integer.parseInt(strs[0]);
                int targetSum = Integer.parseInt(strs[1]);
                int arr[] = new int[total];
                lines = br.readLine();
                strs = lines.trim().split("\\s+");
                for (int j = 0; j < strs.length; j++) {
                    arr[j] = Integer.parseInt(strs[j]);
                }
                findSubArr(arr, targetSum);
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    /**
     * Find continuous sub array with given sum
     *
     * @param ar        original array
     * @param targetSum sum to found
     */
    private static void findSubArr(int[] ar, int targetSum) {
        int currentSum = ar[0];
        int startP = 0;
        int endP = 0;
        boolean found = false;

        // 1 2 3 7 5
        for (int i = 1; i <= ar.length; i++) {

            while (currentSum > targetSum && startP < i) {
                currentSum = currentSum - ar[startP];
                startP++;
            }

            if (currentSum == targetSum) {
                endP = i - 1;
                found = true;
                break;
            }
            currentSum += ar[i];
        }
        if (found) {
            System.out.println((startP) + " " + (endP));
        } else {
            System.out.println(-1);
        }
    }


}

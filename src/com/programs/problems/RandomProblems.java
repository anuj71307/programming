package com.programs.problems;

import java.util.Queue;

/**
 * class which will call appropriate method and classes
 * for different problems
 *
 * @author anujjha
 */
public class RandomProblems {


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

        int sum = findMaxValue("4567089");
        System.out.println(compareVersion("1.0","1"));
    }

    public static int compareVersion(String a, String b) {
        String str1[] = a.split("\\.");
        String str2[] = b.split("\\.");
        int i = 0;
        while(i<str1.length && i < str2.length){
            double one = Double.parseDouble(str1[i]);
            double two = Double.parseDouble(str2[i]);
            if(one>two){
                return 1;
            }
            else if(one<two){
                return -1;
            }
            i++;

        }

        if(i==str1.length && i == str2.length){
            return 0;
        }

        if(i<str1.length){
            return 1;
        }
        return -1;

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


    /**
     * given a integer find maximum possible value by doing addition or
     * subtraction of digit - in any order
     *
     * @param num input num
     * @return max value
     */
    public static int maxPossible(int num) {
        int sum = 0;
        int multi = 1;
        while (num > 9) {
            int remainder = num % 10;
            if (remainder > 1) {
                multi = multi * remainder;
            } else {
                sum = sum + remainder;
            }
            num = num / 10;
        }

        if (num > 1) {
            multi = multi * num;
        } else {
            sum = sum + num;
        }
        if (sum <= 1) {
            return multi + sum;
        }

        return multi * sum;
    }


    /**
     * given a integer value as string find maximum possible value by inserting only + or * between them
     *
     * @param num input num
     * @return max possible value
     */
    public static int findMaxValue(String num) {
        if (num == null || num.length() < 1) {
            return -1;
        }
        int maxValue = num.charAt(0) - '0';
        for (int i = 1; i < num.length(); i++) {
            int var = num.charAt(i) - '0';
            if (var <= 1) {
                maxValue += var;
            } else {
                if (maxValue <= 1) {
                    maxValue = maxValue + var;
                } else {
                    maxValue = maxValue * var;
                }
            }
        }

        return maxValue;
    }

}

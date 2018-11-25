package com.programs.problems;

import java.util.Queue;

/**
 * class which will call appropriate method and classes
 * for different problems
 *
 * @author anujjha
 */
public class RandomProblems {


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

        int num = 152;
        System.out.println(num==cubeSum(num));
    }

    /**
     * find sum of cube of all digits in a num
     * @param num input num
     * @return sum of cubeof all num
     */
    public static int cubeSum(int num){

        if(num==0) return 0;
        int sum = 0;
        while(num>0){
            int rem = num%10;
            sum+= rem*rem*rem;
            num=num/10;
        }
        return  sum;
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

}

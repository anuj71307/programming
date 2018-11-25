package com.programs.problems;

import java.util.*;

public class ArrayProblems {

    //An array is given of n length, and we need to calculate the next greater element
    // for each element in given array. If next greater element is not available in
    // given array then we need to fill ‘_’ at that index place
    //https://www.geeksforgeeks.org/smallest-greater-elements-in-whole-array/

    private static int firstElementApperaEvenNumberTimes(int[] arr){
      LinkedHashMap<Integer, Boolean> linkedHashMap = new LinkedHashMap<>();

       for(int j: arr){
           if(linkedHashMap.containsKey(j)){
               boolean value = linkedHashMap.get(j);
               linkedHashMap.put(j, !value);
           }else{
               linkedHashMap.put(j, false);
           }
       }
       Set<Integer> set = linkedHashMap.keySet();
       int value = 0;
       for(int k: set){
           if(linkedHashMap.get(k)){
               value = k;
               break;
           }
       }
       return value;
    }

    public static void main(String [] args ){

        int [] arr  = new int[]{1};
        System.out.println("first element "+firstElementApperaEvenNumberTimes(arr));
    }

}

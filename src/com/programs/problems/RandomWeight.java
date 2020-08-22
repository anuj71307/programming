package com.programs.problems;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 * Leetcode 528
 * We store the sum accumated so for for each index , from left to right
 * then generate a random number between 0 to that sum
 * now since our array is sorted so we do a binary search and find first element whose value is >= random number
 */
public class RandomWeight {

    int[] arr;
    Random random;

    /**
     * Time complexity O(N) since we traverse the full array
     * @param w
     */
    public RandomWeight(int[] w) {
        random = new Random();
        arr = new int[w.length];
        int sum = 0;
        for(int i=0;i<arr.length;i++){
            sum+=w[i];
            arr[i] = sum;
        }
    }

    /**
     * Time complexity of O(LogN) to do binary search
     * @return
     */
    public int pickIndex() {
        int i = 0;
        int j = arr.length-1;
        int target = getRandom();
        while (i<=j){
            int mid = (i+j)/2;
            if(arr[mid]==target) return mid;
            if(arr[mid]>target){
                if(mid==0 || arr[mid-1]<target) return mid;
                else j = mid-1;
            }
            else{
                if(mid < arr.length-1 && arr[mid+1]>=target) return mid+1;
                else i = mid+1;
            }
        }
        return -1;
    }

    public int getRandom(){
        return random.nextInt(arr[arr.length-1])+1;
    }
    public static void main(String[] args){
        int[] arr = {1,4,3,5,2,8};
        RandomWeight rw = new RandomWeight(arr);
        for(int i=0;i<5;i++){
            System.out.print(rw.pickIndex()+" ");
        }
    }
}

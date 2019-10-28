package com.programs.trees;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 */
public class SegmentTree {
    int[] arr;

    public static void main(String [] arg){

        int[] src = new int[]{1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(src);
        Arrays.fill(st.arr, -1);
        st.constructTree(src);
        for(int i : st.arr){
            System.out.print(i+" ");
        }
    }

    public SegmentTree(int [] source){

        // Allocate memory for segment tree
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(source.length) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        arr = new int[max_size]; // Memory allocation
    }

    void constructTree(int[] src){
        constructSegmentTree(src, 0, src.length-1, 0);
    }

    private int constructSegmentTree(int[] source, int start, int end, int index) {
        //if(index>= arr.length) return 0;
        if(start==end){
            arr[index] = source[start];
            return source[start];
        }

        int mid = (start+end)/2;
        arr[index] = constructSegmentTree(source, start, mid, 2*index+1)+
                     constructSegmentTree(source, mid+1, end, 2*index+2);
        return arr[index];

    }
}

package com.programs.problems;

import java.io.*;
import java.util.*;
public class TreeProblems {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node.left = node2;
        node.right = node3;
        TreeNode node4 = new TreeNode(4);
        node3.left = node4;
        TreeNode node5 = new TreeNode(5);
        node4.right = node5;
        int[][] arr = levelOrder(node);
        for(int i = 0 ; i < arr.length;i++){
            for(int j = 0; j < arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }


    public static int[][] levelOrder(TreeNode A) {

        int i = 0;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        doTraversal(A, lists, 0);
        int arr[][] = new int[lists.size()][];
        for(ArrayList<Integer> list: lists){
            int[] temp = new int[list.size()];
            int k = 0;
           for(Integer integer: list){
               temp[k] = integer;
               k++;
           }
           arr[i] = temp;
           i++;
        }

        return  arr;
    }

    private static void doTraversal(TreeNode a, ArrayList<ArrayList<Integer>> lists, int depth) {
        if(a==null) return;
        if(depth==lists.size()){
            ArrayList<Integer> temp = new ArrayList<>();
            lists.add(temp);
        }
        ArrayList<Integer> temp = lists.get(depth);
        temp.add(a.val);
        doTraversal(a.left, lists, depth+1);
        doTraversal(a.right, lists, depth+1);
    }



}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
       val = x;
      left=null;
       right=null;
     }
  }
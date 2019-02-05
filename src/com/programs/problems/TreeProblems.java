package com.programs.problems;

import java.io.*;
import java.util.*;

public class TreeProblems {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node.right = node3;
        node.left = node2;
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        node2.left = node4;
        // node3.right = node5;
        hasPathSum(node, 4);

    }

    /*
      check if tree has root to path with given sum
     */
    static void hasPathSum(TreeNode node, int sum) {
        // Your code here
        ArrayList<Integer> list = new ArrayList<>();
        hasPathSum(node, sum, list);

    }

    static void hasPathSum(TreeNode node, int sum, ArrayList<Integer> list) {
        if (node == null) return;
        list.add(node.data);
        if (node.left == null && node.right == null && node.data == sum) {
            for (Integer i : list) System.out.print(i + " ");
            System.out.println();
        }
        hasPathSum(node.right, sum - node.data, list);
        hasPathSum(node.left, sum - node.data, list);
        if (!list.isEmpty())
            list.remove(list.size() - 1);
    }


    public static int[][] levelOrder(TreeNode A) {

        int i = 0;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        doTraversal(A, lists, 0);
        int arr[][] = new int[lists.size()][];
        for (ArrayList<Integer> list : lists) {
            int[] temp = new int[list.size()];
            int k = 0;
            for (Integer integer : list) {
                temp[k] = integer;
                k++;
            }
            arr[i] = temp;
            i++;
        }

        return arr;
    }

    private static void doTraversal(TreeNode a, ArrayList<ArrayList<Integer>> lists, int depth) {
        if (a == null) return;
        if (depth == lists.size()) {
            ArrayList<Integer> temp = new ArrayList<>();
            lists.add(temp);
        }
        ArrayList<Integer> temp = lists.get(depth);
        temp.add(a.data);
        doTraversal(a.left, lists, depth + 1);
        doTraversal(a.right, lists, depth + 1);
    }


}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        data = x;
        left = null;
        right = null;
    }
}
package com.programs.problems;

import java.util.*;

public class TreeProblems {
    public static void main(String args[]) throws Exception {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node3.left = new TreeNode(6);


        System.out.println(isCompleteTree(node1));

    }


    /**
     * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {

                if (flag) {
                    return false;
                }
                queue.add(node.left);
            } else {
                flag = true;
            }

            if (node.right != null) {

                if (flag) {
                    return false;
                }
                queue.add(node.right);
            } else {
                flag = true;
            }

        }

        return true;
    }


    /*
    https://www.geeksforgeeks.org/find-count-of-singly-subtrees/
     */
    static int countUniValue(TreeNode node) {
        Count count = new Count();
        countUniValue(node, count);
        return count.res;
    }

    static class Count {
        int res;
    }

    /*
    https://www.geeksforgeeks.org/find-count-of-singly-subtrees/
     */
    private static boolean countUniValue(TreeNode node, Count count) {
        if (node == null) return true;
        boolean left = countUniValue(node.left, count);
        boolean right = countUniValue(node.right, count);
        if (left && right && (node.left == null || (node.left != null && node.data == node.left.data))
                && (node.right == null || (node.right != null && node.data == node.right.data))) {
            count.res++;
            return true;
        }
        return false;
    }

    static public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        preOrder(root, 0, map);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> li = entry.getValue();
            Collections.sort(li);
            list.add(li);
        }
        return list;
    }


    static void preOrder(TreeNode root, int dis, TreeMap<Integer, List<Integer>> map) {
        if (root == null) return;
        List<Integer> list = map.get(dis);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(root.data);
        map.put(dis, list);
        preOrder(root.left, dis - 1, map);
        preOrder(root.right, dis + 1, map);
    }

    /**
     * check if two nodes are cousin ie at same level but not same parent
     * https://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public static boolean ifCousin(TreeNode root, TreeNode x, TreeNode y) {
        //Your Code here.
        if (root == null) return false;
        List<TreeNode> pathx = new ArrayList<>();
        getPath(root, x, pathx);
        List<TreeNode> pathy = new ArrayList<>();
        getPath(root, y, pathy);
        if (pathx.size() != pathy.size()) return false;
        if (pathx.isEmpty() || pathy.isEmpty()) return false;
        return pathx.get(pathx.size() - 1) != pathy.get(pathy.size() - 1);
    }

    /**
     * get path of a node from root
     *
     * @param root
     * @param x
     * @param path
     * @return
     */
    static boolean getPath(TreeNode root, TreeNode x, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        if (root == x) return true;
        path.add(root);
        if (getPath(root.left, x, path)) {
            return true;
        } else if (getPath(root.right, x, path)) {
            return true;
        }
        if (path.size() > 0)
            path.remove(path.size() - 1);

        return false;
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
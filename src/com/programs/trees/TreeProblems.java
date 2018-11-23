package com.programs.trees;

import com.programs.stack.Stack;

public class TreeProblems {

    public static void main(String args[]) {
        BinaryTree<Integer> tree = new BinaryTree<>(1);
        BinaryTree<Integer> tree2 = new BinaryTree<>(2);
        BinaryTree<Integer> tree3 = new BinaryTree<>(3);
        tree.setLeftTree(tree2);
        tree.setRightTree(tree3);

        BinaryTree<Integer> tree4 = new BinaryTree<>(4);
        BinaryTree<Integer> tree5 = new BinaryTree<>(5);
        tree2.setLeftTree(tree4);
        tree2.setRightTree(tree5);


        BinaryTree<Integer> tree6 = new BinaryTree<>(6);
        BinaryTree<Integer> tree7 = new BinaryTree<>(7);
        tree3.setLeftTree(tree6);
        tree3.setRightTree(tree7);


        BinaryTree<Integer> tree8 = new BinaryTree<>(8);
        BinaryTree<Integer> tree9 = new BinaryTree<>(9);
        tree6.setLeftTree(tree9);
        tree4.setRightTree(tree8);


        zigzagTraversal(tree);


    }

    /**
     * zig zag traversal of a tree
     *
     * @param tree
     */
    public static void zigzagTraversal(BinaryTree tree) {


        if (tree == null) return;

        boolean leftToRight = false;
        Stack<BinaryTree> stack1 = new Stack<>(100);
        Stack<BinaryTree> stack2 = new Stack<>(100);
        stack1.push(tree);
        while (true) {
            while (!stack1.isStackEmpty()) {
                BinaryTree tree1 = stack1.pop();
                System.out.print(tree1.getData() + " ");
                if (leftToRight) {
                    if (tree1.getLeftTree() != null) {
                        stack2.push(tree1.getLeftTree());
                    }
                    if (tree1.getRightTree() != null) {
                        stack2.push(tree1.getRightTree());
                    }
                } else {
                    if (tree1.getRightTree() != null) {
                        stack2.push(tree1.getRightTree());
                    }
                    if (tree1.getLeftTree() != null) {
                        stack2.push(tree1.getLeftTree());
                    }
                }
            }
            leftToRight = !leftToRight;
            if (stack2.isStackEmpty()) {
                break;
            }
            stack1 = stack2;
            stack2 = new Stack<>(100);
        }
    }
}

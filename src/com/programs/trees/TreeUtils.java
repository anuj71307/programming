package com.programs.trees;

import java.util.LinkedList;
import java.util.Queue;

import com.programs.stack.Stack;

/**
 * Various tree problem and their solution
 *
 * @author anujjha
 */
public class TreeUtils {

    /**
     * find size of tree without recursion
     *
     * @param tree node for which size to be found
     * @return size of tree
     */
    private static int findSizeifTreeWithoutRecursion(ITree tree) {
        int size = 0;
        if (tree == null) {
            return size;
        }
        Queue<ITree> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            size++;
            ITree temp = queue.remove();
            if (temp.getLeftNode() != null) queue.add(temp.getLeftNode());
            if (temp.getRightNode() != null) queue.add(temp.getRightNode());
        }
        return size;
    }

    /**
     * print level order in reverse order
     * 1
     * /\
     * 2  3
     * for above it will print 2 3 1
     *
     * @param tree
     */
    private static void levelOrderTraversalInReverseOrder(ITree tree) {
        //user stack and queue . first put all elements in queue right tree then left tree
        //then put all element in stack
        if (tree == null) {
            return;
        }
        Queue<ITree> queue = new LinkedList<>();
        queue.add(tree);
        Stack<ITree> stack = new Stack<>(tree.size());
        while (!queue.isEmpty()) {
            ITree temp = queue.poll();
            if (temp.getRightNode() != null) {
                queue.add(temp.getRightNode());
            }
            if (temp.getLeftNode() != null) {
                queue.add(temp.getLeftNode());
            }
            stack.push(temp);
        }

        //Iterate all element of stack and print the data
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getData() + " ");
        }

    }

    /**
     * Pre Order traversal
     *
     * @param tree
     */
    private static void preOrderWithoutRecursion(ITree tree) {
        Stack stack = new Stack(tree.size());
        while (true) {

            while (tree != null) {
                System.out.print(tree.getData() + " ");
                stack.push(tree);
                tree = tree.getLeftNode();
            }
            if (stack.isEmpty()) {
                break;
            }
            tree = (BinaryTree) stack.pop();
            tree = tree.getRightNode();
        }
    }

    /**
     * print ancestor of a node
     *
     * @param root
     * @param node
     * @return
     */
    private static boolean printAncestorOfNode(ITree root, BinaryTree node) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        if (printAncestorOfNode(root.getLeftNode(), node) || printAncestorOfNode(root.getRightNode(), node)) {
            System.out.print(root.getData() + " ");
            return true;
        }
        return false;
    }

    private static final void printLeastCommonAncestor(BinaryTree root, BinaryTree node1, BinaryTree node2) {


    }

    public static void main(String[] args) {
        //create a binary tree


    }









}

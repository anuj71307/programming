package com.programs.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple binary tree with 2 nodes
 *
 * @param <T>
 * @author anujjha
 */
public class BinaryTree<T extends Comparable> implements ITree<T> {
    private T data;
    private ITree<T> leftTree;
    private ITree<T> rightTree;

    public BinaryTree(T data) {
        this.data = data;
    }

    @Override
    public void add(T data) {

        Queue<ITree> queue = new LinkedList<>();
        ITree<T> newNode = new BinaryTree<>(data);
        queue.add(this);
        while (!queue.isEmpty()) {
            ITree<T> node = queue.poll();
            if (node.getLeftNode() == null) {
                node.setLeftNode(newNode);
                return;
            } else {
                queue.add(node.getLeftNode());
            }
            if (node.getRightNode() == null) {
                node.setRightNode(newNode);
                return;
            } else {
                queue.add(node.getRightNode());
            }


        }

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        int size = 1;
        ITree leftTree = this.getLeftNode();
        ITree rightTree = this.getRightNode();
        if (leftTree != null) size = size + leftTree.size();
        if (rightTree != null) size = size + rightTree.size();
        return size;
    }

    @Override
    public boolean delete(T data) {
        return false;
    }

    /**
     * find height of tree using queue
     */
    @Override
    public int height() {
        int level = 0;
        Queue<BinaryTree<T>> queue = new LinkedList<>();
        queue.add(this);
        queue.add(null);
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            if (node == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                level++;

            } else {
                if (node.leftTree != null) {
                    queue.add((BinaryTree<T>) node.leftTree);
                }
                if (node.rightTree != null) {
                    queue.add((BinaryTree<T>) node.rightTree);
                }

            }
        }
        return level;
    }

    @Override
    public int depth() {
        //TODO Anuj Implement
        return 0;
    }

    @Override
    public boolean isMember(T data) {
        //TODO Anuj implement
        return false;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public ITree<T> getLeftNode() {
        return leftTree;
    }

    @Override
    public ITree<T> getRightNode() {
        return rightTree;
    }

    @Override
    public void setLeftNode(ITree<T> node) {
        leftTree = node;
    }

    @Override
    public void setRightNode(ITree<T> node) {
        rightTree = node;
    }

}

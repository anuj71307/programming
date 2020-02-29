package com.programs.trees;

/**
 * BST where left node is less than root and right node is greater than root
 *
 * @param <T>
 * @author anujjha
 */
public class BinarySearchTree<T extends Comparable> implements ITree<T> {
    private static final String TAG = BinarySearchTree.class.getSimpleName();
    private T data;
    private ITree<T> leftNode;
    private ITree<T> rightNode;

    public void setData(T data) {
        this.data = data;
    }

    public BinarySearchTree(T data) {
        this.data = data;
        this.leftNode = null;
        this.rightNode = null;
    }

    public static int getHeight(BinarySearchTree root) {
        //Write your code here
        if (root == null || (root.leftNode == null && root.rightNode == null)) {

            return 0;
        }
        return 1 + max(getHeight((BinarySearchTree) root.leftNode), getHeight((BinarySearchTree) root.rightNode));
    }

    public static int max(int left, int right) {
        if (left > right) {
            return left;
        }
        return right;
    }

    @Override
    public void add(T data) {
        if (this.data.compareTo(data) == 0) {
            System.out.println("Data is same " + this.data + " " + data);
        } else if (this.data.compareTo(data) < 0) {
            if (this.rightNode == null) {
                this.rightNode = new BinarySearchTree(data);
            } else {
                this.rightNode.add(data);
            }
        } else {
            if (this.leftNode == null) {
                this.leftNode = new BinarySearchTree(data);
            } else {
                this.leftNode.add(data);
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
        if (this.leftNode != null) {
            size += this.leftNode.size();
        }
        if (this.rightNode != null) {
            size += this.rightNode.size();
        }
        return size;
    }

    @Override
    public boolean delete(Comparable data) {
        //TODO Implement
        return false;
    }

    @Override
    public int height() {
        //TODO Implement
        return 0;
    }

    @Override
    public int depth() {
        //TODO Implement
        return 0;
    }

    @Override
    public boolean isMember(T data) {
        if (this.data.compareTo(data) == 0) {
            return true;
        } else if (this.data.compareTo(data) < 0) {
            if (this.rightNode != null) {
                return this.rightNode.isMember(data);
            }
        } else {
            if (this.leftNode != null) {
                return this.leftNode.isMember(data);
            }

        }
        return false;
    }

    public T getData() {
        return data;
    }

    @Override
    public ITree getLeftNode() {
        return leftNode;
    }

    @Override
    public ITree getRightNode() {
        return rightNode;
    }

    @Override
    public void setLeftNode(ITree<T> node) {
        leftNode = node;
    }

    @Override
    public void setRightNode(ITree<T> node) {
        rightNode = node;
    }
}

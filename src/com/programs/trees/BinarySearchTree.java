package com.programs.trees;

/**
 * BST where left node is less than root and right node is greater than root
 * @author anujjha
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable> implements ITree<T> {
	 private static final String TAG = BinarySearchTree.class.getSimpleName();
	    private T data;
	    private ITree<T> leftTree;
	    private ITree<T> rightTree;


	    public BinarySearchTree(T data)
	    {
	        this.data = data;
	        this.leftTree = null;
	        this.rightTree = null;
	    }

	    public static int getHeight(BinarySearchTree root){
	        //Write your code here
	        if(root==null || (root.leftTree==null && root.rightTree==null)){

	            return 0;
	        }
	        return 1 + max(getHeight((BinarySearchTree) root.leftTree), getHeight((BinarySearchTree) root.rightTree));
	    }

	    public static int max(int left, int right){
	        if(left>right){
	            return left;
	        }
	        return right;
	    }
	    @Override
	    public void add(T data)
	    {
	        if (this.data.compareTo(data) == 0) {
	            System.out.println("Data is same " + this.data + " " + data);
	        }
	        else if (this.data.compareTo(data) < 0) {
	            if (this.rightTree == null) {
	                this.rightTree = new BinarySearchTree(data);
	            }
	            else {
	                this.rightTree.add(data);
	            }
	        }
	        else {
	            if (this.leftTree == null) {
	                this.leftTree = new BinarySearchTree(data);
	            }
	            else {
	                this.leftTree.add(data);
	            }
	        }

	    }

	    @Override
	    public boolean isEmpty()
	    {
	        return false;
	    }

	    @Override
	    public int size()
	    {
	        int size = 1;
	        if (this.leftTree != null) {
	            size += this.leftTree.size();
	        }
	        if (this.rightTree != null) {
	            size += this.rightTree.size();
	        }
	        return size;
	    }

	    @Override
	    public boolean delete(Comparable data)
	    {
	        return false;
	    }

	    @Override
	    public int height()
	    {
	        return 0;
	    }

	    @Override
	    public int depth()
	    {
	        return 0;
	    }

	    @Override
	    public boolean isMemeber(T data)
	    {
	        if (this.data.compareTo(data) == 0) {
	            return true;
	        }
	        else if (this.data.compareTo(data) < 0) {
	            if (this.rightTree != null) {
	                return this.rightTree.isMemeber(data);
	            }
	        }
	        else {
	            if (this.leftTree != null) {
	                return this.leftTree.isMemeber(data);
	            }

	        }
	        return false;
	    }

	    public T getData()
	    {
	        return data;
	    }
	    @Override
	    public ITree getLeftTree()
	    {
	        return leftTree;
	    }
	    @Override
	    public ITree getRightTree()
	    {
	        return rightTree;
	    }
}

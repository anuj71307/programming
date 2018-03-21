package com.programs.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends  Comparable> implements ITree<T>
{
    private T data;
    private BinaryTree<T> leftTree;
    private BinaryTree<T> rightTree;

    public BinaryTree(T data)
    {
        this.data = data;
    }

    @Override
    public void add(T data)
    {

        Queue<BinaryTree> queue = new LinkedList<>();
        BinaryTree<T> newNode = new BinaryTree(data);
        queue.add(this);
        while (!queue.isEmpty()) {
            BinaryTree<T> node = queue.poll();
            if (node.leftTree == null) {
                node.leftTree = newNode;
                return;
            }
            else {
                queue.add(node.leftTree);
            }
            if (node.rightTree == null) {
                node.rightTree = newNode;
                return;
            }
            else {
                queue.add(node.rightTree);
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
    	BinaryTree leftTree = this.getLeftTree();
    	BinaryTree rightTree = this.getRightTree();
    	if(leftTree!=null) size = size+leftTree.size();
    	if(rightTree!=null) size = size+rightTree.size();
    return size;
    }

    @Override
    public boolean delete(T data)
    {
        return false;
    }

    /**
     * find height of tree using queue
     */
    @Override
    public int height()
    {
      	int level = 0;
      	Queue<BinaryTree<T>> queue = new LinkedList<>();
      	queue.add(this);
      	queue.add(null);
      	while(!queue.isEmpty()) {
          BinaryTree node = queue.poll();
          if(node == null) {
        	     if(!queue.isEmpty()) {
        	    	  queue.add(null);
        	     }
        	     level++;
        	  
          }
          else {
        	   if(node.leftTree!=null) {
        		   queue.add((BinaryTree<T>) node.leftTree);
        	   }
        	   if(node.rightTree!=null) {
        		   queue.add((BinaryTree<T>) node.rightTree);
        	   }
        	  
          }
      	}
        return level;
    }

    @Override
    public int depth()
    {
        return 0;
    }

    @Override
    public boolean isMemeber(T data)
    {
        return false;
    }

    @Override
	public T getData() {
		return data;
	}

	@Override
	public BinaryTree<T> getLeftTree() {
		return leftTree;
	}

	public void setLeftTree(BinaryTree<T> leftTree) {
		this.leftTree = leftTree;
	}

	@Override
	public BinaryTree<T> getRightTree() {
		return rightTree;
	}

	public void setRightTree(BinaryTree<T> rightTree) {
		this.rightTree = rightTree;
	}
    
    
}

package com.programs.trees;

import java.util.LinkedList;
import java.util.Queue;

import com.programs.stack.Stack;
/**
 * Various tree problem and their solution
 * @author anujjha
 *
 */
public class TreeUtils {

	/**
	 * find size of tree without recursion
	 * @param tree node for which size to be found
	 * @return size of tree
	 */
	private static int findSizeifTreeWithoutRecursion(ITree tree){
		int size = 0;
		if(tree==null) {
			return size;
		}
		Queue<ITree> queue = new LinkedList<>();
		queue.add(tree);
		while(!queue.isEmpty()) {
			size++;
			ITree temp = queue.remove();
			if(temp.getLeftTree()!=null) queue.add(temp.getLeftTree());
			if(temp.getRightTree()!=null) queue.add(temp.getRightTree());
		}
		return size;
	}
	
	/**
	 * print level order in reverse order
	 *   1
	 *  /\
	 * 2  3
	 * for above it will print 2 3 1
	 * @param tree
	 */
	private static void levelOrderTraversalInReverseOrder(ITree tree) {
		//user stack and queue . first put all elements in queue right tree then left tree
		//then put all element in stack
		if(tree==null) {
			return;
		}
		Queue<ITree> queue = new LinkedList<>();
		queue.add(tree);
		Stack<ITree> stack = new Stack<>(tree.size());
		while(!queue.isEmpty()) {
			ITree temp = queue.poll();
			if(temp.getRightTree()!=null) {
				queue.add(temp.getRightTree());
			}
			if(temp.getLeftTree()!=null) {
				queue.add(temp.getLeftTree());
			}
			stack.push(temp);
		}
		
		//Iterate all element of stack and print the data
		while(!stack.isStackEmpty()) {
			System.out.print(stack.pop().getData()+" ");
		}
		
	}
	
	public static void main(String[] args) {
 		//create a binary tree
		BinaryTree<Integer> binaryTree = new BinaryTree<>(1);
	    binaryTree.setLeftTree(new BinaryTree<>(2));
	    binaryTree.setRightTree(new BinaryTree<>(3));
	        
	    binaryTree.getLeftTree().setLeftTree(new BinaryTree<>(4));
	    binaryTree.getLeftTree().setRightTree(new BinaryTree<>(5));
	    binaryTree.getRightTree().setLeftTree(new BinaryTree<>(6));
	    binaryTree.getRightTree().setRightTree(new BinaryTree<>(7));
	    //print size of tree
	    System.out.println("Size of tree is "+ findSizeifTreeWithoutRecursion(binaryTree));
	    //level order traversal without recursion
	    levelOrderTraversalInReverseOrder(binaryTree);
	        
	}

}

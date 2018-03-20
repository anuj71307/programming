package com.programs.trees;

/**
 * This class is for solving various binary tree problems
 * @author anujjha
 *
 */
public class BinaryTreeProblems {

	public static final void createBinaryTree(){
        BinaryTree<Integer> binaryTree = new BinaryTree<>(1);
        binaryTree.setLeftTree(new BinaryTree<>(2));
        binaryTree.setRightTree(new BinaryTree<>(3));
        
        binaryTree.getLeftTree().setLeftTree(new BinaryTree<>(4));
        binaryTree.getLeftTree().setRightTree(new BinaryTree<>(5));
        binaryTree.getRightTree().setLeftTree(new BinaryTree<>(6));
        binaryTree.getRightTree().setRightTree(new BinaryTree<>(7));
        System.out.println("Height of root is " + binaryTree.height());
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createBinaryTree();

	}
}

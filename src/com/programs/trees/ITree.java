package com.programs.trees;

/**
 * Basic interface with some utility methods which other tree class should implement
 * @author anujjha
 *
 * @param <T>
 */
public interface ITree<T extends Comparable> {
	void add(T data);
    boolean isEmpty();
    int size();
    boolean delete(T data);
    int height();
    int depth();
    boolean isMemeber(T data);
    public ITree<T> getLeftTree();
    public ITree<T> getRightTree() ;
    public T getData();
}

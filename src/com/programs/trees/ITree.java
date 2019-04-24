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

    boolean isMember(T data);

    /**
     * return left child
     * @return
     */
    ITree<T> getLeftTree();

    /**
     * return right node
     * @return
     */
    ITree<T> getRightTree() ;

    /**
     * return the data
     * @return
     */
    T getData();
}

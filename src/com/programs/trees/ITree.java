package com.programs.trees;

public interface ITree<T extends Comparable> {
	void add(T data);
    boolean isEmpty();
    int size();
    boolean delete(T data);
    int height();
    int depth();
    boolean isMemeber(T data);
}

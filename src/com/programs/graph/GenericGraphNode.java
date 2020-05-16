package com.programs.graph;

import java.util.LinkedList;
import java.util.List;

public class GenericGraphNode<T> {

    private boolean isVisited;
    private List<GenericGraphNode<T>> children;
    private T key;

    public GenericGraphNode(T key) {
        this.key = key;
        children = new LinkedList<>();
    }

    public void addChild(GenericGraphNode<T> child) {
        children.add(child);
    }

    public List<GenericGraphNode<T>> getChildren(){
        return children;
    }

    public T getKey(){
        return key;
    }

    public boolean isVisited(){
        return isVisited;
    }

    public void setVisited(boolean value){
        isVisited = value;
    }
}
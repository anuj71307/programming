package com.programs.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GenericGraph<T> {

    int vertices;
    HashMap<T, GenericGraphNode> map;
    boolean isDirected;

    public GenericGraph(){
        this(0);
    }
    public GenericGraph(int vertices) {
        this(vertices, false);
    }

    public GenericGraph(int vertices, boolean isDirected) {
        this(null, null, isDirected);
    }

    public GenericGraph(T[] listOfVertices, T[][] edges, boolean directed) {
        this.vertices = listOfVertices== null?0:listOfVertices.length;
        this.isDirected = directed;
        this.map = new HashMap<>();
        addAllVertices(listOfVertices);
        addAllEdges(edges);
    }

    public void addAllEdges(T[][] edges) {
        if(edges==null || edges.length==0) return;
        for (T[] edge : edges) {
            addEdge(edge);
        }
    }

    public void addEdge(T[] edge) {
        if (edge == null || edge.length < 2) return;
        GenericGraphNode source = getOrCreate(edge[0]);
        GenericGraphNode dest = getOrCreate(edge[1]);
        source.addChild(dest);
        if (isDirected) return;
        dest.addChild(source);
    }

    public void addAllVertices(T[] listOfVertices) {
        if(listOfVertices==null) return;
        for (T node : listOfVertices) {
            addVertices(node);
        }
    }

    public void addVertices(T node) {
        if (map.containsKey(node) || node == null) return;
        map.put(node, new GenericGraphNode(node));
    }

    public GenericGraphNode getOrCreate(T source) {
        addVertices(source);
        return map.get(source);
    }

    public boolean traverseDfs(T source, T dest) {
        GenericGraphNode sourceNode = map.getOrDefault(source, null);
        GenericGraphNode  destNode = map.getOrDefault(dest, null);
        if(sourceNode==null || destNode==null) return false;

        return doesPathExistDfs(sourceNode, dest);
    }

    private boolean doesPathExistDfs(GenericGraphNode<T> sourceNode, T destNode) {
        sourceNode.isVisited = true;
        for (GenericGraphNode<T> child : sourceNode.children) {
            if (child.key.equals(destNode)) return true;
            if (!child.isVisited && doesPathExistDfs(child, destNode)) return true;
        }
        return false;
    }
}

class GenericGraphNode<T> {

    boolean isVisited;
    List<GenericGraphNode<T>> children;
    T key;

    public GenericGraphNode(T key) {
        this.key = key;
        children = new LinkedList<>();
    }

    public void addChild(GenericGraphNode<T> child) {
        children.add(child);
    }
}

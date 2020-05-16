package com.programs.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GenericGraph<T> {

    int vertices;
    public HashMap<T, GenericGraphNode> map;
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

    /**
     * Add edge between source and destination
     * @param s source vertices
     * @param d dest vertices
     */
    public void addEdge(T s, T d) {
        GenericGraphNode source = getOrCreate(s);
        GenericGraphNode dest = getOrCreate(d);
        source.addChild(dest);
        if (isDirected) return;
        dest.addChild(source);
    }

    public void addEdge(T[] edge) {
        if (edge == null || edge.length < 2) return;
        addEdge(edge[0], edge[1]);
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
        sourceNode.setVisited(true);
        for (GenericGraphNode<T> child : sourceNode.getChildren()) {
            if (child.getKey().equals(destNode)) return true;
            if (!child.isVisited() && doesPathExistDfs(child, destNode)) return true;
        }
        return false;
    }
}

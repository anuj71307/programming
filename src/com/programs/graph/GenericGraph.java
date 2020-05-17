package com.programs.graph;

import com.sun.tools.javac.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GenericGraph<T> {

    public HashMap<T, GenericGraphNode> map;
    boolean isDirected;

    public GenericGraph(){
        this(false);
    }
    public GenericGraph(boolean isDirected) {
        this(null, null, isDirected);
    }

    public GenericGraph(T[] listOfVertices, T[][] edges, boolean directed) {
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

    /**
     * Given a source vertices and destination vertices check if there is a path from source to destination
     * @param source
     * @param dest
     * @return
     */
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

    /**
     * Breadth first search to find shortest distance from start node to end node.
     * This method works in unweighted graph. We start from start node and check all it child
     * If ot found we move to next level and so on. We keep it in pair so that we can know how far current node
     * from start node. We can also do this without pair by using a count variable and keep creating new
     * Queue/List at each level
     * @param start
     * @param end
     * @return
     */
    public int shortestDistanceBfs(GenericGraphNode<T> start, GenericGraphNode<T> end){
        if(start==null || start.getChildren().isEmpty()) return -1;
        Queue<Pair<GenericGraphNode<T>, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(start, 0));
        while(!queue.isEmpty()){
            Pair<GenericGraphNode<T>, Integer> pair = queue.poll();
            if(pair.fst == end) return pair.snd;
            pair.fst.setVisited(true);
            for(GenericGraphNode<T> child: pair.fst.getChildren()){
                if(!child.isVisited()){
                    queue.add(new Pair<>(child, pair.snd+1));
                }
            }
        }
        return -1;
    }

    /**
     * Find shortest distance between two nodes using dfs. For solution using bfs refer [shortestDistanceBfs]
     * @param start
     * @param end
     * @return
     */
    public int shortestDistanceDfs(GenericGraphNode<T> start, GenericGraphNode<T> end){
        int[] res = {Integer.MAX_VALUE};
        shortestDistanceDfs(start, end, 0, res);
        return res[0];
    }

    private void shortestDistanceDfs(GenericGraphNode<T> start, GenericGraphNode<T> end, int depth, int[] arr) {
        if(start==null || start.isVisited()) return;
        if(depth > arr[0]) return;
        if(start==end){
            arr[0] = Math.min(arr[0], depth);
            return;
        }
        start.setVisited(true);
        for(GenericGraphNode<T> child: start.getChildren()){
            shortestDistanceDfs(child, end, depth+1, arr);
        }
    }
}

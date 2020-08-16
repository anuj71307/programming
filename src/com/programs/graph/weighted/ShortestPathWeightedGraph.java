package com.programs.graph.weighted;

import java.util.*;

/**
 * Represent weighted directed graph
 */
public class ShortestPathWeightedGraph {
    int vertices;
    List<GraphNode>[] nodes;

    ShortestPathWeightedGraph(int size) {
        vertices = size+1;
        nodes = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            nodes[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest, int weight){
        GraphNode gp = new GraphNode(dest,weight);
        nodes[src].add(gp);
    }

    /**
     * Find shortest path from given source to each other nodes
     * @param src
     */
    void shortestPath(int src){

        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[vertices];

        dfsTraversal(src, visited, st);
        int def = Integer.MAX_VALUE;
        int [] dist = new int[vertices];
        Arrays.fill(dist, def);

        dist[src] = 0;
        while(!st.isEmpty()){
            int k = st.pop();
            if(dist[k]!=def){
                for(GraphNode n: nodes[k]){
                    if(dist[n.v]>dist[k]+n.weight){
                        dist[n.v] = dist[k]+n.weight;
                    }
                }
            }
        }


       // System.out.println("Distance to " +dest +" is " + dist[dest]);

        // Print the calculated shortest distances
        // Print the calculated shortest distances
        System.out.println(Arrays.toString(dist));

    }

    private void dfsTraversal(int src, boolean[] visited, Stack<Integer> st) {

        visited[src] = true;
        for(GraphNode i: nodes[src]){
            if(!visited[i.v]){
                dfsTraversal(i.v, visited, st);
            }
        }
        st.push(src);
    }

    public  static void main(String[] args){

        ShortestPathWeightedGraph g = new ShortestPathWeightedGraph(5);
        g.addEdge(1, 3, 68);
        g.addEdge(1, 4, 20);
        g.addEdge(4, 1, 65);
        g.addEdge(3, 2, 74);
        g.addEdge(2, 1, 44);
        g.addEdge(3, 4, 61);
        //g.addEdge(7, 8, 0);

        g.addEdge(4, 3, 68);
        g.addEdge(3, 1, 26);
        g.addEdge(5, 1, 60);
        //g.addEdge(2, 5, 2);
        g.addEdge(5, 3, 3);
        g.addEdge(4, 5, 5);
        g.addEdge(2, 5, 36);


        g.addEdge(2, 3, 94);
        g.addEdge(1, 2, 0);
        g.addEdge(3, 5, 90);

        g.addEdge(2, 4, 28);
        g.addEdge(4, 2, 12);
        g.addEdge(5, 4, 52);
        System.out.println("Following are shortest distances from source " + 1 );
        g.shortestPath(1);
        g.shortestPathUsingMinHeap(1);
    }

    /**
     * https://leetcode.com/problems/network-delay-time/
     * Leetcode 743.
     * We need to find shortest distance to each reacjable node from given source.
     * And maximum among then will be our answer. If graph is not connected then answer should be -1 as we cant reach all the source
     * This method only find shorted distance. we need to add check for whether we can reach everynode or not.
     * to check this just check if any node's distance is Integer.MAX_VALUE
     * @param src
     */
    public void shortestPathUsingMinHeap(int src){
        PriorityQueue<GraphNode> pq = new PriorityQueue();
        pq.add(new GraphNode(src, 0));
        boolean visited[] = new boolean[vertices];
        int distance[] = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        while(!pq.isEmpty()){
            GraphNode parent = pq.poll();
            visited[parent.v] = true;
            for(GraphNode node: nodes[parent.v]){
                if(!visited[node.v] && distance[node.v]>= distance[parent.v]+node.weight ){
                    GraphNode p = new GraphNode(node.v, distance[parent.v]+node.weight);
                    distance[node.v] = distance[parent.v]+node.weight;
                    pq.offer(p);
                }
            }
        }

        System.out.println("Distance using pq");
        // Print the calculated shortest distances
        System.out.println(Arrays.toString(distance));
    }

}

class GraphNode implements Comparable<GraphNode> {
    int v;
    int weight;

    GraphNode(int v, int w) {
        this.v = v;
        weight = w;
    }

    @Override
    public int compareTo(GraphNode o) {
        if(this.weight <o.weight) return -1;
        if(this.weight>o.weight) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return v == graphNode.v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }
}
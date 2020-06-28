package com.programs.graph;

import java.util.*;

/**
 * Represent weighted directed graph
 */
public class ShortestPathWeightedGraph {
    int vertices;
    List<GraphNode>[] nodes;

    ShortestPathWeightedGraph(int size) {
        vertices = size;
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
        for (int i = 0; i < vertices; i++)
        {
            if (dist[i] == def)
                System.out.println( i+" INF ");
            else
                System.out.println( i +" " + dist[i] + " ");
        }

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

        ShortestPathWeightedGraph g = new ShortestPathWeightedGraph(9);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 6, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 8, 6);
        g.addEdge(1, 7, 0);
        //g.addEdge(7, 8, 0);

        g.addEdge(1, 2, 2);
        g.addEdge(1, 5, 9);
        g.addEdge(2, 4, 4);
        //g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 5, 1);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 11);
        //g.addEdge(4, 5, -2);
        System.out.println("Following are shortest distances from source " + 1 );
        g.shortestPath(1);
        g.shortestPathUsingMinHeap(1);
    }

    public void shortestPathUsingMinHeap(int src){
        PriorityQueue<GraphNode> pq = new PriorityQueue();
        pq.add(new GraphNode(src, 0));
        for (int i = 0; i < vertices; i++) {
            if (i != src) {
                pq.add(new GraphNode(i, Integer.MAX_VALUE));
            }
        }
        boolean visited[] = new boolean[vertices];
        int distance[] = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        while(!pq.isEmpty()){
            GraphNode parent = pq.poll();
            visited[parent.v] = true;
            for(GraphNode node: nodes[parent.v]){
                GraphNode p = new GraphNode(node.v, distance[parent.v]+node.weight);
                if(!visited[p.v] && distance[parent.v]!= Integer.MAX_VALUE && distance[node.v]>= distance[parent.v]+node.weight ){
                    distance[node.v] = distance[parent.v]+node.weight;
                    pq.remove(p);
                    pq.offer(p);
                }
            }
        }

        System.out.println("Distance using pq");
        // Print the calculated shortest distances
        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                System.out.println(i + " Not Reachable");
            else
                System.out.println(i + " " + distance[i] + " ");
        }
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
package com.programs.graph;

import java.util.*;

/**
 * Represent weighted directed graph
 */
public class WeightedGraph {


    public  static void main(String[] args){

        WeightedGraph g = new WeightedGraph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);
        System.out.println("Following are shortest distances "+
                "from source " + 1 );
        g.shortestPath(1,4);
    }
    int vertices;
    List<GraphNode>[] nodes;

    WeightedGraph(int size) {
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
    void shortestPath(int src, int dest){

        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[vertices];

        topologicalSort(src, visited, st);
        for(int i =0; i< vertices;i++){
            if(!visited[i]){
              //  topologicalSort(i, visited, st);
            }
        }
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


        System.out.println("Distance to " +dest +" is " + dist[dest]);

        // Print the calculated shortest distances
        for (int i = 0; i < vertices; i++)
        {
            if (dist[i] == def)
                System.out.println( i+" INF ");
            else
                System.out.println( i +" " + dist[i] + " ");
        }

    }

    private void topologicalSort(int src, boolean[] visited, Stack<Integer> st) {

        visited[src] = true;
        for(GraphNode i: nodes[src]){
            if(!visited[i.v]){
                topologicalSort(i.v, visited, st);
            }
        }
        st.push(src);
    }

    class GraphNode {
        int v;
        int weight;

        GraphNode(int v, int w) {
            this.v = v;
            weight = w;
        }
    }

}

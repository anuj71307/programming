package com.programs.graph;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Graph class
 */
public class Graph {
    //No of vertices
    int vertices;
    boolean directed;
    //Array of linked list
    public LinkedList<Integer> adjacencyList[];

    public Graph(int v, boolean directed) {
        vertices = v;
        this.directed = directed;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // add edge for given vertices
    public void addEdge(int source, int dest) {
        LinkedList<Integer> list = adjacencyList[source];
        if (list == null) {
            list = new LinkedList();
            adjacencyList[source] = list;
        }
        list.add(dest);

        if (directed) return;
        list = adjacencyList[dest];
        if (list == null) {
            list = new LinkedList();
            adjacencyList[dest] = list;
        }
        list.add(source);

    }

    /**
     * Given a source and dest of a directed unweighted graph. Find shortest path from source to destination
     *
     * @param s
     * @param d
     */
    public void shortestPath(int s, int d) {
        if (s < 0 || d < 0 || s >= vertices || d >= vertices) {
            System.out.println("Invalid vertices");
            return;
        }

        boolean visited[] = new boolean[vertices];
        int[] path = new int[vertices];
        int dist[] = new int[vertices];

        boolean isFound = traverse(s, d, visited, path, dist);
        if (isFound) {
            System.out.println("Dist is " + dist[d]);

            while (d != s) {
                System.out.print(d + " ");
                d = path[d];
            }
            System.out.print(s);
        } else {
            System.out.print("No Path found");
        }

    }

    private boolean traverse(int s, int d, boolean[] visited, int[] path, int[] dist) {
        visited[s] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(s);
        while (!que.isEmpty()) {
            int k = que.poll();
            for (int i : adjacencyList[k]) {
                if (!visited[i]) {
                    visited[i] = true;
                    path[i] = k;
                    dist[i] = dist[k] + 1;
                    if (i == d) return true;
                    que.add(i);
                }
            }
        }


        return false;
    }

    /*
    print all the vertices and their respective/adjacent edges
     */
    public void print() {

        for (int i = 0; i < adjacencyList.length; i++) {
            LinkedList<Integer> list = adjacencyList[i];
            if (list == null) continue;
            System.out.print(i);
            for (Integer intg : list) {
                System.out.print("-> " + intg);
            }
            System.out.println();
        }
    }

    public void bfs(int s) {
        if (s >= vertices) return;
        boolean visited[] = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int k = queue.poll();
            System.out.println(k);
            for (Integer j : adjacencyList[k]) {
                if (!visited[j]) {
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }

    }


    public void iterativeDfs(int s, boolean[] visited) {
        if (s >= vertices) return;
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        while (!stack.isEmpty()) {
            int k = stack.pop();
            System.out.print(k + " ");
            if (adjacencyList[k] == null) continue;
            for (Integer j : adjacencyList[k]) {
                if (!visited[j]) {
                    stack.push(j);
                    visited[j] = true;
                }
            }
        }
    }

    private void recursiveDfs(int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s + " ");

        // reverse iterator
        if (adjacencyList[s] == null) return;
        Iterator<Integer> it = adjacencyList[s].iterator();
        while (it.hasNext()) {
            int j = it.next();
            if (!visited[j]) {
                recursiveDfs(j, visited);
            }
        }

    }


    //dfs frm given source
    public void recursiveDfs(int s) {
        if (s >= vertices) return;
        boolean[] visited = new boolean[vertices];
        recursiveDfs(s, visited);
    }

    public void dfs() {
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                iterativeDfs(i, visited);
            }
        }
    }


    public boolean isCyclic_undirected() {
        boolean visited[] = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (isCyclic_undirected(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * detect cycle in graph using DFS
     * Time complexity O(V+E)
     *
     * @param i
     * @param visited
     * @param parent
     * @return
     */
    private boolean isCyclic_undirected(int i, boolean[] visited, int parent) {
        visited[i] = true;

        for (Integer child : adjacencyList[i]) {
            if (child != parent) {
                if (visited[child]) return true;
                if (isCyclic_undirected(child, visited, i)) {
                    return true;
                }
            }
        }

        return false;
    }

}

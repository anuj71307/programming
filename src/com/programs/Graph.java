package com.programs;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    //No of vertices
    int vertices;
    boolean directed = false;
    //Array of linked list
    LinkedList<Integer> adjacencyList[];

    public Graph(int v, boolean directed) {
        vertices = v;
        this.directed = directed;
        adjacencyList = new LinkedList[vertices];
    }

    // add edge for given vertices
   public  void addEdge(int source, int dest) {
        LinkedList<Integer> list = adjacencyList[source];
        if (list == null) {
            list = new LinkedList();
            adjacencyList[source] = list;
        }
        list.add(dest);

        if(directed) return;
        list = adjacencyList[dest];
        if (list == null) {
            list = new LinkedList();
            adjacencyList[dest] = list;
        }
        list.add(source);

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

    public void bfs(int s){
       if(s>=vertices) return;
       boolean visited[] = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()){
            int k = queue.poll();
            System.out.println(k);
            for( Integer j :adjacencyList[k]){
                if(!visited[j]){
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }

    }


    public void iterativeDfs(int s, boolean[] visited){
        if(s>=vertices) return;
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        while(!stack.isEmpty()){
            int k = stack.pop();
            System.out.print(k +" ");
            if(adjacencyList[k]==null) continue;
            for(Integer j : adjacencyList[k]){
                if(!visited[j]){
                    stack.push(j);
                    visited[j] = true;
                }
            }
        }
    }

    public void recursiveDfs(int s, boolean[] visited){
        visited[s] = true;
        System.out.print(s+" ");

            // reverse iterator
        if(adjacencyList[s]==null) return;
            Iterator<Integer> it = adjacencyList[s].iterator();
            while(it.hasNext()){
                int j = it.next();
                if(!visited[j]){
                    recursiveDfs(j, visited);
                }
            }

    }


    //dfs frm given source
    public void recursiveDfs(int s){
        if(s>=vertices) return;
        boolean[] visited = new boolean[vertices];
        recursiveDfs(s,visited);
    }

    public void dfs(){
        boolean[] visited = new boolean[vertices];
        for(int i = 0; i< vertices;i++){
            if(!visited[i]){
                iterativeDfs(i, visited);
            }
        }
    }

}

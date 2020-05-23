package com.programs.problems.graph;

import java.util.*;

public class GraphTopologicalSort {

    public boolean canFinish(int numCourses, int[][] edges) {

        if (edges == null || edges.length == 0) return true;
        Graph gr = new Graph(numCourses);
        for (int[] edge : edges) {
            gr.addEdge(edge[1], edge[0]);
        }

        return gr.isCyclic();
    }

    class Graph {
        int vertices;
        HashMap<Integer, GraphNode> map;

        Graph(int v) {
            vertices = v;
            map = new HashMap<>();
        }

        void addEdge(int source, int dest) {
            GraphNode node = map.get(source);
            if (node == null) {
                node = new GraphNode();
                map.put(source, node);
            }
            GraphNode temp = map.get(dest);
            if (temp == null) {
                temp = new GraphNode();
                map.put(dest, temp);
            }
            node.children.add(temp);
            temp.dependent++;
        }

        boolean isCyclic() {
            Queue<GraphNode> queue = new LinkedList();

            for (Map.Entry<Integer, GraphNode> entry : map.entrySet()) {
                if (entry.getValue().dependent == 0) {
                    queue.add(entry.getValue());
                }
            }


            int size = 0;
            while (!queue.isEmpty()) {

                GraphNode node = queue.poll();
                size++;
                for (GraphNode temp : node.children) {
                    temp.dependent--;
                    if (temp.dependent == 0) {
                        queue.add(temp);
                    }
                }

            }

            return size == map.size();
        }

    }

    class GraphNode {
        public int dependent;
        public List<GraphNode> children;

        GraphNode() {
            children = new ArrayList<>();
        }

    }
}

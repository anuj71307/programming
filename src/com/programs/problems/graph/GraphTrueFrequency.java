package com.programs.problems.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphTrueFrequency {


    public static void main(String args[]) {
        HashMap<String, Integer> nameCount = new HashMap<>();
        nameCount.put("Jon", 10);
        nameCount.put("John", 2);
        nameCount.put("Johny", 3);
        nameCount.put("Karl", 4);
        nameCount.put("Carleton", 2);
        nameCount.put("Max", 3);
        HashMap<String, String> synonyms = new HashMap<>();
        synonyms.put("John", "Jon");
        synonyms.put("Johny", "John");
        synonyms.put("Karl", "Carleton");
        synonyms.put("Johny", "John");

        nameCount = new GraphTrueFrequency().getTrueFrequency(nameCount, synonyms);
        for (Map.Entry<String, Integer> entry : nameCount.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private HashMap<String, Integer> getTrueFrequency(HashMap<String, Integer> nameCount, HashMap<String, String> synonyms) {

        HashMap<String, Integer> res = new HashMap<>();

        Graph graph = buildGraph(nameCount, synonyms);

        GraphNode node = null;
        for (Map.Entry<String, GraphNode> entry : graph.map.entrySet()) {
            node = entry.getValue();
            if (!node.isVisited) {
                int count = traverse(node);
                res.put(node.name, count);
            }
        }
        return res;
    }

    private int traverse(GraphNode node) {

        node.isVisited = true;
        int count = node.count;
        for (GraphNode child : node.children) {
            if (!child.isVisited) {
                child.isVisited = true;
                count += traverse(child);
            }
        }

        return count;
    }

    private Graph buildGraph(HashMap<String, Integer> nameCount, HashMap<String, String> synonyms) {
        Graph graph = new Graph();
        for (Map.Entry<String, Integer> entry : nameCount.entrySet()) {
            graph.createNode(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> entry : synonyms.entrySet()) {
            graph.addEdge(entry.getKey(), entry.getValue());
        }

        return graph;
    }

    class Graph {

        HashMap<String, GraphNode> map;

        Graph() {
            map = new HashMap<>();
        }

        void createNode(String name, int count) {
            map.put(name, new GraphNode(name, count));
        }

        public void addEdge(String name1, String name2) {
            GraphNode first = map.get(name1);
            GraphNode second = map.get(name2);
            if (first == null || second == null) return;
            first.children.add(second);
            second.children.add(first);
        }
    }


    class GraphNode {
        String name;
        int count;
        List<GraphNode> children;
        boolean isVisited;

        GraphNode(String name, int count) {
            this.name = name;
            this.count = count;
            children = new LinkedList<>();
        }
    }
}

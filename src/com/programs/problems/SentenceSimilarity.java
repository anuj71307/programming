package com.programs.problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * https://medium.com/@rebeccahezhang/leetcode-737-sentence-similarity-ii-2ca213f10115
 */
public class SentenceSimilarity {

    public static void main(String[] arg) {

        String sentence1[] = {"A", "B", "C"};
        String sentence2[] = {"A", "E", "F"};
        String pairs[][] = {{"B", "E"}, {"C", "F"}};

        System.out.println(new SentenceSimilarity().areSame(sentence1, sentence2, pairs));
    }

    /**
     * We create graph for each string, we traverse pair and add edge between them in our graph class.
     * once graph is constructed we just traverse sentence1 and sentence2 and take the param at respoective index and just check if
     * ther eis a path in graoh from word in sentence1 to word in sentence2 at same index
     *
     * @param sentence1
     * @param sentence2
     * @param synonyms
     * @return
     */
    public boolean areSame(String[] sentence1, String[] sentence2, String[][] synonyms) {

        if (sentence1 == null && sentence2 == null) return true;
        if (sentence1 == null || sentence2 == null) return false;
        if (sentence1.length != sentence2.length) return false;
        Graph gp = new Graph();
        for (String[] synonym : synonyms) {
            gp.addEdge(synonym[0], synonym[1]);
        }
        for (int i = 0; i < sentence1.length; i++) {
            if (sentence1[i].equals(sentence2[i])) continue;
            if (!gp.dfs(sentence1[i], sentence2[i])) return false;
        }
        return true;

    }

}

class Graph {

    HashMap<String, GraphNode> map;

    public Graph() {
        map = new HashMap<>();
    }

    public void addEdge(String src, String dest) {

        if (!map.containsKey(src)) {
            map.put(src, new GraphNode(src));
        }
        GraphNode source = map.get(src);
        if (!map.containsKey(dest)) {
            map.put(dest, new GraphNode(dest));
        }
        GraphNode destination = map.get(dest);
        source.children.add(destination);
        destination.children.add(source);

    }

    boolean dfs(String src, String dest) {
        if (!map.containsKey(src) || !map.containsKey(dest)) return false;
        GraphNode node = map.get(src);
        return isSame(node, dest);
    }

    private boolean isSame(GraphNode node, String dest) {
        node.isVisited = true;
        if (node.key == dest) return true;

        for (GraphNode temp : node.children) {
            if (!temp.isVisited && temp.key.equals(dest)) return true;
            if (!temp.isVisited && isSame(temp, dest)) return true;
        }

        return false;
    }
}

class GraphNode {
    String key;
    List<GraphNode> children;
    boolean isVisited;

    public GraphNode(String key) {
        this.key = key;
        this.children = new LinkedList<>();
    }
}

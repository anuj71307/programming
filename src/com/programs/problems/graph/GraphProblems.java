package com.programs.problems.graph;

import com.programs.graph.GenericGraph;
import com.programs.graph.GenericGraphNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class GraphProblems {


    /**
     * Create graph dynamically
     * @param args
     * @throws Exception
     */
    public static void dynamic(String[] args) throws Exception {
        //code
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int j = Integer.parseInt(reader.readLine().trim());
        for (int k = 0; k < j; k++) {
            String str = reader.readLine();
            String arr[] = str.split(" ");
            int v = Integer.parseInt(arr[0]);
            int e = Integer.parseInt(arr[1]);
            //Graph graph = new Graph(v, false);
            for (int i = 0; i < e; i++) {
                String str1 = reader.readLine();
                String arr2[] = str1.split(" ");
                int s = Integer.parseInt(arr2[0]);
                int d = Integer.parseInt(arr2[1]);
                // graph.addEdge(s, d);
            }
            //graph.print();

        }
        reader.close();
    }


    public static void main(String[] args) {
        GraphProblems gp = new GraphProblems();
        int n = 6;
        int edges[][] = {{0,3}, {1,3}, {2,3}, {4,3}, {5,4}, {3,5}, {0,5}};
        GenericGraph<Integer> graph = gp.createGraph(n, edges);
       // System.out.println(graph.shortestDistanceBfs(graph.map.get(3), graph.map.get(5)));
        System.out.println("Using Dfs");
        System.out.println(graph.shortestDistanceDfs(graph.map.get(0), graph.map.get(5)));
    }

    public GenericGraph<Integer> createGraph(int n, int[][] edges){
        GenericGraph<Integer> gp = new GenericGraph();
        for(int i = 0; i < n; i++){
            gp.addVertices(i);
        }
        for(int edge[]:edges){
            gp.addEdge(edge[0], edge[1]);
        }
        return gp;
    }



    /**
     * https://leetcode.com/problems/minimum-height-trees/
     * This seems like a tree problem where a tree can have multiple nodes as its child. We need to find out tree from
     * which this graph will result into min height.
     * Idea is we first traverse all the leaf nodes and remove their reference from other vertices. We keep on doing so.
     * Its like removing layer until we are at inner most leavel.
     * We can not have more than 2 nodes as a solution.
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        GenericGraph<Integer> gp = new GenericGraph();
        for(int i = 0; i < n; i++){
            gp.addVertices(i);
        }
        for(int edge[]:edges){
            gp.addEdge(edge[0], edge[1]);
        }

        List<Integer> leaves = new ArrayList<>();
        HashMap<Integer, GenericGraphNode> map = gp.map;
        for (Map.Entry<Integer, GenericGraphNode> entry : map.entrySet()) {
            if (entry.getValue().getChildren().size() < 2) {
                leaves.add(entry.getKey());
                entry.getValue().setVisited(true);
            }
        }

        while(n>2){
            n = n - leaves.size();
            List<Integer> list = new ArrayList<>();
            for(Integer leave : leaves){
                List<GenericGraphNode> child = map.get(leave).getChildren();
                child.get(0).getChildren().remove(map.get(leave));
                if(child.get(0).getChildren().size()<2 && !child.get(0).isVisited()){
                    child.get(0).setVisited(true);
                    list.add((Integer) child.get(0).getKey());
                }
            }
            leaves = list;
        }
        return leaves;
    }

    /**
     * https://medium.com/@rebeccahezhang/leetcode-737-sentence-similarity-ii-2ca213f10115
     *
     * We create graph for each string, we traverse pair and add edge between them in our graph class.
     * once graph is constructed we just traverse sentence1 and sentence2 and take the param at respoective index and just check if
     * ther eis a path in graoh from word in sentence1 to word in sentence2 at same index
     *
     * @param sentence1
     * @param sentence2
     * @param synonyms
     * @return
     */
    public boolean areSentenceSimilar(String sentence1[], String sentence2[], String[][] synonyms){
        if (sentence1 == null && sentence2 == null) return true;
        if (sentence1 == null || sentence2 == null) return false;
        if (sentence1.length != sentence2.length) return false;
        GenericGraph<String> gp = new GenericGraph();
        gp.addAllEdges(synonyms);
        for (int i = 0; i < sentence1.length; i++) {
            if (sentence1[i].equals(sentence2[i])) continue;
            if (!gp.traverseDfs(sentence1[i], sentence2[i])) return false;
        }
        return true;
    }

    private void dfs(int index, LinkedList<Integer>[] adjacencyList, int depth, int[] res, boolean[] visited) {

        List<Integer> list = adjacencyList[index];
        if (list != null) {
            visited[index] = true;
            res[0] = Math.max(res[0], depth);
            for (Integer i : list) {

                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, adjacencyList, depth + 1, res, visited);
                }
            }
        }
    }


}


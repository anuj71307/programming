package com.programs.problems;

import com.programs.graph.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GraphProblems {


    public static void dynamic(String[] args) throws Exception {
        //code
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int j = Integer.parseInt(reader.readLine().trim());
        for (int k = 0; k < j; k++) {
            String str = reader.readLine();
            String arr[] = str.split(" ");
            int v = Integer.parseInt(arr[0]);
            int e = Integer.parseInt(arr[1]);
            Graph graph = new Graph(v, false);
            for (int i = 0; i < e; i++) {
                String str1 = reader.readLine();
                String arr2[] = str1.split(" ");
                int s = Integer.parseInt(arr2[0]);
                int d = Integer.parseInt(arr2[1]);
                graph.addEdge(s, d);
            }
            graph.print();

        }
        reader.close();
    }


    public static void main(String[] args) {
    }

    /* https://leetcode.com/problems/clone-graph/
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return cloneGraph(node, map);
    }

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (node == null) return node;
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        map.put(node, cloned);
        for (UndirectedGraphNode child : node.neighbors) {
            if (!map.containsKey(child)) {
                cloneGraph(child, map);
            }
            cloned.neighbors.add(map.get(child));
        }

        return node;
    }
    */

}
/*
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
*/


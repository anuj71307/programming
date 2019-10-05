package com.programs.problems;

import com.programs.graph.Graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

        int n = 6;
        int arr[][] = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};

        GraphProblems gp = new GraphProblems();
        System.out.println(gp.findMinHeightTrees(n, arr));
        System.out.println(gp.findMinHeightTrees_optimized(n, arr));

    }


    /**
     * https://leetcode.com/problems/minimum-height-trees/
     * implementation  is a idea of https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees_optimized(int n, int[][] edges) {

        Graph graph = new Graph(n, false);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < graph.adjacencyList.length; i++) {

            if (graph.adjacencyList[i].size() == 1) {
                set.add(i);
            }
        }

        while (n > 2) {
            n = n - set.size();
            List<Integer> list = new ArrayList<>();
            for (Integer i : set) {
                int k = graph.adjacencyList[i].getFirst();
                graph.adjacencyList[k].remove(i);
                if (graph.adjacencyList[k].size() == 1) {
                    list.add(k);
                }
            }
            set = list;
        }

        return set;

    }


    /**
     * https://leetcode.com/problems/minimum-height-trees/
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        Graph graph = new Graph(n, false);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        List<Integer> set = new ArrayList<>();
        int res[] = new int[1];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < graph.adjacencyList.length; i++) {
            boolean visited[] = new boolean[n];
            List<Integer> list = graph.adjacencyList[i];
            if (list != null && !visited[i]) {
                res[0] = 0;
                dfs(i, graph.adjacencyList, 1, res, visited);
                if (res[0] < min) {
                    min = res[0];
                    set.clear();
                    set.add(i);
                } else if (res[0] == min) {
                    set.add(i);
                }


            }
        }

        return set;

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


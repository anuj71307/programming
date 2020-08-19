package com.programs.problems.graph;

import com.programs.graph.GenericGraph;
import com.programs.graph.GenericGraphNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class GraphProblems {


    /**
     * Create graph dynamically
     *
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
        String arr[] = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target = "8888";
        System.out.println(gp.openLock(arr, target));
        System.out.println(gp.openLockBiDirectionalBfs(arr, target));
    }

    /**
     * https://leetcode.com/problems/open-the-lock/
     * Leetcode 752
     * Bi direction graph bfs for uni-directional refer [openlock method]
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLockBiDirectionalBfs(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        HashSet<String> set = new HashSet();
        for (String deadend : deadends) {
            set.add(deadend);
        }
        if (set.contains("0000")) return -1;
        HashMap<String, Integer> visitedStart = new HashMap<>();
        Queue<String> queueStart = new LinkedList();
        queueStart.add("0000");
        visitedStart.put("0000", 0);

        Queue<String> queueEnd = new LinkedList();
        queueEnd.add(target);


        HashMap<String, Integer> visitedEnd = new HashMap<>();
        visitedEnd.put(target, 0);
        while (!queueStart.isEmpty() || !queueEnd.isEmpty()) {
            int k = checkQueue(queueStart, visitedStart, visitedEnd, set);
            if (k != -1) return k + 1;
            k = checkQueue(queueEnd, visitedEnd, visitedStart, set);
            if (k != -1) return k + 1;

        }
        return -1;
    }

    private int checkQueue(Queue<String> queue, HashMap<String, Integer> visitedMap, HashMap<String, Integer> otherMap, HashSet<String> deadEnds) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            String str = queue.poll();
            int time = visitedMap.get(str);
            for (int k = 0; k < 4; k++) {
                char c = str.charAt(k);
                String first = str.substring(0, k) + (c == '9' ? 0 : c - '0' + 1) + str.substring(k + 1);
                String second = str.substring(0, k) + (c == '0' ? 9 : c - '0' - 1) + str.substring(k + 1);
                if (otherMap.containsKey(first)) return otherMap.get(first) + time;
                if (otherMap.containsKey(second)) return otherMap.get(second) + time;
                if (!deadEnds.contains(first) && !visitedMap.containsKey(first)) {
                    queue.add(first);
                    visitedMap.put(first, time + 1);
                }
                if (!deadEnds.contains(second) && !visitedMap.containsKey(second)) {
                    queue.add(second);
                    visitedMap.put(second, time + 1);
                }
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/open-the-lock/
     * Leetcode 752
     * Uni direction graph bfs for bi-directional(faster) refer [openLockBiDirectionalBfs method]
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        HashSet<String> set = new HashSet();
        for (String deadend : deadends) {
            set.add(deadend);
        }
        if (set.contains("0000")) return -1;
        HashSet<String> visited = new HashSet<>();
        int time = 0;
        Queue<String> queue = new LinkedList();
        queue.add("0000");
        while (!queue.isEmpty()) {

            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for (int k = 0; k < 4; k++) {
                    char c = str.charAt(k);
                    String first = str.substring(0, k) + (c == '9' ? 0 : c - '0' + 1) + str.substring(k + 1);
                    String second = str.substring(0, k) + (c == '0' ? 9 : c - '0' - 1) + str.substring(k + 1);
                    if (first.equals(target) || second.equals(target)) return time;
                    if (!set.contains(first) && !visited.contains(first)) {
                        queue.add(first);
                        visited.add(first);
                    }
                    if (!set.contains(second) && !visited.contains(second)) {
                        queue.add(second);
                        visited.add(second);
                    }
                }
            }

        }
        return -1;
    }


    int count = 0;

    /**
     * https://leetcode.com/problems/keys-and-rooms/
     * Leetcode 841
     * Typical Graph traversal problem.. we only need to check if can visited every node from source
     * or not. we can do either dfs or bfs
     * Time complexity(Worst case) = O(V+E), V is number of vertices and E is number of edges
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        count = 0;
        dfs(rooms, 0, visited);
        return count == rooms.size();

    }

    void dfs(List<List<Integer>> rooms, int src, boolean visited[]) {
        if (visited[src]) return;
        if (count == visited.length) return;
        visited[src] = true;
        count++;
        for (int child : rooms.get(src)) {
            dfs(rooms, child, visited);
        }
    }

    int time = 0;

    /**
     * Critical Connection in a graph/network
     * Leetcode 119
     * https://leetcode.com/problems/critical-connections-in-a-network/
     *
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        LinkedList<Integer>[] graph = new LinkedList[n];

        List<List<Integer>> result = new ArrayList<>();

        int discoveryTime[] = new int[n];
        int lowLink[] = new int[n];
        buildGraph(n, graph, connections, discoveryTime, lowLink);
        for (int i = 0; i < n; i++) {
            if (discoveryTime[i] == -1) {
                criticalConnections(graph, discoveryTime, lowLink, i, -1, result);
            }
        }
        return result;
    }

    private void criticalConnections(LinkedList<Integer>[] graph, int[] discoveryTime, int[] lowLink, int node, int parent, List<List<Integer>> result) {
        discoveryTime[node] = lowLink[node] = time++;
        for (int child : graph[node]) {
            if (child == parent) continue;
            if (discoveryTime[child] == -1) {
                criticalConnections(graph, discoveryTime, lowLink, child, node, result);
                lowLink[node] = Math.min(lowLink[node], lowLink[child]);

                if (lowLink[child] > discoveryTime[node]) {
                    result.add(new ArrayList<>(Arrays.asList(node, child)));
                }

            } else {
                lowLink[node] = Math.min(lowLink[node], discoveryTime[child]);
            }

        }

    }

    /**
     * Used for critical connection
     *
     * @param n
     * @param graph
     * @param connections
     * @param discoveryTime
     * @param lowLink
     */
    private void buildGraph(int n, LinkedList<Integer>[] graph, List<List<Integer>> connections, int discoveryTime[], int lowLink[]) {
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList();
            discoveryTime[i] = -1;
            lowLink[i] = i;
        }
        for (List<Integer> connection : connections) {
            int first = connection.get(0);
            int second = connection.get(1);
            graph[first].add(second);
            graph[second].add(first);
        }
    }

    public GenericGraph<Integer> createGraph(int n, int[][] edges) {
        GenericGraph<Integer> gp = new GenericGraph();
        for (int i = 0; i < n; i++) {
            gp.addVertices(i);
        }
        for (int edge[] : edges) {
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
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        GenericGraph<Integer> gp = new GenericGraph();
        for (int i = 0; i < n; i++) {
            gp.addVertices(i);
        }
        for (int edge[] : edges) {
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

        while (n > 2) {
            n = n - leaves.size();
            List<Integer> list = new ArrayList<>();
            for (Integer leave : leaves) {
                List<GenericGraphNode> child = map.get(leave).getChildren();
                child.get(0).getChildren().remove(map.get(leave));
                if (child.get(0).getChildren().size() < 2 && !child.get(0).isVisited()) {
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
     * <p>
     * We create graph for each string, we traverse pair and add edge between them in our graph class.
     * once graph is constructed we just traverse sentence1 and sentence2 and take the param at respoective index and just check if
     * ther eis a path in graoh from word in sentence1 to word in sentence2 at same index
     *
     * @param sentence1
     * @param sentence2
     * @param synonyms
     * @return
     */
    public boolean areSentenceSimilar(String sentence1[], String sentence2[], String[][] synonyms) {
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


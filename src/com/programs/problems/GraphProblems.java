package com.programs.problems;

import com.programs.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GraphProblems {


    public static void dynamic (String[] args) throws Exception {
        //code
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int j = Integer.parseInt(reader.readLine().trim());
        for(int k = 0; k<j;k++){
            String str = reader.readLine();
            String arr[] = str.split(" ");
            int v = Integer.parseInt(arr[0]);
            int e = Integer.parseInt(arr[1]);
            Graph graph = new Graph(v, false);
            for(int i = 0 ; i < e ; i ++){
                String str1 = reader.readLine();
                String arr2[] = str1.split(" ");
                int s = Integer.parseInt(arr2[0]);
                int d = Integer.parseInt(arr2[1]);
                graph.addEdge(s,d);
            }
            graph.print();

        }
        reader.close();
    }


    public static void main(String [] args){
        Graph g = new Graph(5, true);

        g.addEdge(1, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 4);

        System.out.println("Following is the Depth First Traversal");
        //boolean [] visited = new boolean[5];
        g.dfs();
    }
}

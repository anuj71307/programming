package com.programs.problems.graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {

    int total;
    List<Integer>[] list;

    public static void main(String args[]) {
        CourseSchedule schedule = new CourseSchedule();
        int total = 2;
        int edges[][] = {{0, 1}, {1, 0}};
        System.out.println(schedule.canFinish(total, edges));
    }


    /**
     * https://leetcode.com/problems/course-schedule/
     *
     * @param numCourses
     * @param edges
     * @return
     */
    public boolean canFinish(int numCourses, int[][] edges) {
        total = numCourses;
        list = new List[total];
        for (int i = 0; i < total; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            list[edge[0]].add(edge[1]);
        }
        boolean visited[] = new boolean[total];
        boolean curr[] = new boolean[total];
        for (int i = 0; i < total; i++) {
            if (!visited[i]) {
                if (!canFinish(i, visited, curr)) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean canFinish(int vertices, boolean[] visited, boolean[] curr) {
        curr[vertices] = true;
        visited[vertices] = true;
        for (Integer node : list[vertices]) {
            if (curr[node]) {
                return false;
            }
            if (!visited[node]) {
                if (!canFinish(node, visited, curr)) {
                    return false;
                }
            }
        }

        curr[vertices] = false;
        return true;
    }
}

package com.programs.problems;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/snapshot-array/
 * LeetCode 1146
 */
public class SnapshotArray {
    TreeMap<Integer, Integer> map[];
    int snapId;

    public SnapshotArray(int length) {
        map = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new TreeMap<>();
            map[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        if (map[index] == null) {
            map[index] = new TreeMap<>();
        }
        TreeMap<Integer, Integer> snapMap = map[index];
        snapMap.put(snapId, val);
    }

    public int snap() {
        int id = snapId;
        snapId++;
        return id;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> snapMap = map[index];
        if (snapMap == null) return 0;
        return snapMap.floorEntry(snap_id).getValue();
    }

    public static void main(String[] args) {
        SnapshotArray ssa = new SnapshotArray(3);
        ssa.set(0, 5);
        System.out.println(ssa.snap());
        ssa.set(0, 6);
        System.out.println(ssa.get(0, 2));
    }
}

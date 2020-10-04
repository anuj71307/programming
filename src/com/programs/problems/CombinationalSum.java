package com.programs.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationalSum {

    public static void main(String args[]) {
        CombinationalSum cbs = new CombinationalSum();
        System.out.println(cbs.combinationSum(new int[]{2, 3, 5}, 8));
    }

    /**
     * https://leetcode.com/problems/combination-sum/
     * Leetcode-39
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        combinationSum(candidates, target, 0, list, new ArrayList<>());
        return list;
    }

    /**
     * @param candidates array of elements
     * @param target     target to be found
     * @param index      index from which we want to start considering elements. This avoid duplicate list as well.
     * @param list       Result set
     * @param result     current result set
     */
    private void combinationSum(int[] candidates, int target, int index, List<List<Integer>> list, ArrayList<Integer> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(result));
            return;
        }

        for (int k = index; k < candidates.length; k++) {
            if (candidates[k] > target) continue;
            result.add(candidates[k]);
            combinationSum(candidates, target - candidates[k], k, list, result);
            result.remove(result.size() - 1);
        }
    }


    /**
     * https://leetcode.com/problems/combination-sum-ii/
     * Leetcode 39
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        combinationSum2(candidates, target, 0, list, new ArrayList<>());
        return list;

    }

    private void combinationSum2(int[] candidates, int target, int index, List<List<Integer>> list, ArrayList<Integer> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(result));
            return;
        }

        for (int k = index; k < candidates.length; k++) {
            if (candidates[k] > target) continue;
            if (k > index && candidates[k] == candidates[k - 1]) continue;
            result.add(candidates[k]);
            combinationSum2(candidates, target - candidates[k], k + 1, list, result);
            result.remove(result.size() - 1);
        }

    }
}

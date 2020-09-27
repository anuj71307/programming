package com.programs.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetProblems {

    public static void main(String args[]){
        SubsetProblems sbp = new SubsetProblems();
        System.out.println(sbp.subsetsWithDup(new int[]{1,2,2}));
    }
    /**
     * https://leetcode.com/problems/subsets/
     * Leetcode 78
     * Iterative solution - [subsetsIterative]
     * Time Complexity = N*2^N - (For each element there is two choice to pick or not pick and there are N such element,
     * hence 2^N),
     * Space Complexity = N*2^N
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) { //  [1,2,3]
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length==0) {
            result.add(new ArrayList<>());
            return result;
        }
        createSubset(nums, result, 0, new ArrayList<Integer>());
        return result;
    }

    private void createSubset(int[] nums, List<List<Integer>> result, int index, List<Integer> curr) {
        result.add(new ArrayList<>(curr));

        for(int i = index; i<nums.length;i++){
            curr.add(nums[i]);
            //result.add(new ArrayList<>(curr));
            createSubset(nums, result, i+1, curr);
            curr.remove(curr.size()-1);
        }
    }

    /**
     * https://leetcode.com/problems/subsets/
     * Leetcode 78
     * Recursive solution - [subsets]
     * Time Complexity = N*2^N - (For each element there is two choice to pick or not pick and there are N such element,
     *      hence 2^N),
     * Space Complexity = N*2^N
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsIterative(int[] nums) { //  [1,2,3]
        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        for(int num:nums){
            List<List<Integer>> curr = new ArrayList<>();
            for(List<Integer> result:results){
                List<Integer> temp = new ArrayList<>(result);
                temp.add(num);
                curr.add(temp);
            }

            for(List<Integer> temp:curr){
                results.add(temp);
            }
        }
        return results;
    }

    /**
     * https://leetcode.com/problems/subsets-ii/
     * Leetcode 90
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums==null || nums.length==0) {
            return result;
        }
        Arrays.sort(nums);
        createSubsetDups(nums, result, 0, new ArrayList<Integer>());
        return result;
    }

    /**
     * https://leetcode.com/problems/subsets-ii/
     * @param nums
     * @param result
     * @param index
     * @param curr
     */
    private void createSubsetDups(int[] nums, List<List<Integer>> result, int index, List<Integer> curr) {
        for(int i = index; i<nums.length;i++){
            if(i>index && nums[i] == nums[i-1]) continue;
            curr.add(nums[i]);
            result.add(new ArrayList<>(curr));
            createSubsetDups(nums, result, i+1, curr);
            curr.remove(curr.size()-1);
        }
    }

    /**
     * https://leetcode.com/problems/subsets-ii/
     * Skip the numbers until they are different , make sure to do it after backtracking
     */
    private void backtrack(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {

        result.add(new ArrayList(list));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; ) {
            list.add(nums[i]);
            backtrack(nums, i + 1, list, result);
            list.remove(list.size() - 1);
            i++;
            while (i < nums.length && i > 0) {
                if (nums[i] == nums[i - 1]) i++;
                else break;
            }
        }
    }
}

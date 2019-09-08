package com.programs.problems;

import com.programs.map.THashMap;

import java.util.*;

public class ArrayProblems {

    //An array is given of n length, and we need to calculate the next greater element
    // for each element in given array. If next greater element is not available in
    // given array then we need to fill ‘_’ at that index place
    //https://www.geeksforgeeks.org/smallest-greater-elements-in-whole-array/

    private static int firstElementApperaEvenNumberTimes(int[] arr) {
        LinkedHashMap<Integer, Boolean> linkedHashMap = new LinkedHashMap<>();

        for (int j : arr) {
            if (linkedHashMap.containsKey(j)) {
                boolean value = linkedHashMap.get(j);
                linkedHashMap.put(j, !value);
            } else {
                linkedHashMap.put(j, false);
            }
        }
        Set<Integer> set = linkedHashMap.keySet();
        int value = 0;
        for (int k : set) {
            if (linkedHashMap.get(k)) {
                value = k;
                break;
            }
        }
        return value;
    }


    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int position = quickPartition(arr, start, end);
            quickSort(arr, start, position - 1);
            quickSort(arr, position + 1, end);
        }
    }

    private static int quickPartition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int index = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
                index++;
            }

        }

        arr[end] = arr[index];
        arr[index] = pivot;

        return index;
    }

    /**
     * Find continuous sub array with given sum
     *
     * @param ar        original array
     * @param targetSum sum to found
     */
    private static void findSubArr(int[] ar, int targetSum) {
        int currentSum = ar[0];
        int startP = 0;
        int endP = 0;
        boolean found = false;

        // 1 2 3 7 5
        for (int i = 1; i <= ar.length; i++) {

            while (currentSum > targetSum && startP < i) {
                currentSum = currentSum - ar[startP];
                startP++;
            }

            if (currentSum == targetSum) {
                endP = i - 1;
                found = true;
                break;
            }
            currentSum += ar[i];
        }
        if (found) {
            System.out.println((startP) + " " + (endP));
        } else {
            System.out.println(-1);
        }
    }


    /**
     * given a integer find maximum possible value by doing addition or
     * multiplication of digit - in any order
     *
     * @param num input num
     * @return max value
     */
    public static int maxPossible(int num) {
        int sum = 0;
        int multi = 1;
        while (num > 0) {
            int remainder = num % 10;
            if (remainder > 1) {
                multi = multi * remainder;
            } else {
                sum = sum + remainder;
            }
            num = num / 10;
        }
        if (sum <= 1 && multi > 1) {
            return multi + sum;
        }

        return multi * sum;
    }


    /**
     * given a integer value as string find maximum possible value by inserting only + or * between them
     *
     * @param num input num
     * @return max possible value
     */
    public static int findMaxValue(String num) {
        if (num == null || num.length() < 1) {
            return -1;
        }
        int maxValue = num.charAt(0) - '0';
        for (int i = 1; i < num.length(); i++) {
            int var = num.charAt(i) - '0';
            if (var <= 1) {
                maxValue += var;
            } else {
                if (maxValue <= 1) {
                    maxValue = maxValue + var;
                } else {
                    maxValue = maxValue * var;
                }
            }
        }

        return maxValue;
    }


    public static void main(String[] args) {
        // System.out.print(uniquePaths(3,2));
        ArrayProblems ap = new ArrayProblems();
        int arr[][] = new int[][]{{1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0}};
        System.out.print(new ArrayProblems().numIslands(arr));


    }

    /**
     * https://leetcode.com/problems/target-sum/
     *
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     *
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<String, Integer> map = new HashMap();
        return findTargetSumWays(nums, S, 0, 0, map);

    }

    public int findTargetSumWays(int[] nums, int S, int index, int sum, HashMap<String, Integer> map) {

        String str = index + " " + sum;
        if (map.containsKey(str)) return map.get(str);
        if (index >= nums.length) {
            if (sum == S) return 1;
            return 0;
        }

        int total = findTargetSumWays(nums, S, index + 1, sum + nums[index], map) + findTargetSumWays(nums, S, index + 1, sum - nums[index], map);


        map.put(str, total);
        return total;
    }

    /**
     * https://leetcode.com/problems/frog-jump/
     *
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }
        if (stones[1] != 1) return false;
        HashMap<String, Boolean> check = new HashMap();
        return canCross(map, 0, 1, stones, check);
    }

    public boolean canCross(HashMap<Integer, Integer> map, int pos, int jump, int[] stones, HashMap<String, Boolean> check) {
        //if(pos>=stones.length) return false;
        String str = pos + "" + jump;
        if (check.containsKey(str)) return check.get(str);
        if (jump < 0) return false;
        if (pos == stones.length - 1) return true;
        if (pos >= stones.length) return false;

        int res = stones[pos] + jump;
        if (!map.containsKey(res)) return false;
        int cp = map.get(res);
        if (jump == 0) return false;
        boolean exits = canCross(map, cp, jump, stones, check) || canCross(map, cp, jump - 1, stones, check)
                || canCross(map, cp, jump + 1, stones, check);

        check.put(str, exits);
        return exits;

    }

    /**
     * https://leetcode.com/problems/number-of-islands/
     *
     * @param grid
     * @return
     */
    public int numIslands(int[][] grid) {

        boolean visited[][] = new boolean[grid.length][grid[0].length];

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {
                    traverse(grid, visited, i, j);
                    res++;
                }
            }
        }


        return res;
    }

    public void traverse(int[][] grid, boolean visited[][], int i, int j) {

        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length
                || grid[i][j] == 0 || visited[i][j]) return;

        visited[i][j] = true;
        traverse(grid, visited, i + 1, j);
        traverse(grid, visited, i - 1, j);
        traverse(grid, visited, i, j + 1);
        traverse(grid, visited, i, j - 1);
    }


    /**
     * https://leetcode.com/problems/daily-temperatures/
     *
     * @param arr
     * @return
     */
    public int[] dailyTemperatures(int[] arr) {
        int length = arr.length;
        int[] res = new int[length];
        res[length - 1] = 0;
        boolean found;
        int j;
        for (int i = length - 2; i >= 0; i--) {
            found = false;
            j = i + 1;
            while (j < length) {
                if (arr[j] > arr[i]) {
                    found = true;
                    break;
                } else {
                    if (res[j] == 0) break;
                    j = j + res[j];
                }
            }
            if (found) res[i] = j - i;
        }

        return res;
    }

    /**
     * https://leetcode.com/problems/combination-sum/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return list;
        Arrays.sort(candidates);
        combinationSum_backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    private void combinationSum_backtrack(List<List<Integer>> list, List<Integer> tempList, int[] arr, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < arr.length; i++) {

            tempList.add(arr[i]);
            combinationSum_backtrack(list, tempList, arr, target - arr[i], i);
            tempList.remove(tempList.size() - 1);
        }

    }

    /**
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     *
     * @param arr
     * @param target
     * @return
     */
    private int[] searchRange(int[] arr, int target) {
        int left = searchLeft(arr, target);
        if (left == -1) return new int[]{-1, -1};
        int right = searchRight(arr, target);
        return new int[]{left, right};
    }

    private int searchRight(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == target) {
                if (mid == arr.length - 1) return mid;
                if (arr[mid + 1] != target) return mid;
                start = mid + 1;
            } else {
                if (arr[mid] > target) end = mid - 1;
                else start = mid + 1;
            }
        }
        return -1;
    }

    private int searchLeft(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == target) {
                if (mid == 0) return mid;
                if (arr[mid - 1] != target) return mid;
                end = mid - 1;
            } else {
                if (arr[mid] > target) end = mid - 1;
                else start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/product-of-array-except-self/
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length < 2) return nums;
        int left[] = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        int res = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            left[i] = left[i] * res;
            res = res * nums[i];
        }

        return left;
    }

    /**
     * https://www.youtube.com/watch?v=Pj9378ZsCh4
     *
     * @param board
     */
    public void wallsAndGates(int[][] board) {
        if (board == null || board.length == 0) return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    fillDistance(board, i, j, 0);
                }
            }
        }
    }

    private void fillDistance(int[][] board, int i, int j, int value) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) return;
        if (board[i][j] < value || board[i][j] == -1) return;

        board[i][j] = value;
        fillDistance(board, i + 1, j, value + 1);
        fillDistance(board, i - 1, j, value + 1);
        fillDistance(board, i, j + 1, value + 1);
        fillDistance(board, i, j - 1, value + 1);
    }

    /**
     * @param value
     * @param weight
     * @param total
     * @return
     */
    public Item knapsackProblem(int[] value, int[] weight, int total) {

        if (value == null || weight == null || value.length == 0 ||
                weight.length == 0 || value.length != weight.length)
            return new Item(0, 0);
        Item[] item = new Item[value.length];
        for (int i = 0; i < value.length; i++) {
            item[i] = new Item(weight[i], value[i]);
        }

        Arrays.sort(item, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (o1.value / o1.weight) - (o2.value / o2.weight);
            }
        });

        int w = 0;
        int v = 0;
        for (int i = item.length - 1; i >= 0; i--) {
            if (item[i].weight < total) {
                w += item[i].weight;
                v += item[i].value;
                total = total - item[i].weight;
            }
        }

        return new Item(w, v);
    }

    // USED IN KNAPSACK PROBLEM
    class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    /**
     * https://leetcode.com/problems/merge-intervals/
     * merge intervals
     *
     * @param intervals
     * @return
     */
    private static int[][] mergeIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;

        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        List<int[]> result = new ArrayList<>();
        int j = 0;
        int[] prev = intervals[0];
        int[] temp;
        for (int i = 1; i < intervals.length; ) {
            temp = intervals[i];
            if (temp[0] >= prev[0] && temp[0] <= prev[1]) {
                prev[0] = Math.min(temp[0], prev[0]);
                prev[1] = Math.max(temp[1], prev[1]);
            } else {
                result.add(prev);
                prev = temp;
            }
            i++;
        }

        result.add(prev);
        return result.toArray(new int[result.size()][]);

    }

    /**
     * https://leetcode.com/problems/jump-game/
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int n = nums.length;
        int last = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last <= 0;
    }

    /**
     * https://leetcode.com/problems/permutations-ii/
     * generate all unique permutation of a number
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }


    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }

    }


    /**
     * https://leetcode.com/problems/container-with-most-water/
     *
     * @param arr
     * @return
     */
    private static int maxWaterTrap(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int max = 0;
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            max = Math.max(max, Math.min(arr[start], arr[end]) * (end - start));
            if (arr[start] < arr[end]) start++;
            else end--;
        }

        return max;
    }


    /**
     * https://leetcode.com/problems/sliding-window-maximum/
     *
     * @param arr
     * @param k
     * @return
     */
    private static int[] subArrayMaxUsingDeque(int[] arr, int k) {
        if (arr == null || arr.length == 0) return new int[0];

        int[] res = new int[arr.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        for (int i = k; i < arr.length; i++) {
            res[i - k] = arr[deque.peek()];
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);

        }
        res[res.length - 1] = arr[deque.peek()];

        return res;

    }

    /**
     * find max in subarray of given size
     * https://leetcode.com/problems/sliding-window-maximum/
     *
     * @param arr
     * @param k
     */
    private static void subArrayMax(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            priorityQueue.add(arr[i]);
        }
        System.out.println(priorityQueue.peek());
        priorityQueue.remove(arr[0]);
        int j = 1;
        for (int i = k; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
            System.out.println(priorityQueue.peek());
            priorityQueue.remove(arr[j]);
            j++;
        }
    }

    /**
     * Given a stream of elements too large to store in memory,
     * pick a random element from the stream with uniform probability.
     * <p>
     * https://www.geeksforgeeks.org/select-a-random-number-from-stream-with-o1-space/
     */
    private static void selectRandomFromStream() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};//sample arr/stream
        int[] temp = new int[1];
        for (int i = 0; i < arr.length; i++) {
            System.out.print(selectRandomFromStream(i, temp) + " ");
        }
    }

    /**
     * https://www.geeksforgeeks.org/select-a-random-number-from-stream-with-o1-space/
     *
     * @param i
     * @param temp
     * @return
     */
    private static int selectRandomFromStream(int i, int[] temp) {
        if (i == 0) {
            temp[0] = 1;
        } else {
            Random random = new Random();
            int rand = random.nextInt(i + 1);
            if (rand == i) temp[0] = i + 1;
        }

        return temp[0];
    }

    /**
     * Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
     * Numbers can be 0 or negative.
     * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
     *
     * @param arr
     * @return
     */
    static int nonAdjacentSum(int[] arr) {

        if (arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        int x = arr[0];
        int y = arr[1];
        int res = Math.max(x, y);
        y = res;
        for (int i = 2; i < arr.length; i++) {
            res = Math.max(Math.max(arr[i] + x, y), arr[i]);
            x = y;
            y = res;
        }

        return res;
    }

    /**
     * given an array and a target find all combination of number whose sum equals target
     *
     * @param arr
     * @param target
     */
    static void findCombination(int[] arr, int target) {
        if (arr == null || arr.length == 0) return;

        Arrays.sort(arr);
        int sum = 0;
        findCombination(arr, target, sum, 0, new ArrayList<>());
    }

    private static void findCombination(int[] arr, int target, int sum, int start, List<Integer> list) {
        if (sum == target) {
            System.out.println(list);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            if (i > start && arr[i] == arr[i - 1]) continue;
            Integer integer = arr[i];
            if (sum + integer > target) break;
            list.add(integer);
            findCombination(arr, target, sum + integer, i + 1, list);
            list.remove(integer);
        }
    }

    /**
     * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
     *
     * @param arr
     * @param size
     */
    static void generateAllNum(int[] arr, int size) {
        if (arr == null || arr.length == 0 || size > arr.length) return;
        List<Integer> list = new ArrayList<>();
        generateAllNum(arr, size, 0, list);

    }

    private static void generateAllNum(int[] arr, int size, int start, List<Integer> list) {
        if (list.size() == size) {
            System.out.println(list);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            Integer integer = arr[i];
            list.add(integer);
            generateAllNum(arr, size, i + 1, list);
            list.remove(integer);
        }
    }


    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
     *
     * @param arr
     * @return
     */
    public int findMin(int[] arr) {
        if (arr == null || arr.length == 0) return Integer.MAX_VALUE;

        int start = 0;
        int end = arr.length - 1;
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (arr[mid] > arr[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return arr[start];
    }

    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     *
     * @param arr
     * @param target
     * @return
     */
    private static int searchInRotatedArr(int[] arr, int target) {

        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] >= arr[start]) {
                if (target >= arr[start] && target <= arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {

                if (target >= arr[mid] && target <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }


    // Function to ind missing number
    static int getMissingNo(int v[]) {
        for (int i = 0; i < v.length; i++) {
            int val = v[i];
            if ((val < 0) || (val >= v.length)) {
                continue;
            }
            int curval = v[i], nextval = v[v[i]];
            while (curval != nextval) {
                v[curval] = curval;
                curval = nextval;
                if ((curval < 0) || (curval >= v.length)) {
                    continue;
                }
                nextval = v[nextval];
            }

        }

        int ans = v.length;
        for (int i = 1; i < v.length; i++) {
            if (v[i] != i) {
                ans = i;
                break;
            }
        }

        return ans;
    }

    /**
     * Given an array of integers, return a new array such that each element at index i of the new array
     * is the product of all the numbers in the original array except the one at i.
     * This solution doesn't require any division
     *
     * @param arr
     * @return
     */
    public static int[] multiplyElem(int[] arr) {
        int left[] = new int[arr.length];
        //create an array in which for every index it contain multiplication of element to its left;
        left[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            left[i] = arr[i - 1] * left[i - 1];
        }
        //left = [1,1,2,6,24]
        int result[] = new int[arr.length];
        int k = 1;
        //now we have left multiplication so we can find right one similarly and multiply with left one
        for (int i = arr.length - 1; i >= 0; i--) {

            result[i] = left[i] * k;
            k = arr[i] * k;
        }


        return result;
    }

    /**
     * Given an array of integers, return a new array such that each element at index i of the new array
     * is the product of all the numbers in the original array except the one at i.
     *
     * @param arr
     * @return
     */
    public static int[] multiply(int[] arr) {

        if (arr == null || arr.length == 0) {
            return arr;
        }
        int[] result = new int[arr.length];
        int multiply = arr[0];
        for (int i = 1; i < arr.length; i++) {
            multiply *= arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            //handle when element is zero
            if (arr[i] == 0) {
                result[i] = 0;
                continue;
            }
            result[i] = multiply / arr[i];
        }

        return result;

    }

    /**
     * https://leetcode.com/problems/online-election/
     */
    class TopVotedCandidate {

        //time to person map
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public TopVotedCandidate(int[] persons, int[] times) {

            //person to vote count map
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            int currMax = 0;
            int currWinner = 0;
            for (int i = 0; i < persons.length; i++) {
                int person = persons[i];
                Integer val = hashMap.getOrDefault(person, 0);
                val += 1;
                hashMap.put(person, val);
                if (val >= currMax) {
                    currMax = val;
                    currWinner = person;
                }

                map.put(times[i], currWinner);
            }

        }

        public int q(int t) {
            return map.get(map.floorKey(t));
        }
    }

    /**
     * https://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
     *
     * @param arr
     * @return
     */
    private static int firstCity(City arr[]) {
        if (arr == null || arr.length == 0) return -1;
        int start = 0;
        int end = 1;
        int currPetrol = arr[start].petrol - arr[start].dis;
        while (start != end) {

            while (currPetrol < 0 && start != end) {
                currPetrol -= arr[start].petrol - arr[start].dis;
                start = (start + 1) % arr.length;
                if (start == 0) return -1;
            }

            currPetrol += arr[end].petrol - arr[end].dis;
            end = (end + 1) % arr.length;

        }

        return start;
    }

    static class City {
        int petrol;
        int dis;

        public City(int petrol, int dis) {
            this.petrol = petrol;
            this.dis = dis;
        }
    }

    /**
     * https://leetcode.com/problems/sum-of-subarray-minimums/
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        int sum = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < arr.length; j++) {
                int start = j;
                int end = j + i;
                if (end > arr.length) break;
                int min = arr[start];

                while (start < end) {
                    if (min > arr[start]) {
                        min = arr[start];
                    }
                    start++;
                }
                sum += min;
                sum = (int) (sum % (Math.pow(10, 9) + 7));
            }
        }
        return sum;
    }

    /**
     * https://www.geeksforgeeks.org/sum-of-minimum-elements-of-all-subarrays/
     * https://leetcode.com/problems/sum-of-subarray-minimums/
     * Time Complexity - 0(n), Space Complexity 0(n)
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMinsUsingStack(int[] arr) {
        int mod = (int) Math.pow(10, 9) + 7;

        Stack<Rep> stack = new Stack<>();
        int ans = 0;
        int dot = 0;
        for (int i = 0; i < arr.length; i++) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= arr[i]) {
                Rep rep = stack.pop();
                count += rep.count;
                dot -= rep.val * rep.count;
            }
            Rep re = new Rep(arr[i], count);
            stack.push(re);
            dot += re.val * re.count;
            ans += dot;
            ans %= mod;
        }


        return ans;


    }

    static class Rep {
        int val;
        int count;

        Rep(int v, int c) {
            val = v;
            count = c;
        }
    }

    /**
     * https://leetcode.com/problems/combinations/
     */
    private static void combination() {
        LinkedList<Integer> list = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        int n = 2;
        int k = 2;
        getCombination(k, n, list, res, 1);
    }


    /**
     * https://leetcode.com/problems/combinations/
     *
     * @param k        number of nos
     * @param n        total no from 1..n
     * @param currList
     * @param res
     * @param curr
     */
    public static void getCombination(int k, int n, LinkedList<Integer> currList, List<List<Integer>> res, int curr) {

        if (currList.size() == k) {
            res.add(new LinkedList<>(currList));
            //return res;
        }

        for (int i = curr; i < n + 1; i++) {
            currList.add(i);
            getCombination(k, n, currList, res, i + 1);
            currList.removeLast();
        }

    }


    /**
     * https://leetcode.com/problems/clumsy-factorial/
     *
     * @param N
     * @return
     */
    private static int clumsy(int N) {
        if (N < 2) return 1;
        int j = 1;
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(N);
        integerStack.push(N - 1);
        Stack<Character> characterStack = new Stack<>();
        characterStack.push('*');
        for (int i = N - 2; i > 0; i--) {
            if (j % 4 == 0) {
                updateStack(characterStack, integerStack, '*');

            } else if (j % 4 == 1) {
                updateStack(characterStack, integerStack, '/');
            } else if (j % 4 == 2) {
                updateStack(characterStack, integerStack, '+');
            } else if (j % 4 == 3) {
                updateStack(characterStack, integerStack, '-');
            }
            integerStack.push(i);
            j++;
        }

        while (!characterStack.isEmpty()) {
            int second = integerStack.pop();
            int first = integerStack.pop();
            int r = evaluate(first, characterStack.pop(), second);
            integerStack.push(r);
        }

        return integerStack.peek();

    }

    private static void updateStack(Stack<Character> characterStack, Stack<Integer> integerStack, char c) {
        while (!characterStack.isEmpty() && isHighPriority(c, characterStack.peek())) {
            char operator = characterStack.pop();
            int second = integerStack.pop();
            int first = integerStack.pop();
            int re = evaluate(first, operator, second);
            integerStack.push(re);

        }
        characterStack.push(c);
    }


    private static int evaluate(int first, char operator, int second) {
        if (operator == '+') {
            return first + second;
        } else if (operator == '-') {
            return first - second;
        } else if (operator == '/') {
            return first / second;
        } else if (operator == '*') {
            return first * second;
        }
        return 0;
    }

    private static boolean isHighPriority(char c, Character peek) {
        if (c == '*' || c == '/') {
            if (peek == '*' || peek == '/') {
                return true;
            }
            return false;
        }
        if (c == '+' || c == '-') {
            return true;
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/contains-duplicate-ii/
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        //trying own hash map , hashset can be used to do the same.
        // not working for number outside int range with own hash map , map gives -ve hash
        THashMap<Integer, Integer> map = new THashMap<>(k + 1);
        for (int i = 0; i < nums.length; i++) {
            if (map.size() > k) {
                map.remove(nums[i - k - 1]);
            }
            if (map.containsKey(nums[i])) {
                return true;
            } else {
                map.put(nums[i], i);
            }


        }
        return false;
    }

    /**
     * given an array of top marks and another array of marks
     * find position for after each score
     * Rank //100 90 80 75 60
     * Marks: //50 ,65, 77, 90, 102
     * O/p [6,5,4,2,1]
     * Source: https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
     *
     * @return
     */
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] res = new int[alice.length];
        List<Integer> list = new ArrayList<>();
        list.add(scores[0]);
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] != scores[i - 1]) {
                list.add(scores[i]);
            }
        }

        for (int i = 0; i < alice.length; i++) {
            int k = getRank(alice[i], list);
            res[i] = k;
        }

        return res;

    }

    private static int getRank(int score, List<Integer> list) {

        if (list.get(0) <= score) return 1;
        if (list.get(list.size() - 1) > score) return list.size() + 1;
        if (list.get(list.size() - 1) == score) return list.size();

        int i = 0;
        int j = list.size() - 1;
        int mid;
        while (i <= j) {
            mid = (i + j) / 2;
            if (list.get(mid) == score) {
                return mid + 1;
            } else if (list.get(mid) > score) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        if (j < i) {
            return i + 1;
        } else {
            return j;
        }
    }

    /**
     * given an array find all number whose opposite is present
     * for [0,2,1,-2,-1] o/p should be [1,-1, 2,-2]
     *
     * @param arr
     * @return
     */
    private static HashMap<Integer, Integer> findOpposite(Integer[] arr) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 != o2 || o1 != -02) {
                    return Math.abs(o1) - Math.abs(o2);
                } else {
                    if (o1 < o2) return 1;
                    if (o1 == o2) return -1;
                    else return -1;
                }
            }
        };


        Arrays.sort(arr, comparator);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == -arr[i - 1]) {
                map.put(arr[i], arr[i - 1]);
            }
        }

        return map;
    }

    /**
     * get pow of a number
     *
     * @param x
     * @param pow
     * @return
     */
    static long getPow(int x, int pow) {
        if (pow == 0) return 1;
        if (pow % 2 == 0) {
            return getPow(x * x, pow / 2);
        } else {
            return x * getPow(x * x, (pow - 1) / 2);
        }
    }


    /**
     * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
     *
     * @param A
     * @return
     */
    public static int minSwaps(int[] A) {//add code here.

        if (A == null || A.length <= 1) return 0;
        boolean[] visited = new boolean[A.length];
        int swap = 0;
        for (int i = 0; i < A.length; i++) {
            int j = i;
            int cycle = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = A[j] - 1;
                cycle++;
            }
            if (cycle != 0) {
                swap += cycle - 1;
            }
        }

        return swap;
    }

    /**
     * https://www.geeksforgeeks.org/subset-sum-backtracking-4/
     * Subset Problem
     *
     * @param arr
     */
    static void subSet(int[] arr) {
        long res = 0;
        for (int i : arr) {
            res += (long) i;
        }
        if (res % 2 != 0) {
            System.out.println(false);
        } else {
            boolean r = isSubSet(arr, 0, 0, res / 2);
            System.out.println(r);
        }
    }

    static boolean isSubSet(int[] arr, int index, long sum, long target) {
        if (index >= arr.length || sum > target) return false;
        if (sum == target) return true;
        boolean result = false;
        for (int i = index; i < arr.length; i++) {
            result = result || isSubSet(arr, i + 1, sum + arr[i], target);
        }

        return result;
    }

    static int k = 0;

    /*
      * N-Queen Problem
     https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
     */
    public static boolean solveNQ(int[][] board, int row, int N, int[] arr, int j) {
        if (row >= N) {
            printBoard(arr);
            k++;
            return true;
        }
        boolean res = false;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, row, i)) {
                board[row][i] = 1;
                arr[j] = i + 1;
                res = solveNQ(board, row + 1, N, arr, j + 1) || res;
                board[row][i] = 0;
            }
        }
        return res;
    }

    static boolean isSafe(int[][] board, int row, int col) {
        int i;
        int j;
        for (i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        for (i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

    static void printBoard(int[] board) {
        System.out.print("[");
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
        }

        System.out.print("]" + " ");
    }

    public static long minHealth(List<Integer> dungeon) {
        // Write your code here
        if (dungeon == null || dungeon.size() == 0) return 0;
        long count = 0;
        long potion = 0;
        if (dungeon.get(0) < 0) {
            count = Math.abs(dungeon.get(0)) + 1;
            potion = dungeon.get(0) + count;
        } else {
            count = dungeon.get(0);
            potion = dungeon.get(0);
        }

        for (int i = 1; i < dungeon.size(); i++) {
            int ati = dungeon.get(i);
            if (ati >= 0) {
                potion += ati;
            } else {
                if (potion < 0) {
                    potion = Math.abs(potion) + 1;
                    count += potion;
                }
            }
        }

        return count;
    }


    /*
    find all triplets in array whose sum is zero
     */
    public static int[][] threeSum(int[] A) {
        if (A == null || A.length == 0) return null;
        Arrays.sort(A);
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < A.length - 2; i++) {
            int l = i + 1;
            int r = A.length - 1;
            while (l < r) {
                if (A[i] + A[l] + A[r] == 0) {
                    ArrayList<Integer> li = new ArrayList<>();
                    li.add(A[i]);
                    li.add(A[l]);
                    li.add(A[r]);
                    list.add(li);
                    l++;
                    r--;
                } else if (A[i] + A[l] + A[r] > 0) {
                    r--;
                } else {
                    l++;
                }

            }
        }

        int res[][] = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = list.get(i).get(j);
            }
        }

        return res;
    }

    public static void rotate(ArrayList<ArrayList<Integer>> a) {

        if (a == null || a.size() == 0 || a.size() != a.get(0).size()) {
            return;
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = i; j < a.size(); j++) {
                int temp = a.get(i).get(j);
                int temp2 = a.get(j).get(i);
                a.get(i).set(j, temp2);
                a.get(j).set(i, temp);
            }
        }


        for (int i = 0; i < a.size(); i++) {
            int j = 0;
            int k = a.get(i).size() - 1;
            while (j < k) {
                int temp = a.get(i).get(j);
                int temp2 = a.get(i).get(k);
                a.get(i).set(j, temp2);
                a.get(i).set(k, temp);
                j++;
                k--;
            }
        }
    }


    /**
     * print an array in spiral form
     *
     * @param arr
     */
    private static void printSpiralForm(int[][] arr) {
        int numofRows = arr.length;
        int numOfCols = arr[0].length;
        int row = 0;
        int col = 0;
        int i;

        while (row < numofRows && col < numOfCols) {
            for (i = col; i < numOfCols; i++) {
                System.out.print(arr[row][i] + " ");
            }
            row++;

            for (i = row; i < numofRows; i++) {
                System.out.print(arr[i][numOfCols - 1] + " ");
            }
            numOfCols--;


            if (row < numofRows) {
                for (i = numOfCols - 1; i >= col; i--) {
                    System.out.print(arr[numofRows - 1][i] + " ");

                }
                numofRows--;
            }

            if (col < numOfCols) {
                for (i = numofRows - 1; i >= row; i--) {
                    System.out.print(arr[i][col] + " ");

                }
                col++;
            }
        }

    }

    /**
     * print largest rectangle from histogram
     *
     * @param h
     * @return
     */
    static long largestRectangle(int[] h) {
        Arrays.sort(h);
        long area = 0;
        long area_so_far = 0;
        int i = 0;
        Stack<Integer> stack = new Stack();
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i);
                i++;
            } else {
                int index = stack.pop();
                int j = 0;
                if (stack.isEmpty()) j = i;
                else j = i - stack.peek() - 1;
                area_so_far = h[index] * j;
                if (area_so_far > area) area = area_so_far;
            }
        }

        while (!stack.isEmpty()) {

            int index = stack.pop();
            int j = 0;
            if (stack.isEmpty()) j = i;
            else j = i - stack.peek() - 1;
            area_so_far = h[index] * j;
            if (area_so_far > area) area = area_so_far;

        }


        return area;
    }

    /**
     * search in rotated array
     *
     * @param arr array to be rotated
     * @param k   item to search
     */
    public static void printResult(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;
        // 8 2 3 4 5
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == k) {
                System.out.println(mid);
                return;
            }

            if (arr[low] <= arr[mid]) {
                if (arr[low] <= k && k <= arr[mid]) {
                    high = mid - 1;
                } else low = mid + 1;
            } else {
                if (k >= arr[mid] && k <= arr[high]) {
                    low = mid + 1;
                } else high = mid - 1;
            }
        }
        System.out.println(-1);
    }

    /**
     * longest increasing subsequence
     *
     * @param arr
     */
    public static void printResult(int[] arr) {
        int[] lis = new int[arr.length];
        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] <= lis[j]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int max = 1;
        for (int k : lis) {
            if (k > max) max = k;
        }

        System.out.println(max);
    }

    public static void solve(char[][] board) {


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isValidIndex(board, i, j)) {
                    updateBoard(board, i, j);
                }
            }
        }
    }


    public static void updateBoard(char[][] board, int i, int j) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return;
        }
        board[i][j] = 'X';

        if (i + 1 < board.length && shouldUpdate(board, i + 1, j)) {
            updateBoard(board, i + 1, j);
        }

        if (j + 1 < board[i].length && shouldUpdate(board, i, j + 1))
            updateBoard(board, i, j + 1);
    }


    public static boolean shouldUpdate(char[][] board, int i, int j) {
        return board[i][j] == 'O';
    }

    public static boolean isValidIndex(char[][] board, int i, int j) {

        if (board[i][j] == 'O') {
            if (i - 1 >= 0 && board[i - 1][j] == 'O') return true;
            if (i + 1 < board.length && board[i + 1][j] == 'O') return true;
            if (j - 1 >= 0 && board[i][j - 1] == 'O') return true;
            if (j + 1 < board[i].length && board[i][j + 1] == 'O') return true;

            return false;
        }

        return false;

    }

}

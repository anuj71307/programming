package com.programs.problems;

import com.programs.heap.MaxHeap;
import com.programs.heap.MinHeap;
import com.programs.map.THashMap;

import java.util.*;

public class ArrayProblems {

    public static void main(String[] args) {
        ArrayProblems ap = new ArrayProblems();
        int gas[] = {4};
        int cost[] = {6};
        int v = ap.canCompleteGasCircuit(gas, cost);
        System.out.print(v);
    }

    /**
     * https://leetcode.com/problems/gas-station/
     * LeetCode 134
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteGasCircuit(int[] gas, int[] cost) {

        int start = 0;
        int currPetrol = gas[0]-cost[0];
        int length = gas.length;
        int end = (start+1)%length;
        if(length==1 && gas[0]-cost[0]<0) return -1;
        while(end!=start || currPetrol<0){
            while (currPetrol < 0 && start!=end) {
                currPetrol -= gas[start] - cost[start];
                start = (start+1)%length;
                if(start==0) return -1;
            }
            currPetrol+= gas[end]-cost[end];
            end = (end+1)%length;
        }
        return start;
    }

    /**
     * https://leetcode.com/problems/gas-station/
     * LeetCode 134
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteGasCircuitAlternate(int[] gas, int[] cost) {

      int tank = 0;
      int totalGas = 0;
      int totalCost  = 0;
      int start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }
        if (totalGas < totalCost) return -1;
        return start;
    }

    /**
     * https://leetcode.com/discuss/interview-question/651682/Split-array-into-Groups-of-chars-according-to-the-same-start-and-end-elements
     */
    List<Integer> lengthEachScene(List<Character> inputList) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < inputList.size(); ) {
            int k = getLength(i, inputList);
            result.add(k - i + 1);
            i = k + 1;
        }

        return result;
    }

    /**
     * Iterate from character at given postion and  check it's last occurence,
     * then we iterate over for each character in that range and check last occurence of each character,
     * if last occurence is more then current positon then update k to new value
     */
    private int getLength(int pos, List<Character> inputList) {

        Character c = inputList.get(pos);
        int k = getLastOccurence(inputList, c, pos);
        for (int i = pos; i < k; i++) {
            k = getLastOccurence(inputList, inputList.get(i), k);
        }
        return k;
    }

    /**
     * for each character gets its first occurence from last,
     * if last occurence is not found before last-position provided then return  last position
     */
    private int getLastOccurence(List<Character> inputList, Character c, int lastPos) {
        for (int i = inputList.size() - 1; i >= lastPos; i--) {
            if (inputList.get(i) == c) return i;
        }
        return lastPos;
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days) {
        // WRITE YOUR CODE HERE
        //0 inactive
        // 1  active
        // 1 0 0 0 0 1 0 0
        boolean[] temp = new boolean[states.length];
        for (int i = 0; i < states.length; i++) {
            temp[i] = states[i] == 1 ? true : false;
        }

        while (days > 0) {

            temp[0] = false ^ (states[1] == 1 ? true : false);
            temp[temp.length - 1] = false ^ (states[states.length - 2] == 1 ? true : false);

            for (int i = 1; i < temp.length - 1; i++) {
                if (states[i - 1] == states[i + 1]) {
                    temp[i] = false;
                } else {
                    temp[i] = true;
                }

            }
            for (int i = 0; i < temp.length; i++) {
                states[i] = temp[i] ? 1 : 0;
            }

            days--;
        }

        //return Arrays.asList(states);
        List<Integer> list = new ArrayList();
        for (int i : states) {
            list.add(i);
        }
        return list;

    }

    /**
     * https://leetcode.com/contest/biweekly-contest-25/problems/number-of-ways-to-wear-different-hats-to-each-other/
     * Recursive, Backtrack approach
     *
     * @param hats
     * @return total number of ways
     */
    public int numberWays(List<List<Integer>> hats) {
        int[] res = new int[1];
        numberWays(hats, 0, new HashSet(), res);
        return res[0];
    }

    private void numberWays(List<List<Integer>> hats, int index, HashSet set, int[] res) {
        if (index >= hats.size()) {
            res[0] += 1;
            return;
        }
        List<Integer> hat = hats.get(index);
        for (int i : hat) {
            if (set.contains(i)) continue;
            set.add(i);
            numberWays(hats, index + 1, set, res);
            set.remove(i);
        }

    }

    /**
     * Given an array return an array containing unique elements only
     *
     * @param arr
     * @return
     */
    public static int[] getUnique(int[] arr) {
        Arrays.sort(arr);
        int i = 0;
        int count = 0;
        while (i < arr.length) {
            count++;
            int j = i + 1;
            while (j < arr.length && arr[j] == arr[i]) {
                j++;
            }
            i = j;
        }


        int output[] = new int[count];
        count = 0;
        i = 0;
        while (i < arr.length) {
            output[count] = arr[i];
            count++;
            int j = i + 1;
            while (j < arr.length && arr[j] == arr[i]) {
                j++;
            }
            i = j;
        }

        return output;
    }

    /**
     * https://leetcode.com/problems/subsets-ii/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList(), result);
        return result;
    }

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

    /**
     * https://leetcode.com/problems/n-queens/
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char arr[][] = new char[n][n];
        for (char a[] : arr) {
            Arrays.fill(a, '.');
        }

        solveNQueens(result, n, 0, arr);
        return result;
    }

    private void solveNQueens(List<List<String>> result, int n, int row, char[][] arr) {
        if (row >= n) {
            populateAdd(result, arr);
            return;
        }

        for (int i = 0; i < n; i++) {

            if (canSolve(arr, row, i)) {
                arr[row][i] = 'Q';
                solveNQueens(result, n, row + 1, arr);
                arr[row][i] = '.';
            }
        }
    }

    private boolean canSolve(char[][] arr, int row, int col) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            //  if(arr[row][i]==1) return false;
            if (arr[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            if (arr[i][j] == 'Q') return false;
        }

        return true;
    }

    private void populateAdd(List<List<String>> result, char[][] arrs) {
        List<String> list = new ArrayList<>();
        for (char[] arr : arrs) {
            list.add(new String(arr));
        }
        result.add(list);
    }

    /**
     * https://leetcode.com/problems/largest-values-from-labels/
     *
     * @param values
     * @param labels
     * @param num_wanted
     * @param use_limit  Time complexity O(N+NLogN+N)
     * @return
     */
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        List<ValueToLabel> list = new ArrayList<>();
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            list.add(new ValueToLabel(values[i], labels[i]));
        }
        Collections.sort(list, Collections.reverseOrder());

        for (ValueToLabel valueToLabel : list) {
            if (num_wanted <= 0) break;
            int count = map.getOrDefault(valueToLabel.label, 0);
            if (count == use_limit) continue;
            map.put(valueToLabel.label, count + 1);
            result += valueToLabel.val;
            num_wanted--;
        }
        return result;
    }

    /**
     * Used in [largestValsFromLabels]
     */
    class ValueToLabel implements Comparable {

        Integer val;
        int label;

        public ValueToLabel(int value, int l) {
            val = value;
            label = l;
        }

        @Override
        public int compareTo(Object o) {
            ValueToLabel valueToLabel = (ValueToLabel) o;
            return this.val.compareTo(valueToLabel.val);
        }
    }

    /**
     * https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3289/
     * Given an integer array arr, count element x such that x + 1 is also in arr.
     * If there're duplicates in arr, count them separately.
     *
     * @param arr
     * @return
     */
    public int countElements(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            int next = map.getOrDefault(entry.getKey() + 1, 0);
            if (next > 0) {
                total += count;
            }
        }
        return total;

    }

    /**
     * https://leetcode.com/problems/happy-number/
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) break;
            set.add(n);
            int j = 0;
            while (n != 0) {
                int k = n % 10;
                k *= k;
                j += k;
                n /= 10;
            }
            n = j;
        }
        return (n == 1);
    }

    /**
     * Given two sorted array find lasted array by merging suffix from 1st array and prefix from 2nd.
     * Output array should also be sorted and largest
     * Fo ex if first = [1,2,3], second = [4,5,9] - > Output should be [1,2,3,4,5,9]
     * if first [1,2,3,5,6,] , second = [4,5,8,9] - > output can be [1,2,3,4,5,8,9] or [1,2,3,5,5,8,9]
     * return size of sorted longest array which can be formed
     *
     * @param first
     * @param second
     * @return
     */
    public int merge(int[] first, int[] second) {

        int currentSize = 0;
        int pos = 0;
        int i = first.length - 1;
        for (; i >= 0; i--) {
            int k = binarySearch(second, first[i]);
            int size = second.length - k;
            if (i + 1 + size > currentSize) {
                currentSize = i + 1 + size;
                pos = k;
            }
            if (k == 0) {
                break;
            }
        }

        return currentSize;
    }

    /**
     * Binary Search to find first index of element or index of first greater element
     * Scenario handled: If item is duplicated then it return first position Ex For input [1,2,3,3,3,4,5], target 3. Output would be 2
     * If item is not present it return index of next greater element for input [1,3,3,4,6], target 2 o/p would be 1
     * If no greater element is present then return -1 for input [1,2,3,4] and target 6 , output would be -1
     *
     * @param arr    sorted array
     * @param target
     * @return
     */
    private int binarySearch(int[] arr, int target) {
        if (target < arr[0]) return 0;
        int low = 0;
        int high = arr.length - 1;
        // 1 2 3 4, 5, 7  8
        int mid = (low + high) / 2;
        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid] == target) {
                if (mid == low || (mid - 1 >= low && arr[mid - 1] < target)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (target < arr[mid]) {

                if (mid - 1 >= low && arr[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                if (mid + 1 <= high && target < arr[mid + 1]) {
                    return mid + 1;
                } else {
                    low = mid + 1;
                }

            }
        }

        return -1;

    }

    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotatedArray(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = start;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            }
        }

        return -1;
    }

    /**
     * https://leetcode.com/problems/container-with-most-water/
     *
     * @param arr
     * @return
     */
    public int maxArea(int[] arr) {
        int max = 0;
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int min = Math.min(arr[start], arr[end]);
            int water = min * Math.abs(start - end);
            max = Math.max(max, water);
            if (arr[start] < arr[end]) start++;
            else end--;
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        int prod = 1;
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if (prod == 0) {
                prod = 1;
            }
        }
        prod = 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            prod = prod * nums[i];
            result = Math.max(prod, result);
            if (prod == 0) {
                prod = 1;
            }
        }
        return result;
    }


    /**
     * https://algorithm-notes-allinone.blogspot.com/2019/08/leetcode-1167-minimum-cost-to-connect.html
     * Leet code 1167
     *
     * @param arr
     * @return
     */
    public int minimum_cost_to_stick(int[] arr) {
        MinHeap minHeap = new MinHeap(arr);
        int cost = 0;
        while (minHeap.size > 1) {
            int f = minHeap.extractMin();
            int v = minHeap.extractMin();
            int curr_cost = f + v;
            cost += curr_cost;
            minHeap.insert(curr_cost);
        }

        return cost;
    }

    /**
     * https://leetcode.com/problems/last-stone-weight/
     *
     * @return
     */
    public int lastStoneWeight(int[] arr) {
        MaxHeap maxHeap = new MaxHeap(arr);
        while (maxHeap.size > 1) {
            int f = maxHeap.extractMax();
            int v = maxHeap.extractMax();
            int p = Math.abs(f - v);
            if (p > 0) {
                maxHeap.insert(p);
            }
        }
        return maxHeap.size >= 1 ? maxHeap.extractMax() : 0;
    }

    /**
     * https://leetcode.com/problems/remove-element/
     *
     * @param arr
     * @param val
     * @return
     */
    public int removeElement(int[] arr, int val) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (arr[i] == val) {
                swap(arr, i, j);
                j--;
            } else {
                i++;
            }
        }
        return j + 1;
    }

    /**
     * Given an array which needs to be sorted constraint is an elemenet can move either 1 postion to left or right of it.
     * Input: [4 ,2, 6, 8, 10, 9]
     * Result: [2, 3, 5, 6, 8, 9, 10]
     *
     * @param arr
     */
    void sortArray(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int i = 0;
        while (i < arr.length - 1) { //4
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                i++;
            }
            i++;
        }

    }

    /**
     * Given an array and k where k is always less than length of array. We need to sort the array
     * Constraint: Any otem can move k posotion to its left or right. We need to sort the array
     * Solution: Idea is if we keep right side of array sorted we need to process only left side with k+1 element.
     * First create max heap and insert k+1 element from end of array to it.
     * Start traversing array from end. Extract element from max heap and insert at last [position
     * and then insert new elemenet to max heap. Keep doing this untill all of element is traversed.
     * <p>
     * Min heal can also be used here.
     * Time complexity O(nlogk), Space O(k)
     *
     * @param arr
     * @param k
     */
    void sortArray(int[] arr, int k) {
        if (arr == null || arr.length < 2) return;
        MaxHeap mh = new MaxHeap(k + 1);
        int j = k;
        // 1 2 3 4 5 6
        for (int i = arr.length - 1; i >= 0; i--) {
            mh.insert(arr[i]);
            j--;
            if (j < 0) break;
        }

        int m = arr.length - 1;
        for (int i = arr.length - 2 - k; i >= 0; i--, m--) {
            arr[m] = mh.extractMax();
            mh.insert(arr[i]);

        }
        while (m > 0) {
            arr[m] = mh.extractMax();
            m--;
        }

    }

    /**
     * https://leetcode.com/problems/subsets/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> list = new ArrayList();
        if (nums == null || nums.length == 0) return list;
        backtrack(list, new ArrayList(), nums, 0);
        return list;
    }

    void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int index) {
        list.add(new ArrayList(temp));
        for (int i = index; i < nums.length; i++) {

            temp.add(nums[i]);
            backtrack(list, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * Add one to a number
     * https://www.interviewbit.com/problems/add-one-to-number/
     *
     * @param arr
     * @return
     */
    public int[] plusOne(int[] arr) {

        int i = 0;
        while (i < arr.length && arr[i] == 0) {
            i++;
        }

        if (i >= arr.length) {
            int[] res = new int[1];
            res[0] = 1;
            return res;
        }
        int carry = 1;
        for (int j = arr.length - 1; j >= i; j--) {
            int sum = arr[j] + carry;
            arr[j] = sum % 10;
            carry = sum / 10;
        }
        int res[];
        int k = 0;
        if (carry > 0) {
            res = new int[arr.length - i + 1];
            res[0] = carry;
            k = 1;
        } else {
            res = new int[arr.length - i];
        }

        for (; k < res.length; k++) {
            res[k] = arr[i];
            i++;
        }

        return res;
    }

    /**
     * https://leetcode.com/problems/path-with-maximum-gold/
     *
     * @param grid
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    //res[0]=0;
                    max = Math.max(visit(grid, i, j), max);
                }
            }
        }
        return max;
    }


    int visit(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length
                || grid[i][j] == 0) {

            return 0;
        }
        int c = grid[i][j];
        grid[i][j] = 0;
        int val = visit(grid, i + 1, j);
        val = Math.max(visit(grid, i - 1, j), val);
        val = Math.max(visit(grid, i, j + 1), val);
        val = Math.max(visit(grid, i, j - 1), val);
        grid[i][j] = c;
        return c + val;
    }

    /**
     * https://leetcode.com/problems/array-nesting/
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int len = 0;
        int i = 0;
        while (i < nums.length) {
            int length = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = nums[j];
                length++;
            }
            i++;
            len = Math.max(len, length);
        }
        return len;
    }

    public boolean predictTheWinner(int[] nums) {

        return win(nums, 0, nums.length - 1, 0, 0, true);
    }

    public boolean win(int[] nums, int i, int j, int a, int b, boolean t) {
        if (i > j) {
            if (a > b) return true;
            return false;
        }

        boolean k = false;
        if (t) {
            k = k || win(nums, i + 1, j, a + nums[i], b, !t);
            k = k || win(nums, i, j - 1, a + nums[j], b, !t);
        } else {
            k = k || win(nums, i + 1, j, a, b + nums[i], !t);
            k = k || win(nums, i, j - 1, a, b + nums[j], !t);
        }

        return k;
    }

    /**
     * Dp solution is in DynamicProgramming class
     * https://leetcode.com/problems/combination-sum-iv/
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list.size();
        //Arrays.sort(nums);
        backtrack(nums, target, list, new ArrayList(), 0);
        return list.size();
    }

    void backtrack(int[] arr, int target, List<List<Integer>> list, List<Integer> tempList, int start) {
        if (target < 0) return;
        if (target == 0) {

            list.add(new ArrayList<>(tempList));

            return;
        }


        for (int i = start; i < arr.length; i++) {
            tempList.add(arr[i]);
            backtrack(arr, target - arr[i], list, tempList, i);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/combination-sum-iii/
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> list = new ArrayList<>();
        //if(n>45 || k>9) return list;
        backtrack(k, n, list, new ArrayList<>(), 1);
        return list;
    }

    void backtrack(int k, int n, List<List<Integer>> list, List<Integer> tempList, int start) {
        if (n == 0) {
            if (tempList.size() == k) {
                list.add(new ArrayList<>(tempList));
            }
            return;
        }
        if (n < 0 || tempList.size() > k) return;

        for (int i = start; i < 10; i++) {
            tempList.add(i);
            backtrack(k, n - i, list, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        int cnt[] = new int[nums.length];
        Arrays.fill(cnt, 1);
        int max = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] >= dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] = cnt[i] + cnt[j];

                    }
                }
            }
            if (max == dp[i]) {
                res += cnt[i];
            } else if (max < dp[i]) {
                max = dp[i];
                res = cnt[i];
            }

        }
        return res;
    }

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

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     * The idea is to check if and element i price at i is greater then price at i-1, if yes
     * we can find the diff and add it out profit. For ex even if we have {1,2,3} our best profit will come by to
     * buy at 1 and sell at 3, so even if we buy at 1 and sell at 2 and again buy at 2 and sell at one out profit remains same.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }

        return max;
    }

    public int movesToMakeZigzag(int[] nums) {
        boolean flag = true;
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (flag && nums[i] > nums[i + 1]) {
                int k = Math.abs(nums[i] - nums[i + 1]);
                res = res + k + 1;
            } else if (!flag && nums[i] < nums[i + 1]) {
                int k = Math.abs(nums[i] - nums[i + 1]);
                res = res + k + 1;
            }
            flag = !flag;
        }

        return 0;
    }

    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        int count = 0;
        if (nums == null || nums.length == 0) return count;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }

        return count;

    }

    /**
     * https://leetcode.com/problems/minimum-size-subarray-sum/
     * Find a min subaaray with sum >= s
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = 0;
        int start = 0;
        int end = 0;
        int currSum = 0;
        while (end < nums.length) {
            currSum += nums[end];

            while (currSum >= s && start <= end) {
                if (end - start + 1 < len || len == 0) {
                    len = end - start + 1;
                    if (len == 1) return 1;
                }
                currSum -= nums[start];
                start++;

            }

            end++;
        }

        if (len != 0) return len;
        return 0;
    }

    /**
     * https://leetcode.com/problems/battleships-in-a-board/
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') continue;
                    if (j > 0 && board[i][j - 1] == 'X') continue;
                    result++;
                }

            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/task-scheduler/
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] arr = new int[26];
        for (char task : tasks) {
            arr[task - 'A'] += 1;
        }
        Arrays.sort(arr);
        int time = 0;
        int i = 0;
        while (arr[25] > 0) {
            i = 0;
            while (i <= n) {
                if (arr[25] == 0) break;
                if (i < 26 && arr[25 - i] > 0) {
                    arr[25 - i] -= 1;
                }
                i++;
                time++;
            }

            Arrays.sort(arr);
        }
        return time;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     *
     * @param nums
     * @return
     */
    private int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int prev = nums[0];
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) {

            } else {
                prev = nums[i];
                swap(nums, index, i);
                index++;
            }
        }
        return index;
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * https://leetcode.com/problems/find-the-duplicate-number/
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int j = nums[0];
        while (j != 0) {
            if (nums[j] < 0) return j;
            nums[j] = nums[j] * -1;
            j = Math.abs(nums[j]);
        }
        return j;
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int temp[] = new int[nums.length];
        temp[0] = 1;

        int max = 1;
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            j = 0;
            temp[i] = 1;
            while (j < i) {
                if (nums[i] > nums[j]) {
                    temp[i] = Math.max(temp[i], temp[j] + 1);
                    max = Math.max(max, temp[i]);
                }
                j++;
            }
        }

        return max;
    }

    /**
     * https://leetcode.com/problems/search-a-2d-matrix-ii/
     * Search in sorted 2D array O(n) time
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;

        int i = 0;
        int j = col - 1;

        while (i >= 0 && j >= 0 && i < row && j < col) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }

        }

        return false;
    }

    /**
     * https://leetcode.com/problems/target-sum/
     * <p>
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     * <p>
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     *
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

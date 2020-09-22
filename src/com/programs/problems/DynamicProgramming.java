package com.programs.problems;


import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Dynamic Programming problems
 */
public class DynamicProgramming {

    public static final int MOD = 1000000007;
    /**
     * // System.out.println(dp.editDistDP("horse", "ros"));
     * BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     * int test = Integer.parseInt(reader.readLine());
     * for(int i = 0 ; i<test;i++){
     * int k = Integer.parseInt(reader.readLine());
     * String str = reader.readLine();
     * String [] ar = str.split(",");
     * int arr[] = new int[ar.length];
     * for(int m = 0; m < arr.length;m++){
     * arr[m] = Integer.parseInt(ar[m].trim());
     * }
     * System.out.println(dp.longestZigZag(arr));
     * }
     * reader.close();
     */
    final int[] xMoves = {-1,0,1,0};
    final int[] yMoves = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        DynamicProgramming dp = new DynamicProgramming();

        int[] arr = {1,2,3};
        System.out.println(dp.largestDivisibleSubset(arr));
    }

    /**
     * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
     * Leetcode 329
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {

        if(matrix==null || matrix.length==0) return 0;
        int res = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int[] row : dp) Arrays.fill(row,-1);
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){

                check(matrix,dp,i,j);
                res = Math.max(res,dp[i][j]);
            }
        }

        return res;
    }

    public int check(int[][] matrix,int[][] dp,  int x, int y){
        if(dp[x][y]!=-1) return dp[x][y];
        int max = 1;
        for(int m=0;m<xMoves.length;m++){

            int i = x+xMoves[m];
            int j = y+yMoves[m];
            if(i<0 || j<0 || i>=dp.length || j>=dp[0].length  || matrix[i][j]<=matrix[x][y]) continue;
            max = Math.max(max, check(matrix,dp,i,j)+1);

        }

        dp[x][y] = max;
        return max;
    }
    /**
     * https://leetcode.com/problems/largest-divisible-subset/
     * Leetcode : 368. Largest Divisible Subset
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        ArrayList<Integer> result = new ArrayList();
        Arrays.sort(nums);
        ArrayList<Integer> lists[] = new ArrayList[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lists[i] = new ArrayList<>();
            ArrayList<Integer> largest = new ArrayList();
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (lists[j].size() >= largest.size()) {
                        largest = lists[j];
                    }
                }
            }

            lists[i].addAll(largest);
            lists[i].add(nums[i]);
            if (lists[i].size() > result.size()) {
                result = lists[i];
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/wiggle-subsequence/
     * Leetcode 376.
     * Idea is if we iterate and do bottom up then if we know the length at i-1 index can know the length at i index
     * but if ith element is more then i-1 element then we need to know value at i-1 with less value
     * to do this we need two array one which will contain if ith value is more then previous
     * and other exact opposite
     * Top Top Down - Time complexity - O(N)
     * For O(n) check wiggleMaxLength
     */
    public int wiggleMaxLengthLinear(int[] nums) {

        if(nums==null || nums.length==0) return 0;
        if(nums.length==1) return 1;

        int dpUp[] = new int[nums.length];
        int dpDown[] = new int[nums.length];
        dpUp[0] = dpDown[0]=1;
        for(int i =1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                dpUp[i] = dpDown[i-1]+1;
                dpDown[i] = dpDown[i-1];
            }
            else if(nums[i]<nums[i-1]){
                dpDown[i] = dpUp[i-1]+1;
                dpUp[i] = dpUp[i-1];
            }
            else{
                dpUp[i] = dpUp[i-1];
                dpDown[i] = dpDown[i-1];
            }
        }
        return Math.max(dpDown[nums.length-1], dpUp[nums.length-1]);
    }

    /**
     * https://leetcode.com/problems/wiggle-subsequence/
     * Leetcode 376.
     * Top Top Down - Time complexity - O(N^2)
     * For O(n) check wiggleMaxLengthLinear
     */
    int res = 0;
    public int wiggleMaxLength(int[] nums) {

        if(nums==null || nums.length==0) return 0;
        if(nums.length==1) return 1;

        int dp[][] = new int[nums.length][2];
        wiggle(nums, dp, 0, nums[0], 1);
        wiggle(nums,dp,1, nums[0],1);
        return res+1;
    }

    public int wiggle(int[] nums, int[][] dp, int greater, int val, int index){
        if(index>=nums.length) return 0;
        if( dp[index][greater]!=0) return dp[index][greater];

        int count = 0;
        if(greater==0){
            for(int i=index;i<nums.length;i++){
                if(nums[i]>val){
                    count = wiggle(nums, dp,1,nums[i], i+1);
                    dp[index][greater] = Math.max(dp[index][greater], count+1);
                    //break;
                }
            }
        }
        else{
            for(int i=index;i<nums.length;i++){
                if(nums[i]<val){
                    count = wiggle(nums, dp,0,nums[i], i+1);
                    dp[index][greater] = Math.max(dp[index][greater], count+1);
                    //break;
                }
            }
        }
        res = Math.max(dp[index][greater], res);
        return dp[index][greater];
    }
    /**
     * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
     * Leet code 363
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int dp[][] = new int[m+1][nums.length];
        int sums[] = new int[nums.length];
        sums[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2;i>=0;i--){
            sums[i]= nums[i]+sums[i+1];
        }
        return splitArray(nums,m, 0,dp, sums);
    }


    /**
     * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
     * Leet code 363
     * @param nums
     * @param m
     * @param start
     * @param dp
     * @param sums
     * @return
     */
    private int splitArray(int[] nums, int m, int start, int[][] dp, int[] sums) {
        if (m == 1) {
            return sums[start];
        }
        if(dp[m][start]!=0) return dp[m][start];

        int min= Integer.MAX_VALUE;
        int sum = 0;
        for(int i = start; i<=nums.length-m;i++){
            sum+=nums[i];
            min = Math.min(min, Math.max(sum, splitArray(nums, m-1, i+1, dp, sums)));
        }
        dp[m][start] = min;
        return dp[m][start];
    }

    /**
     * https://leetcode.com/problems/knight-dialer/
     * Leetcode 935
     * Similar to graph problem where find number of ways to take N steps in a directed graph
     * For recursive approach check [ArrayProblems.knightDialer]. We need to memorize the already
     * calculated input since same number can be repeated
     * @param N
     * @return
     */
    public int knightDialer(int N) {
        int[][] arr = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
        Integer [][] dp = new Integer[N+1][10];
        int count = 0;
        for(int i =0;i<10;i++){
            count =(count+dfs(arr, dp, N-1, i))%MOD;
        }
        return count;
    }

    private int dfs(int[][] arr, Integer[][] dp, int total, int index) {
        if(total<=0) return 1;
        if(dp[total][index]!=null) return dp[total][index];
        int count=0;
        for(int num:arr[index]){
            count= (count+dfs(arr, dp, total-1, num))%MOD;
        }
        dp[total][index] = count;
        return count;
    }

    /**
     * https://leetcode.com/problems/longest-string-chain/
     * LeetCode 1048
     *
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        int[] dp = new int[words.length];
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (oneAddAway(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * Check if first string can be transformed to second by adding just one char at any place
     * Condition- second is always bigger in length than first
     * * Time Complexity - O(M) M is length of first string
     *
     * @param first
     * @param second
     * @return
     */
    private boolean oneAddAway(String first, String second) {
        int k = second.length() - first.length();
        if (k != 1) return false;
        int diff = 0;
        for (int i = 0, j = 0; i < first.length(); ) {
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else {
                diff++;
                if (diff > 1) return false;
                j++;
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/integer-break/
     *
     * @param n
     * @return
     */
    public int maxProduct(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= n; i++) {
            int max = 1;
            for (int j = 1; j <= i / 2; j++) {
                int v = Math.max(j * (i - j), j * dp[i - j]);
                max = Math.max(max, v);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    /**
     * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
     *
     * @param totalDice
     * @param faces
     * @param sum
     * @return
     */
    public int findWays(int totalDice, int faces, int sum) {
        int[][] dp = new int[totalDice + 1][sum + 1];
        for (int i = 0; i <= totalDice; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        findWays(totalDice, faces, sum, dp);
        return dp[totalDice][sum];
    }

    public int findWays(int totalDice, int faces, int sum, int[][] dp) {
        if (sum == 0 && totalDice == 0) return 1;
        if (sum == 0 && totalDice > 0) return 0;
        if (totalDice == 0) return 0;
        if (dp[totalDice][sum] != -1) return dp[totalDice][sum];
        int s = 0;
        for (int j = 1; j <= faces; j++) {
            if (j > sum) break;
            int x = findWays(totalDice - 1, faces, sum - j, dp);
            s += x;
            //  s = (s % mod + x % mod) % mod; for mode and leet code
        }
        dp[totalDice][sum] = s;
        return s;
    }

    /**
     * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
     * Recursive solution
     *
     * @param totalDice
     * @param faces
     * @param sum
     * @return
     */
    public int throwDiceRecursive(int totalDice, int faces, int sum) {
        if (sum == 0 && totalDice == 0) return 1;
        if (sum == 0 && totalDice > 0) return 0;
        if (totalDice == 0) return 0;
        int s = 0;
        // for(int i =1; i<=totalDice;i++){
        for (int j = 1; j <= faces; j++) {
            if (j > sum) break;
            s += throwDiceRecursive(totalDice - 1, faces, sum - j);
        }
        // }
        return s;
    }

    /**
     * https://practice.geeksforgeeks.org/problems/cutted-segments/0
     *
     * @param arr
     * @param length
     * @return
     */
    public int cutLength(int[] arr, int length) {
        int dp[] = new int[length + 1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= length) {
                dp[arr[i]] = 1;
            }
        }
        for (int i = 0; i <= length; i++) {
            int max = dp[i];
            for (int j = 0; j <= i / 2; j++) {
                if (dp[j] == 0 || dp[i - j] == 0) continue;
                max = Math.max(max, dp[j] + dp[i - j]);
            }
            dp[i] = max;
        }
        return dp[length];
    }

    /**
     * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
     *
     * @param arr
     * @return
     */
    public int cutRoad(int arr[]) {
        int length = arr.length;
        int dp[] = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            int max = arr[i - 1];
            for (int j = 0; j <= i / 2; j++) {
                int price = dp[j] + dp[i - j];
                max = Math.max(max, price);
            }
            dp[i] = max;
        }
        return dp[length];
    }

    /**
     * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
     * Time complexity -> Target*N, where is N is length of array
     * Space complexity is also same
     *
     * @param arr
     * @param target
     * @return
     */
    public boolean subSetSum(int[] arr, int target) {
        int dp[][] = new int[target + 1][arr.length];
        boolean res = subSetSum(arr, target, 0, dp);
        return res;
    }

    private boolean subSetSum(int[] arr, int target, int index, int[][] dp) {
        if (target == 4 && index == 1)
            if (target == 0) return true;
        if (target < 0 || index >= arr.length) return false;
        if (dp[target][index] != 0) return dp[target][index] == 100;
        if (target - arr[index] == 0) {
            dp[target][index] = 100;
            return true;
        }
        boolean res = subSetSum(arr, target, index + 1, dp) || subSetSum(arr, target - arr[index], index + 1, dp);
        ;
        dp[target][index] = res ? 100 : 200;
        return res;
    }

    /**
     * https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
     *
     * @param arr
     * @return
     */
    public int maxIncreasingPath(int[][] arr) {
        int[][] max = new int[arr.length][arr[0].length];
        int[] res = new int[1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int m = max[i][j];
                if (max[i][j] == 0) {
                    m = findMax(arr, i, j, res, max);
                }
                res[0] = Math.max(m, res[0]);

            }
        }
        return res[0];
    }

    private int findMax(int[][] arr, int row, int col, int[] res, int[][] max) {
        if (max[row][col] != 0) return max[row][col];
        int cmax = 1;
        int val = arr[row][col];
        if (row - 1 >= 0 && arr[row - 1][col] == val + 1) {
            cmax = Math.max(cmax, findMax(arr, row - 1, col, res, max) + 1);
        }
        if (row + 1 < arr.length && arr[row + 1][col] == val + 1) {
            cmax = Math.max(cmax, findMax(arr, row + 1, col, res, max) + 1);
        }
        if (col - 1 >= 0 && arr[row][col - 1] == val + 1) {
            cmax = Math.max(cmax, findMax(arr, row, col - 1, res, max) + 1);
        }
        if (col + 1 < arr[row].length && arr[row][col + 1] == val + 1) {
            cmax = Math.max(cmax, findMax(arr, row, col + 1, res, max) + 1);
        }

        max[row][col] = cmax;
        res[0] = Math.max(res[0], cmax);
        return cmax;
    }

    /**
     * https://leetcode.com/problems/climbing-stairs/
     * TopDown [climbStairsTopDown]
     *
     * @param n
     * @return
     */
    public int climbStairsBottomUp(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    public int climbStairsTopDown(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        return climbStairsTopDown(n, arr);
    }

    public int climbStairsTopDown(int n, int[] dp) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (dp[n] != 0) return dp[n];
        dp[n] = climbStairsTopDown(n - 1, dp) + climbStairsTopDown(n - 2, dp);
        return dp[n];
    }

    public int climbStairsRecursive(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-subsequence/
     * LeetCode 516
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) return 0;
        int dp[][] = new int[s.length()][s.length()];
        return subSeq(s, 0, s.length() - 1, dp);
    }

    private int subSeq(String arr, int i, int j, int[][] dp) {
        if (i > j || i < 0 || j >= arr.length()) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        if (i == j) return 1;
        int len = 0;
        if (arr.charAt(i) == arr.charAt(j)) {
            return 2 + subSeq(arr, i + 1, j - 1, dp);
        }
        len = Math.max(len, subSeq(arr, i + 1, j, dp));
        len = Math.max(len, subSeq(arr, i, j - 1, dp));
        dp[i][j] = len;
        return len;
    }

    /**
     * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
     * https://leetcode.com/problems/longest-common-subsequence/
     * Leet code - 1143
     * Find longest common subsequence in two string
     *
     * @param first  string
     * @param second string
     *               Time complexity - O(N*M), N is length of first string and M is length of second string
     *               Space complexity - O(N*M)
     */
    private int longestCommonSequence(String first, String second) {
        if (first == null || first.length() == 0 || second == null || second.length() == 0) return 0;

        int fLength = first.length();
        int sLength = second.length();
        int[][] arr = new int[fLength + 1][sLength + 1];
        int max = 0;
        for (int i = 1; i <= fLength; i++) {
            for (int j = 1; j <= sLength; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
                max = Math.max(max, arr[i][j]);

            }
        }
        return max;
    }


    /**
     * https://www.geeksforgeeks.org/minimum-steps-minimize-n-per-given-condition/
     * Problem Statement: On a positive integer, you can perform any one of the following 3 steps.
     * 1.) Subtract 1 from it. ( n = n - 1 )  ,
     * 2.) If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2  )  ,
     * 3.) If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3  ).
     * Now the question is, given a positive integer n, find the minimum number of steps that takes n to 1
     * Bottom up approach, Tiem complexity O(N). where N is the given number, space complexity -> O(N)
     *
     * @param n
     * @return
     */
    private int steps(int n) {
        if (n < 2) return 0;
        if (n < 4) return 1;
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 1;
        arr[3] = 1;
        for (int i = 4; i <= n; i++) {
            arr[i] = 1 + arr[i - 1];
            if (i % 2 == 0) arr[i] = Math.min(arr[i], arr[i / 2] + 1);
            if (i % 3 == 0) arr[i] = Math.min(arr[i], arr[i / 3] + 1);
        }
        return arr[n];
    }


    /**
     * Given a booking timing for a room return max number of books that can be booked
     * All the bookings will have endtime>startTime
     * Booking are sorted based on start time
     *
     * @param timings
     * @return
     */
    private int max_booking(int[][] timings) {
        int dp[] = new int[timings.length];
        int[] res = new int[1];
        max_booking(timings, dp, res, 0, Integer.MIN_VALUE);
        return res[0];
    }

    private int max_booking(int[][] timings, int[] dp, int[] res, int index, int endTime) {
        if (index >= timings.length) {
            return 0;
        }
        if (dp[index] != 0) {
            return dp[index];
        }
        System.out.println("Check for " + index);
        if (timings[index][0] < endTime) {
            return max_booking(timings, dp, res, index + 1, endTime);
        }
        int choose = 1 + max_booking(timings, dp, res, index + 1, timings[index][1]);
        int not = max_booking(timings, dp, res, index + 1, endTime);
        dp[index] = Math.max(choose, not);
        res[0] = Math.max(res[0], dp[index]);
        return Math.max(choose, not);
    }

    /**
     * https://www.geeksforgeeks.org/longest-zig-zag-subsequence/
     *
     * @param arr
     * @return
     */
    public int longestZigZag(int[] arr) {

        int[] greater = new int[arr.length];
        Arrays.fill(greater, 1);
        int smaller[] = new int[arr.length];
        Arrays.fill(smaller, 1);
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    greater[i] = Math.max(smaller[j] + 1, greater[i]);
                    max = Math.max(greater[i], max);
                }
                if (arr[i] < arr[j]) {
                    smaller[i] = Math.max(greater[j] + 1, smaller[i]);
                    max = Math.max(smaller[i], max);
                }
            }
        }
        return max;
    }


    /**
     * https://leetcode.com/problems/longest-happy-string/
     * create a array of size where 0th index will represent count and 1st index will char
     * we use this array to store it in priority que and sortit descending array
     * then each time we fetch value with max count and if its not similar to last char then append it.
     * If count has reached 2 re take next char with max value.
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr2[0] - arr1[0]);
        if (a > 0) pq.offer(new int[]{a, 0});
        if (b > 0) pq.offer(new int[]{b, 1});
        if (c > 0) pq.offer(new int[]{c, 2});
        int last[] = new int[]{0, 0};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            // if this character is already consider twice we can not append it again so need to
            // get next char with max count left
            if (curr[1] == last[1] && last[0] == 2) {
                if (pq.isEmpty()) break; // if queue is empty then break the loop
                int[] temp = pq.poll();
                pq.offer(curr); // add the previous polled item back
                curr = temp;
            }

            sb.append((char) (curr[1] + 'a'));
            if (last[1] != curr[1]) { // if last char is not same as currently added char then update last
                last[1] = curr[1];
                last[0] = 0;
            }
            last[0] += 1;// increment count ie number of times this char added
            curr[0] -= 1;
            if (curr[0] > 0) pq.offer(curr);
        }


        return sb.toString();
    }

    /**
     * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
     * This is a typical sliding window problem where we first find sum of first k number.
     * then we iterate for k number from end, in each step we are going to decrease our sum by number at k-1 index
     * from start and we are going to add number from end.
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        // find sum of first k number
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        // if k is == length of array then return sum
        if (k == cardPoints.length) return sum;
        int maxSum = sum;
        int end = cardPoints.length - 1;
        // Now this is sliging window problem, we will iterate for k number from end
        // each time we add a number from end we also remove the kth element from start ie from our sum
        // then we just compare the max value
        while (k > 0) {
            sum += cardPoints[end--];
            sum -= cardPoints[k - 1];
            k--;
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }


    /**
     * https://leetcode.com/problems/maximum-length-of-pair-chain/
     * This is similar to https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/ only difference is we need
     * to compare element at different index. But before that we need to sort the pairs based on their 0th value.
     * Then is we compare two pairs then if previeous pairs elemtn at first position is smaller then next pair's element
     * at 0 then they can form a chain. So at index if we keep the longest possible chain then it becomes easy to solve this.
     *
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[pairs.length];

        Arrays.fill(dp, 1);
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i : dp) {
            ans = Math.max(ans, i);
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/divisor-game/
     * Cache the result
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N, HashMap<Integer, Boolean> map) {
        if (map.containsKey(N)) return map.get(N);
        for (int x = 1; x < N; x++) {
            if ((N % x == 0) && !divisorGame(N - x, map)) {
                map.put(N, true);
                return true;
            }
        }
        map.put(N, false);
        return false;
    }

    /**
     * https://leetcode.com/problems/divisor-game/
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        for (int x = 1; x < N; x++) {
            if ((N % x == 0) && !divisorGame(N - x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/combination-sum-iv/
     * Leetcode 377
     * Time complexity = Target *N -> N is length of input array
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4BottomUp(int[] nums, int target) {
        long[] dp = new long[target+1];
        dp[0]=1;
        for(int i=1;i<=target;i++){
            for(int num:nums){
                if(num>i) continue;
                dp[i] = dp[i]+dp[i-num];
            }
        }
        return (int)(dp[target]);
    }

    /**
     * https://leetcode.com/problems/combination-sum-iv/
     * Leetcode 377
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        Arrays.sort(nums);
        target(nums, target, dp);
        return dp[target];
    }

    public int target(int[] nums, int target, int[] dp) {

        if (dp[target] != -1) return dp[target];
        if (target < 0) return 0;
        if (target == 0) return 1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] < 0) break;
            res += target(nums, target - nums[i], dp);
        }

        dp[target] = res;
        return res;

    }

    /**
     * https://leetcode.com/problems/count-numbers-with-unique-digits/.
     * Explanation: When n =1 we should pick all one digit number which are unique. so {0,1,2,3,4,5,6,7,8,9}
     * when n = 2 we can first pick between {1,2,3,4,5,6,7,8,9} and then 2nd digit from {0,1,2,3,4,5,6,7,8,9} total 10
     * but the number we pick first time we can not pick 2nd time so our choices of number limited to 10-1 = 9
     * so tatal would be 9*9
     * similary when n =3, the number we pich for 1st and 2nd position we can not keep for 3rd position so our choices would be
     * 9*9*8 . 8 cause total numbers to pick is 10 but we have already picked 2 so its 10-2
     * similary for n =4 it is 9*9*8*7
     * ..
     * ..
     * for n =11 there would defintely be a duplicated number after picking 10 numbers to our max count is limited till number with 10 digits only
     *
     * @param n
     * @return Time complexity 0(1);
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int ans = 10;
        int base = 9;
        for (int i = 2; i <= n && i <= 10; i++) {
            base = base * (9 - i + 2);
            ans += base;
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/regular-expression-matching/
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean isMatch_dp(String str, String pattern) {
        Result dp[][] = new Result[str.length() + 1][pattern.length() + 1];

        return isMatch(str, pattern, 0, 0, dp);
    }

    enum Result {
        TRUE, FALSE
    }

    boolean isMatch(String str, String pattern, int i, int j, Result[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = (i == str.length());

        } else {
            boolean isSameChar = (i < str.length() && (str.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = isMatch(str, pattern, i, j + 2, dp);
                if (!ans) {
                    ans = isSameChar && isMatch(str, pattern, i + 1, j, dp);
                }

            } else {
                ans = isSameChar && isMatch(str, pattern, i + 1, j + 1, dp);
            }
        }

        dp[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /**
     * https://leetcode.com/problems/regular-expression-matching/
     * We do a recursive approach. character in str at index matches the character in pattern at same index if pattern has same char or '.'
     * We need special handling of scenario when pattern has '*'. if pattern.charAt(index+1) is '*' then there are 2 scenarios
     * 1. we check of string and pattern.substring(2). For ex if string is a and pattern is c*a then we need to
     * check if a and a(substring of pattern)match cause c can be present 0 or more time
     * 2. if character of str and pattern match at same index and pattern has * as next char then we can ignore the
     * character in str and continue with same pattern. In this lets say our string is aab and pattern is a*b
     * we check then pattern has * so we check recursively for ab and a*b. which in turn do recursive call for b and a*b.
     * and then b and b which result true.
     * aab a*b
     * aab , b -> false
     * ab a*b
     * ab, b-> false
     * b, a*b
     * b,b -> true
     *
     * @param str
     * @param pattern
     * @return
     */
    boolean isMatch(String str, String pattern) {
        if (pattern.isEmpty() && str.isEmpty()) return true;
        if (pattern.isEmpty()) return false;
        //check if character matches
        boolean isSameChar = str.length() > 0 && (str.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        if (pattern.length() > 1 && pattern.charAt(1) == '*') {
            if (isMatch(str, pattern.substring(2))) return true;
            if (isSameChar) {
                return isMatch(str.substring(1), pattern);
            }

        } else {
            if (isSameChar) {
                return isMatch(str.substring(1), pattern.substring(1));
            }
        }
        return false;

    }

    public int integerBreak(int n) {

        if (n < 4) return n - 1;
        return prod_dp(n, new int[n + 1]);
    }

    /**
     * https://leetcode.com/problems/integer-break/
     *
     * @param n
     * @param dp
     * @return
     */
    int prod_dp(int n, int[] dp) {
        if (n <= 4) return n;
        if (dp[n] != 0) return dp[n];
        int max;
        int run = 1;
        for (int i = 2; i <= n; i++) {
            max = 1;
            max = max * i * prod_dp(n - i, dp);
            run = Math.max(run, max);
        }
        dp[n] = run;
        return run;
    }

    /**
     * https://leetcode.com/problems/integer-break/
     *
     * @param n
     * @return
     */
    int prod(int n) {
        if (n < 1) return 1;
        int max = 1;
        int run = 1;
        for (int i = 1; i <= n; i++) {
            max = 1;
            max = max * i * prod(n - i);
            run = Math.max(run, max);
        }

        return run;
    }

    /**
     * https://leetcode.com/problems/triangle/
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_arr(List<List<Integer>> triangle) {

        int size = triangle.size();
        int res[] = new int[triangle.get(size - 1).size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = triangle.get(size - 1).get(i);
        }

        for (int i = size - 2; i >= 0; i--) {
            List<Integer> curr = triangle.get(i);
            for (int j = 0; j < curr.size(); j++) {
                res[j] = Math.min(res[j], res[j + 1]) + curr.get(j);
            }
        }

        return res[0];

    }

    /**
     * https://leetcode.com/problems/triangle/
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_hashMap(List<List<Integer>> triangle) {
        return triangle.get(0).get(0) + minimumTotal(triangle, 1, 0, new HashMap<>());

    }

    public int minimumTotal(List<List<Integer>> triangle, int index, int i, HashMap<String, Integer> map) {
        if (index >= triangle.size()) return 0;
        if (i >= triangle.get(index).size()) return 0;
        String key = index + " " + i;
        if (map.containsKey(key)) return map.get(key);
        int val1 = triangle.get(index).get(i);
        val1 += minimumTotal(triangle, index + 1, i, map);
        int val2 = triangle.get(index).get(i + 1);
        val2 += minimumTotal(triangle, index + 1, i + 1, map);

        val1 = Math.min(val1, val2);
        map.put(key, val1);
        return val1;
    }

    /**
     * https://leetcode.com/problems/unique-paths-ii/
     *
     * @param grid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int x = grid[0].length;
        int y = grid.length;
        if (grid[0][0] == 1) return 0;
        grid[0][0] = 1;
        for (int i = 1; i < x; i++) {
            if (grid[0][i] == 1 || grid[0][i - 1] == 0) {
                grid[0][i] = 0;
            } else {
                grid[0][i] = 1;
            }

        }
        for (int i = 1; i < y; i++) {
            if (grid[i][0] == 1 || grid[i - 1][0] == 0) grid[i][0] = 0;
            else grid[i][0] = 1;
        }
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                } else grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        return grid[y - 1][x - 1];
    }

    /**
     * https://www.geeksforgeeks.org/edit-distance-dp-5/
     * https://leetcode.com/problems/edit-distance/
     *
     * @param word1
     * @param word2
     * @return
     */
    int editDistDP(String word1, String word2) {
        // Create a table to store results of subproblems
        if (word1 == null && word2 == null) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]);
            }
        }

        return dp[m][n];
    }

    private static int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    /**
     * https://leetcode.com/problems/maximal-square/
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int dp[][] = new int[r + 1][c + 1];
        int max = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = getMin(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }

        }
        return max * max;
    }


    int getMin(int x, int y, int z) {

        int min = x;
        if (y < min) min = y;
        if (z < min) min = z;
        return min;
    }

    /**
     * DP Solution
     * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
     *
     * @param prices
     * @param length
     * @return
     */
    int roadCutPrice(int[] prices, int length) {
        int dp[] = new int[length + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                max = Math.max(max, prices[j] + dp[i - j - 1]);
            }
            dp[i] = max;
        }
        return dp[length];
    }

    /**
     * recursive solution to
     * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
     *
     * @param prices
     * @param length
     * @return
     */
    int roadCutRecursive(int[] prices, int length) {

        if (length <= 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, prices[i] + roadCutRecursive(prices, length - i - 1));
        }

        return max;
    }

    /**
     * https://www.geeksforgeeks.org/maximum-profit-sale-wines/
     *
     * @param prices
     * @return
     */
    private int sellWine(int[] prices) {
        start = 0;
        end = prices.length - 1;
        cache = new int[prices.length][prices.length];

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                cache[i][j] = -1;
            }
        }
        return maxProfit(prices, start, end);
    }

    private int maxProfit(int[] prices, int start, int end) {
        if (start > end) return 0;
        if (cache[start][end] != -1) return cache[start][end];
        int year = prices.length - (end - start);
        int startProfit = year * prices[start] + maxProfit(prices, start + 1, end);
        int endProfit = year * prices[end] + maxProfit(prices, start, end - 1);
        cache[start][end] = Math.max(startProfit, endProfit);
        return cache[start][end];
    }

    int start = 0;
    int end = 0;
    int cache[][];

    /**
     * https://www.geeksforgeeks.org/count-ofdifferent-ways-express-n-sum-1-3-4/
     *
     * @param num
     * @return
     */
    private int countWays(int num) {
        if (num <= 0) return 0;
        int arr[] = new int[num + 1];
        arr[0] = 0;
        arr[1] = arr[2] = 1;
        arr[3] = 2;
        arr[4] = 4;
        for (int i = 5; i <= num; i++) {
            arr[i] = arr[i - 4] + arr[i - 3] + arr[i - 1];
        }
        return arr[num];

    }

    /**
     * On a positive integer, you can perform any one of the following 3 steps.
     * 1.) Subtract 1 from it. ( n = n - 1 )  ,
     * 2.) If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2  )  ,
     * 3.) If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3  ).
     * Now the question is, given a positive integer n, find the minimum number of steps that takes n to 1
     * <p>
     * eg: 1.)For n = 1 , output: 0
     * 2.) For n = 4 , output: 2  ( 4  /2 = 2  /2 = 1 )
     * 3.)  For n = 7 , output: 3  (  7  -1 = 6   /3 = 2   /2 = 1 )
     *
     * @param num
     * @return
     */
    private int reduceToOne(int num) {
        int res[] = new int[num + 1];
        res[2] = 1;
        for (int i = 3; i <= num; i++) {
            int min = 1 + res[i - 1];
            if (i % 2 == 0) min = Math.min(min, 1 + res[i / 2]);
            if (i % 3 == 0) min = Math.min(min, 1 + res[i / 3]);
            res[i] = min;
        }
        return res[num];
    }

    /**
     * https://leetcode.com/problems/perfect-squares/
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        if (n <= 3) return n;
        int[] arr = new int[n + 1];

        squares(n, arr);
        return arr[n];
    }

    public int squares(int n, int[] cache) {
        if (n <= 3) return n;

        if (cache[n] != 0) return cache[n];

        int res = n;
        for (int i = 1; i * i <= n; i++) {
            res = Math.min(res, 1 + squares(n - i * i, cache));
        }
        cache[n] = res;
        return cache[n];
    }


    /**
     * https://leetcode.com/problems/coin-change/
     * LeetCode 322
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);

    }

    public int coinChange(int[] coins, int amount, int[] count) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (count[amount - 1] != 0) return count[amount - 1];
        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            int res = coinChange(coins, amount - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }

        count[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return count[amount - 1];

    }

    /**
     * https://leetcode.com/problems/coin-change/
     * LeetCode 322,
     * Find minimum coin required for each value less then amount
     */
    public int coinChangesBottomUP(int[] coins, int amount) {
        int[] amounts = new int[amount + 1];
        Arrays.fill(amounts, amount + 1);
        amounts[0] = 0;
        // 1 2 5
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > i) continue;
                if (amounts[i - coins[j]] < amount) {
                    amounts[i] = Math.min(amounts[i], amounts[i - coins[j]] + 1);
                }
            }
        }
        return amounts[amount] > amount ? -1 : amounts[amount];
    }

    /**
     * https://leetcode.com/problems/partition-equal-subset-sum/
     * Leetcode : 416
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum % 2 != 0) return false;

        sum = sum / 2;
        int dp[] = new int[sum + 1];
        return sumTarget(nums, sum, 0, dp) == 1;
    }

    private int sumTarget(int[] nums, int sum, int index, int dp[]) {
        if (sum == 0) return 1;
        if (index >= nums.length) return 2;
        if (dp[sum] != 0) return dp[sum];
        int res = 2;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > sum) continue;
            res = sumTarget(nums, sum - nums[i], i + 1, dp);
            {
                if (res == 1) {
                    break;
                }
            }
        }
        dp[sum] = res;
        return dp[sum];
    }

    /**
     * https://leetcode.com/problems/minimum-path-sum/
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[m - 1][n - 1];

    }

    /**
     * https://leetcode.com/problems/minimum-path-sum/
     *
     * @param grid
     * @return
     */
    public int minPathSum_recur(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int res[] = new int[1];
        res[0] = Integer.MAX_VALUE;
        minPathSum_Recur(grid, res, 0, 0, 0);
        return res[0];
    }

    public void minPathSum_Recur(int[][] grid, int[] res, int i, int j, int sum) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return;

        if (i == grid.length - 1 && j == grid[i].length - 1) {
            sum += grid[i][j];
            if (sum < res[0]) res[0] = sum;
            return;
        }
        minPathSum_Recur(grid, res, i + 1, j, sum + grid[i][j]);
        minPathSum_Recur(grid, res, i, j + 1, sum + grid[i][j]);

    }

    /**
     * https://leetcode.com/problems/unique-paths/
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            res[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }

        return res[m - 1][n - 1];
    }

    /**
     * https://leetcode.com/problems/unique-paths/
     * Using recursion
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathRecur(int m, int n) {
        if (m < 1 && n < 1) return 0;

        int res[] = new int[1];
        uniquePathRecur(m, n, 1, 1, res);
        return res[0];
    }

    private void uniquePathRecur(int m, int n, int i, int j, int[] res) {
        if (i >= m && j >= n) {
            res[0] += 1;
            return;
        }
        if (i > m || j > n) return;
        uniquePathRecur(m, n, i + 1, j, res);
        uniquePathRecur(m, n, i, j + 1, res);
    }
}

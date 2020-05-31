package com.programs.problems;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dynamic Programming problems
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        // System.out.println(dp.editDistDP("horse", "ros"));
        System.out.println(dp.isMatch_dp("aa", "a*"));
        int cardPoints[] = {1,79,80,1,1,1,200,1};
        int k = 3;
        System.out.print(dp.maxScore(cardPoints, k));
    }


    /**
     * https://leetcode.com/problems/longest-happy-string/
     * create a array of size where 0th index will represent count and 1st index will char
     * we use this array to store it in priority que and sortit descending array
     * then each time we fetch value with max count and if its not similar to last char then append it.
     * If count has reached 2 re take next char with max value.
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int []> pq = new PriorityQueue<>((arr1, arr2) -> arr2[0] - arr1[0]);
        if(a>0) pq.offer(new int[]{a,0});
        if(b>0) pq.offer(new int[]{b,1});
        if(c>0) pq.offer(new int[]{c,2});
        int last[] = new int[]{0,0};
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            // if this character is already consider twice we can not append it again so need to
            // get next char with max count left
            if(curr[1]==last[1] && last[0]==2){
                if(pq.isEmpty()) break; // if queue is empty then break the loop
                int[] temp = pq.poll();
                pq.offer(curr); // add the previous polled item back
                curr = temp;
            }

            sb.append((char)(curr[1]+'a'));
            if(last[1]!=curr[1]){ // if last char is not same as currently added char then update last
                last[1] = curr[1];
                last[0] = 0;
            }
            last[0]+=1;// increment count ie number of times this char added
            curr[0]-=1;
            if(curr[0]>0) pq.offer(curr);
        }


        return sb.toString();
    }

    /**
     * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
     * This is a typical sliding window problem where we first find sum of first k number.
     * then we iterate for k number from end, in each step we are going to decrease our sum by number at k-1 index
     * from start and we are going to add number from end.
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        // find sum of first k number
        for(int i = 0; i< k;i++){
            sum+= cardPoints[i];
        }
        // if k is == length of array then return sum
        if(k==cardPoints.length) return sum;
        int maxSum = sum;
        int end = cardPoints.length-1;
        // Now this is sliging window problem, we will iterate for k number from end
        // each time we add a number from end we also remove the kth element from start ie from our sum
        // then we just compare the max value
        while(k>0){
            sum+=cardPoints[end--];
            sum-=cardPoints[k-1];
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

        Arrays.sort(pairs,(a,b)->a[0]-b[0]);
        int[] dp = new int[pairs.length];

        Arrays.fill(dp,1);
        for(int i=1;i<pairs.length;i++){
            for(int j =0;j<i;j++){
                if(pairs[j][1]<pairs[i][0]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int ans = 0;
        for(int i :dp){
            ans = Math.max(ans,i);
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
     *
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
     * https://leetcode.com/problems/partition-equal-subset-sum/
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i : nums) sum += i;
        if (sum % 2 != 0) return false;

        HashMap<String, Boolean> map = new HashMap<>();
        sum = sum / 2;
        return sumTarget(nums, sum, 0, map);
    }

    private boolean sumTarget(int[] nums, int sum, int index, HashMap<String, Boolean> map) {
        String key = sum + "_" + index;
        if (map.containsKey(key)) return map.get(key);
        if (sum == 0) return true;
        if (index >= nums.length) return false;


        boolean found = sumTarget(nums, sum - nums[index], index + 1, map)
                || sumTarget(nums, sum, index + 1, map);
        map.put(key, found);
        return found;

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

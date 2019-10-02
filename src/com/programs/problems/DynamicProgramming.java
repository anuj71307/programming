package com.programs.problems;


import java.util.HashMap;

/**
 * Dynamic Programming problems
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int target = 7168;
        DynamicProgramming dp = new DynamicProgramming();
        int arr[] = new int[]{2, 3, 5};
        int size = arr.length;
        System.out.println(dp.roadCutPrice(arr, size));
        System.out.println(dp.roadCutRecursive(arr, size));

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

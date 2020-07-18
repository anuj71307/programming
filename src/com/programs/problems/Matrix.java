package com.programs.problems;

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * LeetCode 304
 */
public class Matrix {
    int dp[][];

    /**
     * Idea is to keep track of  continuous sum of each array in mattrix
     * For ex if first array is [3,0,1,4,2] we keep [3,3,4,8,10]
     * We keep cache of array at each row
     * If it is required to find sum from column 2-4 then we know sum till 4
     * so we get that decrease sum we found till 1. That will be sum 2-4
     * Time complexity M*N where M is number of rows and N is number columns
     * Space is same as time since we are creating separate array.
     *
     * @param matrix
     */
    public Matrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    dp[i][j] = matrix[i][j] + dp[i][j - 1];
                }
            }
        }
    }

    /**
     * Since each individual 1D array is processed and cached in [dp] we just find sum in that
     * arrar for given column. if start column if after 0 so just decrease the count till then
     * If it is required to find sum from column 2-4 then we know sum till 4
     * * so we get that decrease sum we found till 1. That will be sum 2-4
     * Time complexity O(M) M is row2-row1
     *
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null || dp.length == 0 || dp[0].length == 0) return 0;
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            if (col1 == 0) {
                sum += dp[i][col2];
            } else {
                sum += (dp[i][col2] - dp[i][col1 - 1]);
            }
        }
        return sum;
    }
}

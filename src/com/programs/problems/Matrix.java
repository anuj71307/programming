package com.programs.problems;

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * LeetCode 304
 */
public class Matrix {
    int dp[][];

    public static void main(String[] srg) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        Matrix mat = new Matrix(matrix, 4);
        for (int[] i : mat.dp) {
            for (int k : i) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

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


    /**
     * LeetCode 304
     * https://leetcode.com/problems/range-sum-query-2d-immutable/
     * Another approach. Time complexity is same as above method but now [sumRegion2] run in constant time
     *
     * @param matrix
     * @param p      just extra param to avoid conflict
     */
    public Matrix(int[][] matrix, int p) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        if (dp == null || dp.length == 0 || dp[0].length == 0) return 0;
        row1++;
        col1++;
        row2++;
        col2++;
        int sum = 0;
        sum += dp[row2][col2] - dp[row1 - 1][col2] - dp[row2][col1 - 1] + dp[row1 - 1][col1 - 1];
        return sum;
    }
}

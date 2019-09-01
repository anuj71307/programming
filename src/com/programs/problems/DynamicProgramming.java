package com.programs.problems;


/**
 * Dynamic Programming problems
 */
public class DynamicProgramming {

    public static void main(String[] args){
        int i = 4;
        int j = 7;
        DynamicProgramming dp = new DynamicProgramming();
        int[][] arr =  {{1,3,1},
                        {1,5,1},
                        {4,2,1},
                     {1,1,1}};
        System.out.print(dp.minPathSum(arr));
    }


    /**
     * https://leetcode.com/problems/minimum-path-sum/
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 1;i<n;i++){
            grid[0][i]+=grid[0][i-1];
        }
        for(int i = 1; i< m;i++){
            grid[i][0]+=grid[i-1][0];
        }

        for(int i =1;i< m;i++){
            for(int j =1;j<n;j++){
                grid[i][j]+=Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[m-1][n-1];

    }

    /**
     * https://leetcode.com/problems/minimum-path-sum/
     * @param grid
     * @return
     */
    public int minPathSum_recur(int[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int res[] = new int[1];
        res[0] = Integer.MAX_VALUE;
        minPathSum_Recur(grid, res, 0, 0,0);
        return res[0];
    }

    public void minPathSum_Recur(int[][] grid, int[] res, int i, int j, int sum){
        if(i<0||j<0||i>=grid.length||j>=grid[i].length) return;

        if(i==grid.length-1 && j == grid[i].length-1){
            sum+=grid[i][j];
            if(sum<res[0]) res[0] = sum;
            return;
        }
        minPathSum_Recur(grid, res, i+1, j, sum+grid[i][j]);
        minPathSum_Recur(grid, res, i, j+1,sum+grid[i][j]);

    }

    /**
     * https://leetcode.com/problems/unique-paths/
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m , int n){
        int[][] res = new int[m][n];
        for(int i =0;i<m;i++){
            res[i][0] = 1;
        }
        for(int j =0;j<n;j++){
            res[0][j] = 1;
        }

        for(int i =1;i<m;i++){
            for(int j = 1; j<n;j++){
                res[i][j] = res[i-1][j]+res[i][j-1];
            }
        }

        return  res[m-1][n-1];
    }

    /**
     * https://leetcode.com/problems/unique-paths/
     * Using recursion
     * @param m
     * @param n
     * @return
     */
    public int uniquePathRecur(int m , int n){
        if(m<1 && n <1) return 0;

        int res[] = new int[1];
        uniquePathRecur(m,n,1,1, res);
        return  res[0];
    }

    private void uniquePathRecur(int m, int n,int i, int j, int[] res) {
        if(i>=m && j >=n){
            res[0]+=1;
            return;
        }
       if(i>m || j > n ) return;
        uniquePathRecur(m,n,i+1,j, res);
        uniquePathRecur(m,n,i,j+1, res);
    }
}

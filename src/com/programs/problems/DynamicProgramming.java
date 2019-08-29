package com.programs.problems;


/**
 * Dynamic Programming problems
 */
public class DynamicProgramming {

    public static void main(String[] args){
        int i = 4;
        int j = 7;
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.uniquePaths(i,j));
        System.out.println(dp.uniquePathRecur(i,j));
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

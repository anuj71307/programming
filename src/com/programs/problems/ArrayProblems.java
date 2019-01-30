package com.programs.problems;

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




    private static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
        for(int i : arr){
            System.out.print(i +" ");
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if(start<end){
            int position = quickPartition(arr, start, end);
            quickSort(arr, start, position-1);
            quickSort(arr, position+1, end);
        }
    }

    private static int quickPartition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int index = start;
        for(int i = start; i<end;i++){
           if(arr[i]<pivot){
               int temp = arr[index];
               arr[index] = arr[i];
               arr[i] = temp;
               index++;
           }

        }

        arr[end] = arr[index];
        arr[index] = pivot;

        return  index;
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
        if (sum <= 1&& multi>1) {
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
        //solve(table);
     int[] arr = new int []{ 1, -4, 0, 0, 5, -5, 1, 0, -2, 4, -4, 1, -1, -4, 3, 4, -1, -1, -3 };
     int[][]res = threeSum(arr);
     for(int i =0; i< res.length;i++){
         System.out.println(Arrays.toString(res[i]));
     }
    }
    public static int[][] threeSum(int[] A) {
        if(A==null || A.length==0) return null;
        Arrays.sort(A);
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

        for(int i =0; i< A.length-2;i++){
            int l = i+1;
            int r = A.length-1;
            while(l<r){
                if(A[i]+A[l]+A[r]==0){
                    ArrayList<Integer> li = new ArrayList<>();
                    li.add(A[i]);
                    li.add(A[l]);
                    li.add(A[r]);
                    list.add(li);
                    l++;
                    r--;
                }
                else if(A[i]+A[l]+A[r]>0){
                    r--;
                }
                else{
                    l++;
                }

            }
        }

        int res [][] = new int[list.size()][3];
        for(int i = 0; i< list.size();i++){
            for(int j = 0; j < 3;j++){
                res[i][j] = list.get(i).get(j);
            }
        }

        return res;
    }

    public static void rotate(ArrayList<ArrayList<Integer>> a) {

        if(a==null || a.size()==0|| a.size()!=a.get(0).size()){
            return;
        }

        for(int i = 0 ; i< a.size();i++){
            for(int j =i; j<a.size();j++){
                int temp = a.get(i).get(j);
                int temp2 = a.get(j).get(i);
                a.get(i).set(j,temp2);
                a.get(j).set(i,temp);
            }
        }


        for(int i =0; i< a.size();i++){
            int j = 0;
            int k = a.get(i).size()-1;
            while(j<k){
                int temp = a.get(i).get(j);
                int temp2 = a.get(i).get(k);
                a.get(i).set(j,temp2);
                a.get(i).set(k,temp);
                j++;
                k--;
            }
        }
    }

    public static int[][] prettyPrint(int A) {
        if(A<1) return null;
        int n = A+(A-1);
        int arr[][] = new int[n][n];
        int numofRows = n;
        int numOfCols = n;
        int row = 0;
        int col = 0;
        int i;
        n = A;

        while(row<numofRows && col<numOfCols){
            for(i = col; i < numOfCols;i++){
                arr[row][i] = n;
            }
            row++;

            for(i = row; i<numofRows;i++){
                arr[i][numOfCols-1] = n;
            }
            numOfCols--;


            if(row<numofRows){
                for(i = numOfCols-1; i>=col;i--){
                    arr[numofRows-1][i] = n;

                }
                numofRows--;
            }

            if(col<numOfCols){
                for(i = numofRows-1; i>=row;i--){
                    arr[i][col] = n;

                }
                col++;
            }
            n--;
        }

        return arr;

    }

    private static void printSpiralForm(int[][] arr) {
        int numofRows = arr.length;
        int numOfCols = arr[0].length;
        int row = 0;
        int col = 0;
        int i;

        while(row<numofRows && col<numOfCols){
            for(i = col; i < numOfCols;i++){
                System.out.print(arr[row][i]+" ");
            }
            row++;

            for(i = row; i<numofRows;i++){
                System.out.print(arr[i][numOfCols-1]+" ");
            }
            numOfCols--;


            if(row<numofRows){
                for(i = numOfCols-1; i>=col;i--){
                    System.out.print(arr[numofRows-1][i]+" ");

                }
                numofRows--;
            }

            if(col<numOfCols){
                for(i = numofRows-1; i>=row;i--){
                    System.out.print(arr[i][col]+" ");

                }
                col++;
            }
        }

    }

    /**
     * print largest rectangle from histogram
     * @param h
     * @return
     */
    static long largestRectangle(int[] h) {
        Arrays.sort(h);
        long area = 0;
        long area_so_far = 0;
        int i = 0;
        Stack<Integer> stack = new Stack();
        while(i<h.length){
            if(stack.isEmpty()|| h[stack.peek()]<=h[i]){
                stack.push(i);
                i++;
            }
            else{
                int index = stack.pop();
                int j = 0;
                if(stack.isEmpty()) j = i;
                else j = i-stack.peek()-1;
                area_so_far = h[index] *j;
                if(area_so_far>area) area = area_so_far;
            }
        }

        while(!stack.isEmpty()){

            int index = stack.pop();
            int j = 0;
            if(stack.isEmpty()) j = i;
            else j = i-stack.peek()-1;
            area_so_far = h[index] *j;
            if(area_so_far>area) area = area_so_far;

        }


        return area;
    }



    static int getMaxArea(int hist[], int n)
    {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < n)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.isEmpty() || hist[s.peek()] <= hist[i])
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.isEmpty())
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;

    }

    public static void printResult(int[] arr, int k ){

        int low = 0;
        int  high = arr.length-1;
        // 8 2 3 4 5
        while(low<=high){
            int mid = (low+high)/2;

            if(arr[mid] == k){
                System.out.println(mid);
                return;
            }

            if(arr[low]<=arr[mid]){
                if(arr[low]<=k && k <= arr[mid]){
                    high = mid-1;
                }
                else low = mid+1;
            }
            else{
                if(k>=arr[mid] && k<=arr[high]){
                    low = mid+1;
                }
                else high = mid-1;
            }
        }
        System.out.println(-1);
    }

    public static void printResult(int[] arr){
        int[] lis = new int[arr.length];
        for(int i=0;i<lis.length;i++){
            lis[i]=1;
        }

        for(int i = 0; i< arr.length;i++){
            for(int j = 0; j<i;j++){
                if(arr[i]>arr[j] && lis[i]<=lis[j]){
                    lis[i] = lis[j]+1;
                }
            }
        }

        int max = 1;
        for(int k :lis){
            if(k>max) max = k;
        }

        System.out.println(max);
    }

    public static void solve(char[][] board) {


        for(int i =0;i<board.length;i++){
            for(int j = 0; j<board[i].length;j++){
                if(isValidIndex(board,i,j)){
                    updateBoard(board,i,j);
                }
            }
        }
    }


    public static void updateBoard(char[][] board, int i , int j){

        if(i<0||j<0||i>=board.length||j>= board[i].length){
            return;
        }
        board[i][j]='X';

        if(i+1<board.length && shouldUpdate(board,i+1,j)) {
            updateBoard(board, i + 1, j);
        }

        if(j+1<board[i].length && shouldUpdate(board,i,j+1))
        updateBoard(board,i,j+1);
    }


    public static boolean shouldUpdate(char[][] board, int i, int j ){
        return board[i][j]=='O';
    }

    public static boolean isValidIndex(char[][] board, int i , int j){

        if(board[i][j]=='O'){
            if(i-1>=0 && board[i-1][j]=='O') return true;
            if(i+1<board.length&& board[i+1][j]=='O') return true;
            if(j-1>=0 && board[i][j-1]=='O') return true;
            if(j+1<board[i].length&& board[i][j+1]=='O') return true;

            return false;
        }

        return false;

    }

}

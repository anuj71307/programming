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

    static int k = 0;

    public static void main(String[] args) {
        //solve(table);
        Integer[] arr = new Integer[]{4,-3,2,-1,-2, 3, 4};
        //System.out.println(minSwaps(arr));
        System.out.println(getPow(5,1));

        HashMap<Integer, Integer> map = findOpposite(arr);
        for(Integer key : map.keySet()){
            System.out.println(key + " "+map.get(key));
        }

        Geek g1 = new Geek("aa", 1);
        Geek g2 = new Geek("aa", 1);

        // comparing above created Objects.


            if(g1.equals(g2))
                System.out.println("Both Objects are equal. ");
            else
                System.out.println("Both Objects are not equal. ");



}

   static class Geek
    {

        public String name;
        public int id;

        Geek(String name, int id)
        {

            this.name = name;
            this.id = id;
        }



    }

    private static HashMap<Integer,Integer> findOpposite(Integer[] arr) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1!=o2 || o1 !=-02){
                    return Math.abs(o1)-Math.abs(o2);
                }
                else{
                    if(o1<o2) return 1;
                    if(o1==o2) return  -1;
                    else return  -1;
                }
            }
        };


        Arrays.sort(arr,comparator);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =1; i< arr.length;i++){
            if(arr[i]==-arr[i-1]){
                map.put(arr[i], arr[i-1]);
            }
        }

        return map;
    }

    /**
     * get pow of a number
     * @param x
     * @param pow
     * @return
     */
    static long getPow(int x , int pow){
        if(pow==0) return 1;
        if(pow%2==0){
            return getPow(x*x, pow/2);
        }
        else{
            return x*getPow(x*x,(pow-1)/2);
        }
    }


    /**
     * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
     * @param A
     * @return
     */
    public static int minSwaps(int[] A)
    {//add code here.

        if(A==null||A.length<=1) return 0;
        boolean [] visited = new boolean[A.length];
        int swap = 0;
        for(int i = 0 ; i < A.length;i++){
            int j = i;
            int cycle=0;
            while(!visited[j]){
                visited[j] = true;
                j = A[j]-1;
                cycle++;
            }
            if(cycle!=0){
                swap+=cycle-1;
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
                arr[j] = i+1;
                res = solveNQ(board, row + 1, N, arr,j+1) || res;
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
                    System.out.print(board[i]+" ");
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
     * @param arr array to be rotated
     * @param k item to search
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

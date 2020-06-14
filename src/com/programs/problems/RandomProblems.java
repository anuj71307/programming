package com.programs.problems;

import java.util.*;

/**
 * class which will call appropriate method and classes
 * for different problems
 *
 * @author anujjha
 */
public class RandomProblems {


    public static void main(String[] args) {
     RandomProblems rp = new RandomProblems();
     rp.generatePascal(7);
    }

    public int diffPossible(final List<Integer> a, int b) {
        HashSet<Integer> hash = new HashSet<>();

        // 1 3 5 2
        for (Integer k : a) {
            Integer diff = Math.abs(k - b);
            if (hash.contains(diff)) return 1;
            hash.add(k);
        }

        return 0;
    }

    static int migratoryBirds(int n, int[] ar) {
        // Complete this function
        int type = 0;
        int arr[] = new int[5];
        for (int k : ar) {
            arr[--k]++;
        }
        for (int i = 0; i < 5; i++) {
            if (arr[i] > type) {
                type = i + 1;
            }
        }
        return type;
    }

    /**
     * generate all biary number of a given length
     * https://www.geeksforgeeks.org/generate-binary-strings-without-consecutive-1s/
     *
     * @param len
     */
    private static void generateBinary(int len) {
        if (len <= 0) return;
        char[] arr = new char[len];
        arr[0] = '0';
        generateBinary(len, arr, 1);
        arr[0] = '1';
        generateBinary(len, arr, 1);
    }

    private static void generateBinary(int len, char[] arr, int i) {
        if (i == len) {
            System.out.println(new String(arr));
            return;
        }

        if (arr[i - 1] == '0') {
            arr[i] = '0';
            generateBinary(len, arr, i + 1);
            arr[i] = '1';
            generateBinary(len, arr, i + 1);
        } else if (arr[i - 1] == '1') {
            arr[i] = '0';
            generateBinary(len, arr, i + 1);
        }

    }

    /**
     * Given a list return first two item which is present only once
     * if given list is {1,3,3,56,7,56,1,56,2} then return {7,2}
     *
     * @param list
     * @return
     */
    public static List<Integer> getFirstTwoItemsWithoutPair(List<Integer> list) {
        if (list == null || list.isEmpty()) return list;
        HashSet<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (Integer num : list) {
            if (set.add(num)) {
                res.add(num);
            } else {
                res.remove(num);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < res.size() && i < 2; i++) {
            result.add(res.get(i));
        }
        return result;
    }

    /**
     * reverse word in char array separated by space
     *
     * @param arr
     * @return
     */
    static char[] reverseWords(char[] arr) {
        if (arr == null || arr.length == 0) return arr;

        char temp[] = new char[arr.length];
        int j = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            temp[j] = arr[i];
            j++;
        }


        int start = 0;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] == ' ') {
                reverse(temp, start, i - 1);
                start = i + 1;
            }
        }
        reverse(temp, start, temp.length - 1);

        return temp;

    }

    public static void reverse(char[] temp, int start, int end) {

        while (start < end) {
            char t = temp[start];
            temp[start] = temp[end];
            temp[end] = t;
            start++;
            end--;
        }

    }

    /*
    generate pascal triangle
     */
    public int[][] generatePascal(int numRows) {
        if (numRows <= 0) return null;
        int[][] list = new int[numRows][];
        int[] sublist = new int[1];
        sublist[0]=1;
        list[0] = sublist;
        for (int i = 1; i < numRows; i++) {
            sublist = getSubList(sublist);
            list[i] = sublist;
        }
        for(int k: list[numRows-1]){
            System.out.print(k+" ");
        }
        return list;
    }

    public int[] getSubList(int[] list) {
        int[]sublist = new int[list.length+1];
        for (int i = 0; i < sublist.length; i++) {
            if (i == 0 || i == list.length) {
                //first and last element will always be 1
                sublist[i]=1;
                continue;
            }
            sublist[i]= list[i] + list[i-1];
        }
        return sublist;
    }

    /**
     * find sqrt of a number
     *
     * @param n
     * @return
     */
    public int sqrt(int n) {
        if (n == 0) return 0;
        if (n < 4) return 1;

        long start = 1;
        long end = n;
        long ans = 0;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (mid * mid == n) return (int) mid;
            if (mid * mid > n) end = mid - 1;
            else {
                start = mid + 1;
                ans = mid;
            }
        }


        return (int) ans;
    }

    public static int floorSqrt(int x) {
        // Base Cases
        if (x == 0 || x == 1)
            return x;

        // Do Binary Search for floor(sqrt(x))
        int start = 1, end = x, ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            // If x is a perfect square
            if (mid * mid == x)
                return mid;

            // Since we need floor, we update answer when mid*mid is
            // smaller than x, and move closer to sqrt(x)
            if (mid * mid < x) {
                start = mid + 1;
                ans = mid;
            } else   // If mid*mid is greater than x
                end = mid - 1;
        }
        return ans;
    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        if (a == null || a.size() == 0) return 0;
        int length = a.size();//3
        int i = 0;
        int j = 0;
        for (i = 0; i < a.size(); ) {
            if (i + 1 < a.size() && a.get(i).intValue() == a.get(i + 1).intValue()) {
                Integer k = a.get(i);

                a.set(j, k);
                j++;
                a.set(j, k);
                j++;

                while (i + 1 < a.size() && a.get(i).intValue() == a.get(i + 1).intValue()) {
                    i++;
                }
                i++;
            } else {
                Integer k = a.get(i);

                a.set(j, k);
                j++;
                i++;

            }
        }

        return j;
    }

    public static int removeDuplicates(int[] a) {
        if (a == null || a.length == 0) return 0;
        int length = a.length;//3
        int i = 0;
        int j = 0;//2
        for (i = 0; i < a.length; ) {
            if (i + 1 < a.length && a[i] == a[i + 1]) {
                a[j] = a[i];
                j++;
                a[j] = a[i];
                j++;
                while (i + 1 < a.length && a[i + 1] == a[i]) {
                    i++;
                }
                i++;
            } else {
                a[j] = a[i];
                j++;
                i++;
            }
        }

        return j;
    }

    /**
     * find sum of cube of all digits in a num
     *
     * @param num input num
     * @return sum of cubeof all num
     */
    public static int cubeSum(int num) {

        if (num == 0) return 0;
        int sum = 0;
        while (num > 0) {
            int rem = num % 10;
            sum += rem * rem * rem;
            num = num / 10;
        }
        return sum;
    }

    public static int compareVersion(String a, String b) {
        String str1[] = a.split("\\.");
        String str2[] = b.split("\\.");
        int i = 0;
        while (i < str1.length && i < str2.length) {
            double one = Double.parseDouble(str1[i]);
            double two = Double.parseDouble(str2[i]);
            if (one > two) {
                return 1;
            } else if (one < two) {
                return -1;
            }
            i++;

        }

        if (i == str1.length && i == str2.length) {
            return 0;
        }

        if (i < str1.length) {
            return 1;
        }
        return -1;

    }

}

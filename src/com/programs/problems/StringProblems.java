package com.programs.problems;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class StringProblems {

    public static void main(String[] args) {
        System.out.println("Ans is "+ longestSubString("abcdeabdsgth"));
    }


    private static int longestSubString(String string){
        if(string==null||string.isEmpty()) return -1;

        //abcabcbb
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int i = 0, j = 0;//j

        for(;i<string.length();i++){
            char c = string.charAt(i);
            if(map.containsKey(c)){
                j = Math.max(map.get(c),j);
            }
            ans = Math.max(ans, i-j+1);
            map.put(c, i+1);

        }

        return  ans;
    }
    /**
     * generate next greater number
     *
     * @param arr
     */
    private static void generateNextNum(String[] arr) {
        int[] intArr = new int[arr.length];
        int i = 0;
        for (String s : arr) {
            intArr[i] = Integer.parseInt(s);
            i++;
        }
        int n = intArr.length;
        for (i = n - 1; i > 0; i--) {
            if (intArr[i] > intArr[i - 1]) {
                break;
            }
        }
        if (i == 0) {
            System.out.println("Not Possible");
            return;
        }
        int x = intArr[i - 1], min = i;
        for (int j = i + 1; j < n; j++) {
            if (intArr[j] > x && intArr[j] < intArr[min]) {
                min = j;
            }
        }
        swap(intArr, i - 1, min);
        Arrays.sort(intArr, i, n);
        for (int s : intArr) {
            System.out.print(s + " ");
        }
    }

    private static void swap(int[] intArr, int i, int min) {
        int temp = intArr[i];
        intArr[i] = intArr[min];
        intArr[min] = temp;
    }

    /**
     * https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
     * decode a string in place
     *
     * @param str number as string to decode
     * @return number of possible decoding
     */
    public static int decode(String str) {
        //index i-2
        int i = 1;
        //index i-1
        int j = 1;
        //current count
        int current = 0;

        for (int k = 2; k <= str.length(); k++) {
            current = 0;
            if (str.charAt(k - 1) > '0') {
                current = j;
            }
            if (str.charAt(k - 2) == '1' || (str.charAt(k - 2) == '2' && str.charAt(k - 1) < '7')) {
                current = current + i;
            }
            i = j;
            j = current;
        }
        return current;

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

    public static String sortString(String str) {
        char[] temp = mergeSortString(str.toCharArray());
        int i = 0;
        int sum = 0;
        try {
            for (char c : temp) {
                int m = Character.getNumericValue(c);
                if (m < 10) {
                    i++;
                    sum += m;
                } else {
                    break;
                }
            }
        } catch (Exception e) {

        }
        String result = new String(temp);
        result = result.substring(i);
        return result + sum;
    }

    private static char[] mergeSortString(char[] chars) {
        if (chars == null || chars.length < 2) {
            return chars;
        }

        int mid = (chars.length) / 2;
        char[] left = new char[mid];
        char[] right = new char[chars.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = chars[i];
        }
        for (int i = mid; i < chars.length; i++) {
            right[i - mid] = chars[i];
        }

        mergeSortString(left);
        mergeSortString(right);
        return mergeArr(left, right, chars);
    }

    private static char[] mergeArr(char[] left, char[] right, char[] chars) {
        int i = 0;
        int k = 0;
        int j = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                chars[k] = left[i];
                i++;
            } else {
                chars[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            chars[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            chars[k] = right[j];
            j++;
            k++;
        }


        return chars;
    }

    public static boolean findKPalindrome(String str, int k) {
        boolean result = false;
        int i = 0;
        int j = str.length() - 1;
        int count = 0;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                count = count + 2;
            }
            i++;
            j--;
        }

        if (count <= k) {
            result = true;
        }

        return result;
    }

    /**
     * https://practice.geeksforgeeks.org/problems/total-decoding-messages/0
     *
     * @param digits
     * @return
     */
    public static int countDecoding(char[] digits) {
        //digits[1,2,3] n ==3
        // base cases

        int n = digits.length;
        // Initialize count
        int count[] = new int[n + 1];

        count[0] = 1;
        count[1] = 1;
        //count[1,1,2,2]
        // If the last digit is not 0, then
        // last digit must add to
        // the number of words
        for (int i = 2; i <= n; i++) {

            if (digits[i - 1] > '0') {
                count[i] = count[i - 1];
            }

            if (digits[i - 2] == '1' || (digits[i - 2] == '2' && digits[i - 1] < '7')) {
                count[i] = count[i] + count[i - 2];
            }
        }

        return count[n];
    }

    /**
     * check if a string is palindrome or not by removing space
     * Ref: https://practice.geeksforgeeks.org/problems/string-palindromic-ignoring-spaces/0
     *
     * @param str
     * @return
     */
    public static boolean checkPalindromeline(String str) {
        if (str == null) return true;
        str = str.trim();
        if (str.isEmpty()) return true;

        int i = 0;
        int j = str.length() - 1;

        while (i <= j) {
            if (str.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (str.charAt(j) == ' ') {
                j--;
                continue;
            }

            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;

    }

    public static int longestBalancedParenthesis(String str) {
        int length = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
                continue;
            }

            stack.pop();
            if (!stack.isEmpty()) {
                int temp = i - stack.peek();
                if (temp > length) length = temp;
            } else stack.push(i);
        }

        return length;
    }

    /**
     * Method check if two string are one edit away
     * Ref: https://www.geeksforgeeks.org/check-if-two-given-strings-are-at-edit-distance-one/
     *
     * @param first  string 1
     * @param second string 2
     * @return true if one edit can make the string similar, false otherwise
     */
    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        if (first.length() == second.length()) {
            return areOneEditAway(first, second);
        }

        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        if (first.length() > second.length()) {
            return checkOneEditAway(first, second);
        } else {
            return checkOneEditAway(second, first);
        }
    }

    //plea // second = ple
    private static boolean checkOneEditAway(String first, String second) {
        boolean isOneEditDone = false;
        int indexFirst = 0;
        int indexSecond = 0;
        while (indexFirst < first.length() && indexSecond < second.length()) {

            if (first.charAt(indexFirst) != second.charAt(indexSecond)) {
                if (isOneEditDone) {
                    return false;
                }
                isOneEditDone = true;
                indexFirst++;
                continue;
            }

            indexFirst++;
            indexSecond++;
        }


        return true;
    }

    private static boolean areOneEditAway(String first, String second) {
        boolean isOneEditDone = false;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                if (isOneEditDone) {
                    return false;
                }
                isOneEditDone = true;
            }
        }

        return true;
    }
}

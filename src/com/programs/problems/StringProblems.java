package com.programs.problems;


import java.util.*;

public class StringProblems {


    //form smallest possible number from number given as string
    public static String smallestNumber(String str) {

        char[] num = str.toCharArray();
        int n = str.length();
        int[] rightMin = new int[n];

        // for the rightmost digit, there
        // will be no smaller right digit
        rightMin[n - 1] = -1;

        // index of the smallest right digit
        // till the current index from the
        // right direction
        int right = n - 1;

        // traverse the array from second
        // right element up to the left
        // element
        for (int i = n - 2; i >= 1; i--) {
            // if 'num[i]' is greater than
            // the smallest digit
            // encountered so far
            if (num[i] > num[right])
                rightMin[i] = right;

            else {
                // there is no smaller right
                // digit for 'num[i]'
                rightMin[i] = -1;

                // update 'right' index
                right = i;
            }
        }

        // special condition for the 1st
        // digit so that it is not swapped
        // with digit '0'
        int small = -1;
        for (int i = 1; i < n; i++)
            if (num[i] != '0') {
                if (small == -1) {
                    if (num[i] < num[0])
                        small = i;
                } else if (num[i] < num[small])
                    small = i;
            }

        if (small != -1) {
            char temp;
            temp = num[0];
            num[0] = num[small];
            num[small] = temp;
        } else {
            // traverse the 'rightMin[]'
            // array from 2nd digit up
            // to the last digit
            for (int i = 1; i < n; i++) {
                // if for the current digit,
                // smaller right digit exists,
                // then swap it with its smaller
                // right digit and break
                if (rightMin[i] != -1) {
                    // performing the required
                    // swap operation
                    char temp;
                    temp = num[i];
                    num[i] = num[rightMin[i]];
                    num[rightMin[i]] = temp;
                    break;
                }
            }
        }

        // required smallest number
        return (new String(num));
    }


    public static ArrayList<String> getPerms(String str) {

        if (str == null) return null;
        ArrayList<String> perm = new ArrayList<>();
        if (str.length() == 0) {
            perm.add("");
            return perm;
        }
        char first = str.charAt(0);
        String remainder = str.substring(1);
        ArrayList<String> words = getPerms(remainder);
        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                String temp = insertChar(word, first, j);
                perm.add(temp);
            }
        }


        return perm;
    }

    private static String insertChar(String word, char first, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + first + end;

    }

    /*
    find longest palindromic substring from given string
     */
    public static String longestPalindromic(String str) {
        if (str == null || str.length() <= 1) return str;
        int start = 0;
        int end = 0;
        for (int i = 0; i < str.length(); i++) {
            int len1 = expand(str, i, i);

            int len2 = expand(str, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }

        }

        return str.substring(start, end + 1);
    }


    /**
     * check both left and right from given index
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    private static int expand(String str, int start, int end) {
        while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)) {
            start--;
            end++;
        }
        return end - start - 1;
    }

    /**
     * length of longest substring without duplicate character
     *
     * @param string
     * @return
     */
    private static int longestSubString(String string) {
        if (string == null || string.isEmpty()) return -1;

        //abcabcbb
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int i = 0, j = 0;//j

        for (; i < string.length(); i++) {
            char c = string.charAt(i);
            if (map.containsKey(c)) {
                j = Math.max(map.get(c), j);
            }
            ans = Math.max(ans, i - j + 1);
            map.put(c, i + 1);

        }

        return ans;
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
     * 12
     */
    public static int decode(String str) {
        //index i-2
        int i = 1;
        //index i-1
        int j = 1;
        //current count
        int current = 1;

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

    /**
     * sort and given string in alphabetical order
     *
     * @param str
     * @return
     */
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
    public static boolean checkPalindromline(String str) {
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

    /**
     * given paranthesis and string find longest balanced parenthsesis in that
     *
     * @param str
     * @return
     */
    public static int longestBalancedParenthesis(String str) {
        int length = 0;

        //())))
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

        String result = "";
        int k = Integer.parseInt(result.split(" ")[1]);
        return true;
    }

    public static int[][] solution;
    static int path = 1;
    static String result = "";

    // initialize the solution matrix in constructor.
    public StringProblems(char[][] N) {
        solution = new int[N.length][N[0].length];
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                solution[i][j] = 0;
            }
        }
    }

    public boolean searchWord(char[][] matrix, String word) {
        if (word == null || word.length() == 0 || matrix == null || matrix.length == 0) return false;
        int N = matrix.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (search(matrix, word, i, j, 0, N, matrix[i].length, false)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean search(char[][] matrix, String word, int row, int col,
                          int index, int N, int M, boolean rc) {

        // check if current cell not already used or character in it is not not

        if (solution[row][col] != 0 || word.charAt(index) != matrix[row][col]) {
            return false;
        }

        if (index == word.length() - 1) {
            // word is found, return true
            if (rc) result = result + 'R';
            else result = result + 'D';
            solution[row][col] = path++;
            return true;
        }

        // mark the current cell as 1
        solution[row][col] = path++;
        if (rc) result = result + 'R';
        else result = result + 'D';
        // check if cell is already used

        if (row + 1 < N && search(matrix, word, row + 1, col, index + 1, N, M, false)) { // go
            // down
            //charArr[k] = 'D';
            return true;
        }

        if (col + 1 < M && search(matrix, word, row, col + 1, index + 1, N, M, true)) { // go
            // right
            return true;
        }


        // if none of the option works out, BACKTRACK and return false
        solution[row][col] = 0;
        path--;
        result = result.substring(0, result.length() - 1);
        return false;
    }

    public void print() {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                System.out.print(" " + solution[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {

        HashSet<String> dict = new HashSet<>();
        dict.add("and");
        dict.add("cat");
        dict.add("aand");
        System.out.println(wordBreakProblems("cata", dict));
    }

    /**
     * https://www.hackerrank.com/challenges/beautiful-binary-string/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=60-day-campaign
     *
     * @param b beautify a string by removing 010 . count how many change has to happen to remove 010 user can replace a 0 to 1 or vice versa
     * @return
     */
    private static int beautifulBinaryString(String b) {
        if (b == null || b.length() <= 2) return 0;

        int count = 0;
        char arr[] = b.toCharArray();
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == '0') {
                if (arr[i - 1] == '1' && arr[i - 2] == '0') {
                    arr[i] = '1';
                    count++;
                }
            }
        }
        return count;

    }

    /**
     * 1 is read off as one 1 or 11.
     * 11 is read off as two 1s or 21.
     * <p>
     * 21 is read off as one 2, then one 1 or 1211.
     * <p>
     * Given an integer n, generate the nth sequence
     * ??'tod
     *
     * @param A
     * @return
     */
    public static String countAndSay(int A) {
        if (A <= 0) return "0";
        if (A == 1) return "1";

        String str = "1";
        int count = 1;
        for (int k = 1; k < A; k++) {
            char[] arr = str.toCharArray();
            char[] temp = new char[arr.length * 2];
            int j = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != arr[i - 1]) {
                    temp[j] = (char) ('0' + count);
                    j++;
                    temp[j] = arr[i - 1];
                    j++;
                } else {
                    count++;
                }
            }
            temp[j] = (char) ('0' + count);
            j++;
            temp[j] = arr[arr.length - 1];
            j++;
            str = new String(temp).substring(0, j);
            count = 1;
        }

        return str;
    }


    public static String reverseWords(String a) {
        if (a == null) return null;
        if (a.trim().length() == 0) return "";
        String str[] = a.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            String s = str[i].trim();
            if (s.length() == 0) continue;
            builder = builder.append(" ").append(s);
        }
        return builder.toString().trim();
    }


    public static int evalRPN(String[] A) {
        Stack<Integer> stack = new Stack();
        for (String str : A) {
            if (isOperator(str)) {
                evaluate(str, stack);
            } else {
                stack.push(Integer.parseInt(str));
            }
        }

        return stack.pop();

    }

    /**
     * Text Edit - > https://www.hackerrank.com/challenges/simple-text-editor/problem
     *
     * @param str
     * @param stack
     */
    public static void evaluate(String str, Stack<Integer> stack) {
        int second = stack.pop();
        int first = stack.pop();
        if (str.equals("+")) {
            stack.push(first + second);
        } else if (str.equals("-")) {
            stack.push(first - second);
        } else if (str.equals("*")) {
            stack.push(first * second);
        } else if (str.equals("/")) {
            stack.push(first / second);
        }
    }

    public static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private static String parseAction(String str, Stack<String> stack, String[] arr) {
        int action = Integer.parseInt(arr[0]);
        //add
        if (action == 1) {

            stack.push(str);
            str = str + arr[1];
        }
        //delete last charc
        else if (action == 2) {
            stack.push(str);
            int count = Integer.parseInt(arr[1]);
            str = str.substring(0, str.length() - count);

        }
        //print
        else if (action == 3) {
            //str = abs
            int count = Integer.parseInt(arr[1]);
            if (count <= str.length()) {
                System.out.println(str.charAt(count - 1));
            }

        }
        // undo last action
        else {
            if (!stack.isEmpty()) {
                str = stack.pop();
            }
        }

        return str;
    }

    // balanced parenthesis
    static String isBalanced(String s) {
        if (s == null || s.trim().isEmpty()) return "YES";
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (isOpeningBracket(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                char last = stack.pop();
                if (!doesMatch(c, last)) {
                    return "NO";
                }
            }
        }

        if (!stack.isEmpty()) return "NO";
        return "YES";

    }

    private static boolean doesMatch(char c, char last) {
        boolean result = false;
        if (c == ')' && last == '(') result = true;
        else if (c == ']' && last == '[') result = true;
        else if (c == '}' && last == '{') result = true;
        return result;
    }


    static boolean isOpeningBracket(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    /**
     * group anagrams
     *
     * @param str
     */
    public static void groupAnagram(List<String> str) {

        TreeMap<String, List<String>> map = new TreeMap<>();

        // loop over all words
        for (int i = 0; i < str.size(); i++) {

            // convert to char array, sort and
            // then re-convert to string
            String word = str.get(i);
            String temp = word.replaceAll(" ", "");
            char[] letters = temp.toCharArray();
            Arrays.sort(letters);
            String newWord = new String(letters);

            // calculate hashcode of string
            // after sorting
            if (map.containsKey(newWord)) {

                // Here, we already have
                // a word for the hashcode
                List<String> words = map.get(newWord);
                words.add(word);
                map.put(newWord, words);
            } else {

                // This is the first time we are
                // adding a word for a specific
                // hashcode
                List<String> words = new ArrayList<>();
                words.add(word);
                map.put(newWord, words);
            }
        }

        // print all the values where size is > 1
        // If you want to print non-anagrams,
        // just print the values having size = 1
        for (String i : map.keySet()) {
            List<String> values = map.get(i);
            Collections.sort(values);
            for (int k = 0; k < values.size(); k++) {
                if (k == values.size() - 1) {
                    System.out.print(values.get(k));
                } else System.out.print(values.get(k) + ",");
            }
            System.out.println();
        }

    }


    static HashMap<String, String> map = new HashMap<>();

    //https://www.geeksforgeeks.org/word-break-problem-dp-32/
    private static String wordBreakProblems(String str, Set<String> dict) {
        if (dict.contains(str)) return str;
        if (map.containsKey(str)) return map.get(str);
        //catsanddog
        for (int i = 1; i < str.length(); i++) {
            String prefix = str.substring(0, i);
            if (dict.contains(prefix)) {
                String suffix = wordBreakProblems(str.substring(i, str.length()), dict);
                if (suffix != null) {
                    String s = prefix + " " + suffix;
                    map.put(str, s);
                    return s;
                }
            }
        }
        return null;
    }


    private static String removeWhiteSpcae(String str) {
        if (str == null || str.length() <= 0) return str;
        //Anuj Ku
        char[] arr = str.toCharArray();
        int i = 0;
        int j = 0;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] != ' ') {
                arr[j] = arr[i];
                j++;
            }
        }
        while (j < arr.length) {
            arr[j] = '-';
            j++;
        }
        return new String(arr);
    }


    static String find_smallest_numbers(String str) {
        if (str == null || str.length() == 0) return str;
        char arr[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            int small = str.length() - 1;
            for (int j = str.length() - 1; j > i; j--) {
                if (arr[j] < arr[i] && arr[j] < arr[small]) {
                    small = j;
                }
            }
            if (arr[small] < arr[i]) {
                char temp = arr[small];
                arr[small] = arr[i];
                arr[i] = temp;
                break;
            }

        }
        return new String(arr);

    }

    static String find_smallest_number(String str) {
        if (str == null || str.length() == 0) return str;
        int rightMin[] = new int[str.length()];
        char[] arr = str.toCharArray();
        int n = str.length();
        rightMin[n - 1] = -1;
        int right = n - 1;
        for (int i = n - 2; i > 0; i--) {
            if (arr[i] > arr[right]) {
                rightMin[i] = right;
            } else {
                rightMin[i] = -1;
                right = i;
            }
        }


        for (int i = 1; i < n; i++) {
            if (rightMin[i] != -1) {

                char temp = arr[i];
                arr[i] = arr[rightMin[i]];
                arr[rightMin[i]] = temp;
                break;
            }
        }

        return (new String(arr));
    }


    public static String find_path(List<List<String>> matrix, String target_string) {
        if (matrix == null || matrix.size() == 0 || target_string == null || target_string.length() == 0) return result;
        solution = new int[matrix.size()][matrix.get(0).size()];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                solution[i][j] = 0;
            }
        }

        if (searchWord(matrix, target_string)) {
            return result.substring(0, result.length() - 1);
        }

        return "NO PATH";
    }

    public static boolean searchWord(List<List<String>> matrix, String word) {
        if (word == null || word.length() == 0 || matrix == null || matrix.size() == 0)
            return false;
        int N = matrix.size();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (search(matrix, word, i, j, 0, N, matrix.get(i).size(), false)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean search(List<List<String>> matrix, String word, int row, int col, int index, int N, int M, boolean rc) {

        char p = matrix.get(row).get(col).charAt(0);
        if (solution[row][col] != 0 || word.charAt(index) != p) {
            return false;
        }

        if (index == word.length() - 1) {
            if (rc) result = result + 'R';
            else result = result + 'D';
            solution[row][col] = path++;
            return true;
        }
        solution[row][col] = path++;
        if (rc) result = result + 'R';
        else result = result + 'D';


//GO down
        if (row + 1 < N && search(matrix, word, row + 1, col, index + 1, N, M, false)) {
            return true;
        }

        if (col + 1 < M && search(matrix, word, row, col + 1, index + 1, N, M, true)) {
            return true;
        }

        solution[row][col] = 0;
        path--;
        result = result.substring(0, result.length() - 1);
        return false;
    }


    // function to form the smallest number
    // using at most one swap operation
    public static String smallestNumbers(String str) {

        char[] num = str.toCharArray();
        int n = str.length();
        int[] rightMin = new int[n];

        // for the rightmost digit, there
        // will be no smaller right digit
        rightMin[n - 1] = -1;

        // index of the smallest right digit
        // till the current index from the
        // right direction
        int right = n - 1;

        // traverse the array from second
        // right element up to the left
        // element
        for (int i = n - 2; i >= 1; i--) {
            // if 'num[i]' is greater than
            // the smallest digit
            // encountered so far
            if (num[i] > num[right])
                rightMin[i] = right;

            else {
                // there is no smaller right
                // digit for 'num[i]'
                rightMin[i] = -1;

                // update 'right' index
                right = i;
            }
        }

        // special condition for the 1st
        // digit so that it is not swapped
        // with digit '0'
        int small = -1;
        for (int i = 1; i < n; i++)
            if (num[i] != '0') {
                if (small == -1) {
                    if (num[i] < num[0])
                        small = i;
                } else if (num[i] <= num[small])
                    small = i;
            }

        if (small != -1) {
            char temp;
            temp = num[0];
            num[0] = num[small];
            num[small] = temp;
        } else {
            // traverse the 'rightMin[]'
            // array from 2nd digit up
            // to the last digit
            for (int i = 1; i < n; i++) {
                // if for the current digit,
                // smaller right digit exists,
                // then swap it with its smaller
                // right digit and break
                if (rightMin[i] != -1) {
                    // performing the required
                    // swap operation
                    char temp;
                    temp = num[i];
                    num[i] = num[rightMin[i]];
                    num[rightMin[i]] = temp;
                    break;
                }
            }
        }

        // required smallest number
        return (new String(num));
    }

}
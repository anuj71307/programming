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

    /*
    https://leetcode.com/problems/decode-ways/
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) {
            if (s.charAt(0) > '0') return 1;
            else return 0;
        }
        int first = 0;
        int second = 0;
        if (s.charAt(0) > '0') {
            first = 1;
        }
        if (s.charAt(1) > '0' && first != 0) {
            second = 1;
        }
        int current = 1;
        for (int i = 2; i <= s.length(); i++) {
            current = 0;
            if (s.charAt(i - 1) > '0') {
                current = second;
            }
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')) {
                current += first;
            }
            first = second;
            second = current;
        }

        return second;

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

        return true;
    }

    public static int[][] solution;
    static int path = 1;
    static String result = "";

    public StringProblems() {

    }

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


    public static void main(String[] args) {

        StringProblems sp = new StringProblems();
        String[] str = {"awe", "some", "awesome", "is", "hello", "just", "", "isawesome"};
        System.out.print(sp.reArrangeString("AA"));

    }

    /**
     * https://practice.geeksforgeeks.org/problems/rearrange-a-string/0
     * @param str
     * @return
     */
    private String reArrangeString(String str){
        if(str==null || str.length()==0) return str;
        int[] arr = new int[25];
        int count =0;
        for(int i =0; i< str.length();i++){
            char c = str.charAt(i);
            if(c>='0' && c<='9'){
                count+=Character.getNumericValue(c);
            }
            else{
                arr[c-'A']+=1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<arr.length;i++){

            int k = arr[i];
            char c = (char)('A'+i);
            while(k>0){
                sb.append(c);
                k--;
            }

        }
        if(count>0) {
            sb.append(count);
        }

        return sb.toString();
    }

    /**
     * Given a list of word, find longest word which is formed by other words
     *
     * @param str
     * @return
     */
    private String searchLongestWord(String[] str) {
        Arrays.sort(str, (o1, o2) -> o2.length() - o1.length());
        HashMap<String, Boolean> map = new HashMap<>();
        for (String s : str) {
            map.put(s, true);
        }
        for (String s : str) {
            if (canBuild(s, map, true)) {
                return s;
            }
        }
        return "not found";
    }

    private boolean canBuild(String s, HashMap<String, Boolean> map, boolean original) {
        if (map.containsKey(s) && !original) return map.get(s);

        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            if (map.getOrDefault(left, false)) {
                String right = s.substring(i);
                if (canBuild(right, map, false)) return true;
            }
        }

        map.put(s, false);
        return false;
    }

    /**
     * Given a dictinary and a sentence design an alogo to unconcatenate teh document
     * in a way that minimize the number of unrecognized characters
     * @param dict
     * @param sentence
     * @return
     */
    private String bestSplit(HashSet<String> dict, String sentence){

        Result result = split(dict, sentence, 0, new HashMap<>());
        System.out.println("Result is " + result.result+" invalid strings are " + result.invalid);
        return result.result;
    }

    private Result split(HashSet<String> dict, String sentence, int index, HashMap<Integer, Result> map) {

        if(index>=sentence.length()) return new Result(0, "");

        if(map.containsKey(index)) return map.get(index);
        int max = Integer.MAX_VALUE;
        String splitString = null;
        String parsed = "";
        for(int i = index; i< sentence.length();i++){
            parsed = parsed+sentence.charAt(i);
            int invalid = dict.contains(parsed)?0:parsed.length();
            if(invalid<=max){
                Result result = split(dict, sentence, i+1, map);
                if(invalid+result.invalid<=max){
                    max = invalid+result.invalid;
                    splitString = parsed+" "+result.result;
                    if(max==0) break;
                }

            }
        }

        map.put(index, new Result(max, splitString));
        return map.get(index);
    }

    class Result {
        int invalid ;
        String result;

        Result(int invalid, String res){
            this.invalid = invalid;
            this.result = res;
        }
    }

    /**
     * https://leetcode.com/problems/palindromic-substrings/
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if(s==null || s.length()==0) return 0;
        int res[] = new int[1];
        for(int i = 0; i< s.length();i++){
            count(s,i,i,res);
            count(s,i,i+1,res);
        }
        return res[0];
    }

    public void count(String s, int i , int j , int[] res){

        while(i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            res[0]+=1;
            i--;
            j++;
        }
    }

    /**
     * https://leetcode.com/problems/partition-labels/
     *
     * @param str
     * @return
     */
    public List<Integer> partitionLabels(String str) {
        List<Integer> list = new ArrayList<>();
        if (str == null || str.length() == 0) return list;
        int[] arr = new int[26];
        int k = 0;
        for (char c : str.toCharArray()) {
            arr[c - 'a'] = k;
            k++;
        }
        for (int i = 0; i < str.length(); ) {
            char c = str.charAt(i);
            int indexLast = arr[c - 'a'];
            int j = partitionLabels(str, i, indexLast, arr);
            int val = j - i + 1;

            list.add(val);
            i = j + 1;
        }

        return list;
    }


    private int partitionLabels(String str, int start, int indexLast, int arr[]) {

        for (int i = start; i < indexLast; i++) {
            char c = str.charAt(i);
            int last = arr[c - 'a'];
            if (last > indexLast) indexLast = last;
        }

        return indexLast;
    }

    /**
     * https://leetcode.com/problems/edit-distance/
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {

        boolean done = false;
        int i = 0;
        int j = s.length() - 1;
        return valid(s, i, j, done);
    }

    public boolean valid(String s, int i, int j, boolean done) {
        if (i >= j) return true;

        boolean unEqual = s.charAt(i) != s.charAt(j);
        if (unEqual && done) return false;

        if (unEqual) {
            return valid(s, i + 1, j, true) || valid(s, i, j - 1, true);
        }
        return valid(s, i + 1, j - 1, done);
    }

    /**
     * https://leetcode.com/problems/word-search/
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean visited[][] = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            //if(board[i]==null || board[i].length==0) continue;
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, word, visited, i, j, 0)) return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/word-search-ii/
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList();
        boolean visited[][] = new boolean[board.length][board[0].length];
        for (String word : words) {
            boolean found = false;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == null || board[i].length == 0) continue;
                for (int j = 0; j < board[0].length; j++) {
                    if (exist(board, word, visited, i, j, 0)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) res.add(word);
        }
        Collections.sort(res);
        return res;


    }

    private boolean exist(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
        if (index >= word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (visited[i][j]) return false;
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;
        if (exist(board, word, visited, i + 1, j, index + 1)) return true;
        if (exist(board, word, visited, i - 1, j, index + 1)) return true;
        if (exist(board, word, visited, i, j + 1, index + 1)) return true;
        if (exist(board, word, visited, i, j - 1, index + 1)) return true;
        visited[i][j] = false;
        return false;
    }

    /**
     * https://leetcode.com/problems/longest-common-prefix/
     *
     * @param str
     * @return
     */
    public String longestCommonPrefix(String[] str) {
        if (str == null || str.length == 0) return "";
        if (str.length == 1) return str[0];

        Arrays.sort(str);

        int min = Math.min(str[0].length(), str[str.length - 1].length());

        int i = 0;
        if (str[0].isEmpty() || str[str.length - 1].isEmpty()) return "";
        while (i < min && str[0].charAt(i) == str[str.length - 1].charAt(i)) {
            i++;
        }
        return str[0].substring(0, i);
    }

    /**
     * Find next closest time using same digits
     * https://www.programcreek.com/2012/04/leetcode-next-closest-time-java/
     *
     * @param str
     * @return
     */
    public static String nextClosestTime(String str) {

        String arr[] = str.split(":");
        int hour = Integer.parseInt(arr[0]) * 60;
        int min = Integer.parseInt(arr[1]);
        int time = hour + min;
        HashSet<Integer> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c - '0');
        }

        System.out.println(time);
        int h1;
        int h2;
        int m1;
        int m2;
        while (true) {
            time = (time + 1) % (24 * 60);
            h1 = (time / 60) / 10;
            h2 = (time / 60) % 10;
            m1 = (time % 60) / 10;
            m2 = (time % 60) % 10;
            if (set.contains(h1) && set.contains(h2) && set.contains(m1) && set.contains(m2)) {
                break;
            }
        }

        String nextTime = "" + h1 + h2 + ":" + m1 + m2;

        return nextTime;
    }

    public static int numDecodingss(String s) {

        //10
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) {
            if (s.charAt(0) > '0') return 1;
            else return 0;
        }
        int x = s.charAt(0) == '0' ? 0 : 1;

        int y = s.charAt(1) == '0' ? 0 : 1;
        ;
        if (s.charAt(1) > '0' && x != 0) {
            y = 1;
        }
        int curr = 1;
        for (int i = 2; i <= s.length(); i++) {
            curr = 0;
            if (s.charAt(i - 1) > '0') {
                curr = y;
            }
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' &&
                    s.charAt(i - 1) < '7')) {
                curr += x;

            }
            x = y;
            y = curr;

        }
        return y;
    }

    /**
     * https://leetcode.com/problems/longest-valid-parentheses/
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        Stack<Integer> st = new Stack();
        st.push(-1);
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else {
                st.pop();
                if (!st.isEmpty())
                    res = Math.max(res, i - st.peek());
                else st.push(i);
            }
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/generate-parentheses/
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis("", 0, 0, n, res);
        return res;
    }


    private static void generateParenthesis(String str, int start, int end, int max, List<String> res) {
        if (str.length() >= max * 2) {
            res.add(str);
            return;
        }

        if (start < max) {
            generateParenthesis(str + "(", start + 1, end, max, res);
        }
        if (end < start) {
            generateParenthesis(str + ")", start, end + 1, max, res);
        }
    }


    /**
     * Problem https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons).
     *
     * @param nums
     * @return
     */
    private static List<String> findPossibleWords(String nums) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> list = new ArrayList<>();

        findPossibleWords(nums, 0, "", map, list);
        return list;
    }

    private static void findPossibleWords(String nums, int index, String word, Map<Character, String> map, List<String> list) {
        if (index >= nums.length()) {
            list.add(word);
            return;
        }

        String str = map.get(nums.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            char p = str.charAt(i);
            findPossibleWords(nums, index + 1, word + p, map, list);
        }

    }


    /**
     * generate all anagrams of a given string
     *
     * @param good
     */
    private static void generateAllPerm(String good) {
        char[] arr = good.toCharArray();
        Arrays.sort(arr);
        generateAllPerm(arr, new char[arr.length], 0, new boolean[arr.length]);
    }

    private static void generateAllPerm(char[] arr, char[] result, int index, boolean[] used) {

        if (index >= arr.length) {
            System.out.println(new String(result));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (used[i] || (i > 0 && arr[i] == arr[i - 1] && used[i - 1])) continue;
            result[index] = arr[i];
            used[i] = true;
            generateAllPerm(arr, result, index + 1, used);
            used[i] = false;
        }
    }


    /**
     * https://www.geeksforgeeks.org/print-all-interleavings-of-given-two-strings/
     *
     * @param str1
     * @param str2
     * @param interleave
     */
    private static void printInterLeave(String str1, String str2, String interleave) {
        if (str1 == null) {
            System.out.println(str2);
            return;
        }
        if (str2 == null) {
            System.out.println(str1);
            return;
        }

        if (str1.length() == 0 && str2.length() == 0) {
            System.out.println(interleave);
            return;
        }

        if (str1.length() != 0)
            printInterLeave(str1.substring(1), str2, interleave + str1.charAt(0));
        if (str2.length() != 0)
            printInterLeave(str1, str2.substring(1), interleave + str2.charAt(0));
    }


    /**
     * https://www.geeksforgeeks.org/edit-distance-dp-5/
     *
     * @param str1
     * @param str2
     * @param m
     * @param n
     * @return
     */
    static int editDistDP(String str1, String str2, int m, int n) {
        // Create a table to store results of subproblems
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to
                // insert all characters of second string
                if (i == 0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                    // If the last character is different, consider all
                    // possibilities and find the minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1],  // Insert
                            dp[i - 1][j],  // Remove
                            dp[i - 1][j - 1]); // Replace
            }
        }

        return dp[m][n];
    }

    private static int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    /**
     * generate all unique permutation of a string
     *
     * @param input
     * @param res
     */
    private static void printAllPerm(String input, String res, HashSet<Character> set) {
        if (input.length() == 0) {
            System.out.println(res);
            return;
        }

        set = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (set.contains(c)) continue;
            set.add(c);
            String prefix = input.substring(0, i);
            String suffix = input.substring(i + 1, input.length());
            printAllPerm(prefix + suffix, res + c, set);
        }
    }

    /**
     * https://www.geeksforgeeks.org/find-all-strings-that-match-specific-pattern-in-a-dictionary/
     *
     * @param dict
     * @param pattern
     * @return
     */
    public static ArrayList<String> findMatchedWords(ArrayList<String> dict, String pattern) {
        ArrayList<String> list = new ArrayList<String>();
        String patternCode = encodedWord(pattern);
        for (String str : dict) {
            if (patternCode.equals(encodedWord(str))) {
                list.add(str);
            }
        }
        return list;
    }

    private static String encodedWord(String pattern) {
        if (pattern == null) return "";
        int i = 0;
        StringBuilder builder = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int k = 0; k < pattern.length(); k++) {
            if (!map.containsKey(pattern.charAt(k))) {
                map.put(pattern.charAt(k), i);
                i++;
            }
            builder = builder.append(map.get(pattern.charAt(k)));
        }

        return builder.toString();
    }

    /**
     * https://leetcode.com/articles/longest-substring-with-at-most-two-distinct-charac/
     *
     * @param str
     * @return
     */
    public static String longestSubStringWith2Char(String str) {

        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        String res = "";
        while (right < str.length()) {
            if (map.size() < 3) {
                map.put(str.charAt(right), right);
            }
            if (map.size() == 3) {
                int min = Collections.min(map.values());
                map.remove(str.charAt(min));
                left = min + 1;
            }
            int max = Collections.max(map.values());
            if (res.length() < max + 1 - left) {
                res = str.substring(left, max + 1);
            }
            right++;
        }

        return res;
    }

    // Function to count special
// Palindromic susbstring
    public static int CountSpecialPalindrome(String str) {
        int n = str.length();

        // store count of special
        // Palindromic substring
        int result = 0;

        // it will store the count
        // of continues same char
        int[] sameChar = new int[n];
        for (int v = 0; v < n; v++)
            sameChar[v] = 0;

        int i = 0;

        // traverse string character
        // from left to right
        while (i < n) {

            // store same character count
            int sameCharCount = 1;

            int j = i + 1;

            // count smiler character
            while (j < n &&
                    str.charAt(i) == str.charAt(j)) {
                sameCharCount++;
                j++;
            }

            // Case : 1
            // so total number of
            // substring that we can
            // generate are : K *( K + 1 ) / 2
            // here K is sameCharCount
            result += (sameCharCount *
                    (sameCharCount + 1) / 2);

            // store current same char
            // count in sameChar[] array
            sameChar[i] = sameCharCount;

            // increment i
            i = j;
        }

        // Case 2: Count all odd length
        //           Special Palindromic
        //           substring
        for (int j = 1; j < n; j++) {
            // if current character is
            // equal to previous one
            // then we assign Previous
            // same character count to
            // current one
            if (str.charAt(j) == str.charAt(j - 1))
                sameChar[j] = sameChar[j - 1];

            // case 2: odd length
            if (j > 0 && j < (n - 1) &&
                    (str.charAt(j - 1) == str.charAt(j + 1) &&
                            str.charAt(j) != str.charAt(j - 1)))
                result += Math.min(sameChar[j - 1],
                        sameChar[j + 1]);
        }

        // subtract all single
        // length substring
        return result - n;
    }


    /**
     * Reverse each word of String separated by space
     * for ex if string is "How are you" result should be "woh era uoy"
     *
     * @param str input String
     * @return reverse String
     */
    static String reverseAllWords(String str) {
        if (str == null || str.trim().length() == 0) return str;
        String[] split = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (split[i].trim().length() == 0) continue;
            builder.append(reverseString(split[i])).append(" ");
        }
        return builder.toString().trim();
    }

    private static String reverseString(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
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


    /**
     * reverse a list of words ex if given word is "I am Geek" expected o/p is "Geek am I"
     *
     * @param a
     * @return
     */
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


    /**
     * Text Edit - > https://www.hackerrank.com/challenges/simple-text-editor/problem
     */
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


    /**
     * https://www.geeksforgeeks.org/word-break-problem-dp-32/
     *
     * @param str
     * @param dict
     * @param map
     * @return
     */
    private static String wordBreakProblems(String str, Set<String> dict, HashMap<String, String> map) {
        if (dict.contains(str)) return str;
        if (map.containsKey(str)) return map.get(str);
        //catsanddog
        for (int i = 1; i < str.length(); i++) {
            String prefix = str.substring(0, i);
            if (dict.contains(prefix)) {
                String suffix = wordBreakProblems(str.substring(i, str.length()), dict, map);
                if (suffix != null) {
                    String s = prefix + " " + suffix;
                    map.put(str, s);
                    return s;
                }
            }
        }
        return null;
    }


    private static String removeWhiteSpace(String str) {
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
package com.programs.problems;


import com.programs.stack.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StringProblems {

    /**
     * // System.out.println(dp.editDistDP("horse", "ros"));
     * BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     * int test = Integer.parseInt(reader.readLine());
     * for(int i = 0 ; i<test;i++){
     * int k = Integer.parseInt(reader.readLine());
     * String str = reader.readLine();
     * String [] ar = str.split(",");
     * int arr[] = new int[ar.length];
     * for(int m = 0; m < arr.length;m++){
     * arr[m] = Integer.parseInt(ar[m].trim());
     * }
     * System.out.println(dp.longestZigZag(arr));
     * }
     * reader.close();
     */

    public static void main(String[] args) throws IOException {

        StringProblems sp = new StringProblems();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(reader.readLine());
        for (int i = 0; i < test; i++) {
            String str = reader.readLine();
            int k = Integer.parseInt(reader.readLine());
            System.out.println(sp.kUniqueLength(str, k));
        }
        reader.close();
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * Leetcode 5
     * Alternate solution [longestPalindrome2]
     *
     * @param str
     * @return
     */
    public String longestPalindrome(String str) {
        if (str == null || str.length() <= 1) return str;
        boolean dp[][] = new boolean[str.length()][str.length()];
        int length = str.length();
        int len = 0;
        int start = 0;
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                dp[i][j] = str.charAt(i) == str.charAt(j) && (j - i <= 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > len) {
                    len = j - i + 1;
                    start = i;
                }
            }
        }
        return str.substring(start, start + len);
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * Leetcode 5
     * Time complexity (N^2) as we have nested for loop, in practical situation this wont happen since loop will break
     * when character doesnt match
     *
     * @param str
     * @return
     */
    public String longestPalindrome2(String str) {
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
     * https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
     * e keep adding count of each character in hashmap, if at any point map.size becomes more than
     * k then we start popping element from start. until size is greater
     *
     * @param str
     * @param k
     * @return
     */
    private int kUniqueLengthWithMap(String str, int k) {
        if (str == null || str.isEmpty() || k == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 1;

        for (int index = 0; index < str.length(); index++) {
            Character character = str.charAt(index);
            map.put(character, map.getOrDefault(character, 0) + 1);
            if (map.size() > k) {
                while (map.size() > k) {
                    character = str.charAt(start);
                    map.put(character, map.get(character) - 1);
                    if (map.get(character) == 0) {
                        map.remove(character);
                    }
                    start++;
                }
            }
            max = Math.max(max, index - start + 1);

        }
        return max;
    }


    /**
     * https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
     * we keep adding count of each character in count array and anytime new character added we increase out count of
     * unique character, if at any point unique character count  becomes more than k then we start popping element from
     * start. until unqieue character count becomes equal to k
     *
     * @param str
     * @param k
     * @return
     */
    private int kUniqueLength(String str, int k) {
        if (str == null || str.isEmpty() || k == 0) return 0;
        int[] charCount = new int[26];
        int count = 0;
        int start = 0;
        int max = -1;

        for (int index = 0; index < str.length(); index++) {
            char character = str.charAt(index);
            int c = charCount[character - 'a'];
            if (c == 0) {
                count++;
            }
            charCount[character - 'a'] = c + 1;
            while (count > k) {
                int pos = str.charAt(start) - 'a';
                c = charCount[pos];
                charCount[pos] = c - 1;
                if (c == 1) {
                    count--;
                }
                start++;
            }
            if (count == k) {
                max = Math.max(max, index - start + 1);
            }

        }
        return max;
    }

    /**
     * https://leetcode.com/problems/repeated-substring-pattern/
     * Another solution: https://leetcode.com/problems/repeated-substring-pattern/discuss/541860/One-liner-with-explaination
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {

        int length = s.length();
        if (length < 2) return false;
        for (int i = (length / 2); i > 0; i--) {
            StringBuilder str = new StringBuilder(s.substring(0, i));
            StringBuilder temp = new StringBuilder(str);
            while (str.length() < s.length()) {
                str.append(temp);
            }
            if (str.toString().equals(s)) {
                return true;
            }
        }

        /*String ss = s+s;
        int idx = ss.indexOf(s, 1);
        return idx<s.length();*/
        return false;
    }

    class Pair {
        int distance;
        String word;

        public Pair(int distance, String word) {
            this.distance = distance;
            this.word = word;
        }
    }

    /**
     * https://leetcode.com/problems/word-ladder/
     * Bidirectional - BFS, Idea is to do two way search
     * we add beginWord to its que and end word to its own que.
     * We maintain map for each traversal which contain each word's distance from source, We do BFS.
     * if while traversing a word is already found in another map ie it is already traversed then we return
     * sum of current traversal and already traversed path
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // boolean fFound = true;
        boolean found = false;
        for (String word : wordList) {
            // if (word.equals(beginWord)) fFound = true;
            if (word.equals(endWord)) found = true;
            if (found) break;
        }
        if (!found) return 0;
        Queue<Pair> que = new LinkedList<>();
        que.offer(new Pair(1, beginWord));
        Map<String, Integer> map = new HashMap();
        map.put(beginWord, 1);
        Queue<Pair> otherQ = new LinkedList<>();
        otherQ.offer(new Pair(1, endWord));
        Map<String, Integer> otherMap = new HashMap();

        while (!que.isEmpty() && !otherQ.isEmpty()) {
            int v = check(que, wordList, map, otherMap, endWord);
            if (v != -1) return v;
            int k = check(otherQ, wordList, otherMap, map, beginWord);
            if (k != -1) return k;
        }

        return 0;
    }

    int check(Queue<Pair> que, List<String> wordList, Map<String, Integer> map, Map<String, Integer> otherMap,
              String target) {
        Pair pair = que.poll();
        for (String word : wordList) {
            if (!map.containsKey(word) && isOneDistanceAway(pair.word, word)) {
                if (word.equals(target)) return pair.distance + 1;
                que.offer(new Pair(pair.distance + 1, word));
                map.put(word, pair.distance + 1);

                if (otherMap.containsKey(word)) {
                    return pair.distance + otherMap.get(word);
                }
            }
        }
        return -1;
    }

    boolean isOneDistanceAway(String first, String second) {
        int count = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) count++;
            if (count > 1) return false;
        }

        return count == 1;
    }

    public ArrayList<String> topNCompetitors(int numCompetitors,
                                             int topNCompetitors,
                                             List<String> competitors,
                                             int numReviews,
                                             List<String> reviews) {

        // map to keep count of each competitor
        HashMap<String, Integer> countMap = new HashMap<>();
        HashSet<String> uniqueWords;
        for (int i = 0; i < reviews.size(); i++) {
            uniqueWords = getUniqueWordsFromReview(reviews.get(i).toLowerCase(), competitors);
            updateCountMap(countMap, uniqueWords);
        }

        return getTopNCompetitors(getSortedValue(countMap.entrySet()), topNCompetitors);
    }

    /**
     * Given a sorted entry set return topNCompetitors
     * if [topNCompetitors] is less than size of entry then all item will be returned.
     *
     * @return
     */
    private ArrayList<String> getTopNCompetitors(List<Map.Entry<String, Integer>> sortedEntry, int topNCompetitors) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = sortedEntry.size() - 1; i >= 0; i--) {
            if (topNCompetitors < 1) {
                break;
            }
            result.add(sortedEntry.get(i).getKey());
            topNCompetitors--;
        }
        return result;
    }

    private List<Map.Entry<String, Integer>> getSortedValue(Set<Map.Entry<String, Integer>> entrySet) {
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int e1Count = e1.getValue();
                int e2Count = e2.getValue();
                if (e1Count != e2Count) {
                    return e1Count - e2Count;
                } else {
                    return e2.getKey().compareTo(e1.getKey());
                }
            }
        };

        List<Map.Entry<String, Integer>> list = new ArrayList<>(entrySet);
        Collections.sort(list, comparator);
        return list;
    }


    /**
     * Given a set of words update it count in hashmap
     */
    private void updateCountMap(HashMap<String, Integer> countMap, HashSet<String> uniqueWords) {
        for (String word : uniqueWords) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
    }

    /**
     * For each review split it into separate words then check if its matching a competitor title,
     * if yes add it to set, using hash set cause we do n0t want to count only once if a title is present twice in a review
     */
    private HashSet<String> getUniqueWordsFromReview(String review, List<String> competitors) {
        HashSet<String> uniqueWords = new HashSet<>();
        for (String competitor : competitors) {
            competitor = competitor.toLowerCase();
            if (review.contains(competitor)) {
                uniqueWords.add(competitor);
            }
        }
        return uniqueWords;
    }

    /**
     * https://www.hackerrank.com/challenges/palindrome-index/problem
     *
     * @param s
     * @return
     */
    int palindromeIndex(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                int index = j;
                if (isPalin(s, i, j - 1)) {
                    return j;
                } else if (isPalin(s, i + 1, j)) {
                    return i;
                } else {
                    return -1;
                }
            }
            i++;
            j--;
        }
        return -1;
    }

    private static boolean isPalin(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }


    /**
     * Given two string check any permutation of string one can break any permutation of string 2 or vice versa
     *
     * @param one
     * @param two
     * @return
     */
    private boolean canBreak(String one, String two) {
        return canBreak(populate(one), populate(two)) || canBreak(populate(two), populate(one));
    }

    private boolean canBreak(int[] first, int[] sec) {

        int i = first.length - 1;
        int j = sec.length - 1;
        while (i >= 0 && j >= 0) {

            if (sec[j] == 0) {
                j--;
                continue;
            }

            if (first[i] == 0) {
                i--;
                continue;
            }

            if (i < j) {
                return false;
            }
            int fc = first[i];
            int sc = sec[j];
            if (fc < sc) {
                sc = sc - fc;
                fc = 0;
            } else {
                fc = fc - sc;
                sc = 0;
            }

            first[i] = fc;
            sec[j] = sc;
            if (fc == 0) i--;
            if (sc == 0) j--;
        }

        if (j < 0) return true;

        return false;
    }

    int[] populate(String str) {

        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'a'] += 1;
        }
        return arr;
    }

    /**
     * generate all unique permutation of a string
     *
     * @param input
     * @param res
     */
    private static boolean printPermt(String input, String res, HashSet<Character> set, String other) {
        if (input.length() == 0) {
            if (isGreater(res, other)) {
                return true;
            }
            return false;
        }

        set = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (set.contains(c)) continue;
            set.add(c);
            String prefix = input.substring(0, i);
            String suffix = input.substring(i + 1, input.length());
            if (printPermt(prefix + suffix, res + c, set, other)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isGreater(String res, String other) {
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) < other.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3299/
     *
     * @param s
     * @param shifts
     * @return
     */
    public String stringShift(String s, int[][] shifts) {

        for (int i = 0; i < shifts.length; i++) {
            int dir = shifts[i][0];
            int move = shifts[i][1];
            String sub;
            String pre;
            if (dir == 1) {
                sub = s.substring(0, s.length() - move);
                pre = s.substring(s.length() - move);
            } else {
                sub = s.substring(0, move);
                pre = s.substring(move);
            }
            s = pre + sub;
        }
        return s;
    }

    /**
     * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
     *
     * @param arr
     * @return
     */
    public int maxLength(List<String> arr) {
        int max[] = new int[1];
        max[0] = arr.get(0).length();
        maxLength(arr, max, 0, "");
        return max[0];
    }

    private void maxLength(List<String> arr, int[] max, int index, String str) {
        for (int i = index; i < arr.size(); i++) {
            if (isUnique(str, arr.get(i))) {
                max[0] = Math.max(max[0], str.length() + arr.get(i).length());
                maxLength(arr, max, i + 1, str + arr.get(i));
            }
        }
    }

    private boolean isUnique(String first, String second) {
        int[] total = new int[26];
        int i = 0;
        int j = 0;
        while (i < first.length() || j < second.length()) {
            if (i < first.length()) {
                total[first.charAt(i) - 'a'] += 1;
                if (total[first.charAt(i) - 'a'] > 1) return false;
            }
            if (j < second.length()) {
                total[second.charAt(j) - 'a'] += 1;
                if (total[second.charAt(j) - 'a'] > 1) return false;
            }
            i++;
            j++;
        }

        return true;
    }

    public List<String> parenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            res.add("");
            return res;
        }
        generateParenthesis(new char[n * 2], res, n, n, 0);
        return res;
    }

    /**
     * For non optimized code check below method
     * This method is a recursive approach
     *
     * @param arr
     * @param res
     * @param opening
     * @param closure
     * @param pos
     */
    private void generateParenthesis(char[] arr, List<String> res, int opening, int closure, int pos) {
        if (closure < 1) { //
            res.add(new String(arr));
            return;
        }
        if (opening == closure) { // if opening and closure size is same it means its already balanced so we
            // can not insert closing brace, we can only insert an opening brace
            arr[pos] = '(';
            generateParenthesis(arr, res, opening - 1, closure, pos + 1);
        } else if (opening > 0) { // if opening braces still left to insert we can insert either opening or
            // close brace at current position
            arr[pos] = '(';
            generateParenthesis(arr, res, opening - 1, closure, pos + 1);
            arr[pos] = ')';
            generateParenthesis(arr, res, opening, closure - 1, pos + 1);
        } else { // if no opening brace left we can only insert closure brace
            arr[pos] = ')';
            generateParenthesis(arr, res, opening, closure - 1, pos + 1);
        }
    }

    /**
     * https://leetcode.com/problems/generate-parentheses/
     * Always insert a balanced parenthesis () at the start and after every occurence of opening brack ie '('
     * For optimized version check above method
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            res.add("");
            return res;
        }
        HashSet<String> result = new HashSet<>();
        String str = "()";
        result.add(str);
        n--;
        HashSet<String> temp = new HashSet<>();
        while (n > 0) {
            temp.addAll(result);
            result.clear();
            for (String brace : temp) {
                result.add(str + brace);
                for (int i = 0; i < brace.length(); i++) {
                    if (brace.charAt(i) == '(') {
                        String pre = brace.substring(0, i + 1) + str + brace.substring(i + 1);
                        result.add(pre);
                    }
                }
            }
            temp.clear();
            n--;
        }
        res.addAll(result);
        return res;
    }

    /**
     * https://leetcode.com/problems/backspace-string-compare/
     *
     * @param first
     * @param second
     * @return
     */
    public boolean backspaceCompare(String first, String second) {
        String fSb = getStringUsingStringBuilder(first);
        String sSb = getStringUsingStringBuilder(second);
        System.out.println("first is " + fSb + " second is " + sSb);
        return fSb.equals(sSb);
    }

    /**
     * Using string builder. For stack implementation check [getStringUsingStack]
     *
     * @param first
     * @return
     */
    private String getStringUsingStringBuilder(String first) {
        if (first == null || first.isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != '#') {
                builder.append(first.charAt(i));
            } else {
                if (builder.length() > 0) {
                    builder = builder.deleteCharAt(builder.length() - 1);
                }
            }
        }
        return builder.toString();
    }

    /**
     * Using string builder. For stack implementation check [getStringUsingStringBuilder]
     *
     * @param first
     * @return
     */
    private String getStringUsingStack(String first) {
        if (first == null || first.isEmpty()) return "";
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != '#') {
                st.push(first.charAt(i));
            } else {
                if (!st.isEmpty()) {
                    st.pop();
                }
            }
        }
        return String.valueOf(st);
    }

    /**
     * https://leetcode.com/problems/word-pattern-ii/
     *
     * @param word
     * @param pattern
     * @return
     */
    boolean patternMatch(String word, String pattern) {

        return patternMatch(word, pattern, 0, new HashMap<Character, String>(), new HashSet<>());
    }

    private boolean patternMatch(String word,
                                 String pattern, int index,
                                 HashMap<Character, String> map,
                                 HashSet<String> set) {

        if (index >= pattern.length()) {
            return word.length() == 0;
        }
        Character c = pattern.charAt(index);
        if (map.containsKey(c)) {
            String val = map.get(c);
            if (val.length() > word.length()) return false;
            if (word.startsWith(val)) {
                return patternMatch(word.substring(val.length()), pattern, index + 1, map, set);
            } else {
                return false;
            }
        }

        for (int i = 1; i <= word.length(); i++) {
            String pre = word.substring(0, i);
            if (set.contains(pre)) continue;
            map.put(c, pre);
            set.add(pre);
            if (patternMatch(word.substring(i), pattern, index + 1, map, set)) {
                return true;
            } else {
                map.remove(c);
                set.remove(pre);
            }
        }

        return false;

    }


    /**
     * https://leetcode.com/problems/verifying-an-alien-dictionary/
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {

            if (isFirstBigger(words[i], words[i + 1], map)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFirstBigger(String first, String second, HashMap<Character, Integer> map) {
        int i = 0;
        int j = 0;
        while (i < first.length() && j < second.length()) {
            int f = map.get(first.charAt(i));
            int s = map.get(second.charAt(j));
            if (f > s) {
                return true;
            }
            // continue checking further characters only if current chars are same.
            if (f != s) {
                return false;
            }
            i++;
            j++;
        }
        //if second is subset of first but first is bigger in size then its not sorted
        if (i < first.length()) {
            return true;
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/add-strings/submissions/
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {

        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        StringBuilder str = new StringBuilder();
        int carry = 0;
        int sum = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                sum = sum + (num1.charAt(i) - '0');
            }
            if (j >= 0) {
                sum = sum + (num2.charAt(j) - '0');
            }
            sum = sum + carry;
            carry = sum / 10;
            str.append(sum % 10);
            sum = 0;
            i--;
            j--;
        }
        if (carry > 0) {
            str.append(carry);
        }
        str.reverse();
        return str.toString();
    }

    /**
     * https://leetcode.com/problems/split-a-string-in-balanced-strings/
     *
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {
        if (s == null || s.length() == 0) return 0;
        int lc = 0;
        int rc = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                rc++;
            } else if (s.charAt(i) == 'L') {

                lc++;
            }
            if (lc == rc) {

                res++;
                lc = 0;
                rc = 0;
            }
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/reverse-only-letters/
     *
     * @param S
     * @return reversed string
     */
    public String reverseOnlyLetters(String S) {

        char[] arr = reverse(S.toCharArray());
        return new String(arr);
    }

    char[] reverse(char[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (!isChar(arr[i])) {
                i++;
            } else if (!isChar(arr[j])) {
                j--;
            } else {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return arr;
    }

    boolean isChar(char c) {

        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * Given a string which might contain Invalid parenthesis. Return a string which has balanced parenthesis.
     * Parenthesis can only be removed
     * ie remove invalid parenthesis. If there are multiple solution return any balanced solution.
     * For ex if given string is (v)())()
     * return either (v)()() or  (v())()
     * Note: parenthesis can only be removed
     * Problem is similar to https://www.geeksforgeeks.org/remove-invalid-parentheses/
     *
     * @param str
     * @return
     */
    public String removeInvalidParenthesis(String str) {
        if (str == null || str.length() == 0) return str;

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isParenthesis(c)) continue;

            if (st.isEmpty() || c == '(') st.push(i);
            else {
                char temp = str.charAt(st.peek());
                if (temp == '(') st.pop();
                else {
                    st.push(i);
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        while (!st.isEmpty()) {
            set.add(st.pop());
        }

        char arr[] = new char[str.length() - set.size()];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(i)) continue;
            arr[j] = str.charAt(i);
            j++;
        }

        return new String(arr);
    }

    private boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }

    /**
     * https://leetcode.com/problems/is-subsequence/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() > t.length()) return false;
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == s.length();
    }

    /**
     * https://leetcode.com/problems/is-subsequence/
     * If there t is final but and method can be called mutiple times we can preprocess and
     * keep all occurrence of each character is sorted order. Then we iterate over s and check whether each character's occurance is after previous character's occurance or not
     * // For this example we will preprocess t. Eg-2. s="abc", t="bahgdcbc"
     * // list[] =[ a={1}, b={0,6}, c={5}] t will look like this
     * // prev = -1
     * //  i=0 ('a'):  binary search a in [a{1}] prev=1
     * //  i=1 ('b'): binary search b in [b{0,6}] but we should not consider 0 as a was present at 1. so prev=6
     * //  i=2 ('c'): prev=? (return false)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence_followUp(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() > t.length()) return false;
        List<Integer>[] list = new ArrayList[28];
        //preprocess the t and store each character and their indexes in sorted order
        for (int i = 0; i < t.length(); i++) {
            char key = t.charAt(i);
            if (list[key - 'a'] == null) {
                list[key - 'a'] = new ArrayList<>();
            }
            list[key - 'a'].add(i);
        }

        int prev = -1;

        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a';
            if (list[pos] == null) return false;
            int k = binarySearchItem(list[pos], prev + 1);
            if (k <= prev || k >= t.length()) return false;
            prev = k;
        }

        return true;

    }

    //{0,2,5,6,8}
    // 0 1 2 3 4
    private int binarySearchItem(List<Integer> list, int key) {

        int i = 0;//1
        int j = list.size() - 1;//1
        while (i <= j) {
            int mid = (i + j) / 2;//1
            if ((list.get(mid) == key || list.get(mid) > key) && (mid == 0 || list.get(mid - 1) < key)) {
                return list.get(mid);
            } else if (list.get(mid) > key) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return -1;
    }

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

    /**
     * https://leetcode.com/problems/find-and-replace-in-string/
     *
     * @param str
     * @param index
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString(String str, int[] index, String[] sources, String[] targets) {
        if (index == null || index.length == 0 || str == null) return str;
        Replacement[] repl = new Replacement[index.length];

        for (int i = 0; i < index.length; i++) {
            repl[i] = new Replacement(index[i], sources[i], targets[i]);
        }
        Arrays.sort(repl, new Comparator<Replacement>() {
            @Override
            public int compare(Replacement o1, Replacement o2) {
                return o1.index - o2.index;
            }
        });
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = str.length();
        for (Replacement r : repl) {
            if (!match(str, r)) continue;
            sb.append(str.substring(i, r.index));
            sb.append(r.after);
            i = r.index + r.before.length();
        }

        sb.append(str.substring(i));

        return sb.toString();

    }

    boolean match(String str, Replacement repl) {
        int index = repl.index;
        int j = 0;
        for (int i = index; i < index + repl.before.length() && i < str.length(); i++) {
            if (str.charAt(i) != repl.before.charAt(j)) return false;
            j++;
        }
        if (j < repl.before.length()) return false;
        return true;
    }

    class Replacement {
        int index;
        String before;
        String after;

        Replacement(int i, String s1, String s2) {
            index = i;
            before = s1;
            after = s2;
        }

    }

    /**
     * https://leetcode.com/problems/word-break/
     * Basically what we do here is we iterate over the string. we find all the substring and check if dictionary contains the word or not
     * if yes then we process further
     * for ex if string is applepenapple and dict is {apple,pen}
     * we iterate over applepenapple and find all substring using two pointer approach
     * first we check if a contains in dictionary if yes we set dp[1] = true or false
     * in our example we come to i =4 when we have first valid word if this is then we set dp[4] = true
     * no other substring in apple is paert of dictionary so all index will be false;
     * similarly when we reach applepen i = 9  we start with applepen and keep removing one letter from start untill the dict cotains the word
     * when dictionary contains word we check if the substring 0-j is also a valid word or not. This can be found by accessing dp[j]
     * In this when we are at applepen we check pen is word then we also validate if apple is word or not.
     * in the end if whole string is processed dp[n] should be true.
     *
     * @param s
     * @param dict
     * @return
     */
    public boolean wordBreak_dp(String s, List<String> dict) {

        if (s == null || s.length() == 0) return false;

        int n = s.length();
        boolean dp[] = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    /**
     * https://leetcode.com/problems/word-break/
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>();
        set.addAll(wordDict);
        return wordBreakProblems(s, set, new HashMap<String, String>()) != null;
    }

    /**
     * This is a recursive approach where we check every substring of a string. There are 2 possibilities
     * 1. subString is a valid word
     * 2. subString is not a valid word
     * while doing this Also there might be chances where we are calculating already evaluated string.
     * In such case we keep a map for any such string and their corresponding value(list of word from dictionary)
     * ie we are caching the result evaluated so far
     * <p>
     * Similarly there are chances where we are evaluating a already processed string for which there was no result.
     * We cache this also in map with value as null. which mean for such there are no possible break ups.
     * So each time before processing any string we just check if our map(which contains valid break up) contains a string if so we return its value
     *
     * @param str  String to process
     * @param dict list of valid words
     * @param map  contains list of string and there break up words
     * @return null if string can not be split into valid dictionary words
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
        map.put(str, null);
        return null;
    }

    /**
     * https://leetcode.com/problems/palindrome-partitioning/
     * https://www.geeksforgeeks.org/given-a-string-print-all-possible-palindromic-partition/
     *
     * @param str
     * @return
     */
    List<List<String>> allPartition(String str) {

        List<List<String>> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        partitionString(str.toLowerCase(), 0, res, curr);
        return res;
    }

    /**
     * Idea is to start iterating string from 1st character and check if its palindrome , if it is then get list of palindrome
     * from remaining substring, For ex "nitin" n is palindrome so check recursively for itin. itin should return {{i,t,i,n}, {iti,n}}
     *
     * @param str
     * @param start
     * @param res
     * @param curr
     */
    private void partitionString(String str, int start, List<List<String>> res, List<String> curr) {
        if (start >= str.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < str.length(); i++) {

            if (isPalindrome(str, start, i)) {
                curr.add(str.substring(start, i + 1));
                partitionString(str.substring(i + 1), 0, res, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, int start, int end) {

        if (start < 0 || end < 0 || start >= str.length() || end >= str.length()) return false;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    /**
     * https://www.geeksforgeeks.org/python-sort-list-of-dates-given-as-strings/
     *
     * @param dates
     * @return
     */
    public static List<String> sortDates(List<String> dates) {

        final HashMap<String, Integer> map = new HashMap<>();
        map.put("Jan", 1);
        map.put("Feb", 2);
        map.put("Mar", 3);
        map.put("Apr", 4);
        map.put("May", 5);
        map.put("Jun", 6);
        map.put("Jul", 7);
        map.put("Aug", 8);
        map.put("Sep", 9);
        map.put("Oct", 10);
        map.put("Nov", 11);
        map.put("Dec", 12);
        Collections.sort(dates, (o1, o2) -> {
            String str1 = o1.substring(7);
            String str2 = o2.substring(7);
            int k = str1.compareTo(str2);
            if (k != 0) {
                return k;
            }

            String month_f = o1.substring(3, 6);
            String month_s = o2.substring(3, 6);
            Integer ma = map.get(month_f);
            Integer ms = map.get(month_s);
            k = ma.compareTo(ms);
            if (k != 0) return k;
            String day1 = o1.substring(0, 2);
            String day2 = o2.substring(0, 2);

            return day1.compareTo(day2);


        });

        return dates;
    }

    /**
     * https://leetcode.com/problems/repeated-dna-sequences/
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() <= 9) return list;
        HashSet<String> seen = new HashSet<>();
        HashSet<String> repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String suffix = s.substring(i, i + 10);
            if (!seen.add(suffix)) {
                repeated.add(suffix);
            }
        }
        return new ArrayList<>(repeated);
    }

    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramss(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        if (p.length() > s.length()) {
            return result;
        }

        int[] chars = new int[26];
        for (char c : p.toCharArray()) {
            chars[c - 'a']++;
        }
        int diff = p.length();
        for (int i = 0; i < p.length(); i++) {

            char key = s.charAt(i);
            chars[key - 'a']--;
            //If it's still >= 0, the anagram still "needed" it so we count it towards the anagram by
            //decrementing diff
            if (chars[key - 'a'] >= 0) diff--;
        }

        //This would mean that s began with an anagram of p
        if (diff == 0) result.add(0);

        int start = 0;
        int end = p.length();
        //At this point, we keep start  at 0, end has moved so that the window is the length of the anagram
        //from this point on we are going to be moving start AND end on each iteration, to shift the window
        //along the string
        while (end < s.length()) {

            //Temp represents the current first character of the window. The character that is
            //going to be "left behind" as the window moves.
            char temp = s.charAt(start);
            //If it's not negative, this means that the character WAS part of the anagram. That means we
            //are one step "farther away" from completing an anagram. So we must increment diff.
            if (chars[temp - 'a'] >= 0) {
                diff++;
            }
            //Increment the count for this character, because it is no longer contained in the window
            chars[temp - 'a']++;
            start++;
            //Temp represents the last character of the window, the "new" character from the window shift.
            //This character "replaces" the one we removed before so the window stays the same length (p.length())
            temp = s.charAt(end);
            //Decrement count for this character, because it is now a part of the window
            chars[temp - 'a']--;
            //Again, if it's not negative it is part of the anagram. So decrement diff
            if (chars[temp - 'a'] >= 0) diff--;
            //If diff has reached zero, that means for the last p.length() iterations, diff was decremented and
            //NOT decremented, which means every one of those characters was in the anagram, so it must be an anagram

            //Note: If many windows in a row find anagrams, then each iteration will have diff incremented then decremented again
            if (diff == 0) result.add(start);
            end++;
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
     * s: "cbaebabacd" p: "abc"
     * <p>
     * Output:
     * [0, 6]
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || s.length() < p.length()) return list;
        int start = 0;
        int index = p.length() - 1;
        HashMap<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {

            if (pMap.containsKey(c)) {
                pMap.put(c, pMap.get(c) + 1);
            } else {
                pMap.put(c, 1);
            }
        }
        while (index < s.length()) {
            boolean res = isAnagram(s, start, index, pMap);
            if (res) list.add(start);
            start++;
            index++;
        }

        return list;
    }

    private boolean isAnagram(String s, int start, int index, HashMap<Character, Integer> pMap) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= index; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            int cVal = pMap.getOrDefault(key, -1);
            if (val != cVal) return false;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/reverse-vowels-of-a-string/
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) return s;
        char arr[] = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {

            if (isVowel(arr[i]) && isVowel(arr[j])) {
                swap(arr, i, j);
                i++;
                j--;
            } else if (!isVowel(arr[i]) && !isVowel(arr[j])) {
                i++;
                j--;
            } else if (!isVowel(arr[i])) {
                i++;
            } else if (!isVowel(arr[j])) {
                j--;
            }
        }

        return new String(arr);
    }

    boolean isVowel(char c) {

        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    void swap(char[] arr, int i, int j) {

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * https://practice.geeksforgeeks.org/problems/rearrange-a-string/0
     *
     * @param str
     * @return
     */
    private String reArrangeString(String str) {
        if (str == null || str.length() == 0) return str;
        int[] arr = new int[25];
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                count += Character.getNumericValue(c);
            } else {
                arr[c - 'A'] += 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {

            int k = arr[i];
            char c = (char) ('A' + i);
            while (k > 0) {
                sb.append(c);
                k--;
            }

        }
        if (count > 0) {
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
     *
     * @param dict
     * @param sentence
     * @return
     */
    private String bestSplit(HashSet<String> dict, String sentence) {

        Result result = split(dict, sentence, 0, new HashMap<>());
        System.out.println("Result is " + result.result + " invalid strings are " + result.invalid);
        return result.result;
    }

    private Result split(HashSet<String> dict, String sentence, int index, HashMap<Integer, Result> map) {

        if (index >= sentence.length()) return new Result(0, "");

        if (map.containsKey(index)) return map.get(index);
        int max = Integer.MAX_VALUE;
        String splitString = null;
        String parsed = "";
        for (int i = index; i < sentence.length(); i++) {
            parsed = parsed + sentence.charAt(i);
            int invalid = dict.contains(parsed) ? 0 : parsed.length();
            if (invalid <= max) {
                Result result = split(dict, sentence, i + 1, map);
                if (invalid + result.invalid <= max) {
                    max = invalid + result.invalid;
                    splitString = parsed + " " + result.result;
                    if (max == 0) break;
                }

            }
        }

        map.put(index, new Result(max, splitString));
        return map.get(index);
    }

    class Result {
        int invalid;
        String result;

        Result(int invalid, String res) {
            this.invalid = invalid;
            this.result = res;
        }
    }

    /**
     * https://leetcode.com/problems/palindromic-substrings/
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int res[] = new int[1];
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i, res);
            count(s, i, i + 1, res);
        }
        return res[0];
    }

    public void count(String s, int i, int j, int[] res) {

        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            res[0] += 1;
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
     * https://leetcode.com/problems/group-anagrams/
     */
    public List<List<String>> groupAnagram(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String encodedString = encode(str);
            List<String> list = map.getOrDefault(encodedString, new ArrayList<>());
            list.add(str);
            map.put(encodedString, list);
        }
        return new ArrayList(map.values());
    }

    /**
     * Only for small case character
     *
     * @param str
     * @return
     */
    private String encode(String str) {
        if (str == null || str.isEmpty()) return "";
        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append("$");
        }

        return sb.toString();
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
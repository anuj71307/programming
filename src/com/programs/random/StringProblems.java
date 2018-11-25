package com.programs.random;

import com.programs.stack.Stack;

public class StringProblems {

    public static void main(String[] args) {

        System.out.println(longestBalancedParenthesis("()((((()))))"));
    }

    public static int longestBalancedParenthesis(String str){
        int length = 0;

        Stack<Integer> stack = new Stack<>(str.length()+1);
        stack.push(-1);
        for(int i = 0;i<str.length();i++ ){
            if(str.charAt(i)=='('){
                stack.push(i);
                continue;
            }

            stack.pop();
            if(!stack.isStackEmpty()){
                int temp = i-stack.top();
                if(temp>length) length = temp;
            }

            else stack.push(i);
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

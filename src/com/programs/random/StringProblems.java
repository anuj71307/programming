package com.programs.random;

public class StringProblems {

    public static void main(String[] args) {

        System.out.println(isOneEditAway("s", "v"));
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

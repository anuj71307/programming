package com.programs.problems

import java.util.*


fun main() {
    print(checkPalindromePermutation("aba&"))
}

/**
 * https://www.geeksforgeeks.org/check-characters-given-string-can-rearranged-form-palindrome/
 *
 * Given a string check if any permutation of it can be palindrome.
 * Ignore special characters
 *
 * Solution: String can only be a palindrome if it has every character appearing even number except any one character
 * can appear odd number if string length is odd
 */
fun checkPalindromePermutation(str: String): Boolean {
    val input = str.toLowerCase()
    val arr = IntArray(26)
    input.forEach {
        if (it in 'a'..'z') {
            arr[it - 'a']++
        }
    }
    var oddFound = false
    for (i in arr) {
        if (i % 2 == 1) {
            if (oddFound) return false
            oddFound = true
        }
    }

    return true
}

/**
 * https://leetcode.com/problems/reorganize-string/
 */
fun reorganizeString(str: String): String {

    val len = str.length
    var count = IntArray(26) // used to decode the chars
    str.forEach {
        count[it - 'a'] += 100 // add 100 for each time a char appears
    }
    for (i in count.indices) {
        count[i] += i
    }
    // count represent count*100+char
    Arrays.sort(count) ///sort the count array
    val ans = CharArray(len)
    var t = 1
    count.forEach { total ->
        val cnt = total / 100
        if (cnt > (len + 1) / 2) return ""
        if (cnt > 0) {
            val ch: Char = ('a' + total % 100)
            for (i in 0 until cnt) {
                if (t >= len) t = 0
                ans[t] = ch
                t += 2
            }
        }

    }
    return String(ans)
}

/**
 * https://leetcode.com/problems/palindromic-substrings/
 */
fun countPalindromes(s: String): Any? {

    val res = IntArray(1)
    res[0] = 0
    for (i in 0..s.length) {
        countPalindromes(s, i, i, res)
        countPalindromes(s, i, i + 1, res)
    }
    return res[0]
}

fun countPalindromes(s: String, i: Int, j: Int, res: IntArray) {
    var left = i
    var right = j
    while (left >= 0 && right < s.length && s[left] == s[right]) {
        res[0] += 1
        left--
        right++
    }
}


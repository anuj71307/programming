package com.programs.problems


fun main() {
    println("Hello World!")
    print(countPalindromes("aaa"))
}

/**
 * https://leetcode.com/problems/palindromic-substrings/
 */
fun countPalindromes(s: String): Any? {

    val res = IntArray(1)
    res[0] = 0
    for (i in 0..s.length){
        countPalindromes(s, i,i, res)
        countPalindromes(s, i,i+1,res)
    }
    return res[0]
}

fun countPalindromes(s: String, i: Int, j: Int, res: IntArray) {
    var left = i
    var right = j;
    while(left>=0 && right<s.length && s[left]==s[right]){
        res[0]+=1
        left--
        right++
    }
}


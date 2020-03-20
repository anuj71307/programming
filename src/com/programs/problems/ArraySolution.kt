package com.programs.problems

import kotlin.math.abs


fun main() {
    println("Hello ${minDominoRotations(intArrayOf(1,2,3,4,6), intArrayOf(6,6,6,6,5))}")
}

/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 *
 */
fun minDominoRotations(first: IntArray, second: IntArray): Int {

    if(first.size!=second.size) return -1
    // keep count of element if element is duplicated at same index in both array we consider it as 1
    val total = IntArray(7)
    // keep count of each element in first array
    val f = IntArray(7)
    // keep count of each element in second array
    val s = IntArray(7)
    // have kept size of each array to 1 to avoid doing -1 ,
     for(i in first.indices){
         // if both array has same element in same index , then increment by 1 only
         if(first[i] == second[i]){
             total[first[i]] = total[first[i]]+1
         }
         else{
             total[first[i]] = total[first[i]]+1
             total[second[i]] = total[second[i]]+1
         }
         f[first[i]] = f[first[i]]+1
         s[second[i]] = s[second[i]]+1

     }
    var min = -1
    // iterate over total array and find minimum if some element count is equal to original array size
    for(i in 1..6){
        if(total[i]==first.size ){
            // if oen of the array has same element at all index then return 0
            if(f[i]==first.size || s[i]==first.size) return 0
            // find diff otherwise
            val q  = Math.min(total[i]-f[i], total[i]-s[i])
            if(min ==-1 || min>q) min = q
        }
    }
    return min
}


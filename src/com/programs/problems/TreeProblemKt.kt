package com.programs.problems

import com.programs.trees.NAryTree

fun main() {
    println("Hello World!")

}

/**
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 * Find max depth/height of a n Ary tree
 */
fun maxDepth(root: NAryTree?):Int{
    if(root==null) return 0;
    var max = 0
    root.children?.forEach {
        max = kotlin.math.max(max, maxDepth(it))
    }
    return max+1
}
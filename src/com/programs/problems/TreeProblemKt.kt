package com.programs.problems

import com.programs.trees.BinarySearchTree
import com.programs.trees.NAryTree

fun main() {
    println("Hello World!")

    val root = BinarySearchTree<Int>(6)
    val left = BinarySearchTree<Int>(2)
    val right = BinarySearchTree<Int>(9)
    root.setLeftNode(left)
    root.setRightNode(right)
    print("LCA is ${lowestCommonAncestor(root, root, right)?.data}")
}

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
fun lowestCommonAncestor(root: BinarySearchTree<Int>?, p: BinarySearchTree<Int>?, q: BinarySearchTree<Int>?): BinarySearchTree<Int>? {
    var p = p
    var q = q
    if (root == null) return null
    if (p == null) return q
    if (q == null) return p
    if (p.data == q.data) return p
    if (p.data > q.data) {
        val temp: BinarySearchTree<Int> = p
        p = q
        q = temp
    }
    return lca(root, p, q)
}

fun lca(root: BinarySearchTree<Int>, p: BinarySearchTree<Int>, q: BinarySearchTree<Int>): BinarySearchTree<Int> {
    if (root.data >= p.data && root.data <= q.data) {
        return root
    }
    if (root.data >= q.data) {
        return if (root.data == null) {
            root
        } else lca((root.leftNode as BinarySearchTree<Int>?)!!, p, q)
    }
    return if (root!!.data == null) {
        root
    } else lca((root.rightNode as BinarySearchTree<Int>?)!!, p, q)
}

/**
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 * Find max depth/height of a n Ary tree
 */
fun maxDepth(root: NAryTree?): Int {
    if (root == null) return 0;
    var max = 0
    root.children?.forEach {
        max = kotlin.math.max(max, maxDepth(it))
    }
    return max + 1
}

data class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null) {

}
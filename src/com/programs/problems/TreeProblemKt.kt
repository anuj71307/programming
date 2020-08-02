package com.programs.problems

import com.programs.trees.BinarySearchTree
import com.programs.trees.NAryTree
import kotlin.math.max

fun main() {
}

/**
 * LeetCode 1339
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
 */
fun maxProduct(root: TreeNode): Int {
    val total = sum(root)
    val res = LongArray(1)
    res[0] = root.value.toLong()
    check(root, total, res)
    return (res[0] % 1000000007).toInt()
}


fun check(root: TreeNode?, total: Long, res: LongArray): Long {
    if (root == null) return 0
    val left = check(root.left, total, res)
    val right = check(root.right, total, res)
    val t: Long = left + right + root.value
    res[0] = Math.max(res[0], (total - t) * t)
    return t
}

/**
 * Find sum of all nodes in binary tree
 */
fun sum(root: TreeNode?): Long {
    return if (root == null) 0L else root.value + sum(root.left) + sum(root.right)
}

/**
 *  LeetCode - 1448
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 */
fun goodNodes(root: TreeNode?): Int {
    if (root == null) return 0
    val res = IntArray(1)
    getNodes(root, root.value, res)
    return res[0]
}

fun getNodes(root: TreeNode?, max: Int, res: IntArray) {
    var max = max
    if (root == null) return
    if (root.value >= max) {
        res[0] += 1
        max = root.value
    }
    getNodes(root.left, max, res)
    getNodes(root.right, max, res)
}

/**
 * LeetCode - 543
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
fun longestPath(root: TreeNode?): Int {
    val res = IntArray(1) { 0 }
    height(root, res)
    return res[0]
}

/**
 * find height of a tree
 */
fun height(root: TreeNode?, res: IntArray): Int {
    if (root == null) return 0
    val lh = height(root.left, res)
    val rh = height(root.right, res)
    //update longest oath while finding height
    res[0] = max(res[0], lh + rh + 1)
    return max(lh, rh) + 1
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

data class TreeNode constructor(@JvmField var value: Int, @JvmField var left: TreeNode? = null,
                                @JvmField var right: TreeNode? = null) {
    constructor(value: Int) : this(value, null, null) {

    }

    override fun toString(): String {
        return "TreeNode(value=$value)"
    }

}
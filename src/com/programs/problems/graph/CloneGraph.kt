package com.programs.problems.graph

import java.util.*
import kotlin.collections.ArrayList

fun main() {
}

/**
 * https://leetcode.com/problems/clone-graph/
 */
fun cloneGraph(node: Node?): Node? {
    if (node == null) return node
    val hashMap = hashMapOf<Node, Node>()
    cloneGraph(node, hashMap)
    return hashMap[node]
}

fun cloneGraph(node: Node?, hashMap: HashMap<Node, Node>) {
    if(node==null) return
    hashMap[node] = Node(node.`val`)
    for(child in node.neighbors){
        if(!hashMap.containsKey(child)){
            cloneGraph(child, hashMap)
        }
        hashMap[node]?.neighbors?.add(hashMap[child])
    }
}

class Node(@JvmField var `val`: Int) {
    @JvmField var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}
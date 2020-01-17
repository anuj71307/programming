package com.programs.trees

class NAryTree(var data: Int) {
    var children:List<NAryTree>? = null
    constructor(data: Int, children: List<NAryTree>) : this(data) {
        this.children = children
    }

}
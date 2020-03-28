package com.programs.problems


fun main() {
    val first: Array<IntArray> = arrayOf(
            intArrayOf(0, 4, 6),
            intArrayOf(1, 0, 3)
    )

    val sec: Array<IntArray> = arrayOf(
            intArrayOf(0, 4, 6),
            intArrayOf(1, 0, 3)
    )

    zeroMatrix(first)
    setZero(sec)
    for (i in first.indices) {
        for (j in first[0].indices) {
            print("${first[i][j]} ")
        }
        println()
    }

    println("Another")
    for (i in sec.indices) {
        for (j in sec[0].indices) {
            print("${sec[i][j]} ")
        }
        println()
    }
}

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * Time - > O(MN(M+N)), Space -> O(1)
 * Check [zeroMatrix] for better time complexity
 */
fun setZero(arr: Array<IntArray>) {

    val def = -10000000
    for (i in arr.indices) {
        for (j in arr[i].indices) {

            if (arr[i][j] == 0) {
                for (k in arr[i].indices) {
                    if (arr[i][k] != 0) {
                        arr[i][k] = def
                    }
                }

                for (k in arr.indices) {
                    if (arr[k][j] != 0) {
                        arr[k][j] = def
                    }
                }
            }
        }
    }


    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] == def) {
                arr[i][j] = 0
            }
        }

    }

}

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * Time O(MN), Space O(M+N)
 * Check [setZero] for better space complexity
 */
fun zeroMatrix(arr: Array<IntArray>) {

    val rows = BooleanArray(arr.size)
    val cols = BooleanArray(arr[0].size)

    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] == 0) {
                rows[i] = true
                cols[j] = true
            }
        }
    }

    for (i in rows.indices) {
        if (rows[i]) {
            setRowsZero(i, arr)
        }
    }

    for (j in cols.indices) {
        if (cols[j]) {
            setColsZero(j, arr)
        }
    }
}

fun setColsZero(j: Int, arr: Array<IntArray>) {
    for (i in arr.indices) {
        arr[i][j] = 0
    }

}

fun setRowsZero(i: Int, arr: Array<IntArray>) {
    for (j in arr[i].indices) {
        arr[i][j] = 0
    }

}

/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 *
 */
fun minDominoRotations(first: IntArray, second: IntArray): Int {

    if (first.size != second.size) return -1
    // keep count of element if element is duplicated at same index in both array we consider it as 1
    val total = IntArray(7)
    // keep count of each element in first array
    val f = IntArray(7)
    // keep count of each element in second array
    val s = IntArray(7)
    // have kept size of each array to 1 to avoid doing -1 ,
    for (i in first.indices) {
        // if both array has same element in same index , then increment by 1 only
        if (first[i] == second[i]) {
            total[first[i]] = total[first[i]] + 1
        } else {
            total[first[i]] = total[first[i]] + 1
            total[second[i]] = total[second[i]] + 1
        }
        f[first[i]] = f[first[i]] + 1
        s[second[i]] = s[second[i]] + 1

    }
    var min = -1
    // iterate over total array and find minimum if some element count is equal to original array size
    for (i in 1..6) {
        if (total[i] == first.size) {
            // if oen of the array has same element at all index then return 0
            if (f[i] == first.size || s[i] == first.size) return 0
            // find diff otherwise
            val q = Math.min(total[i] - f[i], total[i] - s[i])
            if (min == -1 || min > q) min = q
        }
    }
    return min
}


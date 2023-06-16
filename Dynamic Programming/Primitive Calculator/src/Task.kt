
private fun calculateDP(n: Int): IntArray {
    val minOp = IntArray(n + 1)
    minOp[0] = 0
    for (i in 1..n) {
        var current = minOp[i - 1] + 1
        for (div in 2..3) {
            if (i % div == 0) {
                current = minOf(current, minOp[i / div] + 1)
            }
        }
        minOp[i] = current
    }
    return minOp
}

private fun restoreAnswer(n: Int, minOp: IntArray): List<Int> {
    val answer = mutableListOf<Int>()
    var i = n
    while (i > 1) {
        answer.add(i)
        i = when {
            minOp[i] == minOp[i - 1] + 1 -> i - 1
            i % 2 == 0 && minOp[i] == minOp[i / 2] + 1 -> i / 2
            else -> i / 3
        }
    }
    answer.add(1)
    answer.reverse()
    return answer
}

fun findMinimumOperations(n: Int): List<Int> {
    val minOp = calculateDP(n)
    return restoreAnswer(n, minOp)
}

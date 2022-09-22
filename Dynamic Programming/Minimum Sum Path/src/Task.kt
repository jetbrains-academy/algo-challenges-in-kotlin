import kotlin.math.min

fun minimumSumPath(a: IntArray): Long {
    val n = a.size
    val minPath = Array(n) { LongArray(2) { Long.MAX_VALUE } }
    minPath[0][0] = a[0].toLong()
    minPath[1][0] = a[0].toLong() + a[1].toLong()
    for (i in 2 until n) {
        minPath[i][0] = min(minPath[i - 1][0], minPath[i - 1][1]) + a[i]
        minPath[i][1] = minPath[i - 2][0] + a[i]
    }
    return minPath[n - 1].min()
}

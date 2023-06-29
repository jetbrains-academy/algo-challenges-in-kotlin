/**
 * Finds minimum `i` such that `a[i] >= x`,
 * returns `a.size` if all `a[i] < x`
 */
fun lowerBound(a: IntArray, x: Int): Int {
    var lower = -1
    var upper = a.size
    while (upper > lower + 1) {
        val middle = (lower + upper) / 2
        if (a[middle] < x) {
            lower = middle
        } else {
            upper = middle
        }
    }
    return upper
}

fun main() {
    println(lowerBound(intArrayOf(2, 4, 7, 11, 12), 9))
}

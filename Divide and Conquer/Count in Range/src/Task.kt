
fun countInRange(a: IntArray, l: Int, r: Int): Int {
    return lowerBound(a, r + 1) - lowerBound(a, l)
}

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

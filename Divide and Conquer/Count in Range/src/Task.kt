fun countInRange(a: IntArray, l: Int, r: Int): Int {
    return lowerBound(a) { it > r } - lowerBound(a) {it >= l}
}

fun lowerBound(a: IntArray, f: (Int) -> Boolean): Int {
    var lower = -1
    var upper = a.size
    while (upper > lower + 1) {
        val middle = (lower + upper) / 2
        if (f(a[middle])) {
            upper = middle
        } else {
            lower = middle
        }
    }
    return upper
}
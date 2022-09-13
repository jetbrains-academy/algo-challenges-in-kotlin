
fun <T : Comparable<T>> getMedian(first: List<T>, second: List<T>): T {
    assert(first.size == second.size)
    var left = -1
    var right = first.size
    val k = first.size
    while (left < right - 1) {
        val mid = (left + right) shr 1
        if (first[mid] < second[k - mid - 1]) {
            left = mid
        } else {
            right = mid
        }
    }
    return if (left == -1) { // first[0] > second[k - 1] => all second < first
        second[k - 1]
    } else if (right == first.size) { // first[k - 1] < second[0] => all second > first
        first[k - 1]
    } else {
        minOf(first[left], second[k - right - 1])
    }
}

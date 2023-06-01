fun find(a: IntArray, x: Int): Int {
    var lower = 0
    var upper = a.size - 1
    while (upper >= lower) {
        val middle = (lower + upper) / 2
        if (a[middle] == x) {
            return middle
        } else if (a[middle] > x) {
            upper = middle - 1
        } else {
            lower = middle + 1
        }
    }
    return -1
}

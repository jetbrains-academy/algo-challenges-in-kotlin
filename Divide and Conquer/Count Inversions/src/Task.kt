private class InversionsCounter<T : Comparable<T>>(a: List<T>) {
    private val a = MutableList(a.size) { Pair(a[it], it) }
    private val b = mutableListOf<Pair<T, Int>>()
    private val result = IntArray(a.size)

    private fun mergeSort(left: Int, right: Int) {
        if (left + 1 >= right) {
            return
        }
        val mid = (left + right) shr 1
        mergeSort(left, mid)
        mergeSort(mid, right)
        var i = left
        var j = mid
        while (i < mid || j < right) {
            if (j >= right || (i < mid && a[i].first <= a[j].first)) {
                b.add(a[i])
                i++
            } else {
                result[a[j].second] += mid - i
                b.add(a[j])
                j++
            }
        }
        for (pos in 0 until b.size) {
            a[pos + left] = b[pos]
        }
        b.clear()
    }

    fun count(): IntArray {
        mergeSort(0, a.size)
        return result
    }
}

fun <T : Comparable<T>> findInversions(a: List<T>): IntArray {
    return InversionsCounter(a).count()
}

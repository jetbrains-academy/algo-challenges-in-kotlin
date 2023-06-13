class RangeSum(a: IntArray) {
    private val prefix = LongArray(a.size) { a[it].toLong() }

    init {
        for (i in prefix.indices.drop(1)) {
            prefix[i] += prefix[i - 1]
        }
    }

    private fun get(index: Int): Long {
        return when (index) {
            -1 -> 0
            else -> prefix[index]
        }
    }

    fun getSum(left: Int, right: Int): Long {
        return get(right - 1) - get(left - 1)
    }

}



private class RangeSum(a: IntArray) : RangeSumQueries {
    private val prefix = LongArray(a.size) { a[it].toLong() }

    init {
        for (i in prefix.indices.drop(1)) {
            prefix[i] += prefix[i - 1]
        }
    }

    override fun getSum(left: Int, right: Int): Long {
        return prefix[right - 1] - when (left) {
            0 -> 0
            else -> prefix[left - 1]
        }
    }

}

fun createRSQInstance(a: IntArray): RangeSumQueries {
    return RangeSum(a)
}

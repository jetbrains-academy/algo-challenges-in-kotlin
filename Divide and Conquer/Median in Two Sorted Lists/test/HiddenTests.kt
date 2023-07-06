import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds


class HiddenTests {

    companion object {
        const val MAX_N = 10_000_000

        fun <T : Comparable<T>> computeAnswer(first: List<T>, second: List<T>): T {
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
                maxOf(first[left], second[k - right - 1])
            }
        }
    }

    private val rng = Random(239)

    private fun <T : Comparable<T>> testSingle(a: List<T>, b: List<T>, expected: T) {
        val actual = getMedian(a, b)
        assertEquals(expected, actual) {
            "Wrong median when n = ${a.size}"
        }
    }

    private fun testAllBinary(n: Int) {
        val a = generateSequence { 0 }.take(n).toMutableList()
        for (onesA in 0..n) {
            val b = generateSequence { 0 }.take(n).toMutableList()
            for (onesB in 0..n) {
                if (onesB < n) {
                    val expected = when (onesA + onesB > n) {
                        true -> 1
                        else -> 0
                    }
                    testSingle(a, b, expected)
                    b[n - onesB - 1] = 1
                }
            }
            if (onesA < n) {
                a[n - onesA - 1] = 1
            }
        }
    }

    @Test
    fun testBinaryAllUpTo20() = runTimeout(1.seconds, "All binary arrays up to length 20") {
        for (n in 1..20) {
            testAllBinary(n)
        }
    }

    @Test
    fun testBinaryUpTo500() {
        for (n in 30..1000 step 17) {
            runTimeout(1.seconds, "All binary arrays up to length $n") {
                testAllBinary(n)
            }
        }
    }

    @Test
    fun testBinaryMax() {
        val count = 2000
        runTimeout(2.seconds, "${count * (count - 1) / 2} pairs of different binary lists of size $MAX_N") {
            val lists = (0 until count).map {
                BinaryList(MAX_N, rng.nextInt(MAX_N + 1))
            }
            for (i in 0 until count) {
                val first = lists[i]
                for (j in i + 1 until count) {
                    val second = lists[j]
                    val median = if ((first.zeros + second.zeros) * 2 >= (first.size + second.size)) {
                        0
                    } else {
                        1
                    }
                    val found = getMedian(first, second)
                    assertEquals(median, found) {
                        "Median for two binary arrays are incorrect"
                    }
                }
            }
        }
    }

    @Test
    fun testSubLists() {
        val totalSize = MAX_N * 3
        val array = IntArray(totalSize) { rng.nextInt() }
        array.sort()
        val a = IntList(array)
        val count = 1000
        val length = MAX_N
        val lists = generateSequence {
            val from = rng.nextInt(totalSize - length)
            a.subList(from, from + length)
        }.take(count).toList()
        runTimeout(5.seconds, "${count * (count - 1) / 2} pairs of List<Int> of size $length") {
            for (i in 0 until count) {
                val first = lists[i]
                for (j in i + 1 until count) {
                    val second = lists[j]
                    val median = computeAnswer(first, second)
                    val found = getMedian(first, second)
                    assertEquals(median, found) {
                        "MAX test"
                    }
                }
            }
        }
    }

    private class BinaryList(override val size: Int, val zeros: Int) : AbstractList<Int>() {
        override fun get(index: Int): Int {
            if (index < 0 || index >= size) {
                throw IndexOutOfBoundsException("index: $index, size: $size")
            }
            return when {
                index < zeros -> 0
                else -> 1
            }
        }
    }

    private class IntList(val array: IntArray) : AbstractList<Int>() {
        override val size: Int = array.size

        override fun get(index: Int): Int {
            return array[index]
        }
    }
}

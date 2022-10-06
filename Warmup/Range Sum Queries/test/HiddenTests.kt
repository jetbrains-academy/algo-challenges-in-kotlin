import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private fun Random.getArray(n: Int, from: Int, to: Int): IntArray {
    return IntArray(n) {
        this.nextInt(from, to)
    }
}

class HiddenTests {

    companion object {
        const val MAX_LENGTH = 10_000_000
        const val MAX_QUERIES = 30_000_000

        private class TestRangeSum(a: IntArray) : RangeSumQueries {
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

            override fun getSum(left: Int, right: Int): Long {
                return get(right - 1) - get(left - 1)
            }

        }

    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    fun allLengthsRandomUpTo100() {
        val rng = Random(58L)
        for (n in 0..100) {
            val a = rng.getArray(n, -100, 100)
            val rsq = createRSQInstance(a)
            for (left in 0..n) {
                var expectedSum = 0L
                for (right in left..n) {
                    val actualSum = rsq.getSum(left, right)
                    assertEquals(expectedSum, actualSum) {
                        "n = $n, left = $left, right = $right"
                    }
                    if (right < n) {
                        expectedSum += a[right];
                    }
                }
            }
        }
    }

    private fun testRandomQueries(a: IntArray, rng: Random) {
        val realRSQ = TestRangeSum(a.clone())
        val rsq = createRSQInstance(a)
        repeat(MAX_QUERIES) {
            val left = rng.nextInt(a.size + 1)
            val right = rng.nextInt(left, a.size + 1)
            val expected = realRSQ.getSum(left, right)
            val actual = rsq.getSum(left, right)
            assertEquals(expected, actual) {
                "n = ${a.size}, left = $left, right = $right"
            }
        }
    }

    private fun testValuesEqual(length: Int, value: Int) {
        val rsq = createRSQInstance(IntArray(length) { value })
        val expectedFull = value.toLong() * length
        val actualFull = rsq.getSum(0, length)
        assertEquals(expectedFull, actualFull) {
            "n = $length, left = 0, right = $length"
        }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    fun maxValue() {
        testValuesEqual(MAX_LENGTH, Int.MAX_VALUE)
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    fun minValue() {
        testValuesEqual(MAX_LENGTH, Int.MIN_VALUE)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun maxLength() {
        val rng = Random(123L)
        val a = rng.getArray(MAX_LENGTH, Int.MIN_VALUE, Int.MAX_VALUE)
        testRandomQueries(a, rng)
    }
}
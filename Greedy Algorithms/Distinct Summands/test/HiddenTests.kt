import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.concurrent.TimeUnit
import kotlin.math.sqrt
import kotlin.random.Random

class HiddenTests {
    companion object {
        private const val MAX_N: Long = 1_000_000_000_000L
        private val rng = Random(239L)
        /***
         * k * (k + 1) / 2 <= n
         * k * (k + 1) <= n
         * k * k + k - n <= 0
         * k = (-1 +- sqrt(1 + 4 * n)) / 2
         * k = (sqrt(1 + 4 * n) - 1) / 2
         */
        private fun calculateSize(n: Long): Int {
            var k = ((sqrt(1 + 4 * n.toDouble()) - 1) / 2).toLong()
            while (k * (k + 1) < 2 * n) k++
            while (k * (k + 1) > 2 * n) k--
            return k.toInt()
        }
    }

    private fun check(n: Long, expectedSize: Int) {
        val actual = distinctSummands(n)
        assertTrue(actual.size >= expectedSize) {
            "[n = $n] expected list size = $expectedSize, found ${actual.size}"
        }
        assertTrue(actual.toCollection(mutableSetOf()).size == actual.size) {
            "[n = $n] numbers are not distinct"
        }
        assertTrue(actual.all { it <= n }) {
            "[n = $n] sum is not equal to $n"
        }
        assertEquals(n, actual.sum()) {
            "[n = $n] sum of summands equals to ${actual.sum()}, but expected $n"
        }
        assertTrue(actual.size == expectedSize) {
            "you found a solution better than we expected, please file a bug, " +
                    "size = ${actual.size}, expected size = $expectedSize"
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testAllUpTo100000() {
        var triangles = 1
        var size = 0
        for (n in 1..100000) {
            if (n == triangles) {
                size += 1
                triangles += size + 1
            }
            check(n.toLong(), size)
        }
    }

    @ParameterizedTest
    @ValueSource(longs = [MAX_N, MAX_N - 1, MAX_N - 2, MAX_N - 1000, MAX_N / 3])
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    fun big(n: Long) {
        check(n, calculateSize(n))
    }

    @Test
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    fun bigCloseToTriangle() {
        val k = calculateSize(MAX_N).toLong()
        for (triangle in longArrayOf(k * (k + 1) / 2, k * (k - 1) / 2)) {
            for (delta in -5..5) {
                val n = triangle + delta
                check(n, calculateSize(n))
            }
        }
    }

    @Test
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    fun randomMedium() {
        repeat(1000) {
            val n = rng.nextInt(100000, Int.MAX_VALUE).toLong()
            check(n, calculateSize(n))
        }
    }
}

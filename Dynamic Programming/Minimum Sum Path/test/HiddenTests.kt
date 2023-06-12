import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.Random
import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        const val MAX_N = 1_000_000
        const val MAX_VALUE = 1_000_000_000

        private fun calculateAnswer(a: IntArray): Long {
            if (a.size == 1) {
                return a[0].toLong()
            }
            var prev = a[0].toLong()
            var current0 = prev + a[1].toLong()
            var current1 = Long.MAX_VALUE
            for (i in 2 until a.size) {
                val x = a[i]
                val next0 = min(current0, current1) + x
                val next1 = prev + x
                prev = current0
                current0 = next0
                current1 = next1
            }
            return min(current0, current1)
        }

        private fun validate(a: IntArray): Boolean {
            return a.size in 2..MAX_N && a.all { it in -MAX_VALUE..MAX_VALUE }
        }
    }

    private val rng = Random(239L)

    private fun testSingle(a: IntArray, message: String, withTimeout: Boolean = true) {
        check(validate(a))
        val expected = calculateAnswer(a)
        val actual = runIfTimeout(withTimeout, 1.seconds, message) {
            minimumSumPath(a)
        }
        assertEquals(expected, actual) {
            "Wrong answer for test [$message]"
        }
    }

    @Test
    fun testMaxAnswer() {
        testSingle(IntArray(MAX_N) { MAX_VALUE }, "Maximum answer test")
    }

    @Test
    fun testMinAnswer() {
        testSingle(IntArray(MAX_N) { -MAX_VALUE }, "Minimum answer test")
    }

    @Test
    fun testSmallNRandom() = runTimeout(1.seconds, "Random small tests") {
        for (n in 2..30) {
            repeat(100) {
                val input = intArray(n, n)
                testSingle(input, input.contentToString(), withTimeout = false)
            }
        }
    }

    @Test
    fun testSmallNBigRandom() = runTimeout(1.seconds, "Random small tests") {
        for (n in 2..30) {
            repeat(100) {
                val input = intArray(n, MAX_VALUE)
                testSingle(input, input.contentToString(), withTimeout = false)
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [MAX_N, MAX_N - 1])
    fun testRandomBig(n: Int) {
        val input = intArray(n, MAX_VALUE)
        testSingle(input, "n = $n")
    }

    @Test
    fun testRandomMedium() = runTimeout(2.seconds, "Random medium tests") {
        repeat(100) {
            val n = rng.nextInt(5000, 10000)
            val input = intArray(n, MAX_VALUE)
            testSingle(input, "n = $n", withTimeout = false)
        }
    }

    private fun intArray(n: Int, limit: Int) = rng.ints(n.toLong(), 0, limit + 1).toArray()!!
}

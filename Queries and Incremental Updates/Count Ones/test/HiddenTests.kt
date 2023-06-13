import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.AssertionError
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        const val MAX_N = 1_000_000
        private val rng = Random(239L)

        private fun genString(n: Int) = CharArray(n) { '0' + rng.nextInt(2) }.concatToString()

        private fun shorten(s: String): String {
            return if (s.length < 15) {
                "s = \"$s\""
            } else {
                "length(s) = ${s.length}"
            }
        }

        private fun validateTest(s: CharSequence): Boolean {
            return s.all { it == '0' || it == '1' }
        }
    }

    @Test
    fun testEmpty() {
        testSingle("", "Empty string")
    }

    @Test
    fun testManySmall() = runTimeout(5.seconds, "[Many tests length <= 10]") {
        for (len in 1..10) {
            repeat(20000) {
                val input = genString(len)
                testSingle(input, input, withTimeout = false)
            }
        }
    }

    @Test
    fun randomSmall() {
        testRandom(100000, 20, 5.seconds)
    }

    @Test
    fun randomMedium() {
        testRandom(1000, 200, 2.seconds)
        testRandom(1000, 2000, 3.seconds)
    }

    @Test
    fun randomLarge() {
        testRandom(100, 20000, 2.seconds)
        testRandom(20, 200000, 3.seconds)
    }

    @Test
    fun randomMax() {
        testRandom(10, MAX_N, 5.seconds)
    }

    @Test
    fun testMax() = runTimeout(5.seconds, "Maximum tests") {
        for (test in 0 until 10) {
            val input = genString(MAX_N)
            testSingle(input, shorten(input), withTimeout = false)
        }
    }

    @Test
    fun testAllZeros() {
        testSingle("0".repeat(MAX_N), "$MAX_N zeros")
    }

    @Test
    fun testAllOnes() {
        testSingle("1".repeat(MAX_N), "$MAX_N ones")
    }

    private fun testRandom(testsCount: Int, maxLen: Int, timeLimit: Duration) =
        runTimeout(timeLimit, "$testsCount random tests of maximum length $maxLen") {
            for (test in 0..testsCount) {
                val s = genString(rng.nextInt(maxLen / 2) + maxLen / 2)
                testSingle(s, shorten(s), withTimeout = false)
            }
        }

    private fun testSingle(s: String, message: String, withTimeout: Boolean = true) {
        if (!validateTest(s)) {
            throw AssertionError("test is incorrect")
        }
        val ones = runIfTimeout(withTimeout, 1.seconds, message) {
            countOnes(s)
        }
        assertEquals(s.count { it == '1' }, ones) {
            "[$message] incorrect number of '1's"
        }
    }
}

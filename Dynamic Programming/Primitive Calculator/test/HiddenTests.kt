import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private const val MAX_N = 1_000_000
        private val answer = IntArray(MAX_N + 1)

        init {
            answer[1] = 1
            for (i in 2..MAX_N) {
                var current = answer[i - 1] + 1
                if (i % 2 == 0) current = minOf(current, answer[i / 2] + 1)
                if (i % 3 == 0) current = minOf(current, answer[i / 3] + 1)
                answer[i] = current
            }
        }
    }

    private val rng = Random(239)

    fun testSingle(n: Int, withTimeout: Boolean = true) {
        val expectedLength = answer[n]
        val actual = runIfTimeout(withTimeout, 2.seconds, "[n = $n]") {
            findMinimumOperations(n)
        }
        assertTrue(checkSolution(n, actual)) {
            "[n = $n] the sequence is invalid"
        }
        assertTrue(expectedLength >= actual.size) {
            "[n = $n] the sequence is longer than expected"
        }
        check(expectedLength == actual.size) {
            "[n = $n] something went wrong, check failed"
        }
    }

    private fun checkSolution(n: Int, actual: List<Int>): Boolean {
        if (actual.isEmpty() || actual[0] != 1 || actual.last() != n) return false
        for (i in 1 until actual.size) {
            val previous = actual[i - 1]
            val current = actual[i]
            if (previous + 1 != current && previous * 2 != current && previous * 3 != current) {
                return false
            }
        }
        return true
    }

    @Test
    fun testAllUpTo200() = runTimeout(2.seconds, "All n <= 200") {
        for (n in 1..200) {
            testSingle(n, withTimeout = false)
        }
    }

    @TestFactory
    fun testMax(): List<DynamicTest> {
        return (0..30).map { d ->
            val n = MAX_N - d
            DynamicTest.dynamicTest("testN$n") {
                testSingle(n)
            }
        }
    }

    @Test
    fun randomSmallTests() = runTimeout(2.seconds, "50 random tests from 10k to 20k") {
        repeat(1000) {
            testSingle(rng.nextInt(200, 10000), withTimeout = false)
        }
    }

    @Test
    fun randomMediumTests() = runTimeout(2.seconds, "50 random tests from 10k to 20k") {
        repeat(100) {
            testSingle(rng.nextInt(10000, 20000), withTimeout = false)
        }
    }

    @TestFactory
    fun randomBigTests(): List<DynamicTest> {
        return (1..30).map {
            val n = rng.nextInt(MAX_N / 2, MAX_N)
            DynamicTest.dynamicTest("testN$n") {
                testSingle(n)
            }
        }
    }
}

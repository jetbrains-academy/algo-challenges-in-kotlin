import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.provider.ValueSource
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class HiddenTests {
    companion object {
        private val rng = Random(239)
        private const val MAX_N = 500_000

        private fun correctSolution(v: IntArray): Long {
            var ans = 1L
            for (value in v.sorted()) {
                if (value > ans + 1) {
                    return ans
                }
                ans += value
            }
            return ans
        }

        private fun generateRandomSequence(n: Int, maxValue: Int): IntArray {
            return IntArray(n) { rng.nextInt(maxValue) + 1 }
        }

        private fun powersOfTwo(n: Int): IntArray {
            return IntArray(n) { 1 shl it }
        }
    }

    private fun testCoins(v: IntArray, message: String) {
        val expected = correctSolution(v)
        val found = findMinimumChange(v)
        assertEquals(expected, found, message)
    }

    @Test
    fun testAllUpTo50() {
        val v = mutableListOf<Int>()
        var testsRun = 0
        fun generateAllSequences(left: Int, last: Int) {
            testCoins(v.shuffled(rng).toIntArray(), "All sequences with sum up to 50")
            testsRun++
            for (current in last..left) {
                v.add(current)
                generateAllSequences(left - current, current)
                v.removeLast()
            }
        }
        generateAllSequences(50, 1)
        println("All sequences with sum up to 50: $testsRun tests passed")
    }

    @ParameterizedTest
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @ValueSource(ints = [MAX_N, MAX_N - 1])
    fun testTimeLimitRandom(n: Int) {
        val v = generateRandomSequence(n, Int.MAX_VALUE)
        testCoins(v, "Big random test")
    }

    @ParameterizedTest
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @ValueSource(ints = [MAX_N, MAX_N - 1])
    fun testTimeLimitNonTrivial(n: Int) {
        val k = 10 + rng.nextInt(10)
        val v = listOf(
            powersOfTwo(k).toList(),
            generateRandomSequence(n - k, Int.MAX_VALUE).toList()
        )
            .flatten()
            .toIntArray();
        v.shuffle(rng)
        testCoins(v, "Big random non-trivial test")
    }
}

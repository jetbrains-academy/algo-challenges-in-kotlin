import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        private val rng = Random(239)
        private const val MAX_N = 500_000

        private fun correctSolution(v: IntArray): Long {
            var ans = 1L
            for (value in v.sorted()) {
                if (value > ans) {
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

        private fun collectIntoArray(vararg lists: IntArray): IntArray {
            return lists.map { it.toList() }.flatten().toIntArray()
        }

        private fun validateTest(v: IntArray): Boolean {
            return v.all { it > 0 }
        }
    }

    private fun testCoins(v: IntArray, message: String, withTimeout: Boolean = true) {
        if (!validateTest(v)) {
            throw AssertionError("Test is invalid")
        }
        val expected = correctSolution(v)
        val actual = when (withTimeout) {
            true -> runTimeout(1.seconds, message) {
                findMinimumChange(v)
            }

            else -> findMinimumChange(v)
        }
        assertEquals(expected, actual, message)
    }

    @Test
    fun testAllUpTo50() = runTimeout(5.seconds, "All sequences with sum up to 50") {
        val v = mutableListOf<Int>()
        var testsRun = 0
        fun generateAllSequences(left: Int, last: Int) {
            v.shuffled(rng).toIntArray().also {
                testCoins(it, it.contentToString(), withTimeout = false)
            }
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
    @ValueSource(ints = [10, 40, 100, 200, 300])
    fun testRandomSmall(n: Int) {
        val k = rng.nextInt(5) + 2
        testCoins(
            collectIntoArray(
                generateRandomSequence(n - k, 200),
                powersOfTwo(k)
            ),
            "Random test with values up to 200 of size $n"
        )
    }


    @ParameterizedTest
    @ValueSource(ints = [500, 1000, 3000, 5000, 10000, 15000])
    fun testRandomMedium(n: Int) {
        val k = rng.nextInt(10) + 3
        testCoins(
            collectIntoArray(
                generateRandomSequence(n - k, 4000),
                powersOfTwo(k)
            ),
            "Random test with values up to 4000 of size $n"
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [MAX_N, MAX_N - 1])
    fun testTimeLimitRandom(n: Int) {
        val v = generateRandomSequence(n, Int.MAX_VALUE)
        testCoins(v, "Big random test")
    }

    @ParameterizedTest
    @ValueSource(ints = [MAX_N, MAX_N - 1])
    fun testTimeLimitNonTrivial(n: Int) {
        val k = 10 + rng.nextInt(10)
        val v = collectIntoArray(
            powersOfTwo(k),
            generateRandomSequence(n - k, Int.MAX_VALUE)
        )
        v.shuffle(rng)
        testCoins(v, "Big random non-trivial test")
    }

    @Test
    fun testMaxAnswer() {
        val v = collectIntoArray(
            powersOfTwo(31),
            IntArray(MAX_N - 31) { Int.MAX_VALUE }
        )
        v.shuffle(rng)
        testCoins(v, "Big array, max answer")
    }
}
